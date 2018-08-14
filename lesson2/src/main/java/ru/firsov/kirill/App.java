package ru.firsov.kirill;
import java.util.Scanner;

public class App {
    private static final String FIND_PRICE = "/цена";
    private static final String CHANGE_PRICE = "/сменитьцену";
    private static final String PRICE_RANGE = "/товарыпоцене";
    private static Scanner scanner;

    public static void main(String[] args) {
        MyBDApp bd = new MyBDApp();
        try {
            bd.connectionDB();
            bd.createDB();
            bd.writeDB();

            scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                String[] arrayCommand = command.split(" ");

                if (arrayCommand[0].equals(FIND_PRICE) && arrayCommand.length == 2) {
                    String product = arrayCommand[1];
                    bd.findPrice(product);
                }
                if (arrayCommand[0].equals(CHANGE_PRICE) && arrayCommand.length == 3) {
                    String product = arrayCommand[1];
                    int cost = Integer.parseInt(arrayCommand[2]);
                    bd.changePrice(product, cost);
                }
                if (arrayCommand[0].equals(PRICE_RANGE) && arrayCommand.length == 3) {
                    int cost1 = Integer.parseInt(arrayCommand[1]);
                    int cost2 = Integer.parseInt(arrayCommand[2]);
                    bd.findPriceRange(cost1, cost2);
                }
            }
        } finally {
            bd.closeDB();
        }

    }
}
