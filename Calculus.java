public class Calculus implements Function1D{
    private static final double dx = 1e-7;
    private static final double dt = 1e-4;
    

    public static Function1D derivative(Function1D f){
        return x -> (f.apply(x + dx) - f.apply(x - dx))/(2 * dx);//central difference
    }   

    public static double defIntegral(Function1D f, double lbound){

        return x -> {
            double trapezoidalSum = 0.0;
            boolean negative = x < 0;
            if (negative){x = -x;}// ∫ from a to b f(x) dx = - ∫ from b to a f(x) dx
            trapezoidalSum += f.apply(x + dt);
             return trapezoidalSum;
        };
    }
    
    public static Function1D antiDer(Function1D f){
        return null;
    } 


}
