package ru.firsov.kirill.entity;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> boxFruit = new ArrayList<>();
    private float boxWeight = 0;

    public ArrayList<T> getBoxFruit() {
        return boxFruit;
    }

    public float getBoxWeight() {
        if (!boxFruit.isEmpty()) {
            boxWeight = boxFruit.size() * boxFruit.get(0).getWeight();
        }
        return boxWeight;
    }

    public void add(T item) {
        boxFruit.add(item);
    }

    private void setBoxFruit(ArrayList<T> arrayList) {
        boxFruit.clear();
        boxFruit = arrayList;
    }

    public boolean compare(Box<?> anotherBox) {
        return this.getBoxWeight() == anotherBox.getBoxWeight();
    }

    public void changeBox(Box<T> anotherBox) {
        ArrayList<T> newBox = getBoxFruit();
        anotherBox.setBoxFruit(newBox);
    }
}
