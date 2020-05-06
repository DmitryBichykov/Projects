package calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalcKeyListener implements KeyListener {

    private Calculator calculator;
    private Operation operation;

    public CalcKeyListener(Calculator calculator){
        this.calculator=calculator;
        this.operation=calculator.getOperation();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String  key=String.valueOf(e.getKeyChar());
        if (Character.isDigit(key.charAt(0)) || key.equals(".")){
            operation.displayNumberToTextGrid(key);
        }

        switch (key){
            case "+": key="+";
                operation.setNumbersAndNumberOperation(key);
                break;
            case "-": key="-";
                operation.setNumbersAndNumberOperation(key);
                break;
            case "/": key="÷";
                        operation.setNumbersAndNumberOperation(key);
                        break;
            case "*": key="×";
                        operation.setNumbersAndNumberOperation(key);
                        break;
            case "^": key="Xⁱ";
                         operation.setNumbersAndNumberOperation(key);
                         break;
            case "\u001B": key="c";
                        operation.setNumbersAndNumberOperation(key);
                        break;
            case "\u007F": key="c";
                         operation.setNumbersAndNumberOperation(key);
                         break;
            case "\u0008": key="←";
                        operation.setNumbersAndNumberOperation(key);
                        break;
            case "\n": key="=";
                operation.setNumbersAndNumberOperation(key);
                break;
            default:break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
