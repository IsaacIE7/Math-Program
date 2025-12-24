import calculus.*;
import expressions.Expression;
import java.util.Scanner;
import utils.*;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("" + Calc.newtonsMethod("x^2 - 1", 2, 500));
        System.out.println(Parser.parse("x + x + x").simplify());
        System.out.println((Parser.parse("1/x")).sDerivative().evaluate(2));

        System.out.println("=== Math Calculator ===");
        System.out.println("Commands: 'exit' to quit, 'help' for examples");
        System.out.println();
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            if (input.equals("help")) {
                printHelp();
                continue;
            }
            
            if (input.isEmpty()) {
                continue;
            }
            
            try {
                Expression expr = Parser.parse(input);
                System.out.println("f(x) = " + expr);
                
                Expression deriv = expr.sDerivative().simplify();
                System.out.println("f'(x) = " + deriv);
                
                System.out.println("f(1) = " + expr.evaluate(1.0));
                System.out.println();
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }
        
        scanner.close();
    }

    public static void printHelp() {
        System.out.println("\nExamples:");
        System.out.println("  x^2 + 3*x + 1");
        System.out.println("  sin(x^2)");
        System.out.println("  sqrt(x) + ln(x)");
        System.out.println("  cos(pi*x)");
        System.out.println();
    }
}