package softing.ubah4ukdev.avers.calculator.domain;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.domain

 Created by Ivan Sheynmaer

 2021.03.10
 v1.0
 */

public class CalculatorImpl implements ICalculator {
    @Override
    public Double calculate(Double argumentOne, Double argumentTwo, Operation operation) {
        switch (operation) {
            case ADD:
                return argumentOne + argumentTwo;
            case DIV:
                if (argumentTwo == 0) {
                    throw new RuntimeException("Division by zero! Change You arguments...");
                }
                return argumentOne / argumentTwo;
            case MUL:
                return argumentOne * argumentTwo;
            case SUB:
                return argumentOne - argumentTwo;
        }
        return 0.0;
    }
}
