

import java.util.Stack;


public class Main {
    
    public static void main(String[] args) {
        EvalExp x = new EvalExp();
        
       
        System.out.println(x.evalExp("2 / 2 + 3 * 4 - 6"));
    }
    
}

class EvalExp {
    
    static Stack<Integer> valStk = new Stack<>();
    static Stack<String> opStk = new Stack<>();
 
    
    public EvalExp() {
 
    }
    
    public static double evalExp(String exp) {
        
        
        String[] arr = exp.split(" ");
        
        for(String x : arr) {
        
            if(isNumber(x)) {
                valStk.push(Integer.parseInt(x));
            }else {
                repeatOps(x);
                opStk.push(x);
            }
        }
        
        repeatOps("$");
        return valStk.pop();
        
    }
    public static int prec(String x) {
        if(x.equals("*") || x.equals("/")){
            return 1;
        }else {
            return 0;
        }
    }
    public static boolean isNumber(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
       
    }
    
    public static void repeatOps(String refOf) {
        
        while(valStk.size() > 1 && prec(refOf) <= prec(opStk.peek())) {
            doOp();
        }
            
    }
    
    public static void doOp() {
        int y = valStk.pop();// last stack element
        int x = valStk.pop();// first stack element
        String op = opStk.pop();
        int res = 0;
        
        if(op.equals("+")) {
            //we need the old one for the equation
            res = x+y;
        }else if(op.equals("-")) {
            res = x-y;
        }else if(op.equals("*")) {
            res = x*y;
        }else if(op.equals("/")) {
            res = x/y;
        }
        
        valStk.push(res);
    }
}
