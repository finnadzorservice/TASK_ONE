import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FizzBuzz_2 {
    private final AtomicInteger flag_2 = new AtomicInteger(1);
    private final int size_2;
    private final ReentrantLock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public FizzBuzz_2(int n) {
        size_2 = n;
    }

    public void fizz_2() {
        for (int i = 1; i <= size_2; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                lock.lock();
                try {
                    while (i != flag_2.get()) {
                        condition.await();
                    }
                    System.out.println("fizz "+i);
                    flag_2.getAndIncrement();
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public void buzz_2() {
        for (int i = 1; i <= size_2; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                lock.lock();
                try {
                    while (i != flag_2.get()) {
                        condition.await();
                    }
                    System.out.println("buzz "+i);
                    flag_2.getAndIncrement();
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

    public void fizzbuzz_2() {
        for (int i = 1; i <= size_2; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                lock.lock();
                try {
                    while (i != flag_2.get()) {
                        condition.await();
                    }
                    System.out.println("fizzbuzz "+i);
                    flag_2.getAndIncrement();
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

    public void number_2() {
        for (int i = 1; i <= size_2; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                lock.lock();
                try {
                    while (i != flag_2.get()) {
                        condition.await();
                    }
                    System.out.println(i);
                    flag_2.getAndIncrement();
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FizzBuzz_2 fizzBuzz_2 = new FizzBuzz_2(15);
        Executors.newSingleThreadExecutor().submit(fizzBuzz_2::fizzbuzz_2).get();
        Executors.newSingleThreadExecutor().submit(fizzBuzz_2::fizz_2).get();
        Executors.newSingleThreadExecutor().submit(fizzBuzz_2::number_2).get();
        Executors.newSingleThreadExecutor().submit(fizzBuzz_2::buzz_2).get();
    }
}

