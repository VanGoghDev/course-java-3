package ru.firsov.kirill;

import ru.firsov.kirill.annotations.AfterSuite;
import ru.firsov.kirill.annotations.BeforeSuite;
import ru.firsov.kirill.annotations.Test;

public class TestApp {

    @BeforeSuite
    public void setUp() {
        System.out.println("@BeforeSuite is ok");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("@AfterSuite is ok");
    }

    @Test(priority = 4)
    public void test1(){
        System.out.println("Test1 - priority = 4");
    }

    @Test(priority = 2)
    public void test2(){
        System.out.println("Test2 - priority = 2");
    }

    @Test
    public void test3(){
        System.out.println("Test3 - Default priority 1");
    }

    @Test
    public void test4(){
        System.out.println("Test4 - Default priority 2");
    }

}
