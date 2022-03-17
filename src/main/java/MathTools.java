
import java.util.Stack;
/**
 * This class needs to  calculate mathematical expressions.
 **/
public class MathTools {
    /**
     * A method that performs basic operations.
     * @throws Exception Triggers a warning if needed.
     * "Division by zero" - If the divisor is 0;
     * "There are not enough brackets!" - If there are parenthesis operations left in the stack,
     * which means that a string was submitted at the input, in which there are not enough
     * parentheses.
     */
    public void mathOp(Stack<Character> Operations, Stack<Double>Number) throws Exception {
        char op = Operations.pop();
        if (op == '+') {
            Number.push(Number.pop()+Number.pop());
        }
        if (op == '-') {
            Number.push(-Number.pop()+Number.pop());
        }
        if (op == '*') {
            Number.push(Number.pop()*Number.pop());
        }
        if (op == '/') {
            if (Number.peek() == 0) {
                throw new Exception("Division by zero");
            }
            Number.push(Math.pow(Number.pop(), -1) * Number.pop());
        }
        if (op == '(' || op == ')') {
            throw new Exception("There are not enough brackets!");
        }
    }


}
