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
        return tokens;
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
