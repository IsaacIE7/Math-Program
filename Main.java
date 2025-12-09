public class Main {
    public static void main(String[] args){
        double[][] matr = new double[3][3];
        matr[0][0] = -1;
        matr[1][1] = -1;
        matr[0][2] = -1; 
        Matrix m = new Matrix(matr);
        
        System.out.println(m);
    }
}