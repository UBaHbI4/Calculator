package softing.ubah4ukdev.avers.calculator.ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

import softing.ubah4ukdev.avers.calculator.domain.CalculatorImpl;
import softing.ubah4ukdev.avers.calculator.domain.ICalculator;
import softing.ubah4ukdev.avers.calculator.domain.Operation;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.ui

 Created by Ivan Sheynmaer

 2021.03.10
 v1.0
 */
public class CalculatorPresenter implements Parcelable {
    //Аргумент 1
    private Double argOne;
    //Аргумент 2
    private Double argTwo;
    //Тип операции
    private Operation operation = Operation.UNKNOWN;
    //Формат для вывода значений.
    private final DecimalFormat FORMAT = new DecimalFormat("0.##");
    private static CalculatorActivity view;
    private final ICalculator calculator = new CalculatorImpl();

    protected CalculatorPresenter(Parcel in, CalculatorActivity view) {
        this.view = view;
        if (in.readByte() == 0) {
            argOne = null;
        } else {
            argOne = in.readDouble();
        }
        if (in.readByte() == 0) {
            argTwo = null;
        } else {
            argTwo = in.readDouble();
        }
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in, view);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public Double getArgOne() {
        return argOne;
    }

    public void setArgOne(Double argOne) {
        this.argOne = argOne;
    }

    public Double getArgTwo() {
        return argTwo;
    }

