package ru.firsov.kirill.entity;

public class Orange implements Fruit {
    private final float weight = 1.5f;

    @Override
    public float getWeight() {
        return weight;
    }
}
