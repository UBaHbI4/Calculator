package softing.ubah4ukdev.avers.calculator.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import softing.ubah4ukdev.avers.calculator.R;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorPresenter presenter = new CalculatorPresenter(this);

    private final static String MY_SAVED_PRESENTER = "CalcPresenter";

    //Поле для отображения вычислений и результата
    private EditText input;
    //Поле для вывода действий (типа история, отображающая последнее выполненное действие)
    private EditText hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calculator);
            initCalculator();
        } catch (Exception err) {
            Log.i("calc", "onCreate: " + err.getMessage());
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        //Сохраняем наш presenter
        instanceState.putParcelable(MY_SAVED_PRESENTER, presenter);
        super.onSaveInstanceState(instanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        //Восстанавливаем наш presenter
        presenter = instanceState.getParcelable(MY_SAVED_PRESENTER);
    }

    //Метод инициализации. Для всех кнопок привязываем OnClickListener
    private void initCalculator() {
        input = findViewById(R.id.editTextNumberDecimal);
        hint = findViewById(R.id.hint);

        //Обработка нажатий по цифровым клавишам
        OnClickListener clickNumbers = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyDigitClick(v);
            }
        };
        //Обработка нажатий по клавише "."
        OnClickListener clickPoint = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyPointClick();
            }
        };
        //Обработка нажатия на кнопку "="
        OnClickListener clickEqual = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyEqualClick();
            }
        };
        //Обработка нажатия на кнопки действий
        OnClickListener clickOperation = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyOperationClick(v);
            }
        };
        //Обработка нажатия на клавишу DEL
        OnClickListener clickDel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyDelClick();
            }
        };
        //Обработка нажатия на клавишу BACKSPACE
        OnClickListener clickBack = new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keyBackClick();
            }
        };

        findViewById(R.id.btnZero).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnOne).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnTwo).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnThree).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnFour).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnFive).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnSix).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnSeven).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnEight).setOnClickListener(clickNumbers);
        ;
        findViewById(R.id.btnNine).setOnClickListener(clickNumbers);
        ;
        //Точка
        findViewById(R.id.btnPoint).setOnClickListener(clickPoint);
        ;
        //Равно
        findViewById(R.id.btnEqually).setOnClickListener(clickEqual);
        ;
        //Операции
        findViewById(R.id.btnAdd).setOnClickListener(clickOperation);
        ;
        findViewById(R.id.btnSub).setOnClickListener(clickOperation);
        ;
        findViewById(R.id.btnDiv).setOnClickListener(clickOperation);
        ;
        findViewById(R.id.btnMul).setOnClickListener(clickOperation);
        ;
        //Очистка
        findViewById(R.id.btnDel).setOnClickListener(clickDel);
        ;
        findViewById(R.id.btnBack).setOnClickListener(clickBack);
        ;
    }

    public String getInput() {
        return input.getText().toString().trim();
    }

    //Метод для вывода в поле результатов
    public void displayResult(String s) {
        input.setText(s);
    }

    //Метод для вывода текста в поле истории
    public void displayHint(String s) {
        hint.setText(s);
    }

    //Метод для вывода сообщений (например об ошибках)
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}