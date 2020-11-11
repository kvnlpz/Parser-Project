

import java.util.ArrayList;

public class TokenListManager {
    public boolean isFinished = false;
    public int currentIndex = 0;
    ArrayList<Lexer.Token> tokens;
    ArrayList<Lexer.Token> newTokens;


    public TokenListManager(ArrayList<Lexer.Token> tk) {
//        System.out.println("created tokenlistmanager");
//        currentIndex = 0;
        tokens = new ArrayList<Lexer.Token>();
        newTokens = new ArrayList<>();
        for (int i = 0; i < tk.toArray().length; ++i) {
            tokens.add(tk.get(i));
        }
        System.out.println(tokens.toString());

    }


    public Lexer.Token getCurrentToken() {

//        System.out.println("getting the current token!!");
//        System.out.println("index: "+ this.currentIndex);
//        System.out.println("token is: " + tokens.get(this.currentIndex).toString());
        return tokens.get(this.currentIndex);
    }

    public void printCurrentToken() {
        System.out.println(tokens.get(this.currentIndex));
    }

    public boolean hasNextToken() {
//        System.out.println("inside hasNextToken()");
//        if(currentIndex < tokens.size()){
//            return false;
//        }
//        else {
//            return true;
//        }

        if (tokens.get(this.currentIndex + 1) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Lexer.Token getNextToken() {
        System.out.println("---==CURRENTINDEX IS: " + currentIndex);
        if (currentIndex >= tokens.size()) {
            isFinished = true;
        }
        //if it hasnt finished AND the next index is still smaller than the size of the array
        if (!isFinished && (!((currentIndex + 1) <= tokens.size()))) {
//            System.out.println("   ");
//            System.out.println("CURRENTTOKEN: " + tokens.get(this.currentIndex).toString());
//            Lexer.Token returnToken = tokens.get(this.currentIndex);
            currentIndex++;
//            System.out.println("GETNEXTTOKEN: " + tokens.get(this.currentIndex).toString());

            return tokens.get(this.currentIndex);
        } else {
            return tokens.get(this.currentIndex); //return it regardless? ok.
//            Lexer.Token tk = new    Lexer.Token();
//            return tk;
//            return new Lexer.Token();
        }
    }


    public void addToNewArray(Lexer.Token newToken) {
//        System.out.println("---------------");
//        System.out.println("---------------");
//        System.out.println("ADDING TO ARRAY");
//        System.out.println("---------------");
//        System.out.println("---------------");
//        System.out.println("array size:  " + newTokens.size());;
        if (newTokens.size() < 1 && newToken != null) {
            newTokens.add(newToken);
        } else {
            //check the rest of the tokens
            for (Lexer.Token token : newTokens) {
                if(newToken != null){
                    boolean duplicate = checkEquality(token.getPRules(), newToken.getPRules());
//                System.out.println("Duplicate: " + duplicate);
//                System.out.println("token1: " + token.toString());
//                System.out.println("token2: " + newToken.toString());
                    if ((token.data.equals(newToken.data) && token.type.equals(newToken.type) && duplicate)) {
//                duplicate = true;
//                    System.out.println("---------------------");
//                    System.out.println("---------------------");
//                    System.out.println("THEY ARE DUPLICATES");
//                    System.out.println("---------------------");
//                    System.out.println("---------------------");
                        break;
                    } else {
                        newTokens.add(newToken);
                        break;
                    }
                }

            }
        }
    }

    public void printTokens() {
//        System.out.println("==============");
//        System.out.println("==============");
//        System.out.println("PRINTING ARRAYS");
//        System.out.println("==============");
//        System.out.println("==============");
//        for(Lexer.Token s : newTokens){
//            System.out.println(s.toString());
//        }
        for (Lexer.Token token : this.newTokens) {
//            System.out.println(token);
//            file.write(token.toString() + "\n");

            System.out.println("--==Tokens==--");

            System.out.println("Token: " + token.type + "\n Lexeme: " + token.data);
            token.printRules();
            System.out.println("  ");
            System.out.println("  ");

        }


    }

    public boolean checkEquality(ArrayList<String> s1, ArrayList<String> s2) {
//        System.out.println("CHecking equality of arrays");
//        System.out.println("CHecking equality of arrays");
//        System.out.println("CHecking equality of arrays");
//        System.out.println("CHecking equality of arrays");
        if (s1 == s2) {
            return true;
        }

        if (s1 == null || s2 == null) {
            return false;
        }

        int n = s1.size();
        if (n != s2.size()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!s1.get(i).equals(s2.get(i)))
                return false;
        }
        return true;
    }
}
