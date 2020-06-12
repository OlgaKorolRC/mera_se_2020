package com.mera.gokhmak.les11;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.BiFunction;

public class Task2 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        // Tests
        Double op1 = 2.354;
        Double op2 = 10.355;
        System.out.println("Sum: " + calculator.calculate("sum", op1, op2));
        System.out.println("Sub: " + calculator.calculate("sub", op1, op2));
        System.out.println("Mul: " + calculator.calculate("mul", op1, op2));
        System.out.println("Div: " + calculator.calculate("div", op1, op2));
        System.out.println("Pow: " + calculator.calculate("pow", op1, op2));
        System.out.println("Squ: " + calculator.calculate("squ", op1, op2));
        System.out.println("Unknown: " + calculator.calculate("unknown", op1, op2));
        calculator.addOperation("strange", (o1,o2) -> 2. + 2.);
        System.out.println("Strange operation: " + calculator.calculate("strange", op1, op2));
    }
}

class Calculator {
    private Collection<Operation> availableOperations = new HashSet<>();
    {
        this.addOperation("sum", (d1,d2) -> d1 + d2);
        this.addOperation("sub", (d1,d2) -> d1 - d2);
        this.addOperation("mul", (d1,d2) -> d1 * d2);
        this.addOperation("pow", (d1,d2) -> Math.pow(d1, d2));

        availableOperations.add(new Operation("squ") {
            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                if (Math.abs(aDouble2) < .0e-12) {
                    throw new ArithmeticException("must not be a zero");
                }
                return Math.exp(Math.log(aDouble) / aDouble2);
            }
        });
        availableOperations.add(new Operation("div") {
            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                if (Math.abs(aDouble2) < .0e-12) {
                    throw new ArithmeticException("division by zero");
                }
                return aDouble / aDouble2;
            }
        });
    }

    public void addOperation(Operation op) {
        availableOperations.add(op);
    }
    public void addOperation(final String shortName, BiFunction<Double, Double, Double> bf) {
        availableOperations.add(new Operation(shortName).setOperation(bf));
    }

    public Double calculate(final String shortName, Double operand1, Double operand2) {
        Double result = null;
        for (Operation op : availableOperations) {
            if (shortName.equals(op.getShortName())) {
                result = (Double) op.apply(operand1, operand2);
            }
        }
        return result;
    }
}