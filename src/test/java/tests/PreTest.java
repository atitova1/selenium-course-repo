package tests;

import java.util.ArrayList;
import java.util.Scanner;

public class PreTest {
    public static int searchMax(ArrayList<Integer> m) {
        int max = m.get(0);
        for (int d: m
             ) {
            if (d > max) {
                max = d;
            }
        }
        System.out.println("Максимальное число - " + max);
        return max;
    }

    public static void main(String[] args) {
        ArrayList<Integer> m = new ArrayList<Integer>();
        int size;
        Scanner input = new Scanner(System.in);
        System.out.println("Вовочка-пончичек, какой будет размер массива?");
        size = input.nextInt();
        for (int i = 1; i < size+1; i++) {
            System.out.println("Вовочка-пончичек, введи " + i + " число");
            m.add(input.nextInt());
        }
        input.close();
        searchMax(m);
    }
}
