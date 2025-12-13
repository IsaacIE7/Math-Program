public class Main {
    public static void main(String[] args){
        double[][] matr = new double[3][3];
        matr[0][0] = -1;
        matr[1][1] = -1;
        matr[0][2] = -1; 
        Matrix m = new Matrix(matr);
        
         // Base variable
    Expression x = new Variable();

 // ===== TEST A: (x^3 + 2x)^4 =====
    Expression A = new Power(
            new Add(
                    new Power(x, 3),
                    new Multiply(new Constant(2), x)
            ),
            4
    );
    System.out.println("A = " + A);
    System.out.println("A' = " + A.sDerivative());
    System.out.println();

    // ===== TEST B: (x^2 + 5x)^3 =====
    Expression B = new Power(
            new Add(
                    new Power(x, 2),
                    new Multiply(new Constant(5), x)
            ),
            3
    );
    System.out.println("B = " + B);
    System.out.println("B' = " + B.sDerivative());
    System.out.println();

    // ===== TEST C: (x^4 + 3x)^2 * (x + 1) =====
    Expression C = new Multiply(
            new Power(
                    new Add(
                            new Power(x, 4),
                            new Multiply(new Constant(3), x)
                    ),
                    2
            ),
            new Add(x, new Constant(1))
    );
    System.out.println("C = " + C);
    System.out.println("C' = " + C.sDerivative());
    System.out.println();

    // ===== TEST D: ((x^2 + x)^3 + x)^2 =====
    Expression D = new Power(
            new Add(
                    new Power(
                            new Add(
                                    new Power(x, 2),
                                    x
                            ),
                            3
                    ),
                    x
            ),
            2
    );
    System.out.println("D = " + D);
    System.out.println("D' = " + D.sDerivative());
    System.out.println();

    // ===== TEST E: (x^2 + 2x + 1)^5 =====
    Expression E = new Power(
            new Add(
                    new Add(
                            new Power(x, 2),
                            new Multiply(new Constant(2), x)
                    ),
                    new Constant(1)
            ),
            5
    );
    System.out.println("E = " + E);
    System.out.println("E' = " + E.sDerivative());
    }
}