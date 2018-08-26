package ru.firsov.kirill;

import static java.lang.Thread.*;


public class MFU {
    private Object lockPrint = new Object();
    private Object lockScan = new Object();

    public void printMFU(final int countPage) {

        Thread threadPrint = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockPrint) {
                    for (int count = 1; count <= countPage; count++) {
                        System.out.println("Отпечатано " + count + " страниц");
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        threadPrint.start();
    }

    public synchronized void scanMFU(final int countPage) {

        Thread threadScan = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockScan) {
                    for (int count = 1; count <= countPage; count++) {
                        System.out.println("Отсканировано " + count + " страниц");
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        threadScan.start();

    }

}
