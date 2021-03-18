package softing.ubah4ukdev.avers.calculator.domain;

import android.content.Context;
import android.content.SharedPreferences;

import softing.ubah4ukdev.avers.calculator.R;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.domain

 Created by Ivan Sheynmaer

 2021.03.18
 v1.0
 */
//Класс для сохранения и изъятия ID текущей темы в/из Preferences
public class SettingsTheme {
    private static final String NAME_OF_PREFERENCES = "themeSettings";
    private static final String MY_THEME = "myTheme";
    private static final int LIGHT_THEME = R.style.CalculatorLight;

    private SharedPreferences mySetting;
    private Context context;

    public SettingsTheme(Context context) {
        this.context = context;
        mySetting = context.getSharedPreferences(NAME_OF_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setTheme(int themeID) {
        SharedPreferences.Editor editor = mySetting.edit();
        editor.putInt(MY_THEME, themeID);
        editor.apply();
        Logger.printLog("ThemeSaved: " + themeID);
    }

    public int getTheme() {
        int result = mySetting.getInt(MY_THEME, LIGHT_THEME);
        Logger.printLog("ThemeReceived: " + result);
        return result;
    }
}
