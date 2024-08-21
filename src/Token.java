public class Token {

    private TokenType type;
    private Double value;

    public Token(TokenType type) {
        this.type = type;
        this.value = 0d;
    }

    public Token(double value) {
        this.type = TokenType.NUMBER;
        this.value = value;
    }

    @Override
    public String toString() {
        if(type == TokenType.NUMBER) {
            return value.toString();
        }

        return type.toString();
    }

    public TokenType getType() {
        return type;
    }

    public double getValue() {
        if(type != TokenType.NUMBER) {
            System.err.println("Error: " + type.toString() + " given when a number was expected");
            System.exit(-4);
        }
        return value;
    }
}
