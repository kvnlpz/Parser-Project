import java.util.ArrayList;

public class TokenListManager {
    int currentIndex;
    ArrayList<Lexer.Token> tokens;


    public TokenListManager(ArrayList<Lexer.Token> tk){
        System.out.println("creted tokenlistmanager");
        currentIndex = 0;
        tokens = new ArrayList<Lexer.Token>();
        for(int i = 0; i < tk.toArray().length; i++){
            tokens.add(tk.get(i));
        }
        System.out.println(tokens.toString());


    }




    public Lexer.Token getCurrentToken(){
        System.out.println("getting the current token!!");
        System.out.println(tokens.get(currentIndex).toString());
        return tokens.get(currentIndex);
    }

    public void printCurrentToken(){
        System.out.println(tokens.get(currentIndex));
    }

    public boolean hasNextToken(){
        System.out.println("inside hasNextToken()");
        if(tokens.get(currentIndex+1) == null){
            return false;
        }
        else{
            return true;
        }
    }
    public Lexer.Token getNextToken(){
        System.out.println("getting the next token!!!!!");
        if(hasNextToken()){
            currentIndex++;
            return tokens.get(currentIndex);
        }
        else{
            return null;
        }
    }
}
