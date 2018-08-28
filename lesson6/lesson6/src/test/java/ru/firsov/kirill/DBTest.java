package ru.firsov.kirill;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

class AppTestDB {
    private final static String DB_PATH = "src/DBTest.db";
    private static DB  db = new DB();
    String student = "Ivanov";
    String studentDelete = "Surname1";
    double mark = 3.5;

    @BeforeClass
    public static void initializationDB() throws Exception {
        db.connectionDB(DB_PATH);
        db.createDB();
        db.writeDB();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStudentTest() {
        db.addStudent(student, mark);
    }

    @Test
    public void changeStudentTest() {
        db.changeStudent(student, mark);
    }

    @Test
    public void deleteStudentTest() {
        db.deleteStudent(studentDelete);
    }
}