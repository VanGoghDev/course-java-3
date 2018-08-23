package Firsov.Kirill;

import javafx.css.Size;

import java.util.Random;
import java.util.Scanner;


public class Main {

    private static char[][] map;
    private static final int SIZE = 3;
    private static int DOTS_TO_WIN = 3;

    private static final char DOT_EMPTY = '\u2218';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    public static void main(String[] args) {
	    initMap();
	    printMap();
    }

    private static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(){
        for (int i = 0; i <= SIZE; i++) {
            System.out.println();
            for (int j = 0; j <= SIZE; j++) {
                System.out.println(j);
            }
        }
    }
}
