package softing.ubah4ukdev.avers.calculator.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import softing.ubah4ukdev.avers.calculator.R;
import softing.ubah4ukdev.avers.calculator.domain.Logger;


public class SettingsActivity extends BaseActivity implements ISettingsView {
    private SettingsPresenter presenter;
    private MaterialCheckBox checkBox;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.printLog("SettingsActivity onCreate");
        presenter = new SettingsPresenter(this, mySettings);
        setTitle("Настройки");
        setContentView(R.layout.activity_settings);
        init();
        presenter.load();
    }

    private void init() {
        checkBox = findViewById(R.id.isDarkTheme);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            presenter.saveTheme();
            setResult(Activity.RESULT_OK);
            finish();
        });
    }

    @Override
    public boolean darkThemeIsChecked() {
        return checkBox.isChecked();
    }

    @Override
    public void setDarkChecked(boolean checked) {
        Logger.printLog(String.valueOf(checked));
        checkBox.setChecked(checked);
    }

    @Override
    public void reCreate() {
        recreate();
    }

    @Override
    public void setThemeByID(int ThemeID) {
        setTheme(ThemeID);
    }


}