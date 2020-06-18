package com.mera.lection6;

import java.util.Arrays;
import java.util.Random;
import java.util.ServiceLoader;

class Ex1 {
    public static void main(String[] args) {
        MyArrayList<Bicycle> bicycleList = new MyArrayList<Bicycle>();

        for (int i = 0; i < bicycleList.MAX_INDEX + 1; i++) {
            Bicycle newBike = new Bicycle();
            try {
                bicycleList.add(newBike);
            } catch (MyArrayStoreException exception) {
                System.out.printf("The %s is not added to list\n", newBike);
            }
        }

        System.out.println(Arrays.toString(bicycleList.toArray()));

        int randomSpeed = new Random().nextInt(51);
        for (int i = 0; i < bicycleList.size(); i++) {
            Bicycle sample = bicycleList.get(i);
            if (sample.getSpeed() < randomSpeed) {
                bicycleList.remove(sample);
                i--;
            }
        }

        System.out.printf("\nThe random speed is %d\nAfter deletion: ", randomSpeed);
        System.out.println(Arrays.toString(bicycleList.toArray()));


        System.out.println("\nAnd here the part for integer");
        MyArrayList<Integer> numberList = new MyArrayList<Integer>();
        for (int i = 0; i < numberList.MAX_INDEX; i++) {
            Integer newNum = new Random().nextInt(11);
            try {
                numberList.add(newNum);
            } catch (MyArrayStoreException exception) {
                System.out.printf("The %d is not added to list\n", newNum);
            }
        }
        System.out.println(Arrays.toString(numberList.toArray()));

        for (int i = 0; i < numberList.size(); i++) {
            Integer sample = numberList.get(i);
            if (sample % 2 == 0) {
                numberList.remove(sample);
                i--;
            }
        }
        System.out.println("\nAfter deletion: ");
        System.out.println(Arrays.toString(numberList.toArray()));
    }
}
