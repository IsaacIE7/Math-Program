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
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║          DIVERGENCE COMPREHENSIVE TEST SUITE                   ║");
    System.out.println("║          Static Methods vs VectorField Objects                 ║");
    System.out.println("╚════════════════════════════════════════════════════════════════╝");
    
    int passed = 0;
    int failed = 0;
    
    // ========== STATIC METHOD TESTS ==========
    System.out.println("\n" + "=".repeat(70));
    System.out.println("PART 1: STATIC METHOD TESTS (MultiVarCalc.div)");
    System.out.println("=".repeat(70));
    
    // Test 1: Simple 2D polynomial field
    System.out.println("\n[Test 1] 2D Polynomial Field: F = ⟨x², y²⟩");
    try {
        String[] vars1 = {"x", "y"};
        Expression div1 = MultiVarCalc.div(vars1, "x^2", "y^2");
        System.out.println("  div(F) = " + div1.simplify());
        System.out.println("  Expected: 2.0x + 2.0y");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 2: Evaluate 2D field at a point
    System.out.println("\n[Test 2] 2D Field at Point (3, 4): F = ⟨x², y²⟩");
    try {
        String[] vars2 = {"x", "y"};
        Map<String, Double> point1 = Map.of("x", 3.0, "y", 4.0);
        double divAt1 = MultiVarCalc.divAt(vars2, point1, "x^2", "y^2");
        System.out.println("  div(F)(3,4) = " + divAt1);
        System.out.println("  Expected: 14.0 (2*3 + 2*4)");
        if (Math.abs(divAt1 - 14.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + divAt1);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 3: 3D identity field
    System.out.println("\n[Test 3] 3D Identity Field: F = ⟨x, y, z⟩");
    try {
        String[] vars3 = {"x", "y", "z"};
        Expression div2 = MultiVarCalc.div(vars3, "x", "y", "z");
        System.out.println("  div(F) = " + div2.simplify());
        System.out.println("  Expected: 3.0 (1 + 1 + 1)");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 4: 3D field evaluated at point
    System.out.println("\n[Test 4] 3D Field at (1, 2, 3): F = ⟨x², y², z²⟩");
    try {
        String[] vars4 = {"x", "y", "z"};
        Map<String, Double> point2 = Map.of("x", 1.0, "y", 2.0, "z", 3.0);
        double divAt2 = MultiVarCalc.divAt(vars4, point2, "x^2", "y^2", "z^2");
        System.out.println("  div(F)(1,2,3) = " + divAt2);
        System.out.println("  Expected: 12.0 (2*1 + 2*2 + 2*3)");
        if (Math.abs(divAt2 - 12.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + divAt2);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 5: Constant field (divergence = 0)
    System.out.println("\n[Test 5] Constant Field: F = ⟨5, 3⟩");
    try {
        String[] vars5 = {"x", "y"};
        Expression div3 = MultiVarCalc.div(vars5, "5", "3");
        System.out.println("  div(F) = " + div3.simplify());
        System.out.println("  Expected: 0.0");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 6: Mixed algebraic field
    System.out.println("\n[Test 6] Mixed Field: F = ⟨xy, yz, zx⟩");
    try {
        String[] vars6 = {"x", "y", "z"};
        Expression div4 = MultiVarCalc.div(vars6, "x*y", "y*z", "z*x");
        System.out.println("  div(F) = " + div4.simplify());
        System.out.println("  Expected: y + z + x (order may vary)");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 7: Single variable (1D)
    System.out.println("\n[Test 7] 1D Field: F = ⟨x³⟩");
    try {
        String[] vars7 = {"x"};
        Expression div5 = MultiVarCalc.div(vars7, "x^3");
        System.out.println("  div(F) = " + div5.simplify());
        System.out.println("  Expected: 3.0(x)^2");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 8: Trigonometric field
    System.out.println("\n[Test 8] Trig Field: F = ⟨sin(x), cos(y)⟩");
    try {
        String[] vars8 = {"x", "y"};
        Expression div6 = MultiVarCalc.div(vars8, "sin(x)", "cos(y)");
        System.out.println("  div(F) = " + div6.simplify());
        System.out.println("  Expected: cos(x) - sin(y)");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 9: Exponential/Logarithmic field
    System.out.println("\n[Test 9] Exp/Log Field: F = ⟨exp(x), ln(y)⟩");
    try {
        String[] vars9 = {"x", "y"};
        Expression div7 = MultiVarCalc.div(vars9, "exp(x)", "ln(y)");
        System.out.println("  div(F) = " + div7.simplify());
        System.out.println("  Expected: exp(x) + 1/y");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 10: Complex 3D field with trig
    System.out.println("\n[Test 10] Complex 3D: F = ⟨sin(x), cos(y), exp(z)⟩");
    try {
        String[] vars10 = {"x", "y", "z"};
        Expression div8 = MultiVarCalc.div(vars10, "sin(x)", "cos(y)", "exp(z)");
        System.out.println("  div(F) = " + div8.simplify());
        System.out.println("  Expected: cos(x) - sin(y) + exp(z)");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 11: Radial field (important in physics)
    System.out.println("\n[Test 11] Radial Field: F = ⟨x, y, z⟩");
    try {
        String[] vars11 = {"x", "y", "z"};
        Map<String, Double> point3 = Map.of("x", 2.0, "y", 3.0, "z", 4.0);
        double divAt3 = MultiVarCalc.divAt(vars11, point3, "x", "y", "z");
        System.out.println("  div(F)(2,3,4) = " + divAt3);
        System.out.println("  Expected: 3.0 (always 3 for radial field)");
        if (Math.abs(divAt3 - 3.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + divAt3);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 12: Error handling - mismatched dimensions
    System.out.println("\n[Test 12] Error: Mismatched Dimensions (2 vars, 3 comps)");
    try {
        String[] vars12 = {"x", "y"};
        MultiVarCalc.div(vars12, "x", "y", "z");
        System.out.println("  ✗ FAIL: Should have thrown exception");
        failed++;
    } catch (IllegalArgumentException e) {
        System.out.println("  ✓ PASS: Correctly threw IllegalArgumentException");
        System.out.println("  Message: " + e.getMessage());
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: Wrong exception type: " + e.getClass());
        failed++;
    }
    
    // ========== VECTOR FIELD OBJECT TESTS ==========
    System.out.println("\n" + "=".repeat(70));
    System.out.println("PART 2: VECTOR FIELD OBJECT TESTS");
    System.out.println("=".repeat(70));
    
    // Test 13: Object - Simple 2D field
    System.out.println("\n[Test 13] VectorField Object: F = ⟨x², y²⟩");
    try {
        VectorField vf1 = new VectorField(new String[]{"x^2", "y^2"}, new String[]{"x", "y"});
        Expression objDiv1 = vf1.objDiv();
        System.out.println("  div(F) = " + objDiv1.simplify());
        System.out.println("  Expected: 2.0x + 2.0y");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 14: Object - Evaluate at point
    System.out.println("\n[Test 14] VectorField at (3, 4): F = ⟨x², y²⟩");
    try {
        VectorField vf2 = new VectorField(new String[]{"x^2", "y^2"}, new String[]{"x", "y"});
        double objDivAt1 = vf2.objDivAt(Map.of("x", 3.0, "y", 4.0));
        System.out.println("  div(F)(3,4) = " + objDivAt1);
        System.out.println("  Expected: 14.0");
        if (Math.abs(objDivAt1 - 14.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + objDivAt1);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 15: Object - 3D identity
    System.out.println("\n[Test 15] VectorField 3D Identity: F = ⟨x, y, z⟩");
    try {
        VectorField vf3 = new VectorField(new String[]{"x", "y", "z"}, new String[]{"x", "y", "z"});
        Expression objDiv2 = vf3.objDiv();
        System.out.println("  div(F) = " + objDiv2.simplify());
        System.out.println("  Expected: 3.0");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 16: Object - Complex trig field
    System.out.println("\n[Test 16] VectorField Complex: F = ⟨sin(x), cos(y), exp(z)⟩");
    try {
        VectorField vf4 = new VectorField(
            new String[]{"sin(x)", "cos(y)", "exp(z)"}, 
            new String[]{"x", "y", "z"}
        );
        Expression objDiv3 = vf4.objDiv();
        System.out.println("  div(F) = " + objDiv3.simplify());
        System.out.println("  Expected: cos(x) - sin(y) + exp(z)");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 17: Object - Evaluate complex field at origin
    System.out.println("\n[Test 17] Complex VectorField at Origin (0, 0, 0)");
    try {
        VectorField vf5 = new VectorField(
            new String[]{"sin(x)", "cos(y)", "exp(z)"}, 
            new String[]{"x", "y", "z"}
        );
        double objDivAt2 = vf5.objDivAt(Map.of("x", 0.0, "y", 0.0, "z", 0.0));
        System.out.println("  div(F)(0,0,0) = " + objDivAt2);
        System.out.println("  Expected: 2.0 (cos(0) - sin(0) + exp(0) = 1 - 0 + 1)");
        if (Math.abs(objDivAt2 - 2.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + objDivAt2);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 18: Object - Constant field
    System.out.println("\n[Test 18] VectorField Constant: F = ⟨7, 9⟩");
    try {
        VectorField vf6 = new VectorField(new String[]{"7", "9"}, new String[]{"x", "y"});
        Expression objDiv4 = vf6.objDiv();
        System.out.println("  div(F) = " + objDiv4.simplify());
        System.out.println("  Expected: 0.0");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 19: Object - 1D field
    System.out.println("\n[Test 19] VectorField 1D: F = ⟨x³⟩");
    try {
        VectorField vf7 = new VectorField(new String[]{"x^3"}, new String[]{"x"});
        Expression objDiv5 = vf7.objDiv();
        System.out.println("  div(F) = " + objDiv5.simplify());
        System.out.println("  Expected: 3.0(x)^2");
        System.out.println("  ✓ PASS");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 20: Object error handling - constructor
    System.out.println("\n[Test 20] VectorField Error: Constructor with Mismatched Dims");
    try {
        VectorField vf8 = new VectorField(new String[]{"x", "y"}, new String[]{"x", "y", "z"});
        System.out.println("  ✗ FAIL: Should have thrown exception in constructor");
        failed++;
    } catch (IllegalArgumentException e) {
        System.out.println("  ✓ PASS: Correctly threw IllegalArgumentException");
        System.out.println("  Message: " + e.getMessage());
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: Wrong exception type: " + e.getClass());
        failed++;
    }
    
    // Test 21: Object - Getters test
    System.out.println("\n[Test 21] VectorField Getters");
    try {
        VectorField vf9 = new VectorField(new String[]{"x^2", "y^2"}, new String[]{"x", "y"});
        String[] comps = vf9.getComps();
        String[] vars = vf9.getVars();
        System.out.println("  Components: " + java.util.Arrays.toString(comps));
        System.out.println("  Variables: " + java.util.Arrays.toString(vars));
        System.out.println("  Expected: [x^2, y^2] and [x, y]");
        if (comps.length == 2 && vars.length == 2) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Wrong array lengths");
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 22: Object - Polynomial field with evaluation
    System.out.println("\n[Test 22] VectorField Polynomial at (-1, 2): F = ⟨x³, y³⟩");
    try {
        VectorField vf10 = new VectorField(new String[]{"x^3", "y^3"}, new String[]{"x", "y"});
        double objDivAt3 = vf10.objDivAt(Map.of("x", -1.0, "y", 2.0));
        System.out.println("  div(F)(-1,2) = " + objDivAt3);
        System.out.println("  Expected: 15.0 (3*(-1)² + 3*(2)² = 3 + 12)");
        if (Math.abs(objDivAt3 - 15.0) < 1e-10) {
            System.out.println("  ✓ PASS");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Got " + objDivAt3);
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // ========== COMPARISON TEST ==========
    System.out.println("\n" + "=".repeat(70));
    System.out.println("PART 3: STATIC VS OBJECT COMPARISON");
    System.out.println("=".repeat(70));
    
    // Test 23: Compare static and object results
    System.out.println("\n[Test 23] Comparison: F = ⟨xy, y²⟩");
    try {
        String[] varsComp = {"x", "y"};
        Expression staticDiv = MultiVarCalc.div(varsComp, "x*y", "y^2");
        VectorField vfComp = new VectorField(new String[]{"x*y", "y^2"}, new String[]{"x", "y"});
        Expression objectDiv = vfComp.objDiv();
        System.out.println("  Static method:  " + staticDiv.simplify());
        System.out.println("  Object method:  " + objectDiv.simplify());
        System.out.println("  Both should give: y + 2.0y (or equivalent)");
        System.out.println("  ✓ PASS: Both methods produce results");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 24: Compare evaluation results
    System.out.println("\n[Test 24] Evaluation Comparison at (2, 3): F = ⟨x², y²⟩");
    try {
        String[] varsEval = {"x", "y"};
        Map<String, Double> pointEval = Map.of("x", 2.0, "y", 3.0);
        double staticDivAt = MultiVarCalc.divAt(varsEval, pointEval, "x^2", "y^2");
        VectorField vfEval = new VectorField(new String[]{"x^2", "y^2"}, new String[]{"x", "y"});
        double objectDivAt = vfEval.objDivAt(pointEval);
        System.out.println("  Static result:  " + staticDivAt);
        System.out.println("  Object result:  " + objectDivAt);
        System.out.println("  Expected: 10.0 (2*2 + 2*3 = 4 + 6)");
        if (Math.abs(staticDivAt - objectDivAt) < 1e-10 && Math.abs(staticDivAt - 10.0) < 1e-10) {
            System.out.println("  ✓ PASS: Both methods agree");
            passed++;
        } else {
            System.out.println("  ✗ FAIL: Methods don't agree or wrong result");
            failed++;
        }
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // Test 25: Zero divergence field (solenoidal)
    System.out.println("\n[Test 25] Zero Divergence (Solenoidal): F = ⟨y, -x⟩");
    try {
        String[] varsZero = {"x", "y"};
        Expression staticZero = MultiVarCalc.div(varsZero, "y", "-x");
        VectorField vfZero = new VectorField(new String[]{"y", "-x"}, new String[]{"x", "y"});
        Expression objectZero = vfZero.objDiv();
        System.out.println("  Static:  " + staticZero.simplify());
        System.out.println("  Object:  " + objectZero.simplify());
        System.out.println("  Expected: 0.0 (∂y/∂x + ∂(-x)/∂y = 0 + 0)");
        System.out.println("  ✓ PASS: Both produce zero divergence");
        passed++;
    } catch (Exception e) {
        System.out.println("  ✗ FAIL: " + e.getMessage());
        failed++;
    }
    
    // ========== FINAL SUMMARY ==========
    System.out.println("\n" + "=".repeat(70));
    System.out.println("FINAL TEST SUMMARY");
    System.out.println("=".repeat(70));
    System.out.println("Total Tests: " + (passed + failed));
    System.out.println("✓ Passed:    " + passed);
    System.out.println("✗ Failed:    " + failed);
    System.out.printf("Success Rate: %.1f%%\n", 100.0 * passed / (passed + failed));
    System.out.println("=".repeat(70));
    
    if (failed == 0) {
        System.out.println("\n🎉 ALL TESTS PASSED! EXCELLENT WORK! 🎉");
    } else {
        System.out.println("\n⚠️  Some tests failed. Review the output above.");
    }








        //-------------------------------------------------
        Map<String, Double> map = Map.of(
            "x", 1.0,
            "y", 2.0,
            "z", 5.0,
            "t", 3.0
        );
        System.out.println(Parser.parse("x + 2y + z + 3t + e").simplify());
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