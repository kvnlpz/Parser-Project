import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class    Lexer {
    String input;

    public Lexer(String input) {
        this.input = input;
    }


    public static ArrayList<Token> lexFunc(String input) {
        // The tokens
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Our lexer starts here

        //StringBuffer is mutable
        StringBuffer tokenBuffer = new StringBuffer();
        //iterate through all the tokens we have
        for (TokenType tokenType : TokenType.values()) {
            tokenBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        }
        //regex patterns
        matcher(input, tokens, tokenBuffer);
        return tokens;
    }

    private static void matcher(String input, ArrayList<Token> tokens, StringBuffer tokenBuffer) {
        Pattern tokenPatterns = Pattern.compile(tokenBuffer.substring(1));
        // Now we're going to match the tokens with our enums
        Matcher matcher = tokenPatterns.matcher(input);
        int line = 0;
        while (matcher.find()) {
            line++;
            if (matcher.group(TokenType.KEYWORD.name()) != null) {
                tokens.add(new Token(TokenType.KEYWORD, matcher.group(TokenType.KEYWORD.name()), line));
            } else if ((matcher.group(TokenType.IDENTIFIER.name()) != null)) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group(TokenType.IDENTIFIER.name()),line));
            } else if (matcher.group(TokenType.SEPARATOR.name()) != null) {
                tokens.add(new Token(TokenType.SEPARATOR, matcher.group(TokenType.SEPARATOR.name()), line));
            } else if (matcher.group(TokenType.NUMBER.name()) != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name()),line));
            } else if (matcher.group(TokenType.OPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.OPERATOR, matcher.group(TokenType.OPERATOR.name()), line));
            }
        }
    }


    public enum TokenType {
        // The types of tokens we can have
        NUMBER("[0-9]*\\.?[0-9]"),
        OPERATOR("[*|/|+|-]"),
        WHITESPACE("[ \t\f\r\n]+"),
        KEYWORD("(?<![a-zA-Z0-9])(if|while|int|get|for|function)(?![a-zA-Z0-9])"),
        IDENTIFIER("\\b(?!(if|while|int|get|for)\\b)\\w+"),
        SEPARATOR("[\\$]{2,2}|[^a-zA-Z\\\\ds:]"); //ORIGINAL : \$+[^a-zA-Z\d\s:]
        //[\$]{2,2}|[^a-zA-Z\\ds:]

        public final String pattern;

        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token {
        public TokenType type;
        public String data;
        public int lineNumber;

        public Token(TokenType type, String data, int lineNumber) {
            this.type = type;
            this.data = data;
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return type.name() + " " + data;
        }
    }

}
