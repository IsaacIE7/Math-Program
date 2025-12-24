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
                    tokens.add(sb.toString().toLowerCase());
                    i--;
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

    private static boolean isOp(String token) {
        switch (token) {
            case "+", "-", "*", "/", "^":
                return true;    
            default:
                return false;
        }
    }

    private static boolean needsMultiplication(String current, String next) {
        if (isOp(next)) {
            return false;
        }
        if (isNum(current)) {
            return true;
        }
        if (current.equals(")") ){
            return true;
        }
        return false;
    }
}



/*  public static ArrayList<String> tokenize(String input){
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
                    
                }   else {
                    tokens.add(String.valueOf(input.charAt(i)));
                }

            }

        }
        return tokens;
    } */
