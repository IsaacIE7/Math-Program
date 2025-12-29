import calculus.*;
import expressions.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import utils.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Expression currentExpr = null;
    private static String currentExprString = "";

    public static void main(String[] args) {
        Map<String, Double> map = Map.of(
            "x", 1.0,
            "y", 2.0,
            "z", 5.0,
            "t", 3.0
        );
        System.out.println(Parser.parse("x + 2y + z + 3t").simplify());
        System.out.println((float) Parser.parse("x + 2y + z + 3t").evaluate(map));

        System.out.println( "" + Parser.parse("-x").simplify());
        System.out.println( "" + Parser.parse("1(x+1)").simplify());
        System.out.println( "" + Parser.parse("-5 * 3").simplify());



        // System.out.println("╔════════════════════════════════════════════════════════════════╗");
        // System.out.println("║          WELCOME TO THE MATH EXPRESSION CALCULATOR             ║");
        // System.out.println("╚════════════════════════════════════════════════════════════════╝");
        // System.out.println();
        
        // mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(65));
            System.out.println("MAIN MENU");
            System.out.println("=".repeat(65));
            if (currentExpr != null) {
                System.out.println("Current Expression: f(x) = " + currentExprString);
            } else {
                System.out.println("Current Expression: None");
            }
            System.out.println();
            System.out.println("1. Enter new expression");
            System.out.println("2. Evaluate at a point");
            System.out.println("3. Find derivative");
            System.out.println("4. Simplify expression");
            System.out.println("5. Find roots (Newton's Method)");
            System.out.println("6. Find all roots in interval");
            System.out.println("7. Taylor series expansion");
            System.out.println("8. View expression tree");
            System.out.println("9. Expression calculator (quick mode)");
            System.out.println("10. Run test suite");
            System.out.println("11. Help & Examples");
            System.out.println("0. Exit");
            System.out.println("=".repeat(65));
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    enterExpression();
                    break;
                case "2":
                    evaluateExpression();
                    break;
                case "3":
                    findDerivative();
                    break;
                case "4":
                    simplifyExpression();
                    break;
                case "5":
                    findSingleRoot();
                    break;
                case "6":
                    findAllRoots();
                    break;
                case "7":
                    taylorSeries();
                    break;
                case "8":
                    viewExpressionTree();
                    break;
                case "9":
                    quickCalculator();
                    break;
                case "10":
                    runTests();
                    break;
                case "11":
                    showHelp();
                    break;
                case "0":
                    System.out.println("\nThank you for using the Math Expression Calculator!");
                    System.out.println("Goodbye! 👋");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n❌ Invalid option. Please try again.");
            }
        }
    }

    public static void enterExpression() {
        System.out.println("\n" + "-".repeat(65));
        System.out.println("ENTER NEW EXPRESSION");
        System.out.println("-".repeat(65));
        System.out.println("Enter a mathematical expression using:");
        System.out.println("  • Variables: x");
        System.out.println("  • Operations: +, -, *, /, ^");
        System.out.println("  • Functions: sin, cos, tan, arcsin, arccos, arctan, ln, sqrt, exp");
        System.out.println("  • Constants: pi, e");
        System.out.println("  • Example: x^2 + 3*x - 5");
        System.out.println();
        System.out.print("f(x) = ");
        
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("❌ No expression entered.");
            return;
        }

        try {
            currentExpr = Parser.parse(input);
            currentExprString = input;
            System.out.println("\n✓ Expression successfully parsed!");
            System.out.println("f(x) = " + currentExpr);
        } catch (Exception e) {
            System.out.println("\n❌ Error parsing expression: " + e.getMessage());
            currentExpr = null;
            currentExprString = "";
        }
    }

    public static void evaluateExpression() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("EVALUATE EXPRESSION");
        System.out.println("-".repeat(65));
        System.out.println("Current: f(x) = " + currentExprString);
        System.out.print("\nEnter value for x: ");
        
        try {
            double x = Double.parseDouble(scanner.nextLine().trim());
            double result = currentExpr.evaluate(x);
            
            System.out.println("\n✓ Result:");
            System.out.println("  f(" + x + ") = " + result);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid number format.");
        } catch (Exception e) {
            System.out.println("\n❌ Error evaluating: " + e.getMessage());
        }
    }

    public static void findDerivative() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("FIND DERIVATIVE");
        System.out.println("-".repeat(65));
        System.out.println("Current: f(x) = " + currentExprString);
        
        try {
            Expression derivative = currentExpr.sDerivative();
            Expression simplified = derivative.simplify();
            
            System.out.println("\n✓ Derivative:");
            System.out.println("  f'(x) = " + derivative);
            System.out.println("\n  Simplified: f'(x) = " + simplified);
            
            System.out.print("\nEvaluate derivative at a point? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                System.out.print("Enter value for x: ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                double result = simplified.evaluate(x);
                System.out.println("  f'(" + x + ") = " + result);
            }
            
            System.out.print("\nSave derivative as current expression? (y/n): ");
            choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                currentExpr = simplified;
                currentExprString = simplified.toString();
                System.out.println("✓ Derivative saved as current expression.");
            }
        } catch (Exception e) {
            System.out.println("\n❌ Error finding derivative: " + e.getMessage());
        }
    }

    public static void simplifyExpression() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("SIMPLIFY EXPRESSION");
        System.out.println("-".repeat(65));
        System.out.println("Original: f(x) = " + currentExprString);
        
        try {
            Expression simplified = currentExpr.simplify();
            
            System.out.println("\n✓ Simplified:");
            System.out.println("  f(x) = " + simplified);
            
            System.out.print("\nUpdate current expression? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                currentExpr = simplified;
                currentExprString = simplified.toString();
                System.out.println("✓ Expression updated.");
            }
        } catch (Exception e) {
            System.out.println("\n❌ Error simplifying: " + e.getMessage());
        }
    }

    public static void findSingleRoot() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("FIND ROOT (NEWTON'S METHOD)");
        System.out.println("-".repeat(65));
        System.out.println("Current: f(x) = " + currentExprString);
        System.out.println("\nNewton's Method finds a root near an initial guess.");
        System.out.print("\nEnter initial guess: ");
        
        try {
            double guess = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Max iterations (press Enter for 100): ");
            String iterStr = scanner.nextLine().trim();
            int maxIter = iterStr.isEmpty() ? 100 : Integer.parseInt(iterStr);
            
            double root = Calc.newtonsMethod(currentExprString, guess, maxIter);
            double fValue = currentExpr.evaluate(root);
            
            System.out.println("\n✓ Root found!");
            System.out.println("  x = " + root);
            System.out.println("  f(" + root + ") = " + fValue);
            
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid number format.");
        } catch (ArithmeticException e) {
            System.out.println("\n❌ Newton's method failed: " + e.getMessage());
            System.out.println("  Try a different initial guess.");
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public static void findAllRoots() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("FIND ALL ROOTS IN INTERVAL");
        System.out.println("-".repeat(65));
        System.out.println("Current: f(x) = " + currentExprString);
        System.out.println("\nFinds all roots in a specified interval.");
        
        try {
            System.out.print("\nEnter lower bound: ");
            double lower = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Enter upper bound: ");
            double upper = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Number of test points (press Enter for 100): ");
            String testStr = scanner.nextLine().trim();
            int tests = testStr.isEmpty() ? 100 : Integer.parseInt(testStr);
            
            System.out.println("\n🔍 Searching for roots...");
            
            ArrayList<Double> roots = Calc.rootFinder(currentExprString, lower, upper, tests);
            
            if (roots.isEmpty()) {
                System.out.println("\n✓ No roots found in [" + lower + ", " + upper + "]");
            } else {
                System.out.println("\n✓ Found " + roots.size() + " root(s):");
                for (int i = 0; i < roots.size(); i++) {
                    double root = roots.get(i);
                    double fValue = currentExpr.evaluate(root);
                    System.out.printf("  %d. x = %.10f, f(x) = %.2e%n", i+1, root, fValue);
                }
            }
            
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid number format.");
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public static void taylorSeries() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("TAYLOR SERIES EXPANSION");
        System.out.println("-".repeat(65));
        System.out.println("Current: f(x) = " + currentExprString);
        System.out.println("\nTaylor series approximates f(x) around a point c.");
        
        try {
            System.out.print("\nExpand around point c (press Enter for 0): ");
            String cStr = scanner.nextLine().trim();
            double c = cStr.isEmpty() ? 0.0 : Double.parseDouble(cStr);
            
            System.out.print("Number of terms (press Enter for 5): ");
            String termsStr = scanner.nextLine().trim();
            int terms = termsStr.isEmpty() ? 5 : Integer.parseInt(termsStr);
            
            Expression taylor = Calc.taylorSeries(currentExpr, c, terms);
            Expression simplified = taylor.simplify();
            
            System.out.println("\n✓ Taylor series (first " + terms + " terms around x = " + c + "):");
            System.out.println("  P(x) = " + simplified);
            
            System.out.print("\nTest approximation at a point? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                System.out.print("Enter x value: ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                
                double actual = currentExpr.evaluate(x);
                double approx = simplified.evaluate(x);
                double error = Math.abs(actual - approx);
                
                System.out.println("\n  Actual f(" + x + ") = " + actual);
                System.out.println("  Taylor P(" + x + ") = " + approx);
                System.out.println("  Error = " + error);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid number format.");
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public static void viewExpressionTree() {
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded. Please enter an expression first (option 1).");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.println("EXPRESSION STRUCTURE");
        System.out.println("-".repeat(65));
        System.out.println("Expression: f(x) = " + currentExprString);
        System.out.println("\nString representation:");
        System.out.println("  " + currentExpr.toString());
        System.out.println("\nClass type: " + currentExpr.getClass().getSimpleName());
    }

    public static void quickCalculator() {
        System.out.println("\n" + "-".repeat(65));
        System.out.println("QUICK CALCULATOR");
        System.out.println("-".repeat(65));
        System.out.println("Enter expressions to quickly evaluate them.");
        System.out.println("Type 'back' to return to main menu.");
        
        while (true) {
            System.out.print("\nf(x) = ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            
            if (input.isEmpty()) {
                continue;
            }
            
            try {
                Expression expr = Parser.parse(input);
                System.out.println("Parsed: " + expr);
                
                System.out.print("Evaluate at x = ");
                String xStr = scanner.nextLine().trim();
                
                if (xStr.isEmpty()) {
                    continue;
                }
                
                double x = Double.parseDouble(xStr);
                double result = expr.evaluate(x);
                
                System.out.println("✓ f(" + x + ") = " + result);
                
                Expression deriv = expr.sDerivative().simplify();
                System.out.println("  f'(x) = " + deriv);
                System.out.println("  f'(" + x + ") = " + deriv.evaluate(x));
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    public static void runTests() {
        System.out.println("\n" + "-".repeat(65));
        System.out.println("TEST SUITE");
        System.out.println("-".repeat(65));
        System.out.println("Run the comprehensive test suite?");
        System.out.print("This will run 40+ tests (y/n): ");
        
        String choice = scanner.nextLine().trim().toLowerCase();
        
        if (choice.equals("y") || choice.equals("yes")) {
            System.out.println();
            // Call your test method from Main if it exists
            // Main.runRootFinderTests();
            System.out.println("Note: Add 'Main.runRootFinderTests();' here if you have it in Main class");
        }
    }

    public static void showHelp() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("HELP & EXAMPLES");
        System.out.println("=".repeat(65));
        
        System.out.println("\n📚 SUPPORTED OPERATIONS:");
        System.out.println("  Addition:       x + 5");
        System.out.println("  Subtraction:    x - 3");
        System.out.println("  Multiplication: 2*x  or  x*3");
        System.out.println("  Division:       x/2");
        System.out.println("  Exponentiation: x^2  or  x^3");
        
        System.out.println("\n📐 FUNCTIONS:");
        System.out.println("  Trigonometric:  sin(x), cos(x), tan(x)");
        System.out.println("  Inverse Trig:   arcsin(x), arccos(x), arctan(x)");
        System.out.println("  Logarithm:      ln(x)");
        System.out.println("  Exponential:    exp(x)  [e^x]");
        System.out.println("  Square Root:    sqrt(x)");
        
        System.out.println("\n🔢 CONSTANTS:");
        System.out.println("  pi  = 3.14159...");
        System.out.println("  e   = 2.71828...");
        
        System.out.println("\n💡 EXAMPLE EXPRESSIONS:");
        System.out.println("  Quadratic:      x^2 - 4");
        System.out.println("  Cubic:          x^3 - 3*x + 1");
        System.out.println("  Trigonometric:  sin(x^2)");
        System.out.println("  Exponential:    exp(x) - 2");
        System.out.println("  Logarithmic:    ln(x) - 1");
        System.out.println("  Complex:        x^2 + sin(x) - cos(x)");
        
        System.out.println("\n⚡ TIPS:");
        System.out.println("  • Use parentheses for clarity: (x+1)*(x-1)");
        System.out.println("  • Exponents must be integers: x^2 works, x^2.5 doesn't");
        System.out.println("  • Functions need parentheses: sin(x) not sinx");
        
        System.out.println("\n" + "-".repeat(65));
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}