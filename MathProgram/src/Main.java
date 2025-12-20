import calculus.*;
import expressions.*;
import linalg.*;

public class Main {

    public static void main(String[] args){
        double[][] matr = new double[3][3];
        matr[0][0] = -1; 
        matr[1][1] = -1;
        matr[0][2] = -1; 
        Matrix m = new Matrix(matr);
        
        // Expression fx = new Add(new Multiply(new Constant(4),new Variable()), new Constant(16));
        // Expression f = new Constant(4).multiply(new Variable()).add(new Constant(16));
        // System.out.println(fx + "f\n" + f);
        // Expression fp = f.sDerivative();
        // System.out.println(fp);
        // System.out.println(fp.simplify());

        Function1D gx = x -> Math.exp(x);
        Function1D gp = Calc.derivative(gx);
        System.out.println("" + gx.apply(1));
        
        System.out.println("=== BASIC TESTS ===");
        test("5", 5.0);
        test("x", 2.0);
        test("x^2", 4.0);
        test("3^2", 9.0);
        
        System.out.println("\n=== POWER CHAINING (RIGHT-ASSOCIATIVE) ===");
        test("2^3^2", 512.0);     // 2^(3^2) = 2^9 = 512
        test("2^2^3", 256.0);     // 2^(2^3) = 2^8 = 256
        test("3^2^2", 81.0);      // 3^(2^2) = 3^4 = 81
        test("2^1^5", 2.0);       // 2^(1^5) = 2^1 = 2
        test("x^2^2", 16.0);      // x=2: 2^(2^2) = 2^4 = 16
        
        System.out.println("\n=== COMPLEX POWER EXPRESSIONS ===");
        test("(2+1)^2", 9.0);     // 3^2 = 9
        test("2^(1+1)", 4.0);     // 2^2 = 4
        test("(1+1)^(1+1)", 4.0); // 2^2 = 4
        test("x^(2+1)", 8.0);     // x=2: 2^3 = 8
        
        System.out.println("\n=== MULTIPLICATION/DIVISION ===");
        test("2*3", 6.0);
        test("2*3*4", 24.0);
        test("12/3", 4.0);
        test("12/3/2", 2.0);
        test("2*x", 4.0);
        
        System.out.println("\n=== ADDITION/SUBTRACTION ===");
        test("x+1", 3.0);
        test("1+2+3", 6.0);
        test("x^2+1", 5.0);
        test("2*x+3", 7.0);
        test("10-3", 7.0);
        test("10-3-2", 5.0);      // (10-3)-2 = 5
        
        System.out.println("\n=== PARENTHESES ===");
        test("(x)", 2.0);
        test("(x+1)", 3.0);
        test("2*(x+1)", 6.0);
        test("(2+3)*(4+1)", 25.0);
        
        System.out.println("\n=== ORDER OF OPERATIONS ===");
        test("2+3*4", 14.0);      // 2+(3*4) = 14
        test("2*3+4", 10.0);      // (2*3)+4 = 10
        test("2^3*4", 32.0);      // (2^3)*4 = 8*4 = 32
        test("2*3^2", 18.0);      // 2*(3^2) = 2*9 = 18
        test("10-2*3", 4.0);      // 10-(2*3) = 4
        
        System.out.println("\n=== EDGE CASES ===");
        test("x^0", 1.0);         // Anything^0 = 1
        test("x^1", 2.0);         // x^1 = x
        test("0^5", 0.0);         // 0^n = 0
        test("1^100", 1.0);       // 1^n = 1
    }
    
    public static void test(String input, double expected) {
        try {
            Expression expr = Parser.parse(input);
            double result = expr.evaluate(2.0);  // Always evaluate at x=2
            String status = Math.abs(result - expected) < 0.0001 ? "✓ PASS" : "✗ FAIL";
            System.out.println(status + " | \"" + input + "\" = " + result + 
                             " (expected " + expected + ")");
        } catch (Exception e) {
            System.out.println("✗ ERROR | \"" + input + "\" threw: " + e.getMessage());
        }
    }
}