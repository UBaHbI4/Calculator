package softing.ubah4ukdev.avers.calculator.ui;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.ui

 Created by Ivan Sheynmaer

 2021.03.18
 v1.0
 */
public interface ISettingsView {
    boolean darkThemeIsChecked ();

    void setDarkChecked (boolean checked);

    void reCreate();

    void setThemeByID(int ThemeID);

}
