import bicycle.Bicycle;
import myarray.*;
import java.util.Random;


class Main {
    public static void main(String[] args) {

        Random rnd = new Random(System.currentTimeMillis());
        String[] models = new String[] {
            "MERIDA",
            "GT",
            "TREK",
            "STELS",
            "FORWARD",
            "MONGOOSE",
            "STERN",
            "CUBE",
            "NORCO",
            "GIANT",
            "AUTHOR",
            "JAMIS",
            "STARK"
        };



        int min = 5, max = 50;

        int num = 11;

        MyArrayList < Integer > numbers = new MyArrayList < > ();
        MyArrayList < Bicycle > bicycles = new MyArrayList < > ();


        Bicycle bicycle = new Bicycle();

        System.out.println("Велосипеды в  MyArrayList" + ":");

        for (int i = 0; i < num; i++) {
            try {
                int n = rnd.nextInt(models.length);
                int k = min + rnd.nextInt(max - min + 1);
                bicycle = new Bicycle(models[n], k);
                bicycles.add(bicycle);
                System.out.println(bicycles.get(i).toString());
            } catch (MyArrayStoreException ex) {

                System.out.println("Error with element " + (i + 1) + " : " + bicycle.toString() + "\n" + ex.getMessage() + '\n');
            }

        }


        int minSpeed = rnd.nextInt(50);

        System.out.println("Удалить велосипеды со скоростями меньше " + minSpeed + ":");

        int i = 0;


        while (i < bicycles.size()) {
            if (bicycles.get(i).getSpeed() >= minSpeed) {
                i++;
            } else if (bicycles.get(i).getSpeed() < minSpeed) {
                bicycles.remove(i);
            }


        }

        for (int j = 0; j < bicycles.size(); j++) {
            System.out.println(bicycles.get(j).toString());

        }



        ///////////////////////////////////////////////////

        int maxNumber = 10;

        System.out.println('\n' + "Числа в MyArrayList: ");

        for (int j = 0; j < maxNumber; j++) {
            try {
                numbers.add(1 + rnd.nextInt(10)); //случайное число от 1 до 10
                System.out.println(numbers.get(j));
            } catch (MyArrayStoreException ex) {

                System.out.println("Error with element " + (j + 1) + " : " + numbers.get(j) + " " + ex.getMessage() + '\n');
            }

        }

        System.out.println('\n' + "Удалить все четные числа: ");


        int nn = 0;
        int tmp;

        while (nn < numbers.size()) {
            tmp = numbers.get(nn).intValue();
            if (tmp % 2 == 0) {
                numbers.remove(nn);
            } else {
                nn++;
            }

        }


        for (int j = 0; j < numbers.size(); j++) {
            System.out.println(numbers.get(j));

        }
    }
}
