package ru.firsov.kirill.entity;

public class Apple implements Fruit{

    private final float weight = 1.0f;

    @Override
    public float getWeight() {
        return weight;
    }
}
