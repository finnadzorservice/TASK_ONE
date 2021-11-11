import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main_3 {
    private volatile int flag = 0;

    public synchronized void first() {
        while (flag != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("first");
        flag = 1;
        notifyAll();
    }

    public synchronized void second() {
        while (flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");
        flag = 2;
        notifyAll();
    }

    public synchronized void third() {
        while (flag != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("third");
        flag = 0;
        notifyAll();
    }

    static void execute(CompletableFuture<Void> voidCompletableFuture1,
                        CompletableFuture<Void> voidCompletableFuture2,
                        CompletableFuture<Void> voidCompletableFuture3) {
        try {
            voidCompletableFuture2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            voidCompletableFuture3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            voidCompletableFuture1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main_3 main_3 = new Main_3();
        execute(CompletableFuture.runAsync(main_3::second),
                CompletableFuture.runAsync(main_3::third),
                CompletableFuture.runAsync(main_3::first));
    }

}
