import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tokenizer {

    private static final Set<Character> special_chars = Set.of(' ','+', '-', '/', '*', 'x', '^', '(', ')');

    public static List<Token> tokenize_string(String s) {
        List<Token> tokens = new ArrayList<Token>();
        String currentSeries = "";
        for (Character c : s.toCharArray()) {
            if(special_chars.contains(c)) {
                if(!currentSeries.isBlank()) {
                    tokens.add(tokenize_number(currentSeries));
                }
                currentSeries = "";
                if(c != ' '){ 
                    tokens.add(tokenize_char(c));
                }
            } else {
                currentSeries += c;
            }

        }

        return tokens;
    }

    private static HashMap<Character, TokenType> charMap = new HashMap<Character, TokenType>() {{
        put('+', TokenType.PLUS);
        put('-', TokenType.MINUS);
        put('/', TokenType.DIVIDE);
        put('*', TokenType.MULTIPLY);
        put('x', TokenType.MULTIPLY);
        put('^', TokenType.POWER);
        put('(', TokenType.OPEN_BRACKET);
        put(')', TokenType.CLOSE_BRACKET);
    }};

    private static Token tokenize_char(char c) {
        return new Token(charMap.get(c));
    }

    private static Token tokenize_number(String s) {
        try {
            double number = Double.parseDouble(s);
            return new Token(number);
        } catch (Exception e) {
            System.err.println("Unexpected input: " + s);
            System.exit(-2);
        }
        return null;
    }

}
