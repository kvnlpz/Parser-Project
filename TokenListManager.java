import java.util.ArrayList;

public class TokenListManager {
    int currentIndex;
    ArrayList<Lexer.Token> tokens;
    public TokenListManager(ArrayList<Lexer.Token> tokens){
        currentIndex = 0;
        this.tokens = tokens;
    }




    public Lexer.Token getCurrentToken(){
        return tokens.get(currentIndex);
    }

    public void printCurrentToken(){
        System.out.println(tokens.get(currentIndex));
    }

    public boolean hasNextToken(){
        if(tokens.get(currentIndex+1) == null){
            return false;
        }
        else{
            return true;
        }
    }
    public Lexer.Token getNextToken(){
        if(hasNextToken()){
            currentIndex++;
            return tokens.get(currentIndex);
        }
        else{
            return null;
        }
    }
}
