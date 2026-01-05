package calculus;

import expressions.*;
import java.util.ArrayList;
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
        return objCurl2d(vars, xComp, yComp).evaluate(point);
    }

    public VectorField curl(String[] vars, String... comps){
        if (vars.length != 3 || comps.length != 3) throw new IllegalArgumentException("Must be 3d."); 

        ArrayList<Expression> vf = new ArrayList<Expression>();
        String[] compStrings = new String[3];

        for (String s : comps) {
            vf.add(Parser.parse(s));
        }

        Expression curlX = vf.get(2).sPartialDerivative(vars[1]).subtract(vf.get(1).sPartialDerivative(vars[2]));
        Expression curlY = vf.get(0).sPartialDerivative(vars[2]).subtract(vf.get(2).sPartialDerivative(vars[0]));
        Expression curlZ = vf.get(1).sPartialDerivative(vars[0]).subtract(vf.get(0).sPartialDerivative(vars[1]));

        compStrings[0] = curlX.simplify().simplify().toString();
        compStrings[1] = curlY.simplify().simplify().toString();
        compStrings[2] = curlZ.simplify().simplify().toString();

        return new VectorField(compStrings, vars);        
    }

    public VectorField curlAt(String[] vars, Map<String, Double> point, String... comps){
        VectorField curlField = curl(vars, comps);
        String[] curlComps = curlField.getComps();
        String[] res = new String[3];

        for (int i = 0; i < curlComps.length; i++) {
            res[i] = "" + Parser.parse(curlComps[i]).evaluate(point);
        }

        return new VectorField(res, vars);
    }
}
