import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainApp {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier barrier = new CyclicBarrier(MainApp.CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car (race, 20 + (int) (Math.random() * 10), barrier);
            new Thread(cars[i]).start();

        }
        try {
            barrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            barrier.await();
            barrier.await();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (BrokenBarrierException brokenBarrierException) {
            brokenBarrierException.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}