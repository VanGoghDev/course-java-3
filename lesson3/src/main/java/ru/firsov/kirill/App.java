package ru.firsov.kirill;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Logger;


public class App
{
    public static void main( String[] args ) throws IOException {
        File f = new File("./src/someFile.txt");
        FileInputStream fInput = new FileInputStream(f);
        read(f);
        combineFiles();
        readByPage();
    }

    private static byte[] read(File bFile) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            System.out.println(data);
            return data;
        } finally {
            bf.close();
        }
    }
    private static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsolutePath());
    }

    private static void combineFiles() {
        final Logger LOGGER = Logger.getLogger(App.class.getSimpleName());
        SequenceInputStream sequence;
        try (FileOutputStream out = new FileOutputStream("6.txt")) {
            ArrayList<InputStream> arrayList = new ArrayList<>();
            arrayList.add(new FileInputStream("./src/1.txt"));
            arrayList.add(new FileInputStream("./src/2.txt"));
            arrayList.add(new FileInputStream("./src/3.txt"));
            arrayList.add(new FileInputStream("./src/4.txt"));
            arrayList.add(new FileInputStream("./src/5.txt"));
            Enumeration<InputStream> e = Collections.enumeration(arrayList);
            sequence = new SequenceInputStream(e);
            int rb = sequence.read();
            while (rb != -1) {
                out.write(rb);
                rb = sequence.read();
            }
            sequence.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private static void readByPage() throws IOException {
        final int PAGE_SIZE = 1800;
        byte[] arrByte = new byte[PAGE_SIZE];

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("./src/Task3.txt", "r");
                int pageNum = in.nextInt();
                System.out.println(pageNum);
                float pageCount = randomAccessFile.length() / PAGE_SIZE;
                if (pageNum > 0 && pageNum <= pageCount) {
                    randomAccessFile.seek((pageNum - 1) * PAGE_SIZE);
                    randomAccessFile.read(arrByte, 0, PAGE_SIZE);
                    for (byte b:arrByte) {
                        System.out.print((char)b + " ");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

}
