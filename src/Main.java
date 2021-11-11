public class Main {
    public void first() {
        System.out.print("first");
    }

    public void second() {
        System.out.print("second");
    }

    public void third() {
        System.out.print("third");
    }

    public static void main(String[] args) {
        Main main = new Main();
        Thread doFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                main.first();
            }
        });
        Thread doSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                main.second();
            }
        });
        Thread doThird = new Thread(new Runnable() {
            @Override
            public void run() {
                main.third();
            }
        });

        doFirst.start();

        try {
            doFirst.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doSecond.start();

        try {
            doSecond.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doThird.start();

        try {
            doThird.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
