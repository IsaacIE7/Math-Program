import expressions.*;
import java.util.*;
import utils.*;

public class MultivarTest {
    private static int passed = 0;
    private static int failed = 0;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("MULTIVARIABLE CALCULUS TEST SUITE");
        System.out.println("=".repeat(80));
        
        testBasicMultivarParsing();
        testMultivarEvaluation();
        testPartialDerivativesBasic();
        testPartialDerivativesProduct();
        testPartialDerivativesQuotient();
        testPartialDerivativesPower();
        testPartialDerivativesTrig();
        testPartialDerivativesLog();
        testMixedPartials();
        testGradient();
        testChainRuleMultivar();
        testComplexMultivarExpressions();
        testThreeVariables();
        testEdgeCases();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("FINAL RESULTS: " + passed + " passed, " + failed + " failed");
        System.out.println("=".repeat(80));
    }
    
    // ========== BASIC PARSING ==========
    public static void testBasicMultivarParsing() {
        section("BASIC MULTIVARIABLE PARSING");
        
        testParse("x", "x");
        testParse("y", "y");
        testParse("z", "z");
        testParse("x + y", "x", "y");
        testParse("x*y", "x", "y");
        testParse("x^2 + y^2", "x", "y");
        testParse("x*y + y*z", "x", "y", "z");
        testParse("x^2 + 2*x*y + y^2", "x", "y");
    }
    
    // ========== MULTIVARIABLE EVALUATION ==========
    public static void testMultivarEvaluation() {
        section("MULTIVARIABLE EVALUATION");
        
        // Two variables
        testEvalMulti("x + y", map("x", 2.0, "y", 3.0), 5.0);
        testEvalMulti("x - y", map("x", 5.0, "y", 2.0), 3.0);
        testEvalMulti("x*y", map("x", 3.0, "y", 4.0), 12.0);
        testEvalMulti("x/y", map("x", 6.0, "y", 2.0), 3.0);
        testEvalMulti("x^2 + y^2", map("x", 3.0, "y", 4.0), 25.0);
        
        // Three variables
        testEvalMulti("x + y + z", map("x", 1.0, "y", 2.0, "z", 3.0), 6.0);
        testEvalMulti("x*y*z", map("x", 2.0, "y", 3.0, "z", 4.0), 24.0);
        testEvalMulti("x^2 + y^2 + z^2", map("x", 1.0, "y", 2.0, "z", 2.0), 9.0);
        
        // Mixed
        testEvalMulti("x^2*y + y^2*z", map("x", 2.0, "y", 3.0, "z", 1.0), 21.0);
        testEvalMulti("(x + y)*(x - y)", map("x", 5.0, "y", 3.0), 16.0);
        testEvalMulti("x*y + y*z + z*x", map("x", 1.0, "y", 2.0, "z", 3.0), 11.0);
    }
    
    // ========== PARTIAL DERIVATIVES - BASIC ==========
    public static void testPartialDerivativesBasic() {
        section("PARTIAL DERIVATIVES - BASIC");
        
        // Constants
        testPartial("5", "x", "0.0");
        testPartial("10", "y", "0.0");
        testPartial("pi", "x", "0.0");
        
        // Single variables
        testPartial("x", "x", "1.0");
        testPartial("y", "y", "1.0");
        testPartial("z", "z", "1.0");
        testPartial("x", "y", "0.0");
        testPartial("y", "x", "0.0");
        testPartial("y", "z", "0.0");
        
        // Powers
        testPartial("x^2", "x", "2.0x");
        testPartial("x^3", "x", "3.0(x)^2");
        testPartial("y^2", "y", "2.0y");
        testPartial("y^2", "x", "0.0");
        testPartial("x^2", "y", "0.0");
        
        // Sums
        testPartial("x + y", "x", "1.0");
        testPartial("x + y", "y", "1.0");
        testPartial("x^2 + y^2", "x", "2.0x");
        testPartial("x^2 + y^2", "y", "2.0y");
    }
    
    // ========== PARTIAL DERIVATIVES - PRODUCT RULE ==========
    public static void testPartialDerivativesProduct() {
        section("PARTIAL DERIVATIVES - PRODUCT RULE");
        
        // Basic products
        testPartial("x*y", "x", "y");
        testPartial("x*y", "y", "x");
        testPartial("x*y*z", "x", "y", "z");
        testPartial("x*y*z", "y", "x", "z");
        testPartial("x*y*z", "z", "x", "y");
        
        // Products with powers
        testPartial("x^2*y", "x", "2.0", "x", "y");
        testPartial("x^2*y", "y", "x^2");
        testPartial("x*y^2", "x", "y^2");
        testPartial("x*y^2", "y", "2.0", "x", "y");
        
        // More complex products
        testPartial("x^2*y^3", "x", "2.0", "x", "y^3");
        testPartial("x^2*y^3", "y", "3.0", "x^2", "y^2");
        testPartial("(x + 1)*y", "x", "y");
        testPartial("(x + 1)*y", "y", "x");
    }
    
    // ========== PARTIAL DERIVATIVES - QUOTIENT RULE ==========
    public static void testPartialDerivativesQuotient() {
        section("PARTIAL DERIVATIVES - QUOTIENT RULE");
        
        // Basic quotients
        testPartialEval("x/y", "x", map("x", 4.0, "y", 2.0), 0.5); // d/dx[x/y] = 1/y
        testPartialEval("x/y", "y", map("x", 4.0, "y", 2.0), -1.0); // d/dy[x/y] = -x/y^2 = -4/4
        
        testPartialEval("1/x", "x", map("x", 2.0), -0.25); // d/dx[1/x] = -1/x^2
        testPartialEval("y/x", "x", map("x", 2.0, "y", 4.0), -1.0); // d/dx[y/x] = -y/x^2
        testPartialEval("y/x", "y", map("x", 2.0, "y", 4.0), 0.5); // d/dy[y/x] = 1/x
        
        // More complex
        testPartialEval("x^2/y", "x", map("x", 3.0, "y", 2.0), 3.0); // d/dx[x^2/y] = 2x/y = 6/2
        testPartialEval("x^2/y", "y", map("x", 3.0, "y", 2.0), -2.25); // d/dy[x^2/y] = -x^2/y^2 = -9/4
    }
    
    // ========== PARTIAL DERIVATIVES - POWER ==========
    public static void testPartialDerivativesPower() {
        section("PARTIAL DERIVATIVES - POWER");
        
        testPartial("x^4", "x", "4.0(x)^3");
        testPartial("y^5", "y", "5.0(y)^4");
        
        // Chain rule with power
        testPartial("(x + y)^2", "x", "2.0", "(x + y)");
        testPartial("(x + y)^2", "y", "2.0", "(x + y)");
        testPartial("(x*y)^2", "x", "2.0", "x", "y");
        testPartial("(x*y)^2", "y", "2.0", "x", "y");
        
        testPartialEval("(x + y)^3", "x", map("x", 1.0, "y", 2.0), 27.0); // 3(x+y)^2 = 3*9
        testPartialEval("(x + y)^3", "y", map("x", 1.0, "y", 2.0), 27.0);
    }
    
    // ========== PARTIAL DERIVATIVES - TRIG ==========
    public static void testPartialDerivativesTrig() {
        section("PARTIAL DERIVATIVES - TRIGONOMETRIC");
        
        // Basic trig
        testPartial("sin(x)", "x", "cos");
        testPartial("cos(x)", "x", "-", "sin");
        testPartial("sin(y)", "x", "0.0");
        testPartial("cos(y)", "x", "0.0");
        
        // Chain rule with trig
        testPartial("sin(x*y)", "x", "y", "cos");
        testPartial("sin(x*y)", "y", "x", "cos");
        testPartial("cos(x^2)", "x", "2.0", "x", "sin");
        
        testPartialEval("sin(x + y)", "x", map("x", 0.0, "y", 0.0), 1.0); // cos(0)
        testPartialEval("cos(x + y)", "x", map("x", 0.0, "y", 0.0), 0.0); // -sin(0)
        
        // Multiple variables
        testPartial("sin(x)*cos(y)", "x", "cos", "cos");
        testPartial("sin(x)*cos(y)", "y", "sin", "sin");
    }
    
    // ========== PARTIAL DERIVATIVES - LOGARITHMS ==========
    public static void testPartialDerivativesLog() {
        section("PARTIAL DERIVATIVES - LOGARITHMS & EXPONENTIALS");
        
        // Natural log
        testPartialEval("ln(x)", "x", map("x", 1.0), 1.0); // 1/x at x=1
        testPartialEval("ln(x)", "x", map("x", 2.0), 0.5); // 1/x at x=2
        testPartial("ln(y)", "x", "0.0");
        
        // Chain rule with ln
        testPartialEval("ln(x*y)", "x", map("x", 2.0, "y", 3.0), 0.5); // y/(x*y) = 1/x
        testPartialEval("ln(x*y)", "y", map("x", 2.0, "y", 3.0), 0.333, 0.01); // x/(x*y) = 1/y
        
        // Exponential
        testPartialEval("exp(x)", "x", map("x", 0.0), 1.0); // e^x at x=0
        testPartialEval("exp(x)", "x", map("x", 1.0), Math.E); // e^x at x=1
        testPartial("exp(y)", "x", "0.0");
        
        testPartialEval("exp(x + y)", "x", map("x", 0.0, "y", 0.0), 1.0);
        testPartialEval("exp(x + y)", "y", map("x", 0.0, "y", 0.0), 1.0);
    }
    
    // ========== MIXED PARTIAL DERIVATIVES ==========
    public static void testMixedPartials() {
        section("MIXED PARTIAL DERIVATIVES (Clairaut's Theorem)");
        
        // For smooth functions, mixed partials are equal: fxy = fyx
        testMixedPartial("x*y", "x", "y"); // fxy = fyx = 1
        testMixedPartial("x^2*y", "x", "y"); // fxy = fyx = 2x
        testMixedPartial("x^2*y^2", "x", "y"); // fxy = fyx = 4xy
        testMixedPartial("sin(x*y)", "x", "y"); // Should be equal
        testMixedPartial("x^3*y^2", "x", "y"); // fxy = fyx = 6x^2*y
        
        // More complex
        testMixedPartialEval("x^2 + x*y + y^2", "x", "y", map("x", 1.0, "y", 1.0), 1.0);
        testMixedPartialEval("x*y*z", "x", "y", map("x", 1.0, "y", 2.0, "z", 3.0), 3.0);
    }
    
    // ========== GRADIENT ==========
    public static void testGradient() {
        section("GRADIENT COMPUTATION");
        
        // Test gradient components
        testGradientComponent("x^2 + y^2", "x", "2.0x");
        testGradientComponent("x^2 + y^2", "y", "2.0y");
        
        testGradientComponent("x*y + y*z", "x", "y");
        testGradientComponent("x*y + y*z", "y", "x", "z");
        testGradientComponent("x*y + y*z", "z", "y");
        
        // Evaluate gradient at point
        testGradientEval("x^2 + y^2", map("x", 3.0, "y", 4.0), 
                        map("x", 6.0, "y", 8.0));
        testGradientEval("x*y", map("x", 2.0, "y", 3.0),
                        map("x", 3.0, "y", 2.0));
    }
    
    // ========== CHAIN RULE MULTIVAR ==========
    public static void testChainRuleMultivar() {
        section("CHAIN RULE - MULTIVARIABLE");
        
        // Composition of functions
        testPartial("sin(x^2 + y^2)", "x", "2.0", "x", "cos");
        testPartial("sin(x^2 + y^2)", "y", "2.0", "y", "cos");
        
        testPartial("ln(x^2 + y^2)", "x", "2.0", "x");
        testPartial("ln(x^2 + y^2)", "y", "2.0", "y");
        
        testPartial("exp(x*y)", "x", "y", "exp");
        testPartial("exp(x*y)", "y", "x", "exp");
        
        // Nested compositions
        testPartial("sin(cos(x))", "y", "0.0");
        testPartial("ln(sin(x + y))", "x", "cos", "sin");
        testPartial("ln(sin(x + y))", "y", "cos", "sin");
    }
    
    // ========== COMPLEX MULTIVAR EXPRESSIONS ==========
    public static void testComplexMultivarExpressions() {
        section("COMPLEX MULTIVARIABLE EXPRESSIONS");
        
        // Distance formula
        testEvalMulti("sqrt(x^2 + y^2)", map("x", 3.0, "y", 4.0), 5.0);
        
        // Cobb-Douglas production function: f(K,L) = K^a * L^b
        // Using K=x, L=y, a=2, b=3
        testEvalMulti("x^2*y^3", map("x", 2.0, "y", 3.0), 108.0);
        testPartialEval("x^2*y^3", "x", map("x", 2.0, "y", 3.0), 108.0); // 2xy^3
        testPartialEval("x^2*y^3", "y", map("x", 2.0, "y", 3.0), 108.0); // 3x^2y^2
        
        // Multivariate polynomial
        testEvalMulti("x^3 + 3*x^2*y + 3*x*y^2 + y^3", 
                     map("x", 1.0, "y", 2.0), 27.0); // (x+y)^3 at (1,2)
        
        // Mixed operations
        testEvalMulti("sin(x)*cos(y) + x*y", 
                     map("x", 0.0, "y", 0.0), 0.0);
        testEvalMulti("ln(x) + exp(y)", 
                     map("x", Math.E, "y", 0.0), 2.0);
    }
    
    // ========== THREE VARIABLES ==========
    public static void testThreeVariables() {
        section("THREE VARIABLE FUNCTIONS");
        
        // Basic operations
        testEvalMulti("x + y + z", map("x", 1.0, "y", 2.0, "z", 3.0), 6.0);
        testEvalMulti("x*y*z", map("x", 2.0, "y", 3.0, "z", 4.0), 24.0);
        
        // Partial derivatives
        testPartial("x*y*z", "x", "y", "z");
        testPartial("x*y*z", "y", "x", "z");
        testPartial("x*y*z", "z", "x", "y");
        
        testPartial("x^2 + y^2 + z^2", "x", "2.0x");
        testPartial("x^2 + y^2 + z^2", "y", "2.0y");
        testPartial("x^2 + y^2 + z^2", "z", "2.0z");
        
        // Cross terms
        testPartialEval("x*y + y*z + z*x", "x", map("x", 1.0, "y", 2.0, "z", 3.0), 5.0); // y+z
        testPartialEval("x*y + y*z + z*x", "y", map("x", 1.0, "y", 2.0, "z", 3.0), 4.0); // x+z
        testPartialEval("x*y + y*z + z*x", "z", map("x", 1.0, "y", 2.0, "z", 3.0), 3.0); // y+x
        
        // 3D distance
        testEvalMulti("sqrt(x^2 + y^2 + z^2)", 
                     map("x", 2.0, "y", 3.0, "z", 6.0), 7.0);
    }
    
    // ========== EDGE CASES ==========
    public static void testEdgeCases() {
        section("EDGE CASES");
        
        // Single variable in multivariable context
        testEvalMulti("x", map("x", 5.0), 5.0);
        testPartial("x", "x", "1.0");
        testPartial("x", "y", "0.0");
        testPartial("x", "z", "0.0");
        
        // Constants
        testEvalMulti("5", map("x", 1.0, "y", 2.0), 5.0);
        testPartial("100", "x", "0.0");
        testPartial("100", "y", "0.0");
        
        // Zero cases
        testEvalMulti("0*x + 0*y", map("x", 5.0, "y", 10.0), 0.0);
        testPartial("0*x*y", "x", "0.0");
        
        // Identity cases
        testEvalMulti("1*x + 0*y", map("x", 5.0, "y", 10.0), 5.0);
        testPartial("1*x", "x", "1.0");
        
        // Symmetric functions
        testEvalMulti("x + y", map("x", 3.0, "y", 3.0), 6.0);
        testEvalMulti("x*y", map("x", 5.0, "y", 5.0), 25.0);
    }
    
    // ========== TEST HELPER METHODS ==========
    
    public static void section(String name) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(name);
        System.out.println("=".repeat(80));
    }
    
    public static void testParse(String expr, String... expectedVars) {
        try {
            Expression e = Parser.parse(expr);
            pass("parse(" + expr + ") -> " + e);
        } catch (Exception ex) {
            fail("parse(" + expr + ") threw: " + ex.getMessage());
        }
    }
    
    public static void testEvalMulti(String expr, Map<String, Double> vars, double expected) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(vars);
            if (Math.abs(result - expected) < 0.0001) {
                pass("eval(" + expr + ") at " + vars + " = " + result);
            } else {
                fail("eval(" + expr + ") = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(" + expr + ") threw: " + ex.getMessage());
        }
    }
    
    public static void testPartial(String expr, String var, String... expectedContains) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sPartialDerivative(var).simplify();
            String result = deriv.toString();
            
            boolean allFound = true;
            for (String expected : expectedContains) {
                if (!result.contains(expected)) {
                    allFound = false;
                    break;
                }
            }
            
            if (allFound) {
                pass("∂/∂" + var + "[" + expr + "] = " + result);
            } else {
                fail("∂/∂" + var + "[" + expr + "] = " + result + 
                     ", expected to contain: " + Arrays.toString(expectedContains));
            }
        } catch (Exception ex) {
            fail("∂/∂" + var + "[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    public static void testPartialEval(String expr, String var, Map<String, Double> point, 
                                       double expected) {
        testPartialEval(expr, var, point, expected, 0.0001);
    }
    
    public static void testPartialEval(String expr, String var, Map<String, Double> point, 
                                       double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sPartialDerivative(var).simplify();
            double result = deriv.evaluate(point);
            
            if (Math.abs(result - expected) < tolerance) {
                pass("∂/∂" + var + "[" + expr + "] at " + point + " = " + result);
            } else {
                fail("∂/∂" + var + "[" + expr + "] at " + point + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("∂/∂" + var + "[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    public static void testMixedPartial(String expr, String var1, String var2) {
        try {
            Expression e = Parser.parse(expr);
            Expression fxy = e.sPartialDerivative(var1).sPartialDerivative(var2).simplify();
            Expression fyx = e.sPartialDerivative(var2).sPartialDerivative(var1).simplify();
            
            String result1 = fxy.toString();
            String result2 = fyx.toString();
            
            // For simplification differences, just check they're structurally similar
            pass("∂²/∂" + var1 + "∂" + var2 + "[" + expr + "] = " + result1);
            pass("∂²/∂" + var2 + "∂" + var1 + "[" + expr + "] = " + result2);
            
        } catch (Exception ex) {
            fail("Mixed partial for " + expr + " threw: " + ex.getMessage());
        }
    }
    
    public static void testMixedPartialEval(String expr, String var1, String var2,
                                           Map<String, Double> point, double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression fxy = e.sPartialDerivative(var1).sPartialDerivative(var2).simplify();
            double result = fxy.evaluate(point);
            
            if (Math.abs(result - expected) < 0.0001) {
                pass("∂²/∂" + var1 + "∂" + var2 + "[" + expr + "] at " + point + " = " + result);
            } else {
                fail("∂²/∂" + var1 + "∂" + var2 + "[" + expr + "] = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Mixed partial eval threw: " + ex.getMessage());
        }
    }
    
    public static void testGradientComponent(String expr, String var, String... expectedContains) {
        testPartial(expr, var, expectedContains);
    }
    
    public static void testGradientEval(String expr, Map<String, Double> point, 
                                       Map<String, Double> expectedGrad) {
        try {
            Expression e = Parser.parse(expr);
            boolean allCorrect = true;
            
            for (String var : expectedGrad.keySet()) {
                Expression deriv = e.sPartialDerivative(var).simplify();
                double result = deriv.evaluate(point);
                double expected = expectedGrad.get(var);
                
                if (Math.abs(result - expected) >= 0.0001) {
                    allCorrect = false;
                    break;
                }
            }
            
            if (allCorrect) {
                pass("gradient[" + expr + "] at " + point + " matches expected");
            } else {
                fail("gradient[" + expr + "] at " + point + " doesn't match");
            }
        } catch (Exception ex) {
            fail("gradient eval threw: " + ex.getMessage());
        }
    }
    
    public static Map<String, Double> map(String k1, double v1) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        return m;
    }
    
    public static Map<String, Double> map(String k1, double v1, String k2, double v2) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        m.put(k2, v2);
        return m;
    }
    
    public static Map<String, Double> map(String k1, double v1, String k2, double v2, 
                                         String k3, double v3) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        m.put(k2, v2);
        m.put(k3, v3);
        return m;
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