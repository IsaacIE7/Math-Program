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

        Function1D gx = x -> 3 * Math.cos(x*x) * Math.pow(Math.E, x);
        Function1D gp = Calc.derivative(gx);
        System.out.println("" + gx.apply(3));

        // Expression x = Parser.parse("(x^2)^3");
        // System.out.println(x);
        // System.err.println(x.evaluate(2));
        // System.out.println(x.sDerivative());
        // System.out.println(x.sDerivative().simplify());

 

// Base cases
System.out.println(Parser.parse("x"));        // expected: x
System.out.println(Parser.parse("3"));        // expected: 3.0
System.out.println(Parser.parse("42"));       // expected: 42.0

// Single exponent
System.out.println(Parser.parse("x^2"));      // expected: (x)^2
System.out.println(Parser.parse("3^2"));      // expected: (3.0)^2

// Right-associativity (CRITICAL)
System.out.println(Parser.parse("x^2^3"));    // expected: (x)^(2^3)

// No exponent (should stop cleanly)
System.out.println(Parser.parse("x+2"));      // expected: x   (parser stops at '+')

// Invalid exponents (should throw)
try { Parser.parse("x^2.5"); } catch (Exception e) { System.out.println("OK: " + e.getMessage()); }
try { Parser.parse("x^x"); }   catch (Exception e) { System.out.println("OK: " + e.getMessage()); }
try { Parser.parse("x^"); }    catch (Exception e) { System.out.println("OK: " + e.getMessage()); }
try { Parser.parse("^2"); }    catch (Exception e) { System.out.println("OK: " + e.getMessage()); }

// Chained powers edge case
System.out.println(Parser.parse("2^3^2"));    // expected: 2^(3^2) = 2^9 structurally





    }
}