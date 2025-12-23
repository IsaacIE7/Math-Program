import calculus.Calc;
import expressions.*;

public class ComprehensiveSystemTest {
    private static int passed = 0;
    private static int failed = 0;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("COMPREHENSIVE MATH ENGINE TEST SUITE");
        System.out.println("=".repeat(80));
        
        testBasicArithmetic();
        testParsing();
        testDerivatives();
        testSimplification();
        testTrigFunctions();
        testInverseTrig();
        testLogarithms();
        testPowers();
        testComplexExpressions();
        testTaylorSeries();
        testEdgeCases();
        testErrorHandling();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("FINAL RESULTS: " + passed + " passed, " + failed + " failed");
        System.out.println("=".repeat(80));
    }
    
    // ========== BASIC ARITHMETIC ==========
    public static void testBasicArithmetic() {
        section("BASIC ARITHMETIC");
        
        testEval("5 + 3", 8.0);
        testEval("10 - 4", 6.0);
        testEval("6 * 7", 42.0);
        testEval("15 / 3", 5.0);
        testEval("2^3", 8.0);
        testEval("2^10", 1024.0);
        
        // Order of operations
        testEval("2 + 3 * 4", 14.0);
        testEval("(2 + 3) * 4", 20.0);
        testEval("2^3 * 4", 32.0);
        testEval("2 * 3^2", 18.0);
        testEval("10 - 2 * 3", 4.0);
        testEval("(10 - 2) * 3", 24.0);
        
        // Chaining operations
        testEval("1 + 2 + 3 + 4", 10.0);
        testEval("20 - 5 - 3", 12.0);
        testEval("2 * 3 * 4", 24.0);
        testEval("24 / 4 / 2", 3.0);
        testEval("2^2^2", 16.0);  // Right associative: 2^(2^2) = 2^4
        
        // With variables
        testEvalAt("x + 5", 2.0, 7.0);
        testEvalAt("3*x", 4.0, 12.0);
        testEvalAt("x^2", 3.0, 9.0);
        testEvalAt("x^2 + 2*x + 1", 2.0, 9.0);
    }
    
    // ========== PARSING TESTS ==========
    public static void testParsing() {
        section("PARSING");
        
        // Constants
        testEval("pi", Math.PI);
        testEval("e", Math.E);
        testEval("2*pi", 2.0 * Math.PI);
        testEval("pi + e", Math.PI + Math.E);
        testEval("pi*e", Math.PI * Math.E);
        
        // Nested parentheses
        testEval("((2 + 3))", 5.0);
        testEval("(2 + (3 + 4))", 9.0);
        testEval("((2 + 3) * (4 + 5))", 45.0);
        
        // Complex expressions
        testEval("(2 + 3) * (4 - 1) + 5", 20.0);
        testEvalAt("(x + 1) * (x - 1)", 3.0, 8.0);  // (3+1)*(3-1) = 8
    }
    
    // ========== DERIVATIVES ==========
    public static void testDerivatives() {
        section("DERIVATIVES");
        
        // Basic derivatives
        testDerivAt("x", 0.0, 1.0);  // d/dx[x] = 1
        testDerivAt("5", 0.0, 0.0);  // d/dx[5] = 0
        testDerivAt("x^2", 2.0, 4.0);  // d/dx[x^2] = 2x, at x=2 is 4
        testDerivAt("x^3", 2.0, 12.0);  // d/dx[x^3] = 3x^2, at x=2 is 12
        testDerivAt("x^4", 2.0, 32.0);  // d/dx[x^4] = 4x^3, at x=2 is 32
        
        // Sum/difference rule
        testDerivAt("x + 5", 2.0, 1.0);
        testDerivAt("x^2 + x", 2.0, 5.0);  // 2x + 1, at x=2 is 5
        testDerivAt("x^3 - x^2", 2.0, 8.0);  // 3x^2 - 2x, at x=2 is 8
        
        // Product rule
        testDerivAt("x*x", 2.0, 4.0);  // Should be 2x
        testDerivAt("(x+1)*(x+2)", 2.0, 7.0);  // Expand and differentiate
        
        // Chain rule with powers
        testDerivAt("(x+1)^2", 2.0, 6.0);  // 2(x+1), at x=2 is 6
        testDerivAt("(2*x)^2", 2.0, 16.0);  // 8x, at x=2 is 16
        
        // Quotient rule
        testDerivAt("x/2", 2.0, 0.5);
        testDerivAt("1/x", 2.0, -0.25);  // -1/x^2, at x=2 is -0.25
    }
    
    // ========== SIMPLIFICATION ==========
    public static void testSimplification() {
        section("SIMPLIFICATION");
        
        // Basic simplification
        testSimplify("x + 0", "x");
        testSimplify("0 + x", "x");
        testSimplify("x * 1", "x");
        testSimplify("1 * x", "x");
        testSimplify("x * 0", "0.0");
        testSimplify("0 * x", "0.0");
        
        // Constant folding
        testSimplify("2 + 3", "5.0");
        testSimplify("10 - 4", "6.0");
        testSimplify("3 * 4", "12.0");
        testSimplify("15 / 3", "5.0");
        testSimplify("2^3", "8.0");
        
        // Powers
        testSimplify("x^0", "1.0");
        testSimplify("x^1", "x");
        
        // Like terms (if implemented)
        testSimplifyContains("x + x", "x");  // Should contain x
        testSimplifyContains("2*x + 3*x", "x");  // Should contain x
        
        // Trig simplification
        testSimplify("sin(0)", "0.0");
        testSimplify("cos(0)", "1.0");
        testSimplify("tan(0)", "0.0");
    }
    
    // ========== TRIG FUNCTIONS ==========
    public static void testTrigFunctions() {
        section("TRIGONOMETRIC FUNCTIONS");
        
        // Basic values
        testEval("sin(0)", 0.0);
        testEval("cos(0)", 1.0);
        testEval("tan(0)", 0.0);
        testEval("sin(pi/2)", 1.0);  // Note: Need to handle division
        testEval("cos(pi)", -1.0);
        
        // With variables
        testEvalAt("sin(x)", 0.0, 0.0);
        testEvalAt("cos(x)", 0.0, 1.0);
        testEvalAt("sin(x)", Math.PI/2, 1.0);
        
        // Derivatives
        testDerivAt("sin(x)", 0.0, 1.0);  // d/dx[sin(x)] = cos(x), at x=0 is 1
        testDerivAt("cos(x)", 0.0, 0.0);  // d/dx[cos(x)] = -sin(x), at x=0 is 0
        testDerivAt("tan(x)", 0.0, 1.0);  // d/dx[tan(x)] = sec^2(x), at x=0 is 1
        
        // Chain rule
        testDerivAt("sin(2*x)", 0.0, 2.0);  // 2cos(2x), at x=0 is 2
        testDerivAt("sin(x^2)", 0.0, 0.0);  // 2x*cos(x^2), at x=0 is 0
        testDerivAt("cos(x^2)", 0.0, 0.0);  // -2x*sin(x^2), at x=0 is 0
        
        // Nested
        testEvalAt("sin(cos(x))", 0.0, Math.sin(1.0));
        testEvalAt("cos(sin(x))", 0.0, 1.0);
    }
    
    // ========== INVERSE TRIG ==========
    public static void testInverseTrig() {
        section("INVERSE TRIGONOMETRIC FUNCTIONS");
        
        // Basic values
        testEval("arcsin(0)", 0.0);
        testEval("arcsin(1)", Math.PI/2);
        testEval("arccos(1)", 0.0);
        testEval("arccos(0)", Math.PI/2);
        testEval("arctan(0)", 0.0);
        testEval("arctan(1)", Math.PI/4);
        
        // Derivatives
        testDerivAt("arcsin(x)", 0.0, 1.0);
        testDerivAt("arccos(x)", 0.0, -1.0);
        testDerivAt("arctan(x)", 0.0, 1.0);
        testDerivAt("arctan(x)", 1.0, 0.5);  // 1/(1+x^2), at x=1 is 0.5
        
        // Compositions (should return x for small x)
        testEvalAt("arcsin(sin(x))", 0.5, 0.5);
        testEvalAt("arctan(tan(x))", 0.5, 0.5);
    }
    
    // ========== LOGARITHMS ==========
    public static void testLogarithms() {
        section("LOGARITHMS");
        
        // Basic values
        testEval("ln(1)", 0.0);
        testEval("ln(e)", 1.0);
        testEvalAt("ln(x)", Math.E, 1.0);
        testEvalAt("ln(x)", 1.0, 0.0);
        
        // Derivatives
        testDerivAt("ln(x)", 1.0, 1.0);  // d/dx[ln(x)] = 1/x, at x=1 is 1
        testDerivAt("ln(x)", 2.0, 0.5);  // at x=2 is 0.5
        testDerivAt("ln(x^2)", 2.0, 1.0);  // 2x/x^2 = 2/x, at x=2 is 1
        
        // exp function (e^x via EulerX)
        testEvalAt("exp(x)", 0.0, 1.0);
        testEvalAt("exp(x)", 1.0, Math.E);
        testDerivAt("exp(x)", 0.0, 1.0);  // d/dx[e^x] = e^x
        testDerivAt("exp(x)", 1.0, Math.E);
    }
    
    // ========== POWERS & ROOTS ==========
    public static void testPowers() {
        section("POWERS AND ROOTS");
        
        // sqrt
        testEval("sqrt(4)", 2.0);
        testEval("sqrt(9)", 3.0);
        testEval("sqrt(16)", 4.0);
        testEvalAt("sqrt(x)", 4.0, 2.0);
        
        // sqrt derivatives
        testDerivAt("sqrt(x)", 4.0, 0.25);  // 1/(2*sqrt(x)), at x=4 is 0.25
        testDerivAt("sqrt(x^2)", 1.0, 1.0);  // x/|x|, at x=1 is 1
        
        // Power combinations
        testEvalAt("x^2", 3.0, 9.0);
        testEvalAt("x^3", 2.0, 8.0);
        testEvalAt("x^4", 2.0, 16.0);
        testEvalAt("x^5", 2.0, 32.0);
        
        // Fractional-looking (but integer exponents only)
        testEval("2^8", 256.0);
        testEval("3^4", 81.0);
    }
    
    // ========== COMPLEX EXPRESSIONS ==========
    public static void testComplexExpressions() {
        section("COMPLEX EXPRESSIONS");
        
        // Mixed operations
        testEvalAt("sin(x) + cos(x)", 0.0, 1.0);
        testEvalAt("sin(x) * cos(x)", Math.PI/4, 0.5);
        testEvalAt("x^2 + sin(x)", 0.0, 0.0);
        testEvalAt("sqrt(x) + ln(x)", 1.0, 1.0);  // sqrt(1) + ln(1) = 1 + 0
        
        // Nested functions
        testEvalAt("sin(sqrt(x))", 4.0, Math.sin(2.0));
        testEvalAt("sqrt(sin(x))", Math.PI/2, 1.0);
        testEvalAt("ln(cos(x))", 0.0, 0.0);  // ln(1) = 0
        testEvalAt("exp(ln(x))", 5.0, 5.0);  // Should return x
        
        // Complex derivatives
        testDerivAt("sin(x^2)", 1.0, 2.0 * Math.cos(1.0));
        testDerivAt("ln(sin(x))", Math.PI/2, 0.0);  // cos(x)/sin(x), at pi/2 is 0
        testDerivAt("sqrt(x^2 + 1)", 0.0, 0.0);
        
        // Polynomial-like
        testEvalAt("x^3 - 3*x^2 + 3*x - 1", 2.0, 1.0);  // (x-1)^3 at x=2
        testEvalAt("x^4 + x^3 + x^2 + x", 1.0, 4.0);
    }
    
    // ========== TAYLOR SERIES ==========
    public static void testTaylorSeries() {
        section("TAYLOR SERIES");
        
        // sin(x) around 0: x - x^3/6 + x^5/120 - ...
        Expression sinx = new Sin(new Variable());
        Expression taylor3 = Calc.taylorSeries(sinx, 0.0, 3);
        testEvalExpr("sin(x) Taylor n=3 at x=0.5", taylor3, 0.5, 
                    0.5 - Math.pow(0.5, 3)/6.0, 0.01);  // Approximate
        
        // cos(x) around 0: 1 - x^2/2 + x^4/24 - ...
        Expression cosx = new Cos(new Variable());
        Expression taylor3cos = Calc.taylorSeries(cosx, 0.0, 3);
        testEvalExpr("cos(x) Taylor n=3 at x=0.5", taylor3cos, 0.5,
                    1.0 - Math.pow(0.5, 2)/2.0, 0.01);
        
        // e^x around 0: 1 + x + x^2/2 + x^3/6 + ...
        Expression ex = new EulerX(new Variable());
        Expression taylor4ex = Calc.taylorSeries(ex, 0.0, 4);
        testEvalExpr("e^x Taylor n=4 at x=1", taylor4ex, 1.0, 
                    1 + 1 + 0.5 + 1.0/6.0, 0.01);
    }
    
    // ========== EDGE CASES ==========
    public static void testEdgeCases() {
        section("EDGE CASES");
        
        // Zero cases
        testEval("0", 0.0);
        testEval("0 + 0", 0.0);
        testEval("0 * 5", 0.0);
        testEvalAt("0 * x", 5.0, 0.0);
        testEvalAt("x - x", 5.0, 0.0);
        
        // One cases
        testEval("1", 1.0);
        testEvalAt("1 * x", 5.0, 5.0);
        testEvalAt("x / 1", 5.0, 5.0);
        testEvalAt("x^1", 5.0, 5.0);
        
        // Negative numbers
        testEval("-1 + 1", 0.0);  // May not work without unary minus
        testEval("0 - 5", -5.0);
        testEvalAt("x", -2.0, -2.0);
        testEvalAt("x^2", -2.0, 4.0);
        testEvalAt("x^3", -2.0, -8.0);
        
        // Large numbers
        testEval("1000 + 2000", 3000.0);
        testEval("1000000", 1000000.0);
        
        // Very small numbers
        testEval("0.0001", 0.0001);
        testEval("0.001 + 0.002", 0.003);
    }
    
    // ========== ERROR HANDLING ==========
    public static void testErrorHandling() {
        section("ERROR HANDLING");
        
        testError("sin(");  // Missing closing paren
        testError("sin)");  // Missing opening paren
        testError("sin");   // Missing argument
        testError("(x");    // Unclosed paren
        testError("x)");    // Extra closing paren
        testError("+ x");   // No left operand (may work with unary)
        testError("x +");   // No right operand
        testError("x ^ x"); // Variable exponent (not supported)
        testError("x ^ 2.5"); // Non-integer exponent
        
        // Division by zero
        testErrorEval("1/0");
        testErrorEvalAt("1/x", 0.0);
        
        // Domain errors
        testErrorEval("ln(0)");
        testErrorEval("ln(-1)");
        testErrorEval("sqrt(-1)");
        testErrorEval("arcsin(2)");
        testErrorEval("arcsin(-2)");
        testErrorEval("arccos(2)");
    }
    
    // ========== TEST HELPERS ==========
    
    public static void section(String name) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(name);
        System.out.println("=".repeat(80));
    }
    
    public static void testEval(String expr, double expected) {
        testEvalAt(expr, 0.0, expected);  // Default x=0
    }
    
    public static void testEvalAt(String expr, double x, double expected) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(x);
            if (Math.abs(result - expected) < 0.0001) {
                pass("eval(" + expr + ") at x=" + x + " = " + result);
            } else {
                fail("eval(" + expr + ") at x=" + x + " = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(" + expr + ") threw: " + ex.getMessage());
        }
    }
    
    public static void testEvalExpr(String name, Expression expr, double x, double expected, double tolerance) {
        try {
            double result = expr.evaluate(x);
            if (Math.abs(result - expected) < tolerance) {
                pass(name + " = " + result);
            } else {
                fail(name + " = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail(name + " threw: " + ex.getMessage());
        }
    }
    
    public static void testDerivAt(String expr, double x, double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sDerivative().simplify();
            double result = deriv.evaluate(x);
            if (Math.abs(result - expected) < 0.0001) {
                pass("d/dx[" + expr + "] at x=" + x + " = " + result);
            } else {
                fail("d/dx[" + expr + "] at x=" + x + " = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("d/dx[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    public static void testSimplify(String expr, String expected) {
        try {
            Expression e = Parser.parse(expr);
            String result = e.simplify().toString();
            if (result.equals(expected)) {
                pass("simplify(" + expr + ") = " + result);
            } else {
                fail("simplify(" + expr + ") = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("simplify(" + expr + ") threw: " + ex.getMessage());
        }
    }
    
    public static void testSimplifyContains(String expr, String contains) {
        try {
            Expression e = Parser.parse(expr);
            String result = e.simplify().toString();
            if (result.contains(contains)) {
                pass("simplify(" + expr + ") contains '" + contains + "'");
            } else {
                fail("simplify(" + expr + ") = " + result + ", should contain '" + contains + "'");
            }
        } catch (Exception ex) {
            fail("simplify(" + expr + ") threw: " + ex.getMessage());
        }
    }
    
    public static void testError(String expr) {
        try {
            Expression e = Parser.parse(expr);
            fail("parse(" + expr + ") should throw error but didn't");
        } catch (Exception ex) {
            pass("parse(" + expr + ") correctly threw error");
        }
    }
    
    public static void testErrorEval(String expr) {
        try {
            Expression e = Parser.parse(expr);
            e.evaluate(0.0);
            fail("eval(" + expr + ") should throw error but didn't");
        } catch (Exception ex) {
            pass("eval(" + expr + ") correctly threw error");
        }
    }
    
    public static void testErrorEvalAt(String expr, double x) {
        try {
            Expression e = Parser.parse(expr);
            e.evaluate(x);
            fail("eval(" + expr + ") at x=" + x + " should throw error but didn't");
        } catch (Exception ex) {
            pass("eval(" + expr + ") at x=" + x + " correctly threw error");
        }
    }
    
    public static void pass(String msg) {
        System.out.println("✓ PASS | " + msg);
        passed++;
    }
    
    public static void fail(String msg) {
        System.out.println("✗ FAIL | " + msg);
        failed++;
    }
}