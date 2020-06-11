import java.util.HashMap;
import java.util.Map;



public class Task2 {

    public static void main(String[] args) {

        System.out.println("calculator\n");

        Calculator calc = new Calculator();

        calc.addOperation("mult", (a, b) -> a * b); // умножение
        calc.addOperation("division", (a, b) -> a / b); // деление
        calc.addOperation("sum", (a, b) -> a + b); // сложение
        calc.addOperation("subtraction", (a, b) -> a - b); // вычитание
        calc.addOperation("pow", (a, n) -> Math.pow(a, n)); // возведение в степень n
        calc.addOperation("root", (a, n) -> Math.pow(a, 1. / n)); // извлечение корня степени n

        System.out.println("");
        
        System.out.println("mult,2.,3. = " + calc.calculate("mult", 2., 3.));
        System.out.println("division,2.,3. = " + calc.calculate("division", 2., 3.));
        System.out.println("sum,2.,3. = " + calc.calculate("sum", 2., 3.));
        System.out.println("subtraction,2.,3. = " + calc.calculate("subtraction", 2., 3.));
        System.out.println("pow,2.,3. = " + calc.calculate("pow", 2., 3.));
        System.out.println("root,2.,3. = " + calc.calculate("root", 2., 3.));


    }
}



class Calculator {

    private Map < String, Operation > operations = new HashMap < > ();

    public Double calculate(String operationName, Double number1, Double number2) {

        return operations.get(operationName).doOperation(number1, number2);
    }

    public void addOperation(String operationName, Operation implementation) {
        if (operations.containsKey(operationName)) {
            throw new IllegalArgumentException("Операция " + operationName + " уже есть в списке");
        } else {
            operations.put(operationName, implementation);
            System.out.println("Операция " + operationName + " успешно добавлена");
        }


    }

}

interface Operation {
    Double doOperation(Double number1, Double number2) throws ArithmeticException;
}
