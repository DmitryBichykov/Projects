package calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame{

    private CalcButton[] buttons;
    private JTextField textField;
    private Operation operation;
    private String[] arraySymbol = {"÷", "←", "c", "Xⁱ", "×", "9", "8", "7", "-", "6", "5", "4", "+", "3", "2", "1", "=", ".", "0", "+/-"};

    public Calculator(){
        buttons=new CalcButton[arraySymbol.length];
        textField= new JTextField("0");
        operation=new Operation(this);
    }

    public void createForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contents1=new JPanel();
        contents1.setLayout(new BoxLayout(contents1,BoxLayout.Y_AXIS));

        //Окно для ввода чисел
        textField.setPreferredSize(new Dimension(215,50));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        textField.addKeyListener(new CalcKeyListener(this));
        contents1.add(textField);

        //Кнопки
        JPanel contents2=new JPanel(new GridLayout(5,4,5,5));
        contents2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for (int i = 0; i < arraySymbol.length; i++) {
            buttons[i] = new CalcButton(this, i);
            buttons[i].setText(arraySymbol[i]);
            contents2.add(buttons[i]);
        }

        add("North",contents1);
        add("Center",contents2);
        setSize(new Dimension(300,400));
        setTitle("Calculator");
        setLocationRelativeTo(new JDialog());
        setVisible(true);
    }

    public JTextField getTextField() {
        return textField;
    }

    public Operation getOperation() {
        return operation;
    }
}
