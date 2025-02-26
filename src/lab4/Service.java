package lab4;

public class Service {
    double result;
    double currentVal;
    char operator;
    public Service(){
        result = 0;
        currentVal = 0;
        operator = '+';
    }
    public String exec(String command){

        if(command.isBlank()) return "Send emtpy command";

        char op = command.charAt(command.length()-1);
        if(op!='+'&&op!='-'&&op!='=') return "Invalid operator: use\"+\", \"-\" or \"=\" after number";

        String num = command.replace(op,' ');
        try {
            currentVal = Double.parseDouble(num);
        }catch (NumberFormatException e){
            return num + " is invalid number format";
        }

        if(operator=='+') result += currentVal;
        else if (operator=='-') result -= currentVal;

        if(op=='='){
            double r = result;
            result = 0;
            currentVal = 0;
            operator = '+';
            return "Result: "+ r;
        }

        return "Successful";
    }
}
