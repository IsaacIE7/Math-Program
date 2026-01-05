import calculus.*;
import expressions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import linalg.*;
import utils.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Expression currentExpr = null;
    private static String currentExprString = "";
    private static Map<String, Expression> savedExpressions = new HashMap<>();
    private static Map<String, Vector> savedVectors = new HashMap<>();
    private static Map<String, Matrix> savedMatrices = new HashMap<>();
    private static Map<String, VectorField> savedVectorFields = new HashMap<>();

    public static void main(String[] args) {
        clearScreen();
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║       ULTIMATE MATHEMATICAL EXPRESSION ENGINE - CLI               ║");
        System.out.println("║                     All Features Edition                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        mainMenu();
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print newlines
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void mainMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + "═".repeat(70));
            System.out.println("MAIN MENU");
            System.out.println("═".repeat(70));
            if (currentExpr != null) {
                System.out.println("Current Expression: f(x) = " + currentExprString);
            } else {
                System.out.println("Current Expression: None");
            }
            System.out.println();
            System.out.println("┌─ SINGLE VARIABLE CALCULUS ─────────────────────────────────────┐");
            System.out.println("│ 1.  Enter/Parse Expression                                     │");
            System.out.println("│ 2.  Evaluate Expression                                        │");
            System.out.println("│ 3.  Find Derivative                                            │");
            System.out.println("│ 4.  Higher Order Derivatives                                   │");
            System.out.println("│ 5.  Simplify Expression                                        │");
            System.out.println("│ 6.  Taylor Series Expansion                                    │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("┌─ NUMERICAL METHODS ────────────────────────────────────────────┐");
            System.out.println("│ 7.  Find Single Root (Newton's Method)                         │");
            System.out.println("│ 8.  Find All Roots in Interval                                 │");
            System.out.println("│ 9.  Find Critical Points                                       │");
            System.out.println("│ 10. Find Inflection Points                                     │");
            System.out.println("│ 11. Classify Extrema (Max/Min/Inflection)                      │");
            System.out.println("│ 12. Numerical Integration                                      │");
            System.out.println("│ 13. Numerical Derivative                                       │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("┌─ MULTIVARIABLE CALCULUS ───────────────────────────────────────┐");
            System.out.println("│ 14. Partial Derivatives                                        │");
            System.out.println("│ 15. Gradient (Symbolic)                                        │");
            System.out.println("│ 16. Gradient at Point                                          │");
            System.out.println("│ 17. Directional Derivative                                     │");
            System.out.println("│ 18. Mixed Partial Derivatives                                  │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("┌─ VECTOR CALCULUS ──────────────────────────────────────────────┐");
            System.out.println("│ 19. Create Vector Field                                        │");
            System.out.println("│ 20. Divergence                                                 │");
            System.out.println("│ 21. Curl (2D)                                                  │");
            System.out.println("│ 22. Curl (3D)                                                  │");
            System.out.println("│ 23. View Saved Vector Fields                                   │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("┌─ LINEAR ALGEBRA ───────────────────────────────────────────────┐");
            System.out.println("│ 24. Vector Operations                                          │");
            System.out.println("│ 25. Matrix Operations                                          │");
            System.out.println("│ 26. View Saved Vectors/Matrices                                │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("┌─ UTILITIES ────────────────────────────────────────────────────┐");
            System.out.println("│ 27. Expression Manager (Save/Load/Delete)                      │");
            System.out.println("│ 28. Quick Calculator                                           │");
            System.out.println("│ 29. Batch Evaluation                                           │");
            System.out.println("│ 30. View Expression Tree                                       │");
            System.out.println("│ 31. Run Test Suites                                            │");
            System.out.println("│ 32. Help & Examples                                            │");
            System.out.println("│ 0.  Exit                                                       │");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
            System.out.println("═".repeat(70));
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": enterExpression(); break;
                case "2": evaluateExpression(); break;
                case "3": findDerivative(); break;
                case "4": higherOrderDerivatives(); break;
                case "5": simplifyExpression(); break;
                case "6": taylorSeries(); break;
                case "7": findSingleRoot(); break;
                case "8": findAllRoots(); break;
                case "9": findCriticalPoints(); break;
                case "10": findInflectionPoints(); break;
                case "11": classifyExtrema(); break;
                case "12": numericalIntegration(); break;
                case "13": numericalDerivative(); break;
                case "14": partialDerivatives(); break;
                case "15": gradientSymbolic(); break;
                case "16": gradientAtPoint(); break;
                case "17": directionalDerivative(); break;
                case "18": mixedPartials(); break;
                case "19": createVectorField(); break;
                case "20": divergence(); break;
                case "21": curl2D(); break;
                case "22": curl3D(); break;
                case "23": viewVectorFields(); break;
                case "24": vectorOperations(); break;
                case "25": matrixOperations(); break;
                case "26": viewLinearAlgebra(); break;
                case "27": expressionManager(); break;
                case "28": quickCalculator(); break;
                case "29": batchEvaluation(); break;
                case "30": viewExpressionTree(); break;
                case "31": runTests(); break;
                case "32": showHelp(); break;
                case "0":
                    System.out.println("\nThank you for using the Math Expression Engine!");
                    System.out.println("Goodbye! 👋");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n❌ Invalid option. Please try again.");
            }
        }
    }

    public static void enterExpression() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("ENTER NEW EXPRESSION");
        System.out.println("─".repeat(70));
        System.out.println("Variables: x, y, z, a-w | Operations: +, -, *, /, ^");
        System.out.println("Functions: sin, cos, tan, arcsin, arccos, arctan, ln, sqrt, exp");
        System.out.println("Constants: pi, e");
        System.out.println("\nType 'back' to return to menu");
        System.out.print("\nf(x) = ");
        
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("back")) return;
        if (input.isEmpty()) {
            System.out.println("❌ No expression entered.");
            pauseBeforeMenu();
            return;
        }

        try {
            currentExpr = Parser.parse(input);
            currentExprString = input;
            System.out.println("\n✓ Parsed: " + currentExpr);
            
            System.out.print("\nSave? (y/n): ");
            String save = scanner.nextLine().trim();
            if (save.equalsIgnoreCase("y") || save.equalsIgnoreCase("yes")) {
                System.out.print("Name: ");
                savedExpressions.put(scanner.nextLine().trim(), currentExpr);
                System.out.println("✓ Saved");
            }
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void pauseBeforeMenu() {
        System.out.print("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    public static void evaluateExpression() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("EVALUATE: " + currentExprString);
        System.out.println("\nType 'back' to return to menu");
        System.out.print("\nx = ");
        
        try {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("back")) return;
            double x = Double.parseDouble(input);
            System.out.println("✓ f(" + x + ") = " + currentExpr.evaluate(x));
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void findDerivative() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("DERIVATIVE: " + currentExprString);
        
        try {
            Expression deriv = currentExpr.sDerivative().simplify();
            System.out.println("✓ f'(x) = " + deriv);
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("x = ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                System.out.println("f'(" + x + ") = " + deriv.evaluate(x));
            }
            
            System.out.print("\nSave as current? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                currentExpr = deriv;
                currentExprString = deriv.toString();
                System.out.println("✓ Saved");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void higherOrderDerivatives() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("HIGHER ORDER DERIVATIVES");
        System.out.print("Order (1-10): ");
        
        try {
            int order = Integer.parseInt(scanner.nextLine().trim());
            Expression deriv = currentExpr;
            for (int i = 0; i < order; i++) deriv = deriv.sDerivative();
            deriv = deriv.simplify();
            
            System.out.println("✓ f^(" + order + ")(x) = " + deriv);
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("x = ");
                System.out.println("f^(" + order + ")(" + scanner.nextLine().trim() + ") = " + deriv.evaluate(Double.parseDouble(scanner.nextLine().trim())));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void simplifyExpression() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("Original: " + currentExprString);
        Expression simplified = currentExpr.simplify();
        System.out.println("✓ Simplified: " + simplified);
        
        System.out.print("\nUpdate? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            currentExpr = simplified;
            currentExprString = simplified.toString();
        }
        pauseBeforeMenu();
    }

    public static void taylorSeries() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("TAYLOR SERIES: " + currentExprString);
        
        try {
            System.out.print("Center c (Enter=0): ");
            String cStr = scanner.nextLine().trim();
            double c = cStr.isEmpty() ? 0.0 : Double.parseDouble(cStr);
            
            System.out.print("Terms (Enter=5): ");
            String tStr = scanner.nextLine().trim();
            int terms = tStr.isEmpty() ? 5 : Integer.parseInt(tStr);
            
            Expression taylor = Calc.taylorSeries(currentExpr, c, terms).simplify();
            System.out.println("✓ P(x) = " + taylor);
            
            System.out.print("\nTest at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("x = ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                System.out.println("Actual: " + currentExpr.evaluate(x));
                System.out.println("Approx: " + taylor.evaluate(x));
                System.out.println("Error: " + Math.abs(currentExpr.evaluate(x) - taylor.evaluate(x)));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void findSingleRoot() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("NEWTON'S METHOD: " + currentExprString);
        
        try {
            System.out.print("Initial guess: ");
            double guess = Double.parseDouble(scanner.nextLine().trim());
            
            double root = Calc.newtonsMethod(currentExprString, guess, 100);
            System.out.println("✓ Root: x = " + root);
            System.out.println("  f(x) = " + currentExpr.evaluate(root));
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void findAllRoots() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("FIND ALL ROOTS: " + currentExprString);
        
        try {
            System.out.print("Lower bound: ");
            double lower = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Upper bound: ");
            double upper = Double.parseDouble(scanner.nextLine().trim());
            
            ArrayList<Double> roots = Calc.rootFinder(currentExprString, lower, upper, 100);
            
            if (roots.isEmpty()) {
                System.out.println("✓ No roots found");
            } else {
                System.out.println("✓ Found " + roots.size() + " root(s):");
                for (int i = 0; i < roots.size(); i++) {
                    System.out.printf("  %d. x = %.8f%n", i+1, roots.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void findCriticalPoints() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("CRITICAL POINTS: " + currentExprString);
        
        try {
            System.out.print("Lower: ");
            double lower = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Upper: ");
            double upper = Double.parseDouble(scanner.nextLine().trim());
            
            ArrayList<Double> crit = Calc.findCriticalPoints(currentExprString, lower, upper);
            
            if (crit.isEmpty()) {
                System.out.println("✓ None found");
            } else {
                System.out.println("✓ Critical points:");
                for (Double cp : crit) {
                    System.out.printf("  x = %.8f, f(x) = %.6f%n", cp, currentExpr.evaluate(cp));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void findInflectionPoints() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("INFLECTION POINTS: " + currentExprString);
        
        try {
            System.out.print("Lower: ");
            double lower = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Upper: ");
            double upper = Double.parseDouble(scanner.nextLine().trim());
            
            ArrayList<Double> inf = Calc.findInflectionPoints(currentExprString, lower, upper);
            
            if (inf.isEmpty()) {
                System.out.println("✓ None found");
            } else {
                System.out.println("✓ Inflection points:");
                for (Double ip : inf) {
                    System.out.printf("  x = %.8f, f(x) = %.6f%n", ip, currentExpr.evaluate(ip));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void classifyExtrema() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("CLASSIFY EXTREMA: " + currentExprString);
        
        try {
            System.out.print("Lower: ");
            double lower = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Upper: ");
            double upper = Double.parseDouble(scanner.nextLine().trim());
            
            ArrayList<ExtremaInfo> extrema = Calc.classifyCritPoints(currentExprString, lower, upper);
            
            if (extrema.isEmpty()) {
                System.out.println("✓ None found");
            } else {
                System.out.println("✓ Extrema:");
                for (ExtremaInfo info : extrema) {
                    System.out.println("  " + info);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void numericalIntegration() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("NUMERICAL INTEGRATION: " + currentExprString);
        
        try {
            Function1D f = x -> currentExpr.evaluate(x);
            Function1D integral = Calc.integral(f);
            
            System.out.print("Lower bound: ");
            double a = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Upper bound: ");
            double b = Double.parseDouble(scanner.nextLine().trim());
            
            double result = integral.apply(b) - integral.apply(a);
            System.out.println("✓ ∫[" + a + " to " + b + "] = " + result);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void numericalDerivative() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("NUMERICAL DERIVATIVE: " + currentExprString);
        
        try {
            Function1D f = x -> currentExpr.evaluate(x);
            Function1D deriv = Calc.derivative(f);
            
            System.out.print("x = ");
            double x = Double.parseDouble(scanner.nextLine().trim());
            
            double numDeriv = deriv.apply(x);
            double symbDeriv = currentExpr.sDerivative().evaluate(x);
            
            System.out.println("✓ Numerical: " + numDeriv);
            System.out.println("  Symbolic:  " + symbDeriv);
            System.out.println("  Diff:      " + Math.abs(numDeriv - symbDeriv));
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void partialDerivatives() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("PARTIAL DERIVATIVES");
        System.out.println("\nType 'back' at any prompt to return to menu");
        System.out.print("\nFunction: ");
        String func = scanner.nextLine().trim();
        if (func.equalsIgnoreCase("back")) return;
        
        System.out.print("Variables (space-separated): ");
        String varsInput = scanner.nextLine().trim();
        if (varsInput.equalsIgnoreCase("back")) return;
        String[] vars = varsInput.split("\\s+");
        
        try {
            Expression f = Parser.parse(func);
            System.out.println("\n✓ Partials:");
            for (String var : vars) {
                Expression partial = f.sPartialDerivative(var).simplify();
                System.out.println("  ∂f/∂" + var + " = " + partial);
            }
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Map<String, Double> point = new HashMap<>();
                for (String var : vars) {
                    System.out.print(var + " = ");
                    point.put(var, Double.parseDouble(scanner.nextLine().trim()));
                }
                System.out.println("\nAt " + point + ":");
                for (String var : vars) {
                    System.out.println("  ∂f/∂" + var + " = " + f.sPartialDerivative(var).evaluate(point));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void gradientSymbolic() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("GRADIENT (SYMBOLIC)");
        System.out.println("\nType 'back' at any prompt to return to menu");
        System.out.print("\nFunction: ");
        String func = scanner.nextLine().trim();
        if (func.equalsIgnoreCase("back")) return;
        
        System.out.print("Variables: ");
        String varsInput = scanner.nextLine().trim();
        if (varsInput.equalsIgnoreCase("back")) return;
        String[] vars = varsInput.split("\\s+");
        
        try {
            Expression[] grad = MultiVarCalc.gradient(func, vars);
            System.out.println("\n✓ ∇f = (");
            for (int i = 0; i < grad.length; i++) {
                System.out.println("  " + grad[i] + (i < grad.length-1 ? "," : ""));
            }
            System.out.println(")");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void gradientAtPoint() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("GRADIENT AT POINT");
        System.out.println("\nType 'back' at any prompt to return to menu");
        System.out.print("\nFunction: ");
        String func = scanner.nextLine().trim();
        if (func.equalsIgnoreCase("back")) return;
        
        System.out.print("Variables: ");
        String varsInput = scanner.nextLine().trim();
        if (varsInput.equalsIgnoreCase("back")) return;
        String[] vars = varsInput.split("\\s+");
        
        try {
            Map<String, Double> point = new HashMap<>();
            System.out.println("Point:");
            for (String var : vars) {
                System.out.print(var + " = ");
                point.put(var, Double.parseDouble(scanner.nextLine().trim()));
            }
            
            Vector grad = MultiVarCalc.gradientAt(point, func, vars);
            System.out.println("\n✓ ∇f at " + point + " = " + grad);
            System.out.println("  Magnitude: " + grad.magnitude());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void directionalDerivative() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("DIRECTIONAL DERIVATIVE");
        System.out.println("\nType 'back' at any prompt to return to menu");
        System.out.print("\nFunction: ");
        String func = scanner.nextLine().trim();
        if (func.equalsIgnoreCase("back")) return;
        
        System.out.print("Variables: ");
        String varsInput = scanner.nextLine().trim();
        if (varsInput.equalsIgnoreCase("back")) return;
        String[] vars = varsInput.split("\\s+");
        
        try {
            Map<String, Double> point = new HashMap<>();
            System.out.println("Point:");
            for (String var : vars) {
                System.out.print(var + " = ");
                point.put(var, Double.parseDouble(scanner.nextLine().trim()));
            }
            
            System.out.println("Direction vector:");
            double[] dirComps = new double[vars.length];
            for (int i = 0; i < vars.length; i++) {
                System.out.print("Component " + (i+1) + ": ");
                dirComps[i] = Double.parseDouble(scanner.nextLine().trim());
            }
            Vector direction = new Vector(dirComps);
            
            double result = MultiVarCalc.directionalDerivative(func, direction, point, vars);
            System.out.println("\n✓ D_v f = " + result);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void mixedPartials() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("MIXED PARTIAL DERIVATIVES");
        System.out.println("\nType 'back' at any prompt to return to menu");
        System.out.print("\nFunction: ");
        String func = scanner.nextLine().trim();
        if (func.equalsIgnoreCase("back")) return;
        
        try {
            Expression f = Parser.parse(func);
            System.out.print("First variable: ");
            String var1 = scanner.nextLine().trim();
            System.out.print("Second variable: ");
            String var2 = scanner.nextLine().trim();
            
            Expression fxy = f.sPartialDerivative(var1).sPartialDerivative(var2).simplify();
            Expression fyx = f.sPartialDerivative(var2).sPartialDerivative(var1).simplify();
            
            System.out.println("\n✓ Mixed partials:");
            System.out.println("  ∂²f/∂" + var1 + "∂" + var2 + " = " + fxy);
            System.out.println("  ∂²f/∂" + var2 + "∂" + var1 + " = " + fyx);
            System.out.println("\n  (Equal by Clairaut's theorem)");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void createVectorField() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("CREATE VECTOR FIELD");
        System.out.println("\nType 'back' to return to menu");
        System.out.print("\nDimension (2 or 3): ");
        String dimInput = scanner.nextLine().trim();
        if (dimInput.equalsIgnoreCase("back")) return;
        int dim = Integer.parseInt(dimInput);
        
        try {
            String[] comps = new String[dim];
            String[] vars = dim == 2 ? new String[]{"x", "y"} : new String[]{"x", "y", "z"};
            
            for (int i = 0; i < dim; i++) {
                System.out.print(vars[i] + "-component: ");
                comps[i] = scanner.nextLine().trim();
            }
            
            VectorField vf = new VectorField(comps, vars);
            System.out.println("✓ Created F = (" + String.join(", ", comps) + ")");
            
            System.out.print("\nSave? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("Name: ");
                savedVectorFields.put(scanner.nextLine().trim(), vf);
                System.out.println("✓ Saved");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void divergence() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("DIVERGENCE");
        System.out.println("\nType 'back' to return to menu");
        System.out.print("\nDimension: ");
        String dimInput = scanner.nextLine().trim();
        if (dimInput.equalsIgnoreCase("back")) return;
        int dim = Integer.parseInt(dimInput);
        
        try {
            String[] vars = dim == 2 ? new String[]{"x", "y"} : new String[]{"x", "y", "z"};
            String[] comps = new String[dim];
            
            for (int i = 0; i < dim; i++) {
                System.out.print(vars[i] + "-component: ");
                comps[i] = scanner.nextLine().trim();
            }
            
            Expression div = MultiVarCalc.div(vars, comps);
            System.out.println("\n✓ ∇·F = " + div.simplify());
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Map<String, Double> point = new HashMap<>();
                for (String var : vars) {
                    System.out.print(var + " = ");
                    point.put(var, Double.parseDouble(scanner.nextLine().trim()));
                }
                System.out.println("  ∇·F = " + div.evaluate(point));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void curl2D() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("CURL (2D)");
        System.out.println("\nType 'back' at any prompt to return to menu");
        
        try {
            String[] vars = {"x", "y"};
            System.out.print("\nx-component: ");
            String comp1 = scanner.nextLine().trim();
            if (comp1.equalsIgnoreCase("back")) return;
            
            System.out.print("y-component: ");
            String comp2 = scanner.nextLine().trim();
            if (comp2.equalsIgnoreCase("back")) return;
            
            Expression curl = MultiVarCalc.curl2d(vars, comp1, comp2);
            System.out.println("\n✓ curl F = " + curl.simplify());
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Map<String, Double> point = new HashMap<>();
                System.out.print("x = ");
                point.put("x", Double.parseDouble(scanner.nextLine().trim()));
                System.out.print("y = ");
                point.put("y", Double.parseDouble(scanner.nextLine().trim()));
                System.out.println("  curl F = " + curl.evaluate(point));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void curl3D() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("CURL (3D)");
        System.out.println("\nType 'back' at any prompt to return to menu");
        
        try {
            String[] vars = {"x", "y", "z"};
            System.out.print("\nx-component: ");
            String comp1 = scanner.nextLine().trim();
            if (comp1.equalsIgnoreCase("back")) return;
            
            System.out.print("y-component: ");
            String comp2 = scanner.nextLine().trim();
            if (comp2.equalsIgnoreCase("back")) return;
            
            System.out.print("z-component: ");
            String comp3 = scanner.nextLine().trim();
            if (comp3.equalsIgnoreCase("back")) return;
            
            VectorField vf = new VectorField(new String[]{comp1, comp2, comp3}, vars);
            VectorField curl = vf.curl(vars, comp1, comp2, comp3);
            
            System.out.println("\n✓ curl F = (" + String.join(", ", curl.getComps()) + ")");
            
            System.out.print("\nEvaluate at point? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Map<String, Double> point = new HashMap<>();
                for (String var : vars) {
                    System.out.print(var + " = ");
                    point.put(var, Double.parseDouble(scanner.nextLine().trim()));
                }
                VectorField curlAt = vf.curlAt(vars, point, comp1, comp2, comp3);
                System.out.println("  curl F = (" + String.join(", ", curlAt.getComps()) + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void viewVectorFields() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("SAVED VECTOR FIELDS");
        System.out.println("─".repeat(70));
        
        if (savedVectorFields.isEmpty()) {
            System.out.println("No saved vector fields.");
        } else {
            for (Map.Entry<String, VectorField> entry : savedVectorFields.entrySet()) {
                System.out.println(entry.getKey() + ": (" + String.join(", ", entry.getValue().getComps()) + ")");
            }
        }
        pauseBeforeMenu();
    }

    public static void vectorOperations() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("VECTOR OPERATIONS");
        System.out.println("1. Create  2. Add  3. Scalar multiply");
        System.out.println("4. Dot product  5. Magnitude  6. Normalize");
        System.out.println("\nType '0' or 'back' to return to menu");
        System.out.print("\nChoice: ");
        
        try {
            String choiceInput = scanner.nextLine().trim();
            if (choiceInput.equalsIgnoreCase("back") || choiceInput.equals("0")) return;
            int choice = Integer.parseInt(choiceInput);
            
            switch (choice) {
                case 1:
                    System.out.print("Dimension: ");
                    int dim = Integer.parseInt(scanner.nextLine().trim());
                    double[] comps = new double[dim];
                    for (int i = 0; i < dim; i++) {
                        System.out.print("Component " + (i+1) + ": ");
                        comps[i] = Double.parseDouble(scanner.nextLine().trim());
                    }
                    Vector v = new Vector(comps);
                    System.out.println("✓ " + v);
                    System.out.print("Save? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        System.out.print("Name: ");
                        savedVectors.put(scanner.nextLine().trim(), v);
                    }
                    break;
                    
                case 2:
                    System.out.print("Vector 1 name: ");
                    Vector v1 = savedVectors.get(scanner.nextLine().trim());
                    System.out.print("Vector 2 name: ");
                    Vector v2 = savedVectors.get(scanner.nextLine().trim());
                    if (v1 != null && v2 != null) System.out.println("✓ " + v1.add(v2));
                    break;
                    
                case 3:
                    System.out.print("Vector name: ");
                    Vector v3 = savedVectors.get(scanner.nextLine().trim());
                    System.out.print("Scalar: ");
                    double s = Double.parseDouble(scanner.nextLine().trim());
                    if (v3 != null) System.out.println("✓ " + v3.scalarMultiply(s));
                    break;
                    
                case 4:
                    System.out.print("Vector 1: ");
                    Vector v4 = savedVectors.get(scanner.nextLine().trim());
                    System.out.print("Vector 2: ");
                    Vector v5 = savedVectors.get(scanner.nextLine().trim());
                    if (v4 != null && v5 != null) System.out.println("✓ " + v4.dot(v5));
                    break;
                    
                case 5:
                    System.out.print("Vector name: ");
                    Vector v6 = savedVectors.get(scanner.nextLine().trim());
                    if (v6 != null) System.out.println("✓ " + v6.magnitude());
                    break;
                    
                case 6:
                    System.out.print("Vector name: ");
                    Vector v7 = savedVectors.get(scanner.nextLine().trim());
                    if (v7 != null) System.out.println("✓ " + v7.normalize());
                    break;
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void matrixOperations() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("MATRIX OPERATIONS");
        System.out.println("1. Create  2. Scalar multiply  3. Matrix-vector multiply");
        System.out.println("\nType '0' or 'back' to return to menu");
        System.out.print("\nChoice: ");
        
        try {
            String choiceInput = scanner.nextLine().trim();
            if (choiceInput.equalsIgnoreCase("back") || choiceInput.equals("0")) return;
            int choice = Integer.parseInt(choiceInput);
            
            if (choice == 1) {
                System.out.print("Rows: ");
                int rows = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Columns: ");
                int cols = Integer.parseInt(scanner.nextLine().trim());
                
                double[][] data = new double[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        System.out.print("[" + i + "][" + j + "]: ");
                        data[i][j] = Double.parseDouble(scanner.nextLine().trim());
                    }
                }
                
                Matrix m = new Matrix(data);
                System.out.println("✓ Created:\n" + m);
                System.out.print("Save? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    System.out.print("Name: ");
                    savedMatrices.put(scanner.nextLine().trim(), m);
                }
            } else if (choice == 2) {
                System.out.print("Matrix name: ");
                Matrix m = savedMatrices.get(scanner.nextLine().trim());
                System.out.print("Scalar: ");
                double s = Double.parseDouble(scanner.nextLine().trim());
                if (m != null) System.out.println("✓\n" + m.scalarMultiply(s));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void viewLinearAlgebra() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("SAVED VECTORS & MATRICES");
        System.out.println("─".repeat(70));
        
        System.out.println("\nVectors:");
        if (savedVectors.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Map.Entry<String, Vector> e : savedVectors.entrySet()) {
                System.out.println("  " + e.getKey() + ": " + e.getValue());
            }
        }
        
        System.out.println("\nMatrices:");
        if (savedMatrices.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Map.Entry<String, Matrix> e : savedMatrices.entrySet()) {
                System.out.println("  " + e.getKey() + ":\n" + e.getValue());
            }
        }
        pauseBeforeMenu();
    }

    public static void expressionManager() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("EXPRESSION MANAGER");
        System.out.println("1. View  2. Load  3. Delete");
        System.out.println("\nType '0' or 'back' to return to menu");
        System.out.print("\nChoice: ");
        
        try {
            String choiceInput = scanner.nextLine().trim();
            if (choiceInput.equalsIgnoreCase("back") || choiceInput.equals("0")) return;
            int choice = Integer.parseInt(choiceInput);
            
            if (choice == 1) {
                if (savedExpressions.isEmpty()) {
                    System.out.println("\nNo saved expressions.");
                } else {
                    System.out.println("\nSaved:");
                    for (Map.Entry<String, Expression> e : savedExpressions.entrySet()) {
                        System.out.println("  " + e.getKey() + ": " + e.getValue());
                    }
                }
            } else if (choice == 2) {
                System.out.print("Name: ");
                Expression expr = savedExpressions.get(scanner.nextLine().trim());
                if (expr != null) {
                    currentExpr = expr;
                    currentExprString = expr.toString();
                    System.out.println("✓ Loaded");
                }
            } else if (choice == 3) {
                System.out.print("Name: ");
                if (savedExpressions.remove(scanner.nextLine().trim()) != null) {
                    System.out.println("✓ Deleted");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void quickCalculator() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("QUICK CALCULATOR (type 'back' to exit)");
        
        while (true) {
            System.out.print("\nf(x) = ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("back")) break;
            if (input.isEmpty()) continue;
            
            try {
                Expression expr = Parser.parse(input);
                System.out.print("x = ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                
                System.out.println("✓ f(" + x + ") = " + expr.evaluate(x));
                Expression deriv = expr.sDerivative().simplify();
                System.out.println("  f'(x) = " + deriv);
                System.out.println("  f'(" + x + ") = " + deriv.evaluate(x));
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    public static void batchEvaluation() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("BATCH EVALUATION: " + currentExprString);
        System.out.println("\nType 'back' at any prompt to return to menu");
        
        try {
            System.out.print("\nStart: ");
            String startInput = scanner.nextLine().trim();
            if (startInput.equalsIgnoreCase("back")) return;
            double start = Double.parseDouble(startInput);
            
            System.out.print("End: ");
            String endInput = scanner.nextLine().trim();
            if (endInput.equalsIgnoreCase("back")) return;
            double end = Double.parseDouble(endInput);
            
            System.out.print("Step: ");
            String stepInput = scanner.nextLine().trim();
            if (stepInput.equalsIgnoreCase("back")) return;
            double step = Double.parseDouble(stepInput);
            
            System.out.println("\n  x\t\tf(x)");
            System.out.println("  " + "─".repeat(40));
            for (double x = start; x <= end; x += step) {
                System.out.printf("  %.4f\t\t%.6f%n", x, currentExpr.evaluate(x));
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        pauseBeforeMenu();
    }

    public static void viewExpressionTree() {
        clearScreen();
        if (currentExpr == null) {
            System.out.println("\n❌ No expression loaded.");
            pauseBeforeMenu();
            return;
        }
        System.out.println("\n" + "─".repeat(70));
        System.out.println("EXPRESSION STRUCTURE");
        System.out.println("─".repeat(70));
        System.out.println("Original: " + currentExprString);
        System.out.println("String:   " + currentExpr.toString());
        System.out.println("Class:    " + currentExpr.getClass().getSimpleName());
        System.out.println("Simplified: " + currentExpr.simplify());
        pauseBeforeMenu();
    }

    public static void runTests() {
        clearScreen();
        System.out.println("\n" + "─".repeat(70));
        System.out.println("TEST SUITES");
        System.out.println("1. ComprehensiveSystemTest  2. MultivarTest");
        System.out.println("\nType '0' or 'back' to return to menu");
        System.out.print("\nChoice: ");
        
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("back") || choice.equals("0")) return;
        
        if (choice.equals("1")) {
            ComprehensiveSystemTest.main(new String[]{});
        } else if (choice.equals("2")) {
            MultivarTest.main(new String[]{});
        }
        pauseBeforeMenu();
    }

    public static void showHelp() {
        clearScreen();
        System.out.println("\n" + "═".repeat(70));
        System.out.println("HELP & EXAMPLES");
        System.out.println("═".repeat(70));
        
        System.out.println("\n📚 OPERATIONS: +, -, *, /, ^");
        System.out.println("\n📐 FUNCTIONS:");
        System.out.println("  sin, cos, tan, arcsin, arccos, arctan");
        System.out.println("  ln, exp, sqrt");
        
        System.out.println("\n🔢 CONSTANTS: pi, e");
        
        System.out.println("\n💡 EXAMPLES:");
        System.out.println("  Single: x^2-4, sin(x^2), exp(x)-ln(x)");
        System.out.println("  Multi:  x^2+y^2, x*y+sin(x)*cos(y)");
        
        System.out.println("\n⚡ TIPS:");
        System.out.println("  • Parentheses: (x+1)*(x-1)");
        System.out.println("  • Integer exponents only");
        System.out.println("  • Functions need parens: sin(x)");
        
        System.out.println("\n" + "─".repeat(70));
        System.out.print("Press Enter...");
        scanner.nextLine();
    }
}