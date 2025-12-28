package expressions;

import java.util.Map;

public class Variable implements Expression {
    private String name;

    public Variable(){
        name = "x";
    }

    public Variable(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public double evaluate(double x){
        if (!name.equals("x")){
            throw new IllegalArgumentException("Cannot evaluate variable " + name);
        }
        return x;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (!variables.containsKey(name)){
            throw new IllegalArgumentException("No value provided for the variable");
        }
        return variables.get(name);
    }

    @Override
    public Constant sDerivative(){ // assumes with respect to x
        return new Constant(1);
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        if (name.equals(varName)){
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }

    @Override
    public Expression simplify(){
        return this;
    }

    @Override
    public String toString(){
        return name;
    }

    
}
