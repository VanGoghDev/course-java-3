package ru.firsov.kirill;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class DB {

    String DB_PATH = "src/DB.db";
    private static final int QUANTITY_STUDENTS = 5;
    private static final String ADD_STUDENT = "/add";
    private static final String CHANGE_STUDENT = "/change";
    private static final String DELETE_STUDENT = "/delete";
    private static final String END = "/end";
    private static final String SELECT = "/select";
    private static final Logger LOGGER = Logger.getLogger(DB.class.getSimpleName());
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;
    private Scanner scanner;

    public void startTask3() {
        System.out.println("Task3");

        connectionDB(DB_PATH);
        createDB();
        writeDB();

        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] arrayCommand = command.split(" ");

            if (DELETE_STUDENT.equals(arrayCommand[0])) {
                if (arrayCommand.length == 2) {
                    String student = arrayCommand[1];
                    deleteStudent(student);
                }

            } else if (ADD_STUDENT.equals(arrayCommand[0])) {
                if (arrayCommand.length == 3) {
                    String student = arrayCommand[1];
                    double mark = Double.parseDouble(arrayCommand[2]);
                    addStudent(student, mark);
                }

            } else if (CHANGE_STUDENT.equals(arrayCommand[0])) {
                if (arrayCommand.length == 3) {
                    String student = arrayCommand[1];
                    double mark = Double.parseDouble(arrayCommand[2]);
                    changeStudent(student, mark);
                }

            } else if (SELECT.equals(arrayCommand[0])) {
                selectAll();

            } else if (END.equals(arrayCommand[0])) {
                closeDB();

            }
        }
    }

    public void connectionDB(String DB_PATH) {
        connection = null;
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void createDB() {
        try {
            preparedStatement = connection.prepareStatement("CREATE TABLE if not exists students (id INTEGER PRIMARY KEY AUTOINCREMENT, surname TEXT, mark REAL);");
            preparedStatement.execute();
            System.out.println("Table created");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void writeDB() {
        clearTable();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO students (surname, mark) VALUES (?,?)");
            for (int i = 1; i <= QUANTITY_STUDENTS; i++) {
                preparedStatement.setString(1, "Surname" + i);
                preparedStatement.setDouble(2, Math.random() * 5);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        System.out.println("Table is full");

    }

    public void selectAll() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM students");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String student = resultSet.getString("surname");
                double mark = resultSet.getDouble("mark");
                System.out.println(student + " " + mark);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void addStudent(String student, double mark) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO students (surname, mark) VALUES (?,?)");
            preparedStatement.setString(1, student);
            preparedStatement.setDouble(2, mark);

            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("Data added");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            System.out.println("Not added");
        }
    }

    public void changeStudent(String student, double mark) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE students SET mark = ? WHERE surname = ?");
            preparedStatement.setDouble(1, mark);
            preparedStatement.setString(2, student);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Data changed");
            } else System.out.println("No changed");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public void deleteStudent(String student) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM students WHERE surname = ?");
            preparedStatement.setString(1, student);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Data deleted");
            } else System.out.println("No deleted");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public static void clearTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM students");
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