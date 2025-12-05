public class Matrix {
    double [][] tbtMat;
    int rows;
    int columns;

    public Matrix(double[][] mat, int rows, int columns){
        this.tbtMat = mat;
        this.rows = rows;
        this.columns = columns;
    }

    public static Matrix create2x2Matrix(int r1c1, int r1c2, int r2c1, int r2c2){
        double [][] tbtMat = new double[2][2];
        tbtMat[0][0] = r1c1;
        tbtMat[0][1] = r1c2;
        tbtMat[1][0] = r2c1;
        tbtMat[1][1] = r2c2;

        return new Matrix(tbtMat, 2, 2);

    }

    public static  Matrix create3x3Matrix(int r1c1, int r1c2, int r1c3, int r2c1, int r2c2, int r2c3, int r3c1, int r3c2, int r3c3){
        double [][] tbtMat = new double[3][3];     
        tbtMat[0][0] = r1c1;
        tbtMat[0][1] = r1c2;
        tbtMat[0][2] = r1c3;
        tbtMat[1][0] = r2c1;
        tbtMat[1][1] = r2c2;
        tbtMat[1][2] = r2c3;
        tbtMat[2][0] = r3c1;
        tbtMat[2][1] = r3c2;
        tbtMat[2][2] = r3c3;

        return new Matrix(tbtMat, 3, 3);
    }

    public void add2x2Matrix(Matrix mat){
        for (int i = 0; i < rows; i++){
           for (int j = 0; j < columns; j++) {
               this.tbtMat[i][j] += mat.tbtMat[i][j];
           }
        }
    }

    public String toString(){
        String matVals = "";
        for (int i = 0; i < this.rows; i++) {
            matVals += "[ ";
            for (int j = 0; j < this.columns;j++) {
                matVals += this.tbtMat[i][j] + " "; 
            }
            matVals += "]\n";
        }
        return matVals;
    }
}
