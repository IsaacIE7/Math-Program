import java.util.ArrayList;

public class Parser { 
    ArrayList<String> tokens;
    int index;

    //hierarchy: (add, subtract) -> (multiply, divide) -> power ->  lone variable/constant


    public static Expression parseExpression(){ //handles highest expressions in the hierarchy: +,-
        return null;
    }

    public static Expression parseTerm(){ //handles *, /. Example: 3x, 2x/3, x/2
        return null;
    }

    public static Expression parsePower(){//handles power
        return null;
    }

    public static Expression parsePrimary(){//handles lone constants, and variables
        
        return null;
    }


}
