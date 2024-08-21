import java.util.List;

public class Maths {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Error: no input provided");
            System.exit(-1);
        }

        String args_joined = join_string(args);

        List<Token> tokens = Tokenizer.tokenize_string(args_joined);

        System.out.println(ExecutionEngine.execute_tokens(tokens));
    }

    public static String join_string(String[] strings) {
        String joined = "";
        for (String string : strings) {
            joined += string + " ";
        }
        return joined;
    }

}
