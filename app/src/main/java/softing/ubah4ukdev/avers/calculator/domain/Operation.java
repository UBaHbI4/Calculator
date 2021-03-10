package softing.ubah4ukdev.avers.calculator.domain;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator

 Created by Ivan Sheynmaer

 2021.03.09
 v1.0
 */
public enum Operation {
    ADD ("+"),
    DIV ("/"),
    MUL ("x"),
    SUB ("-"),
    UNKNOWN ("");
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    Operation(String label) {
        this.label = label;
    }
}
