import java.util.ArrayList;

public class TokenListManager {
    public int currentIndex = 0;
    ArrayList<Lexer.Token> tokens;


    public TokenListManager(ArrayList<Lexer.Token> tk){
        System.out.println("created tokenlistmanager");
//        currentIndex = 0;
        tokens = new ArrayList<Lexer.Token>();
        for(int i = 0; i < tk.toArray().length; ++i){
            tokens.add(tk.get(i));
        }
//        for(int i = 0; i < 10; i++){
//            currentIndex = i;
//            System.out.println(currentIndex);
//        }
        System.out.println(tokens.toString());

    }




    public Lexer.Token getCurrentToken(){

//        System.out.println("getting the current token!!");
//        System.out.println("index: "+ this.currentIndex);
        System.out.println("token is: " + tokens.get(this.currentIndex).toString());
        return tokens.get(this.currentIndex);
    }

    public void printCurrentToken(){
        System.out.println(tokens.get(this.currentIndex));
    }

    public boolean hasNextToken(){
//        System.out.println("inside hasNextToken()");
        if(tokens.get(this.currentIndex+1) == null){
            return false;
        }
        else{
            return true;
        }
    }
    public Lexer.Token getNextToken(){
//        System.out.println("getting the next token!!!!!");
        if(hasNextToken()){
            this.currentIndex++;
            return tokens.get(this.currentIndex);
        }
        else{
            return null;
        }
    }
}
