package softing.ubah4ukdev.avers.calculator.ui;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.ui

 Created by Ivan Sheynmaer

 2021.03.13
 v1.0
 */

public interface ICalculatorView {
    //Метод для получения текстового поля
    String getInput();

    //Вывести информацию в поле ввода
    void displayResult(String s);

    //Вывести информацию в поле истории
    void displayHint(String s);

    //Отобразить всплывающее сообщению
    void showMessage(String message);
}
