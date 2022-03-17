import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
/**
 * This class is designed to analyze and calculate mathematical expressions.
**/
public class Analyser {
    /** Stack for numbers from an expression. */
    private Stack<Double> Number;

    /** Stack for operations from an expression. */
    private Stack<Character> Operations;

    /** Input string. */
    public String exp;

    /** The current element. */
    private int index;

    /** Flag for unary minus or plus. */
    private boolean flagOfUnary;

    /** Flag to check  current element**/
    private boolean isNumber;

    /** The result.*/
    public double result;

    MathTools makeOperations;

    /** Constructor of the class.
     * @param exp is a mathematical expression*/
    public Analyser(String exp) {
        this.exp = exp;
        Number = new Stack<>();
        Operations = new Stack<>();
        flagOfUnary = true;
        isNumber = false;
        index = 0;
        makeOperations=new MathTools();
    }

    /**
     * The main method designed for analyzing a mathematical expression.
     *
     * @throws Exception Triggers a warning if needed.
     * "Incorrect location of operations!" - If there are several operations in a row (for example, "++", "+-", "+/", "(/" and so on)
     * "The opening parenthesis is missing!" - If there is no opening parenthesis in the expression for some closing parenthesis.
     * "Invalid character(s)" - If there are signs in the expression, they are not defined in the method.
     */
    public void analyzer() throws Exception {

        while (index != exp.length()) {
            char curItem = exp.charAt(index);
            if (curItem >= '0' && curItem <= '9' )
            {
                Number.push(numberPut());
                flagOfUnary = false;
                isNumber = true;
            }
            else if (curItem == '+' || curItem == '-'||curItem == '*' || curItem == '/') {
                if (isNumber == false&&flagOfUnary==false) {
                    throw new Exception("Incorrect location of operations!");
                }
                else if((curItem == '+' || curItem == '-')&&flagOfUnary==true){
                    Number.push(0.0);
                    flagOfUnary = false;
                    isNumber=true;
                }
                else {
                    if((curItem == '+' || curItem == '-')&&flagOfUnary==false) {
                        sumOrMinus();
                    }
                    else if (curItem == '*' || curItem == '/'){
                        multOrDiv();
                    }
                    Operations.push(exp.charAt(index));
                    index++;
                    isNumber = false;
                }
            }

            else if (curItem == '(') {
                if (isNumber == true && index != 0) {
                    throw new Exception("Incorrect location of operations!");
                }
                Operations.push(curItem);
                flagOfUnary = true;
                index++;
                isNumber = false;
            }
            else if (curItem == ')') {
                if (!isNumber) {
                    throw new Exception("Incorrect location of operations!");
                }
                if (!Operations.contains('(')) {
                    throw new Exception("The opening parenthesis is missing!");
                }
                while (Operations.peek() != '(') {
                    makeOperations.mathOp(Operations,Number);
                }
                Operations.pop();
                if (!Operations.isEmpty()) {
                    if (Operations.peek() == '-') {
                        Operations.pop();
                        Operations.push('+');
                        Number.push(Number.pop()*(-1));
                    }
                }
                index++;
                flagOfUnary = false;//
                isNumber = true;
            }
            else if (curItem == ' ') {
                index++;
            }
            else {
                throw new Exception("Invalid character(s)");
            }
        }
        while(!Operations.isEmpty()) {
            makeOperations.mathOp(Operations,Number);
        }
        result = Number.pop();
    }

    /**
     * Gathered number from a mathematical expression.
     * @return The isolated number
     * @throws Exception
     */
    private double numberPut() throws Exception {
        String number = "";
        while (index != exp.length() && ((exp.charAt(index) >= '0' && exp.charAt(index) <= '9')||exp.charAt(index) == ' ')) {
            while(exp.charAt(index) == ' ' && index != exp.length()) index++;
            number += exp.charAt(index++);
        }
        return Integer.parseInt(number);
    }


    /**
     * A method that is called when an addition or subtraction sign is found in a string.
     * @throws Exception
     */
    public void sumOrMinus() throws Exception {
        boolean isRight = true;
        while (!Operations.isEmpty() && isRight == true) {
            if (Operations.peek() != '(' && Operations.peek() != ')') {
                makeOperations.mathOp(Operations, Number);
            }
            else {
                isRight = false;
            }
        }
    }

    /**
     * A method that is called when an multiplication or division sign is found in a string.
     * @throws Exception
     */
    public void multOrDiv() throws Exception {
        boolean isRight = true;
        while (!Operations.isEmpty() && isRight == true) {
            if (Operations.peek() == '*' || Operations.peek() == '/') {
                makeOperations.mathOp(Operations,Number);
            }
            else {
                isRight = false;
            }
        }
    }

    /**
     * Overriding the method of returning the result as a string.
     * @return The result of the calculator is in the form of a string.
     */
    @Override
    public String toString() {
        return String.valueOf(result);
    }

}