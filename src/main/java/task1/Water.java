package task1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Water {
    private final CyclicBarrier cyclicBarrier;
    private final Semaphore hydrogen;
    private final Semaphore oxygen;

    public Water() {
        this.hydrogen = new Semaphore(2);
        this.oxygen = new Semaphore(1);
        this.cyclicBarrier = new CyclicBarrier(3, () -> {
            hydrogen.release(2);
            oxygen.release(1);
        });
    }

    public void releaseHydrogen(Runnable hydro){
        try {
            hydrogen.acquire();
            hydro.run();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void releaseOxygen(Runnable oxy){
        try {
            oxygen.acquire();
            oxy.run();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

