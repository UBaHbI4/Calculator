package softing.ubah4ukdev.avers.calculator.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import softing.ubah4ukdev.avers.calculator.domain.SettingsTheme;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.ui

 Created by Ivan Sheynmaer

 2021.03.18
 v1.0
 */
public class BaseActivity extends AppCompatActivity {
    public SettingsTheme mySettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mySettings == null)
            mySettings = new SettingsTheme(this);
        setTheme(mySettings.getTheme());//задаем тему
    }

    public void reCreateActivity() {
        recreate();
    }
}
