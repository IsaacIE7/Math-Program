import java.util.*;
public class Matrix {
    private double [][] mat;
    private int rows;
    private int cols;

    public Matrix(double[][] mat){
        if (mat == null||mat.length == 0||mat[0].length == 0){
            throw new IllegalArgumentException("Matrix has empty dimensions");
        }

        int firstColLength = mat[0].length;
        for (double[] row: mat) {//checks if all columns have same length
            if (row.length != firstColLength){
                throw new IllegalArgumentException("All rows must have same length");
            }
        }
        int firstRowLength = mat.length;
        for (double[] row: mat) {//checks if all rows have same length
            if (mat.length != firstRowLength){
                throw new IllegalArgumentException("All rows must have same length");
            }
        }

        this.rows = mat.length;
        this.cols = mat[0].length;

        this.mat = new double [rows][cols];

        for (int i = 0; i < rows; i++){
            this.mat[i] = Arrays.copyOf(mat[i], cols);//copies the row and sets length to amnt of cols
        }                                             //copyOf() shortcut instead of nested for loop
    }

    public Matrix(int rows, int columns){
        this.mat = new double [rows][columns]; 
    }

    public int rows(){
        return rows;
    }

    public int cols(){
        return cols;
    }

    public Matrix scalarMultiply(double val){
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.mat[i][j] *= val;
            }
        }
        return result;
    }

    public Vector matrixTimesVector(Vector v){
        if (v.getDimension() != cols){
            throw new IllegalArgumentException("Matrix columns and vector dimensions dont match");
        }
        double[] dim = new double[v.getDimension()];
        Vector result = new Vector(dim);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
            }
        }
        return null;
    }
    // public static Matrix create2x2Matrix(int r1c1, int r1c2, int r2c1, int r2c2){
    //     double [][] tbtMat = new double[2][2];
    //     tbtMat[0][0] = r1c1;
    //     tbtMat[0][1] = r1c2;
    //     tbtMat[1][0] = r2c1;
    //     tbtMat[1][1] = r2c2;

    //     return new Matrix(tbtMat, 2, 2);

    // }

    // public static  Matrix create3x3Matrix(int r1c1, int r1c2, int r1c3, int r2c1, int r2c2, int r2c3, int r3c1, int r3c2, int r3c3){
    //     double [][] tbtMat = new double[3][3];     
    //     tbtMat[0][0] = r1c1;
    //     tbtMat[0][1] = r1c2;
    //     tbtMat[0][2] = r1c3;
    //     tbtMat[1][0] = r2c1;
    //     tbtMat[1][1] = r2c2;
    //     tbtMat[1][2] = r2c3;
    //     tbtMat[2][0] = r3c1;
    //     tbtMat[2][1] = r3c2;
    //     tbtMat[2][2] = r3c3;

    //     return new Matrix(tbtMat, 3, 3);
    // }

    // public void add2x2Matrix(Matrix mat){
    //     for (int i = 0; i < rows; i++){
    //        for (int j = 0; j < columns; j++) {
    //            this.tbtMat[i][j] += mat.tbtMat[i][j];
    //        }
    //     }
    // }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++){
            sb.append("[");
            for (int j = 0; j < cols; j++){
                if (mat[i][j] >= 0){
                    sb.append(" ");
                }
                
                sb.append(mat[i][j]);
                sb.append(" ");
            }
            
            if (i == rows){// need to change for efficiency, compiler would have to go through else everytime except one case instead of opposite
                sb.append("]");
            } else {
                sb.append("]\n");
            }
        }

        return sb.toString();
    }
}
