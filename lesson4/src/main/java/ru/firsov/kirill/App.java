package ru.firsov.kirill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private DataOutputStream fileStream;
    private static int currentPosition = 0;

    public static void main(String[] args) {
        App app = new App();
        app.printABC();
        app.writeToFile();
    }

    //Task1
    public void printABC() {

        final App lock = new App();

        final List<String> arraySymbols = new ArrayList<>();
        arraySymbols.add("A");
        arraySymbols.add("B");
        arraySymbols.add("C");


        for (int i = 0; i <= 2; i++) {
            final int position = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    print(lock, position, arraySymbols);
                }
            });
            thread.start();
        }
    }

    private static void print(App lock, int position, List<String> arraySymbols) {
        synchronized (lock) {
            for (int i = 1; i <= 5; i++) {
                while (currentPosition != position) waitABC(lock);
                System.out.print(arraySymbols.get(position));
                if (currentPosition != (arraySymbols.size() - 1)) currentPosition = position + 1;
                else currentPosition = 0;
                lock.notifyAll();
            }
        }
    }

    public static void waitABC(App lock) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Task2
    public void writeToFile() {
        System.out.println();
        File file = new File("src/fileTask2.txt");
        List<Thread> threads = new ArrayList<>();
        try {
            fileStream = new DataOutputStream(new FileOutputStream(file));


            for (int i = 1; i <= 3; i++) {
                Thread thread = new Thread(new WriteToFileRunnable(fileStream, "Thread " + i));
                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    class WriteToFileRunnable implements Runnable {
        private String nameThread;
        private DataOutputStream fileStream;

        WriteToFileRunnable(DataOutputStream fileStream, String nameThread) {
            this.nameThread = nameThread;
            this.fileStream = fileStream;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    fileStream.writeUTF(nameThread + ". Line" + i + "\n");
                    System.out.println(nameThread + ". Line" + i);
                    Thread.sleep(20);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}