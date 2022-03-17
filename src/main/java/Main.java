import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
/**
 * @author Анастасия
 * This my Main class.
 **/
public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to the calculator!\n" +
                "The calculator supports addition (+), subtraction(-), multiplication(*), division(/), unary minus (-) and parentheses operations.\n");
        //System.out.println("If you want to exit the calculator, then enter an empty line.");
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();
        Analyser pars;
        while (exp != "") {
            pars = new Analyser(exp);
            try {
                pars.analyzer();
                System.out.println("Answer: " + pars.toString());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            exp = sc.nextLine();
        }
    }
}