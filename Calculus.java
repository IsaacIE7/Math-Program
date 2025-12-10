public class Calculus implements Function1D{
    private static final double dx = 1e-7;
    private static final double dt = 1e-4;
    private static final double large = Math.pow(10, 15);

    public static Function1D derivative(Function1D f){
        return x -> (f.apply(x + dx) - f.apply(x - dx))/(2 * dx);//central difference
    }   

    public static double defIntegral(Function1D f, double lbound){

        return x -> {
             return 0;
        };
    }
    
    public static Function1D antiDer(Function1D f){
        return null;
    } 


}
