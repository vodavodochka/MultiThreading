package org.example;

public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            x = 0;
            while (x < 999999) {
                synchronized (Lucky.class)
                {
                    if (x >= 999999) {
                        break;
                    }
                    x+=1;
                    if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                            % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                        System.out.println(x);
                        count+=1;
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}