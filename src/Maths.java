import java.util.List;

public class Maths {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Error: no input provided");
            System.exit(-1);
        }

        String args_joined = join_string(args);

        List<Token> tokens = Tokenizer.tokenize_string(args_joined);
        double result = ExecutionEngine.execute_tokens(tokens);

        print_double(result);
    }

    public static String join_string(String[] strings) {
        String joined = "";
        for (String string : strings) {
            joined += string + " ";
        }
        return joined;
    }

    public static void print_double(double number) {
        if (number == Double.NaN)
            System.out.println("NaN");
        else if (number == Double.POSITIVE_INFINITY)
            System.out.println("Inf");
        else if (number == Double.NEGATIVE_INFINITY) 
            System.out.println("-Inf");
        else if (number == Math.floor(number))
            System.out.println((int)number);
        else 
            System.out.println(number);
    }

}
