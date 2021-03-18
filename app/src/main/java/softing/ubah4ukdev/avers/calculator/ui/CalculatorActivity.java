package softing.ubah4ukdev.avers.calculator.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import softing.ubah4ukdev.avers.calculator.R;
import softing.ubah4ukdev.avers.calculator.domain.Logger;
import softing.ubah4ukdev.avers.calculator.domain.LoggerType;

public class CalculatorActivity extends BaseActivity implements ICalculatorView {
    private CalculatorPresenter presenter;

    //Поле для отображения вычислений и результата
    private EditText input;
    //Поле для вывода действий (типа история, отображающая последнее выполненное действие)
    private EditText hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            presenter = new CalculatorPresenter(this, mySettings);
            setContentView(R.layout.activity_calculator);
            initCalculator();
            presenter.initStartValue(getIntent().getExtras());
        } catch (Exception err) {
            Logger.printLog(err.getMessage(), LoggerType.ERROR);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Logger.printLog("Счелкнули по меню");
        presenter.menuSelect(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString("inputValue", input.getText().toString());
        instanceState.putString("hintValue", hint.getText().toString());
        if (presenter.getArgOne() != null) instanceState.putDouble("arg1", presenter.getArgOne());
        if (presenter.getArgTwo() != null) instanceState.putDouble("arg2", presenter.getArgTwo());
        instanceState.putString("operation", presenter.getOperation().name());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        String inputValue = instanceState.getString("inputValue");
        input.setText(inputValue);
        String hintValue = instanceState.getString("hintValue");
        hint.setText(hintValue);
        double arg1 = instanceState.getDouble("arg1");
        presenter.setArgOne(arg1);
        double arg2 = instanceState.getDouble("arg2");
        presenter.setArgTwo(arg2);
        String nameOperation = instanceState.getString("operation");
        presenter.setOperation(presenter.getOperation().getValue(nameOperation));
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
        findViewById(R.id.btnOne).setOnClickListener(clickNumbers);
        findViewById(R.id.btnTwo).setOnClickListener(clickNumbers);
        findViewById(R.id.btnThree).setOnClickListener(clickNumbers);
        findViewById(R.id.btnFour).setOnClickListener(clickNumbers);
        findViewById(R.id.btnFive).setOnClickListener(clickNumbers);
        findViewById(R.id.btnSix).setOnClickListener(clickNumbers);
        findViewById(R.id.btnSeven).setOnClickListener(clickNumbers);
        findViewById(R.id.btnEight).setOnClickListener(clickNumbers);
        findViewById(R.id.btnNine).setOnClickListener(clickNumbers);
        //Точка
        findViewById(R.id.btnPoint).setOnClickListener(clickPoint);
        //Равно
        findViewById(R.id.btnEqually).setOnClickListener(clickEqual);
        //Операции
        findViewById(R.id.btnAdd).setOnClickListener(clickOperation);
        findViewById(R.id.btnSub).setOnClickListener(clickOperation);
        findViewById(R.id.btnDiv).setOnClickListener(clickOperation);
        findViewById(R.id.btnMul).setOnClickListener(clickOperation);
        //Очистка
        findViewById(R.id.btnDel).setOnClickListener(clickDel);
        findViewById(R.id.btnBack).setOnClickListener(clickBack);
    }

    @Override
    public String getInput() {
        return input.getText().toString().trim();
    }

    //Метод для вывода в поле результатов
    @Override
    public void displayResult(String s) {
        input.setText(s);
    }

    //Метод для вывода текста в поле истории
    @Override
    public void displayHint(String s) {
        hint.setText(s);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showActivity(Intent intent) {
        startActivityForResult(intent, 0);
    }

    @Override
    public void reCreate() {
        recreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.checkChange();
            }
        }
    }
}


