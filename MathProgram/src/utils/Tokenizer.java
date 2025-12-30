package utils;

import java.util.ArrayList;

public class Tokenizer {

    public static ArrayList<String> tokenize(String input){
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) != ' ') {
                if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    StringBuilder number = new StringBuilder();
                    boolean seenDecimal = false;
                    while (i < input.length() && ((input.charAt(i) >= '0' && input.charAt(i) <= '9') || input.charAt(i) == '.')) {
                        if (input.charAt(i) == '.') {
                            if (seenDecimal) {
                                throw new IllegalArgumentException("Invalid number format: multiple decimal points.");
                            }
                            seenDecimal = true;
                        }
                        number.append(input.charAt(i));
                        i++;
                    }
                    tokens.add(number.toString());
                    i--; // adjust for the extra increment in the inner while loop
                    
                } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' || input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' ) {
                    StringBuilder sb = new StringBuilder();
                    while (i < input.length() && (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' || input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')) { 
                        sb.append(input.charAt(i));
                        i++;
                    }
                    String word = sb.toString().toLowerCase();

                    if (isFunctionName(word)) tokens.add(word); // is it a funciton name?
                    else if (word.length() == 1) tokens.add(word); // if its not and its only 1 char then its a single character variable
                    else { //it can be implicit multiplication of variables
                        for (int j = 0; j < word.length(); j++) { // iterate through the word and add eahc character(variable in this case) with multiplication in between
                            tokens.add(String.valueOf(word.charAt(j)));
                            if (j < word.length() - 1) {
                                tokens.add("*");
                            }
                        }
                        
                    }
                    i--;//decrement i to account for the extra increment in the inner while loop
                    
                } else {
                    tokens.add(String.valueOf(input.charAt(i)));
                }

            }

        }
        return implicitMultiplication(tokens);
    }


    public static ArrayList<String> implicitMultiplication(ArrayList<String> tokens){
    ArrayList<String> result = new ArrayList<>();
    
        for (int i = 0; i < tokens.size(); i++) {//iterate through the tokens array and check if there is a need for implicit multiplication
            result.add(tokens.get(i));  

            if (i < tokens.size() - 1) { 
                if (needsMultiplication(tokens.get(i), tokens.get(i + 1))) {// if there is, add the "*" token
                    result.add("*");  
                }
            }
        }
    
        return result;
    }

    private static boolean isNum(String token) {
        try { 
            Double.parseDouble(token);  
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean isVar(String token) {
        if (token.length() != 1) return false;
        char c = token.charAt(0);
        return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') && !token.equals("e");
    }

    private static boolean isOp(String token) {
        switch (token) {
            case "+", "-", "*", "/", "^":
                return true;    
            default:
                return false;
        }
    }

    private static boolean isFunctionName(String s) {
        return s.equals("sin") || s.equals("cos") || s.equals("tan") ||
        s.equals("ln") || s.equals("sqrt") || s.equals("exp") ||
        s.equals("arcsin") || s.equals("arccos") || s.equals("arctan") ||
        s.equals("pi") || s.equals("e");
    }

    private static boolean needsMultiplication(String current, String next) {
        if (isOp(next) || next.equals(")")) { // if the next token is an op or closing parentheses then no implicit multiplication is needed
            return false;
        }

        if (isOp(current) || current.equals("(")) { // if the current token is an op or opening parentheses then no implicit multiplication is needed
            return false;
        }
        //now the valid cases for implicit multiplication
        if (isNum(current) && (isVar(next) || next.equals("(") || isFunctionName(next))) {// number followed by variable or opening parentheses then implicit multiplication is needed
            return true;
        }

        if (isVar(current) && (isVar(next) || isNum(next) || next.equals("(") || isFunctionName(next))) {// variable followed by variable, number, opening parentheses, or function name then implicit multiplication is needed
            return true;
        }

        if (current.equals(")") && (isVar(next) || isNum(next) || next.equals("(") || isFunctionName(next))) {// closing parentheses followed by variable, number, opening parentheses, or function name then implicit multiplication is needed
            return true;
        }
        return false; // if the current token is a closing parenthesis and the next token is not an operator implicit multiplication is needed
    }
}