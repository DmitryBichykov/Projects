package calculator;

import javax.swing.*;

public class CalcButton extends JButton {

    private Calculator calculator;
    private int indexButton;

    public CalcButton(Calculator calculator, int indexButton) {
        this.calculator=calculator;
        this.indexButton = indexButton;
        addActionListener(new CalcActionListener(this));
        addKeyListener(new CalcKeyListener(calculator));
    }

    public Calculator getCalculator() {
        return calculator;
    }

}
