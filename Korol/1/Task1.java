public class Task1 {
    public static void main (String args []) {
        int TheFirstVariable = 3;
        int TheSecondVariable = 8;
        if (TheFirstVariable > TheSecondVariable) {
            System.out.println("Число " + TheFirstVariable + " больше " + TheSecondVariable);
        } else if (TheFirstVariable < TheSecondVariable) {
                System.out.println("Число " + TheFirstVariable + " меньше " + TheSecondVariable);
        }
        System.out.println("Сумма чисел = " + (TheFirstVariable+TheSecondVariable));
    }
}
