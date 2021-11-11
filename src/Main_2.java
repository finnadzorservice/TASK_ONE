import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main_2 {
    private volatile int  flag = 0;

    public void first() {
        while (flag != 0);
        System.out.print("first");
        flag = 1;
    }

    public void second() {
        while (flag != 1);
        System.out.print("second");
        flag = 2;
    }

    public  void third() {
        while (flag != 2);
        System.out.print("third");
        flag = 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main_2 main_2 = new Main_2();

        CompletableFuture<Void> doSecond = CompletableFuture.runAsync(() -> {
            main_2.second();
        });
        CompletableFuture<Void> doThird = CompletableFuture.runAsync(() -> {
            main_2.third();
        });
        CompletableFuture<Void> doFirst = CompletableFuture.runAsync(() -> {
            main_2.first();
        });
        doThird.get();
        doFirst.get();
        doSecond.get();
    }
}
