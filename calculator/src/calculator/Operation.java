package calculator;

public class Operation {

    private static double[] numbers;
    private StringBuilder number;
    private String numberOperation;
    private static int index;
    private Calculator calculator;
    private boolean symbolFraction;

    public Operation(Calculator calculator) {
        numbers = new double[2];
        index=0;
        this.calculator=calculator;
        this.number=new StringBuilder("");
        this.numberOperation="";
        this.symbolFraction=false;
    }

    public void displayNumberToTextGrid(String num){
        if (num.equals("+/-")){
            plusMinus();
        }else if (!num.equals(".")) {
            number.append(num);
        }else if(!symbolFraction){
            number.append(num);
            symbolFraction=true;
        }
        calculator.getTextField().setText(number.toString());
    }

    public void setNumbersAndNumberOperation(String numberOperation){
        if (numberOperation.equals("c")) {
            cleanAll();
        }else if (numberOperation.equals("←") && number.length()>=0) {
            if (number.length()-1>0){
                    number.setLength(number.length()-1);
                    calculator.getTextField().setText(number.toString());
            }
            else{
                calculator.getTextField().setText("0");
            }
                    return;
            }
            else {
                if (number.length() != 0) {
                    numbers[index++] = Double.parseDouble(number.toString());
                }else if(numbers[0]==0){
                    index++;
                }
                switch (this.numberOperation) {
                    case "+":
                        addition();
                        break;
                    case "-":
                        subtraction();
                        break;
                    case "÷":
                        division();
                        break;
                    case "×":
                        multiplication();
                        break;
                    case "=":
                        equally();
                        break;
                    case "Xⁱ":
                        exponentiation();
                        break;
                    default:
                        break;
                }
                this.numberOperation = numberOperation;
            }
        symbolFraction=false;
        number.setLength(0);
    }

    private void cleanAll(){
        numbers[0]=0;
        numbers[1]=0;
        index=0;
        this.numberOperation="";
        calculator.getTextField().setText("0");
    }

    private void plusMinus(){
        double temp;
        if (number.length()!=0) {
            temp = -1 * Double.parseDouble(number.toString());
            number.setLength(0);

        }else
        {
            numbers[0]*=-1;
            temp=numbers[0];
        }

        if(temp==Math.round(temp)) {
            number.append(Math.round(temp));
        }else {
            number.append(temp);
        }
    }

    private void addition(){
            result(numbers[0] + numbers[1]);
    }

    private void subtraction(){
            result(numbers[0] - numbers[1]);
    }

    private void multiplication(){
            result(numbers[0]*numbers[1]);
    }

    private void division(){
            result(numbers[0]/numbers[1]);
    }

    private void exponentiation(){
        result(Math.pow(numbers[0],numbers[1]));
    }

    private void equally(){
        if (numbers[0]!=numbers[1] && index>1){
            numbers[0]=numbers[1];
            index--;
        }
    }

    private void result(double result){
        number.setLength(0);
        if (result==Math.round(result)){
            displayNumberToTextGrid(String.valueOf(Math.round(result)));
        }else {
            displayNumberToTextGrid(String.valueOf(result));
        }
        numbers[0] = result;
        numbers[1]=0;
        index=1;
    }
}
