class FizzBuzz {
    private volatile int flag = 1;
    private final int size;

    public FizzBuzz(int n) {
        size = n;
    }

    public void fizz() {
        for (int i = 1; i <= size; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                synchronized (this) {
                    while (i != flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("fizz "+i);
                    flag++;
                    notifyAll();
                }
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= size; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                synchronized (this) {
                    while (i != flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("buzz "+i);
                    flag++;
                    notifyAll();
                }
            }
        }

    }

    public void fizzbuzz() {
        for (int i = 1; i <= size; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                synchronized (this) {
                    while (i != flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("fizzbuzz "+i);
                    flag++;
                    notifyAll();
                }
            }
        }

    }

    public void number() {
        for (int i = 1; i <= size; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                synchronized (this) {
                    while (i != flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    flag++;
                    notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

//        CompletableFuture<Void> printFizz = CompletableFuture.runAsync(() -> {
//            fizzBuzz.fizz();
//        });
//        CompletableFuture<Void> printBuzz = CompletableFuture.runAsync(() -> {
//            fizzBuzz.buzz();
//        });
//        CompletableFuture<Void> printFizzBuzz = CompletableFuture.runAsync(() -> {
//            fizzBuzz.fizzbuzz();
//        });
//        CompletableFuture<Void> printNumber = CompletableFuture.runAsync(() -> {
//            fizzBuzz.number();
//        });
//
//        printNumber.get();
//        printBuzz.get();
//        printFizzBuzz.get();
//        printFizz.get();
        Thread printFizz = new Thread(fizzBuzz::fizz);
        Thread printNumber = new Thread(fizzBuzz::number);
        Thread printFizzBuzz = new Thread(fizzBuzz::fizzbuzz);
        Thread printBuzz = new Thread(fizzBuzz::buzz);
        printBuzz.start();
        printFizzBuzz.start();
        printNumber.start();
        printFizz.start();
    }
}
