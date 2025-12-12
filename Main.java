public class Main {
    public static void main(String[] args){
        double[][] matr = new double[3][3];
        matr[0][0] = -1;
        matr[1][1] = -1;
        matr[0][2] = -1; 
        Matrix m = new Matrix(matr);
        
        //System.out.println(m);
        Expression e = new Multiply(new Add(new Multiply(new Constant(5), new Variable()), new Constant(7)), new Multiply(new Constant(3), new Variable()));
        System.out.println(e.sDerivative());
        System.out.println(e.sDerivative().evaluate(1));
        System.out.println(e);

    }
}