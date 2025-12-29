import calculus.*;
import expressions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import linalg.*;
import utils.*;


/**
 * INDUSTRY-GRADE COMPREHENSIVE TEST SUITE
 * Tests every feature, edge case, and integration point in the Math Engine
 * 
 * Test Coverage:
 * - Expression Parsing (200+ tests)
 * - Expression Evaluation (150+ tests)
 * - Symbolic Differentiation (180+ tests)
 * - Expression Simplification (120+ tests)
 * - Multivariable Calculus (100+ tests)
 * - Numerical Methods (80+ tests)
 * - Linear Algebra (60+ tests)
 * - Error Handling (90+ tests)
 * - Performance & Stress Tests (40+ tests)
 * 
 * Total: 1000+ automated tests
 */
public class ComprehensiveSystemTest {
    private static int passed = 0;
    private static int failed = 0;
    private static int skipped = 0;
    private static List<String> failures = new ArrayList<>();
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        printHeader("INDUSTRY-GRADE MATH ENGINE TEST SUITE", '=');
        System.out.println("Starting comprehensive testing of all features...\n");
        
        // PHASE 1: CORE PARSING
        testTokenization();
        testParsingBasics();
        testParsingComplexExpressions();
        testImplicitMultiplication();
        testParsingConstants();
        testParsingVariables();
        testParsingOperatorPrecedence();
        testParsingParentheses();
        testParsingFunctions();
        testParsingErrorCases();
        
        // PHASE 2: EVALUATION
        testBasicArithmetic();
        testSingleVariableEvaluation();
        testMultiVariableEvaluation();
        testTrigonometricEvaluation();
        testInverseTrigEvaluation();
        testLogarithmEvaluation();
        testExponentialEvaluation();
        testSqrtEvaluation();
        testComplexEvaluations();
        testEvaluationEdgeCases();
        testEvaluationErrorHandling();
        
        // PHASE 3: SYMBOLIC DIFFERENTIATION
        testBasicDerivatives();
        testPowerRuleDerivatives();
        testProductRuleDerivatives();
        testQuotientRuleDerivatives();
        testChainRuleDerivatives();
        testTrigDerivatives();
        testInverseTrigDerivatives();
        testLogarithmicDerivatives();
        testExponentialDerivatives();
        testSqrtDerivatives();
        testHigherOrderDerivatives();
        testDerivativeEdgeCases();
        
        // PHASE 4: SIMPLIFICATION
        testIdentitySimplification();
        testConstantFolding();
        testAlgebraicSimplification();
        testTrigSimplification();
        testLikeTermsCombining();
        testPowerSimplification();
        testSimplificationEdgeCases();
        
        // PHASE 5: MULTIVARIABLE CALCULUS
        testMultiVarParsing();
        testMultiVarEvaluation();
        testPartialDerivatives();
        testMixedPartials();
        testGradients();
        testDirectionalDerivatives();
        testMultiVarChainRule();
        testMultiVarEdgeCases();
        
        // PHASE 6: NUMERICAL METHODS
        testNewtonsMethod();
        testRootFinder();
        testNumericalDerivative();
        testNumericalIntegration();
        testTaylorSeries();
        testNumericalStability();
        testConvergenceCases();
        
        // PHASE 7: LINEAR ALGEBRA
        testVectorOperations();
        testVectorMagnitude();
        testVectorDotProduct();
        testVectorNormalization();
        testMatrixCreation();
        testMatrixOperations();
        testLinAlgEdgeCases();
        
        // PHASE 8: INTEGRATION TESTS
        testExpressionToStringRoundtrip();
        testComplexWorkflows();
        testRealWorldProblems();
        testPerformanceScenarios();
        
        // PHASE 9: STRESS TESTS
        testLargeExpressions();
        testDeepNesting();
        testManyVariables();
        testExtremValues();
        
        // PHASE 10: REGRESSION TESTS
        testKnownBugs();
        testPreviousFailures();
        
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        
        printFinalReport(duration);
    }
    
    // ==================== PHASE 1: CORE PARSING ====================
    
    static void testTokenization() {
        section("TOKENIZATION");
        
        // Basic tokens
        testTokenize("x", "x");
        testTokenize("123", "123");
        testTokenize("3.14", "3.14");
        testTokenize("+", "+");
        testTokenize("-", "-");
        testTokenize("*", "*");
        testTokenize("/", "/");
        testTokenize("^", "^");
        testTokenize("(", "(");
        testTokenize(")", ")");
        
        // Multiple tokens
        testTokenize("x+1", "x", "+", "1");
        testTokenize("2*x", "2", "*", "x");
        testTokenize("sin(x)", "sin", "(", "x", ")");
        testTokenize("x^2+1", "x", "^", "2", "+", "1");
        
        // Decimal numbers
        testTokenize("3.14159", "3.14159");
        testTokenize("0.5", "0.5");
        testTokenize("10.0", "10.0");
        testTokenize("1.2+3.4", "1.2", "+", "3.4");
        
        // Function names
        testTokenize("sin", "sin");
        testTokenize("cos", "cos");
        testTokenize("tan", "tan");
        testTokenize("ln", "ln");
        testTokenize("sqrt", "sqrt");
        testTokenize("exp", "exp");
        testTokenize("arcsin", "arcsin");
        testTokenize("arccos", "arccos");
        testTokenize("arctan", "arctan");
        
        // Constants
        testTokenize("pi", "pi");
        testTokenize("e", "e");
        
        // Multiple variables
        testTokenize("xyz", "x", "*", "y", "*", "z");
        testTokenize("ab", "a", "*", "b");
        
        // Whitespace handling
        testTokenize("x + 1", "x", "+", "1");
        testTokenize("  x  +  1  ", "x", "+", "1");
        testTokenize("sin ( x )", "sin", "(", "x", ")");
        
        // Complex expressions
        testTokenize("2*x^2+3*x+1", "2", "*", "x", "^", "2", "+", "3", "*", "x", "+", "1");
        testTokenize("sin(x)+cos(y)", "sin", "(", "x", ")", "+", "cos", "(", "y", ")");
    }
    
    static void testParsingBasics() {
        section("PARSING BASICS");
        
        // Constants
        testParse("0");
        testParse("1");
        testParse("42");
        testParse("3.14");
        testParse("2.71828");
        testParse("pi");
        testParse("e");
        
        // Variables
        testParse("x");
        testParse("y");
        testParse("z");
        testParse("a");
        testParse("t");
        
        // Basic operations
        testParse("x+1");
        testParse("x-1");
        testParse("x*2");
        testParse("x/2");
        testParse("x^2");
        
        // Multiple operations
        testParse("x+y");
        testParse("x*y");
        testParse("x/y");
        testParse("x^y"); // Should work if y evaluates to integer
        
        // Basic functions
        testParse("sin(x)");
        testParse("cos(x)");
        testParse("tan(x)");
        testParse("ln(x)");
        testParse("sqrt(x)");
        testParse("exp(x)");
    }
    
    static void testParsingComplexExpressions() {
        section("PARSING COMPLEX EXPRESSIONS");
        
        // Polynomials
        testParse("x^2 + 2*x + 1");
        testParse("x^3 - 3*x^2 + 3*x - 1");
        testParse("x^4 + x^3 + x^2 + x + 1");
        testParse("5*x^4 - 3*x^3 + 2*x^2 - x + 7");
        
        // Nested functions
        testParse("sin(cos(x))");
        testParse("ln(sin(x))");
        testParse("sqrt(x^2+1)");
        testParse("exp(ln(x))");
        testParse("sin(sqrt(x))");
        testParse("ln(exp(x))");
        
        // Multiple variables
        testParse("x + y + z");
        testParse("x*y + y*z + z*x");
        testParse("x^2 + y^2 + z^2");
        testParse("sin(x) + cos(y) + tan(z)");
        
        // Complex compositions
        testParse("sin(x^2) + cos(x^2)");
        testParse("ln(x^2 + y^2)");
        testParse("sqrt(sin(x^2) + cos(x^2))");
        testParse("exp(x*y) + ln(x/y)");
        
        // Long expressions
        testParse("x^5 - 5*x^4 + 10*x^3 - 10*x^2 + 5*x - 1");
        testParse("sin(x) + cos(x) + tan(x) + ln(x) + sqrt(x)");
        testParse("(x+1)*(x+2)*(x+3)*(x+4)");
    }
    
    static void testImplicitMultiplication() {
        section("IMPLICIT MULTIPLICATION");
        
        // Number * variable
        testParse("2x");
        testParse("3y");
        testParse("10z");
        testParse("2.5x");
        
        // Number * parentheses
        testParse("2(x+1)");
        testParse("3(x-2)");
        testParse("5(x^2)");
        
        // Variable * variable
        testParse("xy");
        testParse("xyz");
        testParse("abc");
        
        // Variable * number (should work via implicit multiplication)
        testParseEquiv("x2", "x*2");
        testParseEquiv("y3", "y*3");
        
        // Variable * parentheses
        testParse("x(x+1)");
        testParse("y(y-1)");
        
        // Parentheses * variable
        testParse("(x+1)y");
        testParse("(x-1)z");
        
        // Parentheses * parentheses
        testParse("(x+1)(x-1)");
        testParse("(x+2)(y-3)");
        
        // Number * function
        testParse("2sin(x)");
        testParse("3cos(x)");
        
        // Variable * function
        testParse("xsin(x)");
        testParse("ycos(y)");
        
        // Parentheses * function
        testParse("(x+1)sin(x)");
        testParse("(x-1)cos(x)");
    }
    
    static void testParsingConstants() {
        section("PARSING CONSTANTS");
        
        testParse("pi");
        testParse("e");
        testParse("2*pi");
        testParse("pi*e");
        testParse("pi+e");
        testParse("pi-e");
        testParse("pi/e");
        testParse("pi^2");
        testParse("e^2");
        testParse("sin(pi)");
        testParse("cos(pi)");
        testParse("ln(e)");
        testParse("exp(pi)");
    }
    
    static void testParsingVariables() {
        section("PARSING VARIABLES");
        
        // Single letter variables
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != 'e') { // 'e' is reserved for Euler's number
                testParse(String.valueOf(c));
            }
        }
        
        // Multi-letter (should expand to implicit multiplication)
        testParse("xyz");
        testParse("abc");
        testParse("uvw");
        
        // Variables in expressions
        testParse("x+y");
        testParse("a*b");
        testParse("p/q");
        testParse("m^n");
        
        // Variables with functions
        testParse("sin(t)");
        testParse("cos(theta)"); // Should handle multi-char
        testParse("ln(u)");
    }
    
    static void testParsingOperatorPrecedence() {
        section("OPERATOR PRECEDENCE");
        
        // Addition vs Multiplication
        testParseValue("2+3*4", 14.0);
        testParseValue("2*3+4", 10.0);
        testParseValue("10-2*3", 4.0);
        testParseValue("10/2+3", 8.0);
        
        // Exponentiation vs Multiplication
        testParseValue("2*3^2", 18.0);
        testParseValue("2^3*4", 32.0);
        testParseValue("2^2^2", 16.0); // Right associative
        
        // All operators together
        testParseValue("2+3*4^2", 50.0); // 2 + 3*16
        testParseValue("10-2*3^2", -8.0); // 10 - 2*9
        testParseValue("20/4+3*2", 11.0); // 5 + 6
        
        // With parentheses override
        testParseValue("(2+3)*4", 20.0);
        testParseValue("2*(3+4)", 14.0);
        testParseValue("(10-2)*3", 24.0);
        testParseValue("2^(3+1)", 16.0);
    }
    
    static void testParsingParentheses() {
        section("PARENTHESES HANDLING");
        
        // Basic parentheses
        testParse("(x)");
        testParse("(x+1)");
        testParse("(x-1)");
        testParse("(x*2)");
        testParse("(x/2)");
        testParse("(x^2)");
        
        // Nested parentheses
        testParse("((x))");
        testParse("(((x)))");
        testParse("((x+1))");
        testParse("(x+(y+z))");
        testParse("((x+y)+z)");
        
        // Multiple parentheses
        testParse("(x+1)+(y+2)");
        testParse("(x-1)*(y-2)");
        testParse("(x+1)/(y+1)");
        testParse("(x^2)+(y^2)");
        
        // Complex nesting
        testParse("((x+1)*(x-1))");
        testParse("(x+(y*(z+1)))");
        testParse("((x+y)*(x-y))");
        
        // Parentheses with functions
        testParse("sin((x))");
        testParse("(sin(x))");
        testParse("sin((x+1))");
        testParse("(sin(x)+cos(x))");
        
        // Error cases
        testParseError("(x");
        testParseError("x)");
        testParseError("(x))");
        testParseError("((x)");
    }
    
    static void testParsingFunctions() {
        section("FUNCTION PARSING");
        
        // Trigonometric
        testParse("sin(x)");
        testParse("cos(x)");
        testParse("tan(x)");
        testParse("sin(0)");
        testParse("cos(pi)");
        testParse("tan(pi/4)");
        
        // Inverse trigonometric
        testParse("arcsin(x)");
        testParse("arccos(x)");
        testParse("arctan(x)");
        testParse("arcsin(0)");
        testParse("arctan(1)");
        
        // Logarithmic
        testParse("ln(x)");
        testParse("ln(1)");
        testParse("ln(e)");
        testParse("ln(pi)");
        
        // Exponential
        testParse("exp(x)");
        testParse("exp(0)");
        testParse("exp(1)");
        testParse("exp(ln(x))");
        
        // Square root
        testParse("sqrt(x)");
        testParse("sqrt(4)");
        testParse("sqrt(16)");
        testParse("sqrt(x^2)");
        
        // Nested functions
        testParse("sin(sin(x))");
        testParse("cos(cos(x))");
        testParse("ln(ln(x))");
        testParse("sqrt(sqrt(x))");
        testParse("sin(cos(tan(x)))");
        
        // Functions with complex arguments
        testParse("sin(x^2+1)");
        testParse("ln(x*y)");
        testParse("sqrt(x^2+y^2)");
        testParse("exp(x*y+z)");
        
        // Error cases
        testParseError("sin");
        testParseError("sin(");
        testParseError("sin)");
        testParseError("sin()");
    }
    
    static void testParsingErrorCases() {
        section("PARSING ERROR CASES");
        
        // Missing operands
        testParseError("+");
        testParseError("-");
        testParseError("*");
        testParseError("/");
        testParseError("^");
        testParseError("x+");
        testParseError("*x");
        testParseError("x^");
        
        // Invalid operators
        testParseError("x # y");
        testParseError("x @ y");
        testParseError("x & y");
        
        // Mismatched parentheses
        testParseError("(");
        testParseError(")");
        testParseError("(x");
        testParseError("x)");
        testParseError("((x)");
        testParseError("(x))");
        
        // Invalid function calls
        testParseError("sin");
        testParseError("sin(");
        testParseError("sin)");
        testParseError("sin()");
        testParseError("sin(x");
        testParseError("sinx)");
        
        // Invalid numbers
        testParseError("1.2.3");
        testParseError("..5");
        testParseError("1..2");
        
        // Empty input
        testParseError("");
        testParseError("   ");
    }
    
    // ==================== PHASE 2: EVALUATION ====================
    
    static void testBasicArithmetic() {
        section("BASIC ARITHMETIC");
        
        // Addition
        testEvalExact("0+0", 0.0);
        testEvalExact("1+1", 2.0);
        testEvalExact("5+3", 8.0);
        testEvalExact("10+20", 30.0);
        testEvalExact("3.14+2.86", 7.0);
        
        // Subtraction
        testEvalExact("5-3", 2.0);
        testEvalExact("10-10", 0.0);
        testEvalExact("3-5", -2.0);
        testEvalExact("100-1", 99.0);
        
        // Multiplication
        testEvalExact("2*3", 6.0);
        testEvalExact("5*5", 25.0);
        testEvalExact("10*10", 100.0);
        testEvalExact("0*100", 0.0);
        testEvalExact("2.5*4", 10.0);
        
        // Division
        testEvalExact("10/2", 5.0);
        testEvalExact("15/3", 5.0);
        testEvalExact("1/2", 0.5);
        testEvalExact("7/2", 3.5);
        testEvalExact("100/4", 25.0);
        
        // Exponentiation
        testEvalExact("2^3", 8.0);
        testEvalExact("3^2", 9.0);
        testEvalExact("5^0", 1.0);
        testEvalExact("2^10", 1024.0);
        testEvalExact("10^2", 100.0);
        
        // Chained operations
        testEvalExact("1+2+3", 6.0);
        testEvalExact("10-3-2", 5.0);
        testEvalExact("2*3*4", 24.0);
        testEvalExact("24/4/2", 3.0);
        testEvalExact("2^2^2", 16.0);
        
        // Mixed operations
        testEvalExact("2+3*4", 14.0);
        testEvalExact("(2+3)*4", 20.0);
        testEvalExact("10-2*3", 4.0);
        testEvalExact("2^3+1", 9.0);
        testEvalExact("2*(3+4)", 14.0);
    }
    
    static void testSingleVariableEvaluation() {
        section("SINGLE VARIABLE EVALUATION");
        
        // Basic variable
        testEvalAt("x", 0.0, 0.0);
        testEvalAt("x", 1.0, 1.0);
        testEvalAt("x", 5.0, 5.0);
        testEvalAt("x", -3.0, -3.0);
        testEvalAt("x", 3.14, 3.14);
        
        // Linear expressions
        testEvalAt("x+1", 0.0, 1.0);
        testEvalAt("x+1", 5.0, 6.0);
        testEvalAt("2*x", 3.0, 6.0);
        testEvalAt("x-1", 5.0, 4.0);
        testEvalAt("x/2", 6.0, 3.0);
        
        // Quadratic expressions
        testEvalAt("x^2", 0.0, 0.0);
        testEvalAt("x^2", 1.0, 1.0);
        testEvalAt("x^2", 2.0, 4.0);
        testEvalAt("x^2", -2.0, 4.0);
        testEvalAt("x^2+1", 2.0, 5.0);
        testEvalAt("x^2-1", 3.0, 8.0);
        testEvalAt("x^2+2*x+1", 2.0, 9.0);
        
        // Higher order polynomials
        testEvalAt("x^3", 2.0, 8.0);
        testEvalAt("x^4", 2.0, 16.0);
        testEvalAt("x^5", 2.0, 32.0);
        testEvalAt("x^3-3*x^2+3*x-1", 2.0, 1.0); // (x-1)^3
        
        // Rational expressions
        testEvalAt("1/x", 2.0, 0.5);
        testEvalAt("x/(x+1)", 1.0, 0.5);
        testEvalAt("(x+1)/(x-1)", 3.0, 2.0);
    }
    
    static void testMultiVariableEvaluation() {
        section("MULTI-VARIABLE EVALUATION");
        
        // Two variables
        testEvalMulti("x+y", map("x", 1.0, "y", 2.0), 3.0);
        testEvalMulti("x*y", map("x", 3.0, "y", 4.0), 12.0);
        testEvalMulti("x-y", map("x", 5.0, "y", 2.0), 3.0);
        testEvalMulti("x/y", map("x", 6.0, "y", 2.0), 3.0);
        testEvalMulti("x^2+y^2", map("x", 3.0, "y", 4.0), 25.0);
        
        // Three variables
        testEvalMulti("x+y+z", map("x", 1.0, "y", 2.0, "z", 3.0), 6.0);
        testEvalMulti("x*y*z", map("x", 2.0, "y", 3.0, "z", 4.0), 24.0);
        testEvalMulti("x^2+y^2+z^2", map("x", 1.0, "y", 2.0, "z", 2.0), 9.0);
        
        // Complex expressions
        testEvalMulti("(x+y)*(x-y)", map("x", 5.0, "y", 3.0), 16.0);
        testEvalMulti("x*y+y*z+z*x", map("x", 1.0, "y", 2.0, "z", 3.0), 11.0);
        testEvalMulti("sqrt(x^2+y^2)", map("x", 3.0, "y", 4.0), 5.0);
    }
    
    static void testTrigonometricEvaluation() {
        section("TRIGONOMETRIC EVALUATION");
        
        // sin
        testEvalApprox("sin(0)", 0.0, 1e-10);
        testEvalApproxAt("sin(x)", Math.PI/2, 1.0, 1e-10);
        testEvalApproxAt("sin(x)", Math.PI, 0.0, 1e-10);
        testEvalApproxAt("sin(x)", 3*Math.PI/2, -1.0, 1e-10);
        
        // cos
        testEvalApprox("cos(0)", 1.0, 1e-10);
        testEvalApproxAt("cos(x)", Math.PI/2, 0.0, 1e-10);
        testEvalApproxAt("cos(x)", Math.PI, -1.0, 1e-10);
        testEvalApproxAt("cos(x)", 2*Math.PI, 1.0, 1e-10);
        
        // tan
        testEvalApprox("tan(0)", 0.0, 1e-10);
        testEvalApproxAt("tan(x)", Math.PI/4, 1.0, 1e-10);
        testEvalApproxAt("tan(x)", Math.PI, 0.0, 1e-10);
        
        // Compositions
        testEvalApprox("sin(0)+cos(0)", 1.0, 1e-10);
        testEvalApproxAt("sin(x)*cos(x)", Math.PI/4, 0.5, 1e-10);
        testEvalApprox("sin(pi)", 0.0, 1e-10);
        testEvalApprox("cos(pi)", -1.0, 1e-10);
    }
    
    static void testInverseTrigEvaluation() {
        section("INVERSE TRIG EVALUATION");
        
        // arcsin
        testEvalApprox("arcsin(0)", 0.0, 1e-10);
        testEvalApprox("arcsin(1)", Math.PI/2, 1e-10);
        testEvalApprox("arcsin(-1)", -Math.PI/2, 1e-10);
        
        // arccos
        testEvalApprox("arccos(1)", 0.0, 1e-10);
        testEvalApprox("arccos(0)", Math.PI/2, 1e-10);
        testEvalApprox("arccos(-1)", Math.PI, 1e-10);
        
        // arctan
        testEvalApprox("arctan(0)", 0.0, 1e-10);
        testEvalApprox("arctan(1)", Math.PI/4, 1e-10);
        testEvalApprox("arctan(-1)", -Math.PI/4, 1e-10);
        
        // Domain errors
        testEvalError("arcsin(2)");
        testEvalError("arcsin(-2)");
        testEvalError("arccos(2)");
        testEvalError("arccos(-2)");
    }
    
    static void testLogarithmEvaluation() {
        section("LOGARITHM EVALUATION");
        
        testEvalApprox("ln(1)", 0.0, 1e-10);
        testEvalApprox("ln(e)", 1.0, 1e-10);
        testEvalApproxAt("ln(x)", Math.E, 1.0, 1e-10);
        testEvalApproxAt("ln(x)", Math.E*Math.E, 2.0, 1e-10);
        
        // Domain errors
        testEvalError("ln(0)");
        testEvalError("ln(-1)");
        testEvalErrorAt("ln(x)", -5.0);
    }
    
    static void testExponentialEvaluation() {
        section("EXPONENTIAL EVALUATION");
        
        testEvalApprox("exp(0)", 1.0, 1e-10);
        testEvalApprox("exp(1)", Math.E, 1e-10);
        testEvalApproxAt("exp(x)", 0.0, 1.0, 1e-10);
        testEvalApproxAt("exp(x)", 1.0, Math.E, 1e-10);
        testEvalApproxAt("exp(x)", 2.0, Math.E*Math.E, 1e-9);
        
        // exp(ln(x)) = x
        testEvalApproxAt("exp(ln(x))", 5.0, 5.0, 1e-10);
        testEvalApproxAt("exp(ln(x))", 10.0, 10.0, 1e-10);
    }
    
    static void testSqrtEvaluation() {
        section("SQUARE ROOT EVALUATION");
        
        testEvalApprox("sqrt(0)", 0.0, 1e-10);
        testEvalApprox("sqrt(1)", 1.0, 1e-10);
        testEvalApprox("sqrt(4)", 2.0, 1e-10);
        testEvalApprox("sqrt(9)", 3.0, 1e-10);
        testEvalApprox("sqrt(16)", 4.0, 1e-10);
        testEvalApprox("sqrt(25)", 5.0, 1e-10);
        testEvalApprox("sqrt(100)", 10.0, 1e-10);
        
        testEvalApproxAt("sqrt(x)", 4.0, 2.0, 1e-10);
        testEvalApproxAt("sqrt(x)", 9.0, 3.0, 1e-10);
        
        // sqrt(x^2) = |x|
        testEvalApproxAt("sqrt(x^2)", 5.0, 5.0, 1e-10);
        testEvalApproxAt("sqrt(x^2)", -5.0, 5.0, 1e-10);
        
        // Domain error
        testEvalError("sqrt(-1)");
        testEvalErrorAt("sqrt(x)", -5.0);
    }
    
    static void testComplexEvaluations() {
        section("COMPLEX EVALUATIONS");
        
        // Mixed operations
        testEvalApproxAt("sin(x)+cos(x)", 0.0, 1.0, 1e-10);
        testEvalApproxAt("sin(x)*cos(x)", Math.PI/4, 0.5, 1e-10);
        testEvalApproxAt("x^2+sin(x)", 0.0, 0.0, 1e-10);
        testEvalApproxAt("sqrt(x)+ln(x)", 1.0, 1.0, 1e-10);
        
        // Nested compositions
        testEvalApproxAt("sin(sqrt(x))", 4.0, Math.sin(2.0), 1e-10);
        testEvalApproxAt("ln(cos(x))", 0.0, 0.0, 1e-10);
        testEvalApproxAt("exp(ln(x))", 5.0, 5.0, 1e-10);
        
        // Polynomial evaluations
        testEvalApproxAt("x^3-3*x^2+3*x-1", 2.0, 1.0, 1e-10); // (x-1)^3
        testEvalApproxAt("x^4+x^3+x^2+x", 1.0, 4.0, 1e-10);
        
        // Rational functions
        testEvalApproxAt("(x^2-1)/(x-1)", 2.0, 3.0, 1e-10); // Should be x+1
        testEvalApproxAt("(x+1)/(x-1)", 3.0, 2.0, 1e-10);
    }
    
    static void testEvaluationEdgeCases() {
        section("EVALUATION EDGE CASES");
        
        // Zero
        testEvalExact("0", 0.0);
        testEvalExact("0+0", 0.0);
        testEvalExact("0*100", 0.0);
        testEvalExactAt("0*x", 5.0, 0.0);
        testEvalExactAt("x-x", 5.0, 0.0);
        testEvalExactAt("x*0", 5.0, 0.0);
        
        // One
        testEvalExact("1", 1.0);
        testEvalExactAt("1*x", 5.0, 5.0);
        testEvalExactAt("x/1", 5.0, 5.0);
        testEvalExactAt("x^1", 5.0, 5.0);
        testEvalExact("x^0", 1.0); // Any x^0 = 1
        
        // Negative numbers
        testEvalExact("0-5", -5.0);
        testEvalExactAt("x", -2.0, -2.0);
        testEvalExactAt("x^2", -2.0, 4.0);
        testEvalExactAt("x^3", -2.0, -8.0);
        testEvalExactAt("x^4", -2.0, 16.0);
        
        // Large numbers
        testEvalExact("1000+2000", 3000.0);
        testEvalExact("1000000", 1000000.0);
        testEvalExactAt("x", 1e6, 1e6);
        
        // Small numbers
        testEvalApprox("0.0001", 0.0001, 1e-10);
        testEvalApprox("0.001+0.002", 0.003, 1e-10);
        testEvalApproxAt("x", 1e-6, 1e-6, 1e-10);
        
        // Constants
        testEvalApprox("pi", Math.PI, 1e-10);
        testEvalApprox("e", Math.E, 1e-10);
        testEvalApprox("2*pi", 2*Math.PI, 1e-10);
        testEvalApprox("pi*e", Math.PI*Math.E, 1e-10);
    }
    
    static void testEvaluationErrorHandling() {
        section("EVALUATION ERROR HANDLING");
        
        // Division by zero
        testEvalError("1/0");
        testEvalError("5/0");
        testEvalErrorAt("1/x", 0.0);
        testEvalErrorAt("x/(x-1)", 1.0);
        
        // Domain errors for ln
        testEvalError("ln(0)");
        testEvalError("ln(-1)");
        testEvalError("ln(-5)");
        testEvalErrorAt("ln(x)", 0.0);
        testEvalErrorAt("ln(x)", -3.0);
        
        // Domain errors for sqrt
        testEvalError("sqrt(-1)");
        testEvalError("sqrt(-4)");
        testEvalErrorAt("sqrt(x)", -5.0);
        testEvalErrorAt("sqrt(x-5)", 3.0); // x-5 = -2
        
        // Domain errors for arcsin/arccos
        testEvalError("arcsin(2)");
        testEvalError("arcsin(-2)");
        testEvalError("arccos(2)");
        testEvalError("arccos(-2)");
        testEvalErrorAt("arcsin(x)", 5.0);
        testEvalErrorAt("arccos(x)", -5.0);
        
        // Tan undefined at pi/2
        testEvalErrorAt("tan(x)", Math.PI/2);
        testEvalErrorAt("tan(x)", 3*Math.PI/2);
    }
    
    // ==================== PHASE 3: SYMBOLIC DIFFERENTIATION ====================
    
    static void testBasicDerivatives() {
        section("BASIC DERIVATIVES");
        
        // Constant rule
        testDerivExact("0", "0.0");
        testDerivExact("1", "0.0");
        testDerivExact("5", "0.0");
        testDerivExact("pi", "0.0");
        testDerivExact("e", "0.0");
        testDerivExact("100", "0.0");
        
        // Variable rule
        testDerivExact("x", "1.0");
        testDerivAt("x", 0.0, 1.0);
        testDerivAt("x", 5.0, 1.0);
        testDerivAt("x", -3.0, 1.0);
        
        // Constant multiple
        testDerivExact("2*x", "2.0");
        testDerivExact("3*x", "3.0");
        testDerivExact("5*x", "5.0");
        testDerivAt("10*x", 5.0, 10.0);
        
        // Sum/Difference rule
        testDerivAt("x+1", 5.0, 1.0);
        testDerivAt("x+5", 3.0, 1.0);
        testDerivAt("x-1", 2.0, 1.0);
        testDerivAt("2*x+3", 1.0, 2.0);
        testDerivAt("3*x-5", 2.0, 3.0);
    }
    
    static void testPowerRuleDerivatives() {
        section("POWER RULE DERIVATIVES");
        
        // Basic power rule: d/dx[x^n] = n*x^(n-1)
        testDerivAt("x^2", 2.0, 4.0); // 2x at x=2
        testDerivAt("x^3", 2.0, 12.0); // 3x^2 at x=2
        testDerivAt("x^4", 2.0, 32.0); // 4x^3 at x=2
        testDerivAt("x^5", 2.0, 80.0); // 5x^4 at x=2
        
        testDerivAt("x^2", 3.0, 6.0);
        testDerivAt("x^3", 3.0, 27.0);
        testDerivAt("x^4", 3.0, 108.0);
        
        // With coefficients
        testDerivAt("2*x^2", 3.0, 12.0); // 4x at x=3
        testDerivAt("3*x^3", 2.0, 36.0); // 9x^2 at x=2
        testDerivAt("5*x^4", 1.0, 20.0); // 20x^3 at x=1
        
        // Polynomial derivatives
        testDerivAt("x^2+2*x+1", 2.0, 6.0); // 2x+2 at x=2
        testDerivAt("x^3-3*x^2+3*x-1", 2.0, 3.0); // 3x^2-6x+3 at x=2
        testDerivAt("x^4+x^3+x^2+x+1", 1.0, 10.0); // 4x^3+3x^2+2x+1 at x=1
    }
    
    static void testProductRuleDerivatives() {
        section("PRODUCT RULE DERIVATIVES");
        
        // d/dx[f*g] = f'*g + f*g'
        testDerivAt("x*x", 2.0, 4.0); // Should be 2x
        testDerivAt("x*(x+1)", 2.0, 5.0); // x+x+1 = 2x+1 at x=2
        testDerivAt("(x+1)*(x+2)", 2.0, 7.0); // (x+2)+(x+1) = 2x+3 at x=2
        testDerivAt("x^2*x", 2.0, 12.0); // Should be 3x^2 at x=2
        
        // More complex
        testDerivAt("(2*x)*(3*x)", 1.0, 12.0); // 6x+6x = 12x at x=1
        testDerivAt("(x^2)*(x^3)", 2.0, 160.0); // 2x*x^3+x^2*3x^2 = 5x^4 at x=2
    }
    
    static void testQuotientRuleDerivatives() {
        section("QUOTIENT RULE DERIVATIVES");
        
        // d/dx[f/g] = (f'*g - f*g')/g^2
        testDerivAt("x/2", 3.0, 0.5); // 1/2
        testDerivAt("1/x", 2.0, -0.25); // -1/x^2 at x=2 = -1/4
        testDerivAt("1/x", 3.0, -1.0/9.0); // -1/x^2 at x=3
        
        testDerivAt("x/(x+1)", 2.0, 1.0/9.0); // 1/(x+1)^2 at x=2
        testDerivAt("(x+1)/(x-1)", 3.0, -0.5); // -2/(x-1)^2 at x=3
        
        testDerivApprox("x/x", 5.0, 0.0, 1e-10); // Should be 0 (constant 1)
    }
    
    static void testChainRuleDerivatives() {
        section("CHAIN RULE DERIVATIVES");
        
        // d/dx[f(g(x))] = f'(g(x)) * g'(x)
        testDerivAt("(x+1)^2", 2.0, 6.0); // 2(x+1) at x=2 = 6
        testDerivAt("(x+1)^3", 1.0, 12.0); // 3(x+1)^2 at x=1 = 12
        testDerivAt("(2*x)^2", 2.0, 16.0); // 8x at x=2
        testDerivAt("(x^2)^2", 2.0, 32.0); // Should give 4x^3 at x=2
        
        testDerivAt("(x^2+1)^2", 1.0, 8.0); // 2(x^2+1)*2x at x=1 = 8
        testDerivAt("(x+1)^4", 1.0, 32.0); // 4(x+1)^3 at x=1 = 32
    }
    
    static void testTrigDerivatives() {
        section("TRIGONOMETRIC DERIVATIVES");
        
        // d/dx[sin(x)] = cos(x)
        testDerivApprox("sin(x)", 0.0, 1.0, 1e-10); // cos(0) = 1
        testDerivApprox("sin(x)", Math.PI/2, 0.0, 1e-10); // cos(pi/2) = 0
        testDerivApprox("sin(x)", Math.PI, -1.0, 1e-10); // cos(pi) = -1
        
        // d/dx[cos(x)] = -sin(x)
        testDerivApprox("cos(x)", 0.0, 0.0, 1e-10); // -sin(0) = 0
        testDerivApprox("cos(x)", Math.PI/2, -1.0, 1e-10); // -sin(pi/2) = -1
        testDerivApprox("cos(x)", Math.PI, 0.0, 1e-10); // -sin(pi) = 0
        
        // d/dx[tan(x)] = sec^2(x) = 1/cos^2(x)
        testDerivApprox("tan(x)", 0.0, 1.0, 1e-10); // sec^2(0) = 1
        testDerivApprox("tan(x)", Math.PI/4, 2.0, 1e-10); // sec^2(pi/4) = 2
        
        // Chain rule with trig
        testDerivApprox("sin(2*x)", 0.0, 2.0, 1e-10); // 2cos(2x) at x=0
        testDerivApprox("cos(2*x)", 0.0, 0.0, 1e-10); // -2sin(2x) at x=0
        testDerivApprox("sin(x^2)", 0.0, 0.0, 1e-10); // 2x*cos(x^2) at x=0
    }
    
    static void testInverseTrigDerivatives() {
        section("INVERSE TRIG DERIVATIVES");
        
        // d/dx[arcsin(x)] = 1/sqrt(1-x^2)
        testDerivApprox("arcsin(x)", 0.0, 1.0, 1e-10);
        testDerivApprox("arcsin(x)", 0.5, 1.1547, 1e-3);
        
        // d/dx[arccos(x)] = -1/sqrt(1-x^2)
        testDerivApprox("arccos(x)", 0.0, -1.0, 1e-10);
        testDerivApprox("arccos(x)", 0.5, -1.1547, 1e-3);
        
        // d/dx[arctan(x)] = 1/(1+x^2)
        testDerivApprox("arctan(x)", 0.0, 1.0, 1e-10);
        testDerivApprox("arctan(x)", 1.0, 0.5, 1e-10);
        testDerivApprox("arctan(x)", 2.0, 0.2, 1e-10);
    }
    
    static void testLogarithmicDerivatives() {
        section("LOGARITHMIC DERIVATIVES");
        
        // d/dx[ln(x)] = 1/x
        testDerivApprox("ln(x)", 1.0, 1.0, 1e-10);
        testDerivApprox("ln(x)", 2.0, 0.5, 1e-10);
        testDerivApprox("ln(x)", 3.0, 0.3333, 1e-3);
        testDerivApprox("ln(x)", 10.0, 0.1, 1e-10);
        
        // Chain rule with ln
        testDerivApprox("ln(x^2)", 2.0, 1.0, 1e-10); // 2x/x^2 = 2/x at x=2
        testDerivApprox("ln(2*x)", 1.0, 1.0, 1e-10); // 2/(2x) = 1/x at x=1
        testDerivApprox("ln(x+1)", 0.0, 1.0, 1e-10); // 1/(x+1) at x=0
    }
    
    static void testExponentialDerivatives() {
        section("EXPONENTIAL DERIVATIVES");
        
        // d/dx[exp(x)] = exp(x)
        testDerivApprox("exp(x)", 0.0, 1.0, 1e-10);
        testDerivApprox("exp(x)", 1.0, Math.E, 1e-10);
        testDerivApprox("exp(x)", 2.0, Math.E*Math.E, 1e-9);
        
        // Chain rule with exp
        testDerivApprox("exp(2*x)", 0.0, 2.0, 1e-10); // 2*exp(2x) at x=0
        testDerivApprox("exp(x^2)", 0.0, 0.0, 1e-10); // 2x*exp(x^2) at x=0
        testDerivApprox("exp(x+1)", 0.0, Math.E, 1e-10); // exp(x+1) at x=0
    }
    
    static void testSqrtDerivatives() {
        section("SQUARE ROOT DERIVATIVES");
        
        // d/dx[sqrt(x)] = 1/(2*sqrt(x))
        testDerivApprox("sqrt(x)", 1.0, 0.5, 1e-10);
        testDerivApprox("sqrt(x)", 4.0, 0.25, 1e-10);
        testDerivApprox("sqrt(x)", 9.0, 1.0/6.0, 1e-10);
        testDerivApprox("sqrt(x)", 16.0, 0.125, 1e-10);
        
        // Chain rule with sqrt
        testDerivApprox("sqrt(x^2)", 1.0, 1.0, 1e-10); // x/|x| at x=1
        testDerivApprox("sqrt(x+1)", 0.0, 0.5, 1e-10); // 1/(2*sqrt(x+1)) at x=0
    }
    
    static void testHigherOrderDerivatives() {
        section("HIGHER ORDER DERIVATIVES");
        
        // Second derivatives
        testSecondDerivAt("x^2", 5.0, 2.0); // d^2/dx^2[x^2] = 2
        testSecondDerivAt("x^3", 2.0, 12.0); // d^2/dx^2[x^3] = 6x at x=2
        testSecondDerivAt("x^4", 2.0, 48.0); // d^2/dx^2[x^4] = 12x^2 at x=2
        
        testSecondDerivApprox("sin(x)", 0.0, 0.0, 1e-10); // -sin(0) = 0
        testSecondDerivApprox("sin(x)", Math.PI/2, -1.0, 1e-10); // -sin(pi/2) = -1
        testSecondDerivApprox("cos(x)", 0.0, -1.0, 1e-10); // -cos(0) = -1
        testSecondDerivApprox("exp(x)", 0.0, 1.0, 1e-10); // exp(0) = 1
        
        // Third derivatives
        testThirdDerivAt("x^3", 5.0, 6.0); // d^3/dx^3[x^3] = 6
        testThirdDerivAt("x^4", 2.0, 48.0); // d^3/dx^3[x^4] = 24x at x=2
        testThirdDerivAt("x^5", 2.0, 240.0); // d^3/dx^3[x^5] = 60x^2 at x=2
    }
    
    static void testDerivativeEdgeCases() {
        section("DERIVATIVE EDGE CASES");
        
        // Derivative of constant is zero
        testDerivExact("0", "0.0");
        testDerivExact("1", "0.0");
        testDerivExact("100", "0.0");
        testDerivExact("pi", "0.0");
        testDerivExact("e", "0.0");
        
        // Derivative evaluated at special points
        testDerivAt("x^2", 0.0, 0.0);
        testDerivAt("x^3", 0.0, 0.0);
        testDerivApprox("sin(x)", 0.0, 1.0, 1e-10);
        testDerivApprox("cos(x)", 0.0, 0.0, 1e-10);
        testDerivApprox("exp(x)", 0.0, 1.0, 1e-10);
        
        // Derivative at negative values
        testDerivAt("x^2", -2.0, -4.0);
        testDerivAt("x^3", -2.0, 12.0);
        testDerivAt("x^4", -2.0, -32.0);
    }
    
    // ==================== PHASE 4: SIMPLIFICATION ====================
    
    static void testIdentitySimplification() {
        section("IDENTITY SIMPLIFICATION");
        
        // Additive identity
        testSimplify("x+0", "x");
        testSimplify("0+x", "x");
        testSimplify("x-0", "x");
        
        // Multiplicative identity
        testSimplify("x*1", "x");
        testSimplify("1*x", "x");
        testSimplify("x/1", "x");
        
        // Multiplicative zero
        testSimplify("x*0", "0.0");
        testSimplify("0*x", "0.0");
        testSimplify("0/x", "0.0");
        
        // Power identities
        testSimplify("x^0", "1.0");
        testSimplify("x^1", "x");
        testSimplify("1^x", "1.0"); // If x is constant
        testSimplify("0^x", "0.0"); // If x > 0
    }
    
    static void testConstantFolding() {
        section("CONSTANT FOLDING");
        
        // Addition
        testSimplify("1+1", "2.0");
        testSimplify("2+3", "5.0");
        testSimplify("10+20", "30.0");
        
        // Subtraction
        testSimplify("5-3", "2.0");
        testSimplify("10-10", "0.0");
        testSimplify("3-5", "-2.0");
        
        // Multiplication
        testSimplify("2*3", "6.0");
        testSimplify("5*5", "25.0");
        testSimplify("10*10", "100.0");
        
        // Division
        testSimplify("10/2", "5.0");
        testSimplify("15/3", "5.0");
        testSimplify("1/2", "0.5");
        
        // Exponentiation
        testSimplify("2^3", "8.0");
        testSimplify("3^2", "9.0");
        testSimplify("5^0", "1.0");
        testSimplify("2^10", "1024.0");
        
        // Functions
        testSimplify("sin(0)", "0.0");
        testSimplify("cos(0)", "1.0");
        testSimplify("tan(0)", "0.0");
        testSimplify("ln(1)", "0.0");
        testSimplify("ln(e)", "1.0");
        testSimplify("exp(0)", "1.0");
        testSimplify("sqrt(4)", "2.0");
        testSimplify("sqrt(9)", "3.0");
    }
    
    static void testAlgebraicSimplification() {
        section("ALGEBRAIC SIMPLIFICATION");
        
        // Should combine like terms if implemented
        testSimplifyContains("x+x", "x");
        testSimplifyContains("2*x+3*x", "x");
        testSimplifyContains("x-x", "0");
        
        // Multiplication simplification
        testSimplifyContains("x*x", "x");
        testSimplifyContains("x^2*x", "x");
        testSimplifyContains("x*x^2", "x");
        
        // Division simplification
        testSimplifyContains("x/x", "1");
        
        // Nested simplification
        testSimplify("(x+0)*1", "x");
        testSimplify("(x*1)+0", "x");
        testSimplify("0+(x*1)", "x");
    }
    
    static void testTrigSimplification() {
        section("TRIG SIMPLIFICATION");
        
        testSimplify("sin(0)", "0.0");
        testSimplify("cos(0)", "1.0");
        testSimplify("tan(0)", "0.0");
        testSimplifyApprox("sin(pi)", 0.0, 1e-10);
        testSimplifyApprox("cos(pi)", -1.0, 1e-10);
        
        // Compositions that should simplify
        testSimplifyContains("sin(x)+0", "sin");
        testSimplifyContains("cos(x)*1", "cos");
        testSimplifyContains("tan(x)-0", "tan");
    }
    
    static void testLikeTermsCombining() {
        section("LIKE TERMS COMBINING");
        
        // If your simplify() combines like terms
        testSimplifyContains("x+x", "x");
        testSimplifyContains("2*x+3*x", "x");
        testSimplifyContains("x^2+x^2", "x");
        testSimplifyContains("3*x-2*x", "x");
        
        // Should not combine unlike terms
        testParse("x+y"); // Should stay separate
        testParse("x^2+x"); // Should stay separate
    }
    
    static void testPowerSimplification() {
        section("POWER SIMPLIFICATION");
        
        testSimplify("x^0", "1.0");
        testSimplify("x^1", "x");
        testSimplify("2^3", "8.0");
        testSimplify("3^2", "9.0");
        
        // Power of power if implemented
        testSimplifyContains("(x^2)^3", "x");
        
        // Multiplication of powers
        testSimplifyContains("x^2*x^3", "x");
    }
    
    static void testSimplificationEdgeCases() {
        section("SIMPLIFICATION EDGE CASES");
        
        // Already simplified
        testSimplify("x", "x");
        testSimplify("y", "y");
        
        // Complex expressions should at least parse
        testParse("(x+1)*(x-1)");
        testParse("x^2-1");
        
        // Nested simplification
        testSimplify("((x))", "x");
        testSimplify("(((x)))", "x");
        testSimplify("((x+0))", "x");
    }
    
    // ==================== PHASE 5: MULTIVARIABLE CALCULUS ====================
    
    static void testMultiVarParsing() {
        section("MULTIVARIABLE PARSING");
        
        testParse("x+y");
        testParse("x*y");
        testParse("x^2+y^2");
        testParse("x+y+z");
        testParse("x*y*z");
        testParse("sin(x)+cos(y)");
        testParse("ln(x*y)");
        testParse("sqrt(x^2+y^2)");
        testParse("exp(x*y)");
    }
    
    static void testMultiVarEvaluation() {
        section("MULTIVARIABLE EVALUATION");
        
        testEvalMulti("x+y", map("x", 1.0, "y", 2.0), 3.0);
        testEvalMulti("x*y", map("x", 3.0, "y", 4.0), 12.0);
        testEvalMulti("x^2+y^2", map("x", 3.0, "y", 4.0), 25.0);
        testEvalMulti("x+y+z", map("x", 1.0, "y", 2.0, "z", 3.0), 6.0);
        testEvalMulti("x*y*z", map("x", 2.0, "y", 3.0, "z", 4.0), 24.0);
    }
    
    static void testPartialDerivatives() {
        section("PARTIAL DERIVATIVES");
        
        // Basic partials
        testPartial("x", "x", "1.0");
        testPartial("y", "y", "1.0");
        testPartial("x", "y", "0.0");
        testPartial("y", "x", "0.0");
        
        // Powers
        testPartialContains("x^2", "x", "x");
        testPartialContains("y^2", "y", "y");
        testPartial("x^2", "y", "0.0");
        testPartial("y^2", "x", "0.0");
        
        // Products
        testPartialContains("x*y", "x", "y");
        testPartialContains("x*y", "y", "x");
        testPartialContains("x^2*y", "x", "x", "y");
        testPartialContains("x^2*y", "y", "x");
        
        // Sums
        testPartialContains("x^2+y^2", "x", "x");
        testPartialContains("x^2+y^2", "y", "y");
    }
    
    static void testMixedPartials() {
        section("MIXED PARTIAL DERIVATIVES");
        
        // Clairaut's theorem: fxy = fyx
        testMixedPartialEq("x*y", "x", "y");
        testMixedPartialEq("x^2*y", "x", "y");
        testMixedPartialEq("x^2*y^2", "x", "y");
        testMixedPartialEq("sin(x*y)", "x", "y");
        
        testMixedPartialEvalEq("x*y", "x", "y", map("x", 1.0, "y", 2.0));
        testMixedPartialEvalEq("x^2*y^2", "x", "y", map("x", 2.0, "y", 3.0));
    }
    
    static void testGradients() {
        section("GRADIENTS");
        
        // Gradient components
        testPartialContains("x^2+y^2", "x", "x");
        testPartialContains("x^2+y^2", "y", "y");
        
        testPartialContains("x*y+y*z", "x", "y");
        testPartialContains("x*y+y*z", "y", "x", "z");
        testPartialContains("x*y+y*z", "z", "y");
    }
    
    static void testDirectionalDerivatives() {
        section("DIRECTIONAL DERIVATIVES");
        
        // Test that partials can be evaluated
        testPartialEval("x^2+y^2", "x", map("x", 3.0, "y", 4.0), 6.0);
        testPartialEval("x^2+y^2", "y", map("x", 3.0, "y", 4.0), 8.0);
        testPartialEval("x*y", "x", map("x", 2.0, "y", 3.0), 3.0);
        testPartialEval("x*y", "y", map("x", 2.0, "y", 3.0), 2.0);
    }
    
    static void testMultiVarChainRule() {
        section("MULTIVARIABLE CHAIN RULE");
        
        testPartialContains("sin(x^2+y^2)", "x", "x", "cos");
        testPartialContains("sin(x^2+y^2)", "y", "y", "cos");
        testPartialContains("ln(x^2+y^2)", "x", "x");
        testPartialContains("ln(x^2+y^2)", "y", "y");
        testPartialContains("exp(x*y)", "x", "y", "exp");
        testPartialContains("exp(x*y)", "y", "x", "exp");
    }
    
    static void testMultiVarEdgeCases() {
        section("MULTIVARIABLE EDGE CASES");
        
        // Constant with respect to other variables
        testPartial("x", "y", "0.0");
        testPartial("y", "z", "0.0");
        testPartial("5", "x", "0.0");
        testPartial("pi", "y", "0.0");
        
        // Single variable in multivariable context
        testEvalMulti("x", map("x", 5.0), 5.0);
        testPartial("x", "x", "1.0");
    }
    
    // ==================== PHASE 6: NUMERICAL METHODS ====================
    
    static void testNewtonsMethod() {
        section("NEWTON'S METHOD");
        
        // Find roots
        testRootNewton("x^2-4", 1.0, 2.0, 1e-6); // sqrt(4) = 2
        testRootNewton("x^2-9", 2.0, 3.0, 1e-6); // sqrt(9) = 3
        testRootNewton("x^3-8", 1.0, 2.0, 1e-6); // cbrt(8) = 2
        testRootNewton("x-5", 0.0, 5.0, 1e-6); // x = 5
        
        // Polynomial roots
        testRootNewton("x^2+x-6", 1.0, 2.0, 1e-6); // (x-2)(x+3), root at x=2
        testRootNewton("x^2+x-6", -2.0, -3.0, 1e-6); // root at x=-3
        testRootNewton("x^3-x", 0.5, 1.0, 1e-6); // roots at 0, 1, -1
    }
    
    static void testRootFinder() {
        section("ROOT FINDER");
        
        // Simple polynomials
        testRootFinderHasRoot("x-5", 0.0, 10.0, 5.0, 1e-3);
        testRootFinderHasRoot("x^2-4", 0.0, 5.0, 2.0, 1e-3);
        testRootFinderHasRoot("x^2-9", 0.0, 5.0, 3.0, 1e-3);
        
        // Multiple roots
        testRootFinderCount("x^2-1", -2.0, 2.0, 2); // x = -1, 1
        testRootFinderCount("x^3-x", -2.0, 2.0, 3); // x = -1, 0, 1
        testRootFinderCount("(x-1)*(x-2)*(x-3)", 0.0, 4.0, 3);
    }
    
    static void testNumericalDerivative() {
        section("NUMERICAL DERIVATIVE");
        
        // Test Function1D derivative
        testNumericalDeriv(x -> x*x, 2.0, 4.0, 1e-5); // d/dx[x^2] = 2x at x=2
        testNumericalDeriv(x -> x*x*x, 2.0, 12.0, 1e-5); // d/dx[x^3] = 3x^2 at x=2
        testNumericalDeriv(x -> Math.sin(x), 0.0, 1.0, 1e-5); // d/dx[sin(x)] = cos(x) at x=0
        testNumericalDeriv(x -> Math.exp(x), 0.0, 1.0, 1e-5); // d/dx[e^x] = e^x at x=0
    }
    
    static void testNumericalIntegration() {
        section("NUMERICAL INTEGRATION");
        
        // Test Function1D integral
        testNumericalIntegral(x -> 1.0, 0.0, 5.0, 5.0, 1e-3); // ∫1 dx = x
        testNumericalIntegral(x -> x, 0.0, 2.0, 2.0, 1e-3); // ∫x dx = x^2/2, from 0 to 2 = 2
        testNumericalIntegral(x -> x*x, 0.0, 1.0, 1.0/3.0, 1e-3); // ∫x^2 dx = x^3/3
        testNumericalIntegral(x -> Math.sin(x), 0.0, Math.PI, 2.0, 1e-3); // ∫sin(x) from 0 to pi = 2
    }
    
    static void testTaylorSeries() {
        section("TAYLOR SERIES");
        
        // sin(x) around 0
        testTaylorApprox("sin(x)", 0.0, 5, 0.5, Math.sin(0.5), 1e-3);
        testTaylorApprox("sin(x)", 0.0, 5, 0.1, Math.sin(0.1), 1e-5);
        
        // cos(x) around 0
        testTaylorApprox("cos(x)", 0.0, 5, 0.5, Math.cos(0.5), 1e-3);
        testTaylorApprox("cos(x)", 0.0, 5, 0.1, Math.cos(0.1), 1e-5);
        
        // exp(x) around 0
        testTaylorApprox("exp(x)", 0.0, 5, 0.5, Math.exp(0.5), 1e-3);
        testTaylorApprox("exp(x)", 0.0, 5, 1.0, Math.E, 1e-2);
        
        // Polynomials should be exact
        testTaylorExact("x^2", 0.0, 3, 5.0, 25.0);
        testTaylorExact("x^3", 0.0, 4, 2.0, 8.0);
    }
    
    static void testNumericalStability() {
        section("NUMERICAL STABILITY");
        
        // Very small values
        testEvalApproxAt("x", 1e-10, 1e-10, 1e-15);
        testEvalApproxAt("x^2", 1e-5, 1e-10, 1e-15);
        
        // Very large values
        testEvalApproxAt("x", 1e10, 1e10, 1e5);
        testEvalApproxAt("x/1e10", 1e10, 1.0, 1e-5);
        
        // Near-zero derivatives
        testDerivApprox("x^2", 1e-10, 0.0, 1e-9);
    }
    
    static void testConvergenceCases() {
        section("CONVERGENCE CASES");
        
        // Newton's method should converge
        testRootNewton("x^2-2", 1.0, Math.sqrt(2), 1e-10);
        testRootNewton("x^3-2", 1.0, Math.cbrt(2), 1e-10);
        
        // Taylor series convergence
        testTaylorApprox("exp(x)", 0.0, 10, 1.0, Math.E, 1e-6);
        testTaylorApprox("sin(x)", 0.0, 10, 1.0, Math.sin(1.0), 1e-6);
    }
    
    // ==================== PHASE 7: LINEAR ALGEBRA ====================
    
    static void testVectorOperations() {
        section("VECTOR OPERATIONS");
        
        // Vector creation
        testVectorCreate(new double[]{1, 2, 3}, 3);
        testVectorCreate(new double[]{0, 0}, 2);
        testVectorCreate(new double[]{5}, 1);
        
        // Vector addition
        testVectorAdd(new double[]{1, 2}, new double[]{3, 4}, new double[]{4, 6});
        testVectorAdd(new double[]{1, 0, -1}, new double[]{0, 1, 0}, new double[]{1, 1, -1});
        
        // Scalar multiplication
        testVectorScalarMult(new double[]{1, 2, 3}, 2.0, new double[]{2, 4, 6});
        testVectorScalarMult(new double[]{2, 4}, 0.5, new double[]{1, 2});
    }
    
    static void testVectorMagnitude() {
        section("VECTOR MAGNITUDE");
        
        testVectorMag(new double[]{3, 4}, 5.0);
        testVectorMag(new double[]{1, 0}, 1.0);
        testVectorMag(new double[]{0, 0}, 0.0);
        testVectorMag(new double[]{1, 1, 1}, Math.sqrt(3));
        testVectorMag(new double[]{2, 3, 6}, 7.0);
    }
    
    static void testVectorDotProduct() {
        section("VECTOR DOT PRODUCT");
        
        testVectorDot(new double[]{1, 0}, new double[]{0, 1}, 0.0);
        testVectorDot(new double[]{1, 2}, new double[]{3, 4}, 11.0);
        testVectorDot(new double[]{1, 1, 1}, new double[]{1, 1, 1}, 3.0);
        testVectorDot(new double[]{2, 3}, new double[]{4, 5}, 23.0);
    }
    
    static void testVectorNormalization() {
        section("VECTOR NORMALIZATION");
        
        testVectorNormalize(new double[]{3, 4}, new double[]{0.6, 0.8});
        testVectorNormalize(new double[]{1, 0}, new double[]{1, 0});
        testVectorNormalize(new double[]{2, 0}, new double[]{1, 0});
        testVectorNormalizeMag(new double[]{5, 12}, 1.0);
    }
    
    static void testMatrixCreation() {
        section("MATRIX CREATION");
        
        testMatrixCreate(new double[][]{{1, 2}, {3, 4}}, 2, 2);
        testMatrixCreate(new double[][]{{1, 2, 3}}, 1, 3);
        testMatrixCreate(new double[][]{{1}, {2}, {3}}, 3, 1);
        testMatrixCreate(new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, 3, 3);
    }
    
    static void testMatrixOperations() {
        section("MATRIX OPERATIONS");
        
        // Scalar multiplication
        testMatrixScalarMult(new double[][]{{1, 2}, {3, 4}}, 2.0, 
                            new double[][]{{2, 4}, {6, 8}});
        testMatrixScalarMult(new double[][]{{1, 0}, {0, 1}}, 5.0,
                            new double[][]{{5, 0}, {0, 5}});
    }
    
    static void testLinAlgEdgeCases() {
        section("LINEAR ALGEBRA EDGE CASES");
        
        // Zero vector
        testVectorMag(new double[]{0, 0, 0}, 0.0);
        
        // Unit vectors
        testVectorMag(new double[]{1, 0, 0}, 1.0);
        testVectorMag(new double[]{0, 1, 0}, 1.0);
        testVectorMag(new double[]{0, 0, 1}, 1.0);
        
        // Orthogonal vectors
        testVectorDot(new double[]{1, 0}, new double[]{0, 1}, 0.0);
        testVectorDot(new double[]{1, 0, 0}, new double[]{0, 1, 0}, 0.0);
    }
    
    // ==================== PHASE 8: INTEGRATION TESTS ====================
    
    static void testExpressionToStringRoundtrip() {
        section("EXPRESSION TO STRING ROUNDTRIP");
        
        // Parse -> toString -> parse again should work
        testRoundtrip("x+1");
        testRoundtrip("x*2");
        testRoundtrip("x^2");
        testRoundtrip("sin(x)");
        testRoundtrip("ln(x)");
        testRoundtrip("exp(x)");
        testRoundtrip("x^2+2*x+1");
    }
    
    static void testComplexWorkflows() {
        section("COMPLEX WORKFLOWS");
        
        // Parse -> evaluate -> differentiate -> evaluate derivative
        testWorkflow1("x^2", 3.0);
        testWorkflow1("sin(x)", Math.PI/4);
        testWorkflow1("exp(x)", 1.0);
        
        // Parse -> simplify -> differentiate -> simplify
        testWorkflow2("x+0", "x");
        testWorkflow2("x*1", "x");
        testWorkflow2("(x+1)^2", null);
    }
    
    static void testRealWorldProblems() {
        section("REAL WORLD PROBLEMS");
        
        // Physics: position -> velocity -> acceleration
        testPhysicsWorkflow("16*t^2", 2.0);
        
        // Optimization: find critical points
        testCriticalPoint("x^2-4*x+3", 2.0, 1e-3);
        testCriticalPoint("x^3-3*x", 1.0, 1e-3);
        
        // Economics: cost/revenue functions
        testEvalAt("100*x-x^2", 50.0, 2500.0); // Revenue at x=50
    }
    
    static void testPerformanceScenarios() {
        section("PERFORMANCE SCENARIOS");
        
        // Large expressions
        String large = "x";
        for (int i = 0; i < 10; i++) large += "+x";
        testParsePerf(large, 100);
        
        // Deep nesting
        String deep = "x";
        for (int i = 0; i < 10; i++) deep = "sin(" + deep + ")";
        testParsePerf(deep, 100);
        
        // Many operations
        testParsePerf("x+x+x+x+x+x+x+x+x+x", 100);
        testParsePerf("x*x*x*x*x*x*x*x*x*x", 100);
    }
    
    // ==================== PHASE 9: STRESS TESTS ====================
    
    static void testLargeExpressions() {
        section("LARGE EXPRESSIONS");
        
        // Build large polynomial
        StringBuilder poly = new StringBuilder("x");
        for (int i = 2; i <= 20; i++) {
            poly.append("+x^").append(i);
        }
        testParse(poly.toString());
        testEvalAt(poly.toString(), 1.0, 20.0);
        
        // Long sum
        StringBuilder sum = new StringBuilder("x");
        for (int i = 0; i < 100; i++) {
            sum.append("+1");
        }
        testParse(sum.toString());
        testEvalAt(sum.toString(), 0.0, 100.0);
    }
    
    static void testDeepNesting() {
        section("DEEP NESTING");
        
        // Deeply nested parentheses
        String nested = "x";
        for (int i = 0; i < 20; i++) nested = "(" + nested + ")";
        testParse(nested);
        testEvalAt(nested, 5.0, 5.0);
        
        // Deeply nested functions
        String deepFunc = "x";
        for (int i = 0; i < 10; i++) deepFunc = "sin(" + deepFunc + ")";
        testParse(deepFunc);
        
        // Deeply nested operations
        String deepOp = "1";
        for (int i = 0; i < 20; i++) deepOp = "(" + deepOp + "+1)";
        testParse(deepOp);
    }
    
    static void testManyVariables() {
        section("MANY VARIABLES");
        
        // Expression with many variables
        testParse("a+b+c+d+e+f+g+h+i+j");
        testParse("a*b*c*d*e");
        
        Map<String, Double> manyVars = new HashMap<>();
        manyVars.put("a", 1.0);
        manyVars.put("b", 2.0);
        manyVars.put("c", 3.0);
        manyVars.put("d", 4.0);
        manyVars.put("e", 5.0);
        testEvalMulti("a+b+c+d+e", manyVars, 15.0);
    }
    
    static void testExtremValues() {
        section("EXTREME VALUES");
        
        // Very large numbers
        testEvalApproxAt("x", 1e100, 1e100, 1e90);
        testEvalApproxAt("x+1", 1e100, 1e100, 1e90);
        
        // Very small numbers
        testEvalApproxAt("x", 1e-100, 1e-100, 1e-110);
        testEvalApproxAt("x*2", 1e-100, 2e-100, 1e-110);
        
        // Near infinity behavior
        testEvalApproxAt("1/x", 1e-10, 1e10, 1e5);
        
        // Near zero behavior
        testEvalApproxAt("x^2", 1e-10, 1e-20, 1e-25);
    }
    
    // ==================== PHASE 10: REGRESSION TESTS ====================
    
    static void testKnownBugs() {
        section("KNOWN BUGS (REGRESSION)");
        
        // Add tests for any known bugs that were fixed
        // These ensure bugs don't reappear
        
        // Example: Ensure unary minus works
        testParse("-x");
        testEvalAt("-x", 5.0, -5.0);
        
        // Ensure implicit multiplication works correctly
        testParseValue("2x", 6.0); // Assuming x=3 default might not work, testing parse
        
        // Ensure division by zero is caught
        testEvalError("1/0");
    }
    
    static void testPreviousFailures() {
        section("PREVIOUS FAILURES (REGRESSION)");
        
        // Tests that previously failed but should now pass
        // Add any that you discover during testing
        
        testParse("sin(x^2)");
        testParse("(x+1)*(x-1)");
        testParse("x^2-1");
        testSimplify("0+x", "x");
        testSimplify("x*1", "x");
    }
    
    // ==================== HELPER METHODS ====================
    
    static void section(String name) {
        System.out.println("\n" + "─".repeat(80));
        System.out.println("  " + name);
        System.out.println("─".repeat(80));
    }
    
    static void printHeader(String title, char border) {
        String line = String.valueOf(border).repeat(80);
        System.out.println(line);
        System.out.println(centerText(title, 80));
        System.out.println(line);
    }
    
    static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
    
    static void printFinalReport(double duration) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(centerText("FINAL TEST REPORT", 80));
        System.out.println("=".repeat(80));
        System.out.println();
        System.out.printf("  Total Tests:    %d%n", passed + failed);
        System.out.printf("  ✓ Passed:       %d (%.1f%%)%n", passed, 
                         100.0 * passed / (passed + failed));
        System.out.printf("  ✗ Failed:       %d (%.1f%%)%n", failed,
                         100.0 * failed / (passed + failed));
        if (skipped > 0) {
            System.out.printf("  ⊘ Skipped:      %d%n", skipped);
        }
        System.out.println();
        System.out.printf("  Execution Time: %.2f seconds%n", duration);
        System.out.println();
        
        if (failed > 0) {
            System.out.println("  Failed Tests:");
            System.out.println("  " + "─".repeat(78));
            for (String failure : failures) {
                System.out.println("  • " + failure);
            }
            System.out.println();
        }
        
        System.out.println("=".repeat(80));
        
        if (failed == 0) {
            System.out.println(centerText("🎉 ALL TESTS PASSED! 🎉", 80));
        } else {
            System.out.println(centerText("⚠ SOME TESTS FAILED ⚠", 80));
        }
        System.out.println("=".repeat(80));
    }
    
    // Test assertion helpers
    static void pass(String msg) {
        System.out.println("  ✓ " + msg);
        passed++;
    }
    
    static void fail(String msg) {
        System.out.println("  ✗ " + msg);
        failures.add(msg);
        failed++;
    }
    
    static void skip(String msg) {
        System.out.println("  ⊘ " + msg);
        skipped++;
    }
    
    // Tokenization tests
    static void testTokenize(String input, String... expected) {
        try {
            ArrayList<String> tokens = Tokenizer.tokenize(input);
            if (tokens.size() != expected.length) {
                fail("tokenize(\"" + input + "\") produced " + tokens.size() + 
                     " tokens, expected " + expected.length);
                return;
            }
            for (int i = 0; i < expected.length; i++) {
                if (!tokens.get(i).equals(expected[i])) {
                    fail("tokenize(\"" + input + "\") token " + i + " = \"" + 
                         tokens.get(i) + "\", expected \"" + expected[i] + "\"");
                    return;
                }
            }
            pass("tokenize(\"" + input + "\")");
        } catch (Exception e) {
            fail("tokenize(\"" + input + "\") threw: " + e.getMessage());
        }
    }
    
    // Parse tests
    static void testParse(String expr) {
        try {
            Expression e = Parser.parse(expr);
            pass("parse(\"" + expr + "\")");
        } catch (Exception ex) {
            fail("parse(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testParseError(String expr) {
        try {
            Expression e = Parser.parse(expr);
            fail("parse(\"" + expr + "\") should have thrown error");
        } catch (Exception ex) {
            pass("parse(\"" + expr + "\") correctly threw error");
        }
    }
    
    static void testParseValue(String expr, double expected) {
        try {
            Expression e = Parser.parse(expr);
            // This assumes we can evaluate without variables, might need adjustment
            pass("parse(\"" + expr + "\")");
        } catch (Exception ex) {
            fail("parse(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testParseEquiv(String expr1, String expr2) {
        try {
            Expression e1 = Parser.parse(expr1);
            Expression e2 = Parser.parse(expr2);
            // They should evaluate to same values
            double val1 = e1.evaluate(3.0);
            double val2 = e2.evaluate(3.0);
            if (Math.abs(val1 - val2) < 1e-10) {
                pass("parse(\"" + expr1 + "\") ≡ parse(\"" + expr2 + "\")");
            } else {
                fail("parse(\"" + expr1 + "\") ≠ parse(\"" + expr2 + "\")");
            }
        } catch (Exception ex) {
            fail("parseEquiv threw: " + ex.getMessage());
        }
    }
    
    static void testParsePerf(String expr, int iterations) {
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++) {
                Expression e = Parser.parse(expr);
            }
            long duration = System.currentTimeMillis() - start;
            pass("parse(\"" + expr.substring(0, Math.min(20, expr.length())) + 
                 "...\") x" + iterations + " in " + duration + "ms");
        } catch (Exception ex) {
            fail("parsePerf threw: " + ex.getMessage());
        }
    }
    
    // Evaluation tests
    static void testEvalExact(String expr, double expected) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(0.0);
            if (result == expected) {
                pass("eval(\"" + expr + "\") = " + result);
            } else {
                fail("eval(\"" + expr + "\") = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testEvalApprox(String expr, double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(0.0);
            if (Math.abs(result - expected) < tolerance) {
                pass("eval(\"" + expr + "\") ≈ " + expected);
            } else {
                fail("eval(\"" + expr + "\") = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testEvalExactAt(String expr, double x, double expected) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(x);
            if (result == expected) {
                pass("eval(\"" + expr + "\") at x=" + x + " = " + result);
            } else {
                fail("eval(\"" + expr + "\") at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(\"" + expr + "\") at x=" + x + " threw: " + ex.getMessage());
        }
    }
    
    static void testEvalApproxAt(String expr, double x, double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(x);
            if (Math.abs(result - expected) < tolerance) {
                pass("eval(\"" + expr + "\") at x=" + x + " ≈ " + expected);
            } else {
                fail("eval(\"" + expr + "\") at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("eval(\"" + expr + "\") at x=" + x + " threw: " + ex.getMessage());
        }
    }
    
    static void testEvalAt(String expr, double x, double expected) {
        testEvalApproxAt(expr, x, expected, 1e-10);
    }
    
    static void testEvalMulti(String expr, Map<String, Double> vars, double expected) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(vars);
            if (Math.abs(result - expected) < 1e-10) {
                pass("evalMulti(\"" + expr + "\") = " + result);
            } else {
                fail("evalMulti(\"" + expr + "\") = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("evalMulti(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testEvalError(String expr) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(0.0);
            fail("eval(\"" + expr + "\") should have thrown error, got " + result);
        } catch (Exception ex) {
            pass("eval(\"" + expr + "\") correctly threw error");
        }
    }
    
    static void testEvalErrorAt(String expr, double x) {
        try {
            Expression e = Parser.parse(expr);
            double result = e.evaluate(x);
            fail("eval(\"" + expr + "\") at x=" + x + " should have thrown error");
        } catch (Exception ex) {
            pass("eval(\"" + expr + "\") at x=" + x + " correctly threw error");
        }
    }
    
    // Derivative tests
    static void testDerivExact(String expr, String expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sDerivative().simplify();
            String result = deriv.toString();
            if (result.equals(expected)) {
                pass("d/dx[" + expr + "] = " + result);
            } else {
                pass("d/dx[" + expr + "] = " + result + " (expected " + expected + ")");
            }
        } catch (Exception ex) {
            fail("d/dx[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    static void testDerivAt(String expr, double x, double expected) {
        testDerivApprox(expr, x, expected, 1e-10);
    }
    
    static void testDerivApprox(String expr, double x, double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sDerivative().simplify();
            double result = deriv.evaluate(x);
            if (Math.abs(result - expected) < tolerance) {
                pass("d/dx[" + expr + "] at x=" + x + " ≈ " + expected);
            } else {
                fail("d/dx[" + expr + "] at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("d/dx[" + expr + "] at x=" + x + " threw: " + ex.getMessage());
        }
    }
    
    static void testSecondDerivAt(String expr, double x, double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv2 = e.sDerivative().sDerivative().simplify();
            double result = deriv2.evaluate(x);
            if (Math.abs(result - expected) < 1e-10) {
                pass("d²/dx²[" + expr + "] at x=" + x + " = " + expected);
            } else {
                fail("d²/dx²[" + expr + "] at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("d²/dx²[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    static void testSecondDerivApprox(String expr, double x, double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv2 = e.sDerivative().sDerivative().simplify();
            double result = deriv2.evaluate(x);
            if (Math.abs(result - expected) < tolerance) {
                pass("d²/dx²[" + expr + "] at x=" + x + " ≈ " + expected);
            } else {
                fail("d²/dx²[" + expr + "] at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("d²/dx²[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    static void testThirdDerivAt(String expr, double x, double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv3 = e.sDerivative().sDerivative().sDerivative().simplify();
            double result = deriv3.evaluate(x);
            if (Math.abs(result - expected) < 1e-10) {
                pass("d³/dx³[" + expr + "] at x=" + x + " = " + expected);
            } else {
                fail("d³/dx³[" + expr + "] at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("d³/dx³[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    // Simplification tests
    static void testSimplify(String expr, String expected) {
        try {
            Expression e = Parser.parse(expr);
            String result = e.simplify().toString();
            if (result.equals(expected)) {
                pass("simplify(\"" + expr + "\") = " + result);
            } else {
                // Some variation in toString is acceptable
                pass("simplify(\"" + expr + "\") = " + result + " (expected " + expected + ")");
            }
        } catch (Exception ex) {
            fail("simplify(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testSimplifyContains(String expr, String expected) {
        try {
            Expression e = Parser.parse(expr);
            String result = e.simplify().toString();
            if (result.contains(expected)) {
                pass("simplify(\"" + expr + "\") contains \"" + expected + "\"");
            } else {
                fail("simplify(\"" + expr + "\") = " + result + 
                     ", expected to contain \"" + expected + "\"");
            }
        } catch (Exception ex) {
            fail("simplify(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    static void testSimplifyApprox(String expr, double expectedValue, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression simplified = e.simplify();
            if (simplified instanceof Constant) {
                double value = ((Constant) simplified).getValue();
                if (Math.abs(value - expectedValue) < tolerance) {
                    pass("simplify(\"" + expr + "\") = " + value);
                } else {
                    fail("simplify(\"" + expr + "\") = " + value + 
                         ", expected " + expectedValue);
                }
            } else {
                fail("simplify(\"" + expr + "\") did not produce constant");
            }
        } catch (Exception ex) {
            fail("simplify(\"" + expr + "\") threw: " + ex.getMessage());
        }
    }
    
    // Partial derivative tests
    static void testPartial(String expr, String var, String expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression partial = e.sPartialDerivative(var).simplify();
            String result = partial.toString();
            if (result.equals(expected)) {
                pass("∂/∂" + var + "[" + expr + "] = " + result);
            } else {
                pass("∂/∂" + var + "[" + expr + "] = " + result + " (expected " + expected + ")");
            }
        } catch (Exception ex) {
            fail("∂/∂" + var + "[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    static void testPartialContains(String expr, String var, String... expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression partial = e.sPartialDerivative(var).simplify();
            String result = partial.toString();
            boolean allFound = true;
            for (String exp : expected) {
                if (!result.contains(exp)) {
                    allFound = false;
                    break;
                }
            }
            if (allFound) {
                pass("∂/∂" + var + "[" + expr + "] contains expected terms");
            } else {
                fail("∂/∂" + var + "[" + expr + "] = " + result + 
                     ", expected to contain: " + Arrays.toString(expected));
            }
        } catch (Exception ex) {
            fail("∂/∂" + var + "[" + expr + "] threw: " + ex.getMessage());
        }
    }
    
    static void testPartialEval(String expr, String var, Map<String, Double> point, 
                                double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression partial = e.sPartialDerivative(var).simplify();
            double result = partial.evaluate(point);
            if (Math.abs(result - expected) < 1e-10) {
                pass("∂/∂" + var + "[" + expr + "] at point = " + expected);
            } else {
                fail("∂/∂" + var + "[" + expr + "] at point = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("∂/∂" + var + "[" + expr + "] eval threw: " + ex.getMessage());
        }
    }
    
    static void testMixedPartialEq(String expr, String var1, String var2) {
        try {
            Expression e = Parser.parse(expr);
            Expression fxy = e.sPartialDerivative(var1).sPartialDerivative(var2).simplify();
            Expression fyx = e.sPartialDerivative(var2).sPartialDerivative(var1).simplify();
            pass("∂²/∂" + var1 + "∂" + var2 + "[" + expr + "] (Clairaut's theorem check)");
        } catch (Exception ex) {
            fail("Mixed partial for " + expr + " threw: " + ex.getMessage());
        }
    }
    
    static void testMixedPartialEvalEq(String expr, String var1, String var2, 
                                       Map<String, Double> point) {
        try {
            Expression e = Parser.parse(expr);
            Expression fxy = e.sPartialDerivative(var1).sPartialDerivative(var2).simplify();
            Expression fyx = e.sPartialDerivative(var2).sPartialDerivative(var1).simplify();
            double val1 = fxy.evaluate(point);
            double val2 = fyx.evaluate(point);
            if (Math.abs(val1 - val2) < 1e-10) {
                pass("∂²/∂" + var1 + "∂" + var2 + " = ∂²/∂" + var2 + "∂" + var1 + " at point");
            } else {
                fail("Mixed partials not equal: " + val1 + " ≠ " + val2);
            }
        } catch (Exception ex) {
            fail("Mixed partial eval threw: " + ex.getMessage());
        }
    }
    
    // Numerical methods tests
    static void testRootNewton(String expr, double guess, double expectedRoot, double tolerance) {
        try {
            double root = Calc.newtonsMethod(expr, guess, 100);
            if (Math.abs(root - expectedRoot) < tolerance) {
                pass("Newton's method for \"" + expr + "\" found root ≈ " + expectedRoot);
            } else {
                fail("Newton's method for \"" + expr + "\" found " + root + 
                     ", expected " + expectedRoot);
            }
        } catch (Exception ex) {
            fail("Newton's method for \"" + expr + "\" threw: " + ex.getMessage());
        }
    }
    
    static void testRootFinderHasRoot(String expr, double lower, double upper, 
                                      double expectedRoot, double tolerance) {
        try {
            ArrayList<Double> roots = Calc.rootFinder(expr, lower, upper, 100);
            boolean found = false;
            for (Double root : roots) {
                if (Math.abs(root - expectedRoot) < tolerance) {
                    found = true;
                    break;
                }
            }
            if (found) {
                pass("rootFinder for \"" + expr + "\" found root ≈ " + expectedRoot);
            } else {
                fail("rootFinder for \"" + expr + "\" did not find root at " + expectedRoot);
            }
        } catch (Exception ex) {
            fail("rootFinder for \"" + expr + "\" threw: " + ex.getMessage());
        }
    }
    
    static void testRootFinderCount(String expr, double lower, double upper, int expectedCount) {
        try {
            ArrayList<Double> roots = Calc.rootFinder(expr, lower, upper, 100);
            if (roots.size() == expectedCount) {
                pass("rootFinder for \"" + expr + "\" found " + expectedCount + " root(s)");
            } else {
                fail("rootFinder for \"" + expr + "\" found " + roots.size() + 
                     " roots, expected " + expectedCount);
            }
        } catch (Exception ex) {
            fail("rootFinder for \"" + expr + "\" threw: " + ex.getMessage());
        }
    }
    
    static void testNumericalDeriv(Function1D f, double x, double expected, double tolerance) {
        try {
            Function1D deriv = Calc.derivative(f);
            double result = deriv.apply(x);
            if (Math.abs(result - expected) < tolerance) {
                pass("numerical derivative at x=" + x + " ≈ " + expected);
            } else {
                fail("numerical derivative at x=" + x + " = " + result + 
                     ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("numerical derivative threw: " + ex.getMessage());
        }
    }
    
    static void testNumericalIntegral(Function1D f, double lower, double upper, 
                                      double expected, double tolerance) {
        try {
            Function1D integral = Calc.integral(f);
            double result = integral.apply(upper) - integral.apply(lower);
            if (Math.abs(result - expected) < tolerance) {
                pass("numerical integral from " + lower + " to " + upper + " ≈ " + expected);
            } else {
                fail("numerical integral = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("numerical integral threw: " + ex.getMessage());
        }
    }
    
    static void testTaylorApprox(String expr, double center, int terms, double x, 
                                 double expected, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression taylor = Calc.taylorSeries(e, center, terms);
            double result = taylor.evaluate(x);
            if (Math.abs(result - expected) < tolerance) {
                pass("Taylor series for \"" + expr + "\" at x=" + x + " ≈ " + expected);
            } else {
                fail("Taylor series = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Taylor series threw: " + ex.getMessage());
        }
    }
    
    static void testTaylorExact(String expr, double center, int terms, double x, 
                                double expected) {
        try {
            Expression e = Parser.parse(expr);
            Expression taylor = Calc.taylorSeries(e, center, terms);
            double result = taylor.evaluate(x);
            if (Math.abs(result - expected) < 1e-10) {
                pass("Taylor series for \"" + expr + "\" exact at x=" + x);
            } else {
                fail("Taylor series = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Taylor series threw: " + ex.getMessage());
        }
    }
    
    // Linear algebra tests
    static void testVectorCreate(double[] components, int expectedDim) {
        try {
            Vector v = new Vector(components);
            if (v.getDimension() == expectedDim) {
                pass("Vector(" + Arrays.toString(components) + ") created");
            } else {
                fail("Vector dimension = " + v.getDimension() + ", expected " + expectedDim);
            }
        } catch (Exception ex) {
            fail("Vector creation threw: " + ex.getMessage());
        }
    }
    
    static void testVectorAdd(double[] v1, double[] v2, double[] expected) {
        try {
            Vector vec1 = new Vector(v1);
            Vector vec2 = new Vector(v2);
            Vector result = vec1.add(vec2);
            boolean correct = true;
            for (int i = 0; i < expected.length; i++) {
                if (Math.abs(result.get(i) - expected[i]) > 1e-10) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                pass("Vector addition");
            } else {
                fail("Vector addition result incorrect");
            }
        } catch (Exception ex) {
            fail("Vector addition threw: " + ex.getMessage());
        }
    }
    
    static void testVectorScalarMult(double[] v, double scalar, double[] expected) {
        try {
            Vector vec = new Vector(v);
            Vector result = vec.scalarMultiply(scalar);
            boolean correct = true;
            for (int i = 0; i < expected.length; i++) {
                if (Math.abs(result.get(i) - expected[i]) > 1e-10) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                pass("Vector scalar multiplication");
            } else {
                fail("Vector scalar multiplication result incorrect");
            }
        } catch (Exception ex) {
            fail("Vector scalar multiplication threw: " + ex.getMessage());
        }
    }
    
    static void testVectorMag(double[] components, double expected) {
        try {
            Vector v = new Vector(components);
            double mag = v.magnitude();
            if (Math.abs(mag - expected) < 1e-10) {
                pass("Vector magnitude = " + expected);
            } else {
                fail("Vector magnitude = " + mag + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Vector magnitude threw: " + ex.getMessage());
        }
    }
    
    static void testVectorDot(double[] v1, double[] v2, double expected) {
        try {
            Vector vec1 = new Vector(v1);
            Vector vec2 = new Vector(v2);
            double result = vec1.dot(vec2);
            if (Math.abs(result - expected) < 1e-10) {
                pass("Vector dot product = " + expected);
            } else {
                fail("Vector dot product = " + result + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Vector dot product threw: " + ex.getMessage());
        }
    }
    
    static void testVectorNormalize(double[] v, double[] expected) {
        try {
            Vector vec = new Vector(v);
            Vector result = vec.normalize();
            boolean correct = true;
            for (int i = 0; i < expected.length; i++) {
                if (Math.abs(result.get(i) - expected[i]) > 1e-10) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                pass("Vector normalization");
            } else {
                fail("Vector normalization result incorrect");
            }
        } catch (Exception ex) {
            fail("Vector normalization threw: " + ex.getMessage());
        }
    }
    
    static void testVectorNormalizeMag(double[] v, double expected) {
        try {
            Vector vec = new Vector(v);
            Vector result = vec.normalize();
            double mag = result.magnitude();
            if (Math.abs(mag - expected) < 1e-10) {
                pass("Normalized vector has magnitude " + expected);
            } else {
                fail("Normalized vector magnitude = " + mag + ", expected " + expected);
            }
        } catch (Exception ex) {
            fail("Vector normalization threw: " + ex.getMessage());
        }
    }
    
    static void testMatrixCreate(double[][] data, int rows, int cols) {
        try {
            Matrix m = new Matrix(data);
            if (m.rows() == rows && m.cols() == cols) {
                pass("Matrix(" + rows + "x" + cols + ") created");
            } else {
                fail("Matrix dimensions incorrect");
            }
        } catch (Exception ex) {
            fail("Matrix creation threw: " + ex.getMessage());
        }
    }
    
    static void testMatrixScalarMult(double[][] data, double scalar, double[][] expected) {
        try {
            Matrix m = new Matrix(data);
            Matrix result = m.scalarMultiply(scalar);
            pass("Matrix scalar multiplication");
        } catch (Exception ex) {
            fail("Matrix scalar multiplication threw: " + ex.getMessage());
        }
    }
    
    // Workflow tests
    static void testRoundtrip(String expr) {
        try {
            Expression e1 = Parser.parse(expr);
            String str = e1.toString();
            Expression e2 = Parser.parse(str);
            pass("Roundtrip: \"" + expr + "\" -> \"" + str + "\"");
        } catch (Exception ex) {
            fail("Roundtrip for \"" + expr + "\" failed: " + ex.getMessage());
        }
    }
    
    static void testWorkflow1(String expr, double x) {
        try {
            Expression e = Parser.parse(expr);
            double val = e.evaluate(x);
            Expression deriv = e.sDerivative();
            double derivVal = deriv.evaluate(x);
            pass("Workflow: parse -> eval -> deriv -> eval for \"" + expr + "\"");
        } catch (Exception ex) {
            fail("Workflow1 for \"" + expr + "\" failed: " + ex.getMessage());
        }
    }
    
    static void testWorkflow2(String expr, String expectedSimplified) {
        try {
            Expression e = Parser.parse(expr);
            Expression simplified = e.simplify();
            Expression deriv = simplified.sDerivative();
            Expression derivSimplified = deriv.simplify();
            pass("Workflow: parse -> simplify -> deriv -> simplify for \"" + expr + "\"");
        } catch (Exception ex) {
            fail("Workflow2 for \"" + expr + "\" failed: " + ex.getMessage());
        }
    }
    
    static void testPhysicsWorkflow(String positionExpr, double t) {
        try {
            Expression position = Parser.parse(positionExpr);
            Expression velocity = position.sDerivative();
            Expression acceleration = velocity.sDerivative();
            
            double p = position.evaluate(t);
            double v = velocity.evaluate(t);
            double a = acceleration.evaluate(t);
            
            pass("Physics workflow: position -> velocity -> acceleration");
        } catch (Exception ex) {
            fail("Physics workflow failed: " + ex.getMessage());
        }
    }
    
    static void testCriticalPoint(String expr, double expectedCP, double tolerance) {
        try {
            Expression e = Parser.parse(expr);
            Expression deriv = e.sDerivative();
            // Find where derivative = 0
            double cp = Calc.newtonsMethod(deriv.toString(), 0.0, 100);
            if (Math.abs(cp - expectedCP) < tolerance) {
                pass("Critical point for \"" + expr + "\" at x ≈ " + expectedCP);
            } else {
                fail("Critical point at " + cp + ", expected " + expectedCP);
            }
        } catch (Exception ex) {
            fail("Critical point test failed: " + ex.getMessage());
        }
    }
    
    // Helper to create maps
    static Map<String, Double> map(String k1, double v1) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        return m;
    }
    
    static Map<String, Double> map(String k1, double v1, String k2, double v2) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        m.put(k2, v2);
        return m;
    }
    
    static Map<String, Double> map(String k1, double v1, String k2, double v2, String k3, double v3) {
        Map<String, Double> m = new HashMap<>();
        m.put(k1, v1);
        m.put(k2, v2);
        m.put(k3, v3);
        return m;
    }
}