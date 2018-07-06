package ru.firsov.kirill;

import ru.firsov.kirill.entity.Object1;
import ru.firsov.kirill.entity.Object2;

import java.util.*;

public class Swap<T> {
    private ArrayList<T> arr;

    private Swap(ArrayList<T> o) {
        arr = o;
    }

    private void swap(int index1, int index2) {
        T obj1 = arr.get(index1);
        T obj2 = arr.get(index2);
        arr.set(index1, obj2);
        arr.set(index2, obj1);
    }

    private ArrayList<T> getArr() {
        return arr;
    }

    private void printArr() {
        System.out.println(getArr());
    }

    public static <T> void intoArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>(Arrays.asList(array));
        System.out.println(arrayList);
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, ("The blah blah song").split(" "));
        Object1 obj1 = new Object1();
        Object2 obj2 = new Object2();
        arrayList.add(obj1);
        arrayList.add(obj2);
        Swap<Integer> swap = new Swap<Integer>(arrayList);
        swap.swap(0, 5);
        swap.printArr();
    }
}
