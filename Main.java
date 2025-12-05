public class Main {
    public static void main(String[] args){
        // Vector vec1 = new Vector("(0,0)", 3, 4);
        // Vector vec2 = new Vector("(2,5)", 2, 2);
        // //vec1.addVector(vec2);
        // System.out.println(vec1.toString());

        Matrix mat2x2 = Matrix.create2x2Matrix(0, 1, 3, 2);
        Matrix mat3x3 = Matrix.create3x3Matrix(1, 2, 1, 0, 2, 1, 0, 1, 0);
        System.out.println(mat2x2);
        System.out.println(mat3x3);

    }
}
