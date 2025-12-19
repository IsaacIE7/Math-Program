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

        // Expression x = Parser.parse("(x^2)^3");
        // System.out.println(x);
        // System.err.println(x.evaluate(2));
        // System.out.println(x.sDerivative());
        // System.out.println(x.sDerivative().simplify());




    }
}