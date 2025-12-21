import expressions.*;// static import allows you to not have to call the class name before the method name

public class Main {

    public static void main(String[] args){
        Expression f = new Variable().add(new Constant(1));
        System.out.println(f);
    }
}