package softing.ubah4ukdev.avers.calculator.ui;

import softing.ubah4ukdev.avers.calculator.R;
import softing.ubah4ukdev.avers.calculator.domain.Logger;
import softing.ubah4ukdev.avers.calculator.domain.SettingsTheme;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.ui

 Created by Ivan Sheynmaer

 2021.03.18
 v1.0
 */
public class SettingsPresenter {
    private static final int LIGHT_THEME = R.style.CalculatorLight;
    private static final int DARK_THEME = R.style.CalculatorDark;
    private static ISettingsView view;
    SettingsTheme settingsTheme;

    public SettingsPresenter(ISettingsView view, SettingsTheme settingsTheme) {
        this.view = view;
        this.settingsTheme = settingsTheme;
    }

    public void saveTheme() {
        if (view.darkThemeIsChecked()) {
            settingsTheme.setTheme(DARK_THEME);
        } else {
            settingsTheme.setTheme(LIGHT_THEME);
        }
        Logger.printLog("saveTheme " + String.valueOf(view.darkThemeIsChecked()));
        view.reCreate();
    }

    public void load() {
        if (settingsTheme.getTheme() == LIGHT_THEME) {
            Logger.printLog("load: " + String.valueOf(false));
            view.setDarkChecked(false);
        } else {
            Logger.printLog("load: " + String.valueOf(true));
            view.setDarkChecked(true);
        }
    }
}
