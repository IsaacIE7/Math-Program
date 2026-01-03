package calculus;

import expressions.*;
import java.util.Map;
import utils.*;

public class VectorField {
    private String[] comps, vars;

    public VectorField(String[] comps, String[]vars){
        if (comps.length != vars.length) throw new IllegalArgumentException("Dimension mismatch");
        this.comps = comps;
        this.vars = vars;
    }

    public String[] getComps(){
        return comps;
    }
    public String[] getVars(){
        return vars;
    }

    public Expression objDiv(){ 
        if (comps.length != vars.length){
            throw new IllegalArgumentException("Components and variables do not match.");
        }
        
        Expression sum = new Constant(0);

        for (int i = 0; i < vars.length; i++){
            sum = sum.add(Parser.parse(comps[i]).sPartialDerivative(vars[i]));
        }
        return sum;
    }

    public double objDivAt(Map<String, Double> point){
        return objDiv().evaluate(point);
    }

    public Expression objCurl2d(String[] vars, String Comp1, String Comp2){

        if (vars.length != 2) throw new IllegalArgumentException("Must be 2d");
        Expression x = Parser.parse(Comp1);
        Expression y = Parser.parse(Comp2);
        if (vars.length != 2) throw new IllegalArgumentException("Components and variables do not match."); 

        return y.sPartialDerivative(vars[0]).subtract(x.sPartialDerivative(vars[1]));
    }

    public double objCurl2dAt(String[] vars, String xComp, String yComp, Map<String, Double> point ){
        return curl2d(vars, xComp, yComp).evaluate(point);
    }
}
