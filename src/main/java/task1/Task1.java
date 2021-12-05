package task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Task1 {
    private static final String WATER = "OOOOOOOOOOOOOOOOHHHHHHHHHH";
    public static void main(String[] args) {
        final char [] arrayWater = WATER.toCharArray();
        long amountH = IntStream.range(0, arrayWater.length)
                .mapToObj(i -> arrayWater[i])
                .filter(ch -> ch == 'H')
                .count();
        long amountO = IntStream.range(0, arrayWater.length)
                .mapToObj(i -> arrayWater[i])
                .filter(ch -> ch == 'O')
                .count();
        int amountThread = (int)((amountH  <= amountO * 2)? ((amountH / 2) * 3) : (amountO * 3));
        Water water = new Water();
        ExecutorService service = Executors.newFixedThreadPool(amountThread);
        Runnable hydro = () -> System.out.print("H");
        Runnable oxy = () -> System.out.print("O");

        for (int i = 0; i < ((amountThread / 3) * 2); i++) {
            service.execute(() -> water.releaseHydrogen(hydro));
        }
        for (int i = 0; i < amountThread / 3; i++) {
            service.execute(() -> water.releaseOxygen(oxy));
        }
        service.shutdown();
    }
}
