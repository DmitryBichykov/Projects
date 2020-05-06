package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcActionListener implements ActionListener {

    private CalcButton button;
    private Calculator calculator;
    private Operation operation;

    public CalcActionListener(CalcButton button){
        this.button=button;
        this.calculator=button.getCalculator();
        this.operation=calculator.getOperation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Character.isDigit(button.getText().charAt(0)) || button.getText().equals(".") || button.getText().equals("+/-")){
            operation.displayNumberToTextGrid(button.getText());
        }else{
            operation.setNumbersAndNumberOperation(button.getText());
        }
    }

}
