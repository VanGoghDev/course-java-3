package ru.firsov.kirill;
import org.sqlite.JDBC;
import java.sql.*;
import java.util.logging.Logger;

public class MyBDApp {

    String DB_PATH = "DB.db";
    private static final int QUANTITY_PRODUCTS = 100;
    private static final Logger LOGGER = Logger.getLogger(MyBDApp.class.getSimpleName());
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    public void connectionDB() {
        connection = null;
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        } catch (SQLException  e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void createDB() {
        try {
            preparedStatement = connection.prepareStatement("CREATE TABLE if not exists products (id INTEGER PRIMARY KEY AUTOINCREMENT, prodid INTEGER, title TEXT, cost INTEGER);");
            preparedStatement.execute();
            System.out.println("Таблица создана или уже существует.");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void writeDB() {
        clearTable();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO products (prodid, title, cost) VALUES (?,?,?)");
            for (int i = 1; i <= QUANTITY_PRODUCTS; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2,"Товар" + i);
                preparedStatement.setInt(3,i * 10);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        System.out.println("Таблица заполнена");

    }

    public void findPrice(String product) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE title = ?");
            preparedStatement.setString(1, product);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int cost = resultSet.getInt("cost");
                System.out.println("Цена товара " + product + " = " + cost);
            } else System.out.println("Такого товара нет");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void changePrice(String product, int cost) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET cost = ? WHERE title = ?");
            preparedStatement.setInt(1, cost);
            preparedStatement.setString(2, product);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Цена товара изменена");
            } else System.out.println("Изменений не произошло");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public void findPriceRange(int cost1, int cost2) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE cost BETWEEN ? AND ?");
            preparedStatement.setInt(1, cost1);
            preparedStatement.setInt(2, cost2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String product = resultSet.getString("title");
                System.out.println(product);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public static void clearTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM products");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void closeDB() {
        try {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

}