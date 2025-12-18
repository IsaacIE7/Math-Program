import java.util.ArrayList;

public class Parser { 
    static ArrayList<String> tokens;
    static int index;

    public static Expression parse(String input){
        tokens = Tokenizer.tokenize(input);
        index = 0;
        return parsePower();
    }

    //hierarchy: (add, subtract) -> (multiply, divide) -> power ->  lone variable/constant


    public static Expression parseExpression(){ //handles highest expressions in the hierarchy: +,-
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
            throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
    }

    public static Expression parseTerm(){ //handles *, /. Example: 3x, 2x/3, x/2
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
            throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
        }    

    public static Expression parsePower(){//handles power
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        Expression base = parsePrimary();
        if (index == tokens.size()){
            return base;
        }
        if (index < tokens.size() && tokens.get(index).equals("^")){
            index++;
            Expression expr = parsePower();
            if (expr instanceof Constant constant){ // check if the exp is a constant
                if (constant.getValue() == (double)((int)constant.getValue())){ // check if it is an integer
                    int exp = (int)constant.getValue();
                    // index++;
                    Expression pow = new Power(base, exp);
                    if (index < tokens.size() && tokens.get(index).equals("^")){
                        throw new IllegalArgumentException("Invalid format. Cannot have exponent as exponent");
                    }
                    return pow;
                } else {
                    throw new IllegalArgumentException("Invalid exponent. Must be integer");
                }
            }
        } 
        return base;
    }
        //(3x + 2) pretokenized string
        //Array list has "(", "3", "x", "+", "2", ")" tokenized

    public static Expression parsePrimary(){//handles lone constants, and variables, and parentheses (2x + 3x^2)
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        if (tokens.get(index).equals("x")){
            index++;
            return new Variable();
        } 
        try { 
            double value = Double.parseDouble(tokens.get(index));
            index++;
            return new Constant(value);
        } 
        catch (NumberFormatException e) {
        }

        if (tokens.get(index).equals("(")) {
            Expression inner = parseExpression();
            if (index >= tokens.size() || !tokens.get(index).equals(")")) {
                throw new IllegalArgumentException("Missing parentheses");
            }
            index++;
            return inner;  
        }
        throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
    }
}