package ru.firsov.kirill;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class AppTest {

    @Parameterized.Parameters
    public static List<Integer[][]> data() {
        return Arrays.asList(new Integer[][][] {
                {{0, 0, 0, 0, 0, 0}, {4, 1, 8, 5, 4, 0, 0, 0, 0, 0, 0}, {0}},
                {{1, 8, 5, 1, 2, 5, 0, 5, 3, 0}, {4, 1, 8, 5, 1, 2, 5, 0, 5, 3, 0}, {0}},
                {{}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4}, {1}},
                {{1}, {5, 5, 1, 0, 5, 1, 2, 1, 6, 4, 1}, {0}}
        });
    }

    private int[] array;
    private int[] newArray;

    public AppTest(int[] newArray, int[] array, int[] result) {
        this.array = array;
        this.newArray = newArray;
    }

    @org.junit.Test
    public void checkArrayTest() {
        Assert.assertEquals(newArray, App.task1(array));
    }
}
