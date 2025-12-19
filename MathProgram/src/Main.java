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

        Function1D gx = x -> 3 * Math.cos(x*x) * Math.pow(Math.E, x);
        Function1D gp = Calc.derivative(gx);
        System.out.println("" + gx.apply(3));

        // Expression x = Parser.parse("(x^2)^3");
        // System.out.println(x);
        // System.err.println(x.evaluate(2));
        // System.out.println(x.sDerivative());
        // System.out.println(x.sDerivative().simplify());

        System.out.println("=== SIN / COS TESTS ===");

// ----- BASIC EVALUATION -----
System.out.println(new Sin(new Constant(0)).evaluate(0));        // expect 0
System.out.println(new Cos(new Constant(0)).evaluate(0));        // expect 1
System.out.println(new Sin(new Constant(Math.PI/2)).evaluate(0)); // expect 1
System.out.println(new Cos(new Constant(Math.PI)).evaluate(0));   // expect -1

// ----- VARIABLE EVALUATION -----
Expression sinx = new Sin(new Variable());
Expression cosx = new Cos(new Variable());

System.out.println(sinx.evaluate(0));    // 0
System.out.println(cosx.evaluate(0));    // 1
System.out.println(sinx.evaluate(Math.PI/2)); // ~1
System.out.println(cosx.evaluate(Math.PI/2)); // ~0

// ----- DERIVATIVE STRUCTURE -----
System.out.println(sinx.sDerivative());  // expect cos(x)
System.out.println(cosx.sDerivative());  // expect -1*sin(x)

// ----- DERIVATIVE EVALUATION -----
System.out.println(sinx.sDerivative().evaluate(0)); // cos(0) = 1
System.out.println(cosx.sDerivative().evaluate(0)); // -sin(0) = 0

// ----- CHAIN RULE -----
Expression sin_x2 = new Sin(new Power(new Variable(), 2));
Expression d_sin_x2 = sin_x2.sDerivative();

System.out.println(sin_x2);          // sin(x^2)
System.out.println(d_sin_x2);        // cos(x^2) * 2x
System.out.println(d_sin_x2.evaluate(1)); // cos(1) * 2

// ----- NESTED TRIG -----
Expression sincosx = new Sin(new Cos(new Variable()));
System.out.println(sincosx);                 // sin(cos(x))
System.out.println(sincosx.sDerivative());   // cos(cos(x)) * (-sin(x))
System.out.println(sincosx.evaluate(0));     // sin(1)

// ----- SIMPLIFY CHECKS -----
System.out.println(new Sin(new Constant(0)).simplify()); // expect 0 (if implemented)
System.out.println(new Cos(new Constant(0)).simplify()); // expect 1 (if implemented)

// ----- STABILITY -----
Expression deep = new Sin(new Sin(new Sin(new Variable())));
System.out.println(deep);
System.out.println(deep.sDerivative().simplify());

 




    }
}