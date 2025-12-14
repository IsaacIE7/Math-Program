public class Main {
    public static void main(String[] args){
        double[][] matr = new double[3][3];
        matr[0][0] = -1; 
        matr[1][1] = -1;
        matr[0][2] = -1; 
        Matrix m = new Matrix(matr);
        
        System.out.println("===== SIMPLIFY TESTS =====");

// 0
Expression e0 = new Constant(0);
System.out.println("e0 = " + e0.simplify());

// 1
Expression e1 = new Variable().add(new Constant(0));
System.out.println("x + 0 -> " + e1.simplify());

// 2
Expression e2 = new Variable().multiply(new Constant(1));
System.out.println("x * 1 -> " + e2.simplify());

// 3
Expression e3 = new Variable().multiply(new Constant(0));
System.out.println("x * 0 -> " + e3.simplify());

// 4
Expression e4 = new Constant(0).divide(new Variable());
System.out.println("0 / x -> " + e4.simplify());

// 5
Expression e5 = new Variable().divide(new Constant(1));
System.out.println("x / 1 -> " + e5.simplify());

// 6
Expression e6 = new Constant(2).add(new Constant(3));
System.out.println("2 + 3 -> " + e6.simplify());

// 7
Expression e7 = new Constant(4).multiply(new Constant(5));
System.out.println("4 * 5 -> " + e7.simplify());

// 8
Expression e8 = new Constant(8).divide(new Constant(2));
System.out.println("8 / 2 -> " + e8.simplify());

// 9
Expression e9 = new Variable().power(1);
System.out.println("x^1 -> " + e9.simplify());

// 10
Expression e10 = new Variable().power(0);
System.out.println("x^0 -> " + e10.simplify());

// 11
Expression e11 = new Constant(5).power(0);
System.out.println("5^0 -> " + e11.simplify());

// 12
Expression e12 = new Constant(5).power(1);
System.out.println("5^1 -> " + e12.simplify());

// 13
Expression e13 = new Variable()
        .add(new Constant(3))
        .multiply(new Constant(0));
System.out.println("(x + 3) * 0 -> " + e13.simplify());

// 14
Expression e14 = new Variable()
        .add(new Constant(3))
        .multiply(new Constant(1));
System.out.println("(x + 3) * 1 -> " + e14.simplify());

// 15
Expression e15 = new Variable()
        .add(new Constant(0))
        .power(4);
System.out.println("(x + 0)^4 -> " + e15.simplify());

// 16
Expression e16 = new Variable()
        .multiply(new Variable())
        .divide(new Variable());
System.out.println("x * x / x -> " + e16.simplify());

// 17
Expression e17 = new Constant(0)
        .multiply(new Variable())
        .add(new Constant(5));
System.out.println("0*x + 5 -> " + e17.simplify());

// 18
Expression e18 = new Variable()
        .multiply(new Constant(2))
        .add(new Constant(3))
        .multiply(new Constant(1));
System.out.println("(2x + 3)*1 -> " + e18.simplify());

// 19
Expression e19 = new Variable()
        .power(2)
        .add(new Constant(0))
        .multiply(new Constant(1))
        .divide(new Constant(1));
System.out.println("(x^2 + 0)*1/1 -> " + e19.simplify());

System.out.println("\n===== DERIVATIVE + SIMPLIFY =====");

Expression d1 = new Variable().power(3);
System.out.println("d/dx x^3 = " + d1.sDerivative().simplify());

Expression d2 = new Variable()
        .add(new Constant(3))
        .power(4);
System.out.println("d/dx (x+3)^4 = " + d2.sDerivative().simplify());

Expression d3 = new Variable()
        .multiply(new Variable())
        .add(new Constant(5));
System.out.println("d/dx (x*x + 5) = " + d3.sDerivative().simplify());

Expression d4 = new Variable()
        .divide(new Constant(2));
System.out.println("d/dx (x/2) = " + d4.sDerivative().simplify());

System.out.println("\n===== EVALUATION =====");

System.out.println("x^2 at x=3 = " + new Variable().power(2).evaluate(3));
System.out.println("(x+3)^2 at x=2 = " +
        new Variable().add(new Constant(3)).power(2).evaluate(2));
System.out.println("(2x+1) at x=4 = " +
        new Variable().multiply(new Constant(2)).add(new Constant(1)).evaluate(4));

System.out.println("\n===== EDGE CASE =====");

try {
    Expression bad = new Constant(5).divide(new Constant(0));
    System.out.println(bad.simplify());
} catch (ArithmeticException e) {
    System.out.println("Correctly caught division by zero");
}

    }
}