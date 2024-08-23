import java.util.ArrayList;
import java.util.List;

public class ExecutionEngine {
    
    public static double execute_tokens(List<Token> tokens) {
        return final_pass(third_pass(second_pass(first_pass(tokens))));
    }

    public static double final_pass(List<Token> tokens) {
        while (tokens.size() > 1) {
            boolean found_operation = false;
            for(int i =0 ; i < tokens.size(); i++ ) {
                if(tokens.get(i).getType() != TokenType.NUMBER) {
                    tokens = execute_token(tokens, i);
                    found_operation = true;
                    break;
                }
            }
            if(found_operation == false) {
                break;
            }
        }
        if(tokens.size() > 1) {
            System.err.println("Error: could not compute");
            System.exit(-3);
        }

        return tokens.get(0).getValue();
    }

    public static List<Token> second_pass(List<Token> tokens) {
        boolean found_operation = false;
        do  {
            found_operation = false;

            for(int i =0 ; i < tokens.size(); i++ ) {
                if(tokens.get(i).getType() == TokenType.POWER) {
                    tokens = execute_token(tokens, i);
                    found_operation = true;
                    break;
                }
            }

        } while (found_operation);

        return tokens;
    }

    public static List<Token> third_pass(List<Token> tokens) {
        boolean found_operation = false;
        do  {
            found_operation = false;

            for(int i =0 ; i < tokens.size(); i++ ) {
                if(tokens.get(i).getType() == TokenType.MULTIPLY || tokens.get(i).getType() == TokenType.DIVIDE) {
                    tokens = execute_token(tokens, i);
                    found_operation = true;
                    break;
                }
            }

        } while (found_operation);

        return tokens;
    }

    public static List<Token> first_pass(List<Token> tokens) {
        List<Token> new_tokens = new ArrayList<Token>();

        for (int i = 0; i < tokens.size(); i++) {
            Token current_token = tokens.get(i);

            if(current_token.getType() == TokenType.OPEN_BRACKET) {
                List<Token> in_brackets = new ArrayList<Token>();
                int end = find_closing(tokens,i);
                new_tokens.add(new Token(execute_tokens(tokens.subList(i+1, end))));
                i = end;
            }else{
                new_tokens.add(current_token);
            }
        }

        return new_tokens;
    }

    public static int find_closing(List<Token> tokens, int start) {
        int open = 0;

        for (int i = start; i < tokens.size(); i++) {
            if(tokens.get(i).getType() == TokenType.OPEN_BRACKET) {
                open++;
            } else if (tokens.get(i).getType() == TokenType.CLOSE_BRACKET) {
                open--;
                if(open == 0) {
                    return i;
                }
            }
        }

        System.err.println("Error: closing bracket not found!");
        System.exit(-5);
        return -1;
    }

    public static List<Token> execute_token(List<Token> tokens, int index) {
        if(index <= 0) {
            System.err.println("Error: invalid order of operations");
            System.exit(-3);
        }

        List<Token> updated_list = tokens;
        Double value_1 = tokens.get(index-1).getValue();
        Double value_2 = tokens.get(index+1).getValue();
        Double result = 0d;
        TokenType type = tokens.get(index).getType();

        switch (type) {
            case PLUS:
                result = value_1 + value_2;
                break;
            case MINUS:
                result = value_1 - value_2;
                break;
            case DIVIDE:
                if(value_2 == 0) {
                    System.err.println("Error: Division by 0 is undefined");
                    System.exit(-6);
                }
                result = value_1 / value_2;
                break;
            case MULTIPLY:
                result = value_1 * value_2;
                break;
            case POWER:
                result = Math.pow(value_1 , value_2);
                break;
        
            default:
                System.err.println("Error: Operation " + type.toString() + " not implemented yet");
                break;
        }

        updated_list.set(index, new Token(result));
        updated_list.remove(index+1);
        updated_list.remove(index-1);
        

        return updated_list;
    }
}