    public void setArgTwo(Double argTwo) {
        this.argTwo = argTwo;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public CalculatorPresenter(CalculatorActivity view) {
        this.view = view;
    }

    //Click по цифровым клавишам
    void keyDigitClick(View value) {
        try {
            String digitValue = ((MaterialButton) value).getText().toString();
            String inputValue = view.getInput();

            if (operation == Operation.UNKNOWN) {
                if (inputValue.isEmpty()) {
                    argOne = Double.valueOf(digitValue);
                } else {
                    inputValue += digitValue;
                    argOne = Double.valueOf(inputValue);
                }
                view.displayResult(FORMAT.format(argOne).replace(".0", ""));
            } else {
                int index = inputValue.indexOf(operation.getLabel(), 1) + 1;
                String d = inputValue.substring(index) + digitValue;
                argTwo = Double.valueOf(d);

                view.displayResult(FORMAT.format(argOne).replace(".0", "")
                        + " " + operation.getLabel() + " "
                        + FORMAT.format(argTwo).replace(".0", ""));
            }

            if (inputValue.indexOf("+") > 0) {
                operation = Operation.ADD;
            } else if (inputValue.indexOf("-") > 0) {
                operation = Operation.SUB;
            } else if (inputValue.indexOf("/") > 0) {
                operation = Operation.DIV;
            } else if (inputValue.indexOf("x") > 0) {
                operation = Operation.MUL;
            }
            Log.i("calc", "keyDigitClick. \targsOne=" + argOne + "\t" + "argsTwo=" + argTwo + " operation=" + operation.getLabel());
        } catch (Exception err) {
            Log.i("calc", "keyDigitClick: " + err.getMessage());
        }
    }

    //Click по клавише "."
    void keyPointClick() {
        //TODO Доделать метод
        String inputValue = view.getInput();
        inputValue += ".";
        view.displayResult(inputValue);
        Log.i("calc", "keyPointClick");
    }

    //Click по клавише "+-/*"
    void keyOperationClick(View value) {
        //TODO Доделать метод
        try {
            String inputValue = view.getInput();
            String tag = ((MaterialButton) value).getTag().toString();
            if (operation == Operation.UNKNOWN) {
                operation = getOperation(tag);
                if (!inputValue.isEmpty()) {
                    inputValue += " " + operation.getLabel();
                    view.displayResult(inputValue);
                }
            } else {
                String find = operation.getLabel();
                operation = getOperation(tag);
                if (inputValue.startsWith("-")) {
                    inputValue = inputValue.substring(1);
                    if (inputValue.indexOf(find, 1) > 0)
                        inputValue = inputValue.replace(find, operation.getLabel());
                    view.displayResult("-" + inputValue);
                } else {
                    if (inputValue.indexOf(find, 1) > 0)
                        inputValue = inputValue.replace(find, operation.getLabel());
                    view.displayResult(inputValue);
                }
            }
            Log.i("calc", "keyOperationClick. \targsOne=" + argOne + "\t" + "argsTwo=" + argTwo + " operation=" + operation.getLabel());
        } catch (Exception err) {
            Log.i("calc", "keyOperationClick: " + err.getMessage());
        }
    }

    private Operation getOperation(String value) {
        switch (value) {
            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUB;
            case "x":
                return Operation.MUL;
            case "/":
                return Operation.DIV;
        }
        return Operation.UNKNOWN;
    }

    //Click по клавише "<--"
    void keyBackClick() {
        //TODO Доделать метод
        try {
            String tempValue = "";
            String inputValue = view.getInput();
            if (inputValue.length() > 1) {
                if (argTwo != null) {
                    tempValue = String.valueOf(argTwo).replace(".0", "");
                    if (tempValue.length() > 1) {
                        tempValue = tempValue.substring(0, tempValue.length() - 1);
                        argTwo = Double.valueOf(tempValue);
                    } else {
                        argTwo = null;
                    }
                } else if (operation != Operation.UNKNOWN) {
                    operation = Operation.UNKNOWN;
                } else if (argOne != null) {
                    tempValue = String.valueOf(argOne).replace(".0", "").replace("-", "");
                    if (tempValue.length() > 1) {
                        tempValue = tempValue.substring(0, tempValue.length() - 1);
                        argOne = Double.valueOf(tempValue);
                    } else {
                        argOne = null;
                    }
                }

                inputValue = (argOne == null ? "" : argOne) + ""
                        + (operation == Operation.UNKNOWN ? "" : (" " + operation.getLabel() + " "))
                        + (argTwo == null ? "" : argTwo);

                view.displayResult(inputValue.replace(".0", ""));
                if (inputValue.isEmpty()) view.displayHint("");
            } else {
                view.displayResult("");
                view.displayHint("");
                argOne = null;
                argTwo = null;
                operation = Operation.UNKNOWN;
            }
            Log.i("calc", "inputValue: inputValue=" + inputValue +
                    "\targOne=" + argOne + "\toperation=" + operation.getLabel() + " \targTwo=" + argTwo);
        } catch (Exception err) {
            Log.i("calc", "keyBackClick: " + err.getMessage());
        }
    }

    //Click по клавише "DEL"
    void keyDelClick() {
        //TODO Доделать метод
        view.displayResult("");
        view.displayHint("");
        argOne = null;
        argTwo = null;
        operation = Operation.UNKNOWN;
        Log.i("calc", "keyDelClick");
    }

    //Click по клавише "="
    void keyEqualClick() {
        //TODO Доделать метод
        try {
            if (operation == Operation.UNKNOWN) return;
            Double resultat = calculator.calculate(argOne, argTwo, operation);

            view.displayResult(FORMAT.format(resultat).replace(",", "."));
            view.displayHint((FORMAT.format(argOne) + " " + operation.getLabel() + " "
                    + FORMAT.format(argTwo)));
            Log.i("calc", "keyEqualClick. \targsOne=" + argOne + "\t" + "argsTwo="
                    + argTwo + " operation=" + operation.getLabel()
                    + " resultat:" + resultat);
            argOne = resultat;
            argTwo = null;
            operation = Operation.UNKNOWN;
        } catch (Exception err) {
            view.showMessage(err.getMessage());
            keyDelClick();
            Log.i("calc", "keyEqualClick: " + err.getMessage());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(argOne);
        dest.writeDouble(argOne);
    }
}
