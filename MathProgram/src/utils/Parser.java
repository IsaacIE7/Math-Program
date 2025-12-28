package utils;

import expressions.*;
import java.util.ArrayList;

public class Parser { 
    static ArrayList<String> tokens;
    static int index;

    public static Expression parse(String input){
        tokens = Tokenizer.tokenize(input);
        index = 0;
        Expression res = parseExpression();
        if (index < tokens.size()) {
            throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
        }
        return  res;
    }

    //hierarchy: (add, subtract) -> (multiply, divide) -> power ->  lone variable/constant


    public static Expression parseExpression(){ //handles highest expressions in the hierarchy: +,-
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        Expression expr = parseTerm();
        while (index < tokens.size() && (tokens.get(index).equals("+") || tokens.get(index).equals("-"))) { 
            String op = tokens.get(index);
            index++;
            Expression right = parseTerm();
            if (op.equals("+")){
                expr = new Add(expr, right);
            } else {
                expr = new Subtract(expr, right); 
            }
        }
        return expr;
    }

    public static Expression parseTerm(){ //handles *, /. Example: 3x, 2x/3, x/2
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        Expression expr = parsePower();
        while  (index < tokens.size() && (tokens.get(index).equals("*") || tokens.get(index).equals("/"))){
            String op = tokens.get(index);
            index++;
            Expression right = parsePower();
            if (op.equals("*")){
                expr = new Multiply(expr, right);
            } else {
                expr = new Divide(expr, right);
            }
        }
            return expr;
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
            Expression simplified = expr.simplify();
            if (simplified instanceof Constant constant){ // check if the exp is a constant
                if (constant.getValue() == (double)((int)constant.getValue())){ // check if it is an integer
                    int exp = (int)constant.getValue();
                    // index++;
                    Expression pow = new Power(base, exp);
                    return pow;
                } else {
                    throw new IllegalArgumentException("Invalid exponent. Must be integer");
                }
            } else {
                throw new IllegalArgumentException("Invalid exponent. Must be constant integer expression");
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

        if (tokens.get(index).equals("pi")) {
            index++;
            return new Constant(Math.PI);
        }

        if (tokens.get(index).equals("e")) {
            index++;
            return new Constant(Math.E);
        }
        
        String tokenTemp = tokens.get(index);
        if (tokenTemp.length() == 1 && Character.isLetter(tokenTemp.charAt(0))) { // a method from the Character class to check if the token is a single letter variable
            index++;
            return new Variable(tokenTemp);
        }


        try { 
            double value = Double.parseDouble(tokens.get(index));
            index++;
            return new Constant(value);
        } 
        catch (NumberFormatException e) {
        }
        //Function checks START //need to make more simple with a switch
        if (tokens.get(index).equals("sin")) {
            Expression inner = parseFunctionCall("sin"); 
            return new Sin(inner);  
        }
        if (tokens.get(index).equals("cos")) {
            Expression inner = parseFunctionCall("cos"); 
            return new Cos(inner);  
        }
        if (tokens.get(index).equals("tan")) {
            Expression inner = parseFunctionCall("tan"); 
            return new Tan(inner);  
        }
        if (tokens.get(index).equals("arcsin")) {
            Expression inner = parseFunctionCall("arcsin"); 
            return new ArcSin(inner);  
        }
        if (tokens.get(index).equals("arccos")) {
            Expression inner = parseFunctionCall("arccos"); 
            return new ArcCos(inner);  
        }
        if (tokens.get(index).equals("arctan")) {
            Expression inner = parseFunctionCall("arctan"); 
            return new ArcTan(inner);  
        }
        if (tokens.get(index).equals("ln")) {
            Expression inner = parseFunctionCall("ln"); 
            return new NaturalLog(inner);  
        }
        if (tokens.get(index).equals("sqrt")) {
            Expression inner = parseFunctionCall("sqrt"); 
            return new Sqrt(inner);  
        }
        if (tokens.get(index).equals("exp")) {
            Expression inner = parseFunctionCall("exp"); 
            return new EulerX(inner);  
        }
        //Function checks END

        if (tokens.get(index).equals("(")) {
            index++;//consume "(" token
            Expression inner = parseExpression();
            if (index >= tokens.size() || !tokens.get(index).equals(")")) {
                throw new IllegalArgumentException("Missing parentheses");
            }
            index++;
            return inner;  
        }


        if (index >= tokens.size() || tokens.get(index).equals(")")) {
                throw new IllegalArgumentException("Parentheses error: unmatched ')'");
        }


        throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
    }

    private static Expression parseFunctionCall(String name){
            index++;
            if (!tokens.get(index).equals("(")) throw new IllegalArgumentException("Expected '(' after " + name);
            index++;
            Expression inner = parseExpression();

            if (index >= tokens.size() || !tokens.get(index).equals(")")) {
                throw new IllegalArgumentException("Missing ')' after " + name);
            }
            index++;
            return inner;
        
    }
}







/* import expressions.*;
import java.util.ArrayList;

public class Parser { 
    static ArrayList<String> tokens;
    static int index;

    public static Expression parse(String input){
        tokens = Tokenizer.tokenize(input);
        index = 0;
        return parseExpression();
    }

    //hierarchy: (add, subtract) -> (multiply, divide) -> power ->  lone variable/constant


    public static Expression parseExpression(){ //handles highest expressions in the hierarchy: +,-
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        Expression expr = parseTerm();
        while (index < tokens.size() && (tokens.get(index).equals("+") || tokens.get(index).equals("-"))) { 
            String op = tokens.get(index);
            index++;
            Expression right = parseTerm();
            if (op.equals("+")){
                expr = new Subtract(expr, right);
            } else {
                expr = new Subtract(expr, right.multiply(new Constant(-1.0))); 
            }
        }
        return expr;
    }

    public static Expression parseTerm(){ //handles *, /. Example: 3x, 2x/3, x/2
        if (index >= tokens.size()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        Expression expr = parsePower();
        while  (index < tokens.size() && (tokens.get(index).equals("*") || tokens.get(index).equals("/"))){
            String op = tokens.get(index);
            index++;
            Expression right = parsePower();
            if (op.equals("*")){
                expr = new Multiply(expr, right);
            } else {
                expr = new Divide(expr, right);
            }
        }
            return expr;
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
            Expression simplified = expr.simplify();
            if (simplified instanceof Constant constant){ // check if the exp is a constant
                if (constant.getValue() == (double)((int)constant.getValue())){ // check if it is an integer
                    int exp = (int)constant.getValue();
                    // index++;
                    Expression pow = new Power(base, exp);
                    return pow;
                } else {
                    throw new IllegalArgumentException("Invalid exponent. Must be integer");
                }
            } else {
                throw new IllegalArgumentException("Invalid exponent. Must be constant integer expression");
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
            index++;//consume "(" token
            Expression inner = parseExpression();
            if (index >= tokens.size() || !tokens.get(index).equals(")")) {
                throw new IllegalArgumentException("Missing parentheses");
            }
            index++;
            return inner;  
        }
        throw new IllegalArgumentException("Unexpected token: " + tokens.get(index));
    }
} */