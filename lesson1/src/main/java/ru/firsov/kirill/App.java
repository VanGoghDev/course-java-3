package ru.firsov.kirill;

import ru.firsov.kirill.entity.Apple;
import ru.firsov.kirill.entity.Box;
import ru.firsov.kirill.entity.Orange;

public class App {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println(appleBox.getBoxWeight());
        System.out.println(orangeBox.getBoxWeight());

        System.out.println(appleBox.compare(orangeBox));
        Box<Apple> appleBox2 = new Box<>();
        appleBox.changeBox(appleBox2);
        System.out.println(appleBox2.getBoxFruit());
    }
}
