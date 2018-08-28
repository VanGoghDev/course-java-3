package ru.firsov.kirill;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class AppTest {

    @Parameterized.Parameters
    public static Collection<int[][]> data() {
        return Arrays.asList(new int[][][] {
                {{0, 0, 0, 0}, {4, 4, 3, 1, 8, 5, 4, 0, 0, 0, 0}, {1}},
                {{1, 8, 5, 0, 5, 3, 0}, {4, 1, 8, 5, 0, 5, 3, 0}, {1}},
                {{}, {1, 9, 1, 4}, {1}},
                {{1}, {5, 5, 1, 0, 5, 1, 2, 1, 6, 4, 1}, {1}},
                {{}, {3, 5, 2}, {0}}
        });
    }

    private int[] array;
    private int[] newArray;
    private boolean result;

    public AppTest(int[] newArray, int[] array, int[] result) {
        this.array = array;
        this.newArray = newArray;

        this.result = result[0] != 0;
    }

    @Test
    public void task1Test() {
        Assert.assertEquals(newArray, App.task1(array));
    }

    @Test
    public void task2Test() {
        Assert.assertEquals(result, App.task2(array));
    }
}
