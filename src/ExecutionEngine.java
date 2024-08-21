import java.util.List;

public class ExecutionEngine {
    
    public static double execute_tokens(List<Token> tokens) {
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
