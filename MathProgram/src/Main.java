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
        
       // ===== MULTIPLY SIMPLIFICATION TESTS =====

// basic variable
Expression a = new Variable().multiply(new Variable());
System.out.println(a);                 // expect: (x)^2
System.out.println(a.simplify());       // expect: (x)^2

// parenthesized identical expressions
Expression b = new Variable().add(new Constant(1))
                .multiply(new Variable().add(new Constant(1)));
System.out.println(b);                 // expect: (x + 1.0)(x + 1.0)
System.out.println(b.simplify());       // expect: (x + 1.0)^2

// should NOT simplify
Expression c = new Variable().multiply(new Variable().add(new Constant(1)));
System.out.println(c);                 // expect: x(x + 1.0)
System.out.println(c.simplify());       // expect: x(x + 1.0)

// power multiplied by itself
Expression d = new Variable().power(2).multiply(new Variable().power(2));
System.out.println(d);                 // expect: (x)^2(x)^2
System.out.println(d.simplify());       // expect: (x)^2(x)^2   (NOT x^4 yet)

// numeric constants (already handled)
Expression e = new Constant(3).multiply(new Constant(3));
System.out.println(e);                 // expect: 9.0
System.out.println(e.simplify());       // expect: 9.0

// mixed constants and variables
Expression f = new Constant(2).add(new Variable()).multiply(new Constant(2).add(new Variable()));
System.out.println(f);                 // expect: 2.0x
System.out.println(f.simplify());       // expect: 2.0x

//===== END TESTS =====

    }
}