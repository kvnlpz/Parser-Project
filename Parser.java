/*


R1. <Rat20F>  ::=   <Opt Function Definitions>   $$  <Opt Declaration List>  <Statement List>  $$

R2. <Opt Function Definitions> ::= <Function Definitions>     |  <Empty>

R3. <Function Definitions>  ::= <Function> | <Function> <Function Definitions>

R4. <Function> ::= function  <Identifier>   ( <Opt Parameter List> )  <Opt Declaration List>  <Body>

R5. <Opt Parameter List> ::=  <Parameter List>    |     <Empty>

R6. <Parameter List>  ::=  <Parameter>    |     <Parameter> , <Parameter List>

R7. <Parameter> ::=  <IDs >  <Qualifier>

R8. <Qualifier> ::= int     |    boolean    |  real

R9. <Body>  ::=  {  < Statement List>  }

R10. <Opt Declaration List> ::= <Declaration List>   |    <Empty>

R11. <Declaration List>  := <Declaration> ;     |      <Declaration> ; <Declaration List>

R12. <Declaration> ::=   <Qualifier > <IDs>

R13. <IDs> ::=     <Identifier>    | <Identifier>, <IDs>

R14. <Statement List> ::=   <Statement>   | <Statement> <Statement List>

R15. <Statement> ::=   <Compound>  |  <Assign>  |   <If>  |  <Return>   | <Print>   |   <Scan>   |  <While>

R16. <Compound> ::=   {  <Statement List>  }

R17. <Assign> ::=     <Identifier> = <Expression> ;

R18. <If> ::=     if  ( <Condition>  ) <Statement>   fi   |

                  if  ( <Condition>  ) <Statement>   else  <Statement>  fi

R19. <Return> ::=  return ; |  return <Expression> ;

R20. <Print> ::=    put ( <Expression>);

R21. <Scan> ::=    get ( <IDs> );

R22. <While> ::=  while ( <Condition>  )  <Statement>

R23. <Condition> ::=     <Expression>  <Relop>   <Expression>

R24. <Relop> ::=        ==   |   !=    |   >     |   <    |  <=   |    =>

R25. <Expression>  ::=    <Expression> + <Term>    | <Expression>  - <Term>    |    <Term>

R26. <Term>    ::=      <Term>  *  <Factor>     |   <Term>  /  <Factor>     |     <Factor>

R27. <Factor> ::=      -  <Primary>    |    <Primary>

R28. <Primary> ::=     <Identifier>  |  <Integer>  |   <Identifier>  ( <IDs> )   |   ( <Expression> )   |

                                     <Real>  |   true   |  false

R29. <Empty>   ::= 



Note: <Identifier>, <Integer>, <Real> are token types as defined in section (1) above





 */


//import com.sun.org.apache.bcel.internal.generic.RETURN;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//Since we dont have passing by reference with java
// we can jyst make an array in the parser class
// if the
//
//
//
//
//
//
//
//
//
//
//
//
public class Parser {

    //like up here something like this
    public TokenListManager manager;

    public static Lexer lexer;
    public static ArrayList<Lexer.Token> tokenArrayList;
    public static String table[];
    public static String symbolsArry[];
    public static String nonTerminalArray[];
    public boolean flag;
    List<Lexer.Token> tokensLinkedList;
    PrintStream o;


    FileWriter file;
    // Create tokens and print them
//    ArrayList<Lexer.Token> tokens = Lexer.lexFunc(input);

//    Parser parser = new Parser(tokens);


    // class constructor
    public Parser(ArrayList<Lexer.Token> tokens, PrintStream o) throws IOException {
        manager = new TokenListManager(tokens, o);
        this.o = o;
        // Store current System.out before assigning a new value
        PrintStream console = System.out;
        // Assign o to output stream
        System.setOut(o);


//        Lexer.Token s1 = new Lexer.Token(Lexer.TokenType.SEPARATOR, "ONE", 12);
//        Lexer.Token s2 = new Lexer.Token(Lexer.TokenType.IDENTIFIER, "ONE", 12);
//        Lexer.Token s3 = new Lexer.Token(Lexer.TokenType.IDENTIFIER, "THREE", 12);
//        System.out.println("=========PARSER CONSTTRUCTOR===========");
//        System.out.println("=========PARSER CONSTTRUCTOR===========");
//        System.out.println("=========PARSER CONSTTRUCTOR===========");
//        manager.addToNewArray(s1);
//        manager.addToNewArray(s2);
//        manager.addToNewArray(s3);
//        System.out.println("DONE ADDING");
//        manager.printTokens();


        tokenArrayList = tokens;
        tokensLinkedList = convertALtoLL(tokenArrayList);
        flag = true;
        R1();
    }

    // SAHILS CODE WEDNESDAY CHANGES BY KEVIN
    /*

    //        token.addToRules(s);
//        manager.addToNewArray(token);
        token = R2(token); //OFD
        manager.addToNewArray(token);
        if (token.data.equals("$$")) {
            token = R10(token); //ODL
           manager.addToNewArray(token);
            R14(token); // STATEMENT LIST
        }

//


            // }
            else {
//                System.out.println("THIS IS WHERE THE ERROR WOULD HAVE BEEN");
//                System.out.println("this is the token: " + token.toString());
                printError(token, "$$");
            }

     */





    public void R1() {
        String s = "Rat20F>  ::=   <Opt Function Definitions>   $$  <Opt Declaration List>  <Statement List>  $$";
        if (flag) {System.out.println(s);}
//            Lexer.Token token = new Lexer.Token(Lexer.TokenType.IDENTIFIER, "", 0);
        Lexer.Token token = manager.getCurrentToken();
//        System.out.println("HERE'S THE VERY FIRST TOKEN->"+token.toString());
        token = R2(token);
//        System.out.println("HERE IT IS AFTER R2()->"+token.toString());
        if(!token.data.equals("$$")){
            printError(token, "$$");
        }

        token = R10(token); //ODL
        token = R14(token); //STATEMENTLIST
        if(!token.data.equals("$$")){
            printError(token, "$$");
        }
//
//
//          /*
//
            token.addToRules(s);
        manager.addToNewArray(token);
        token = R2(token); //OFD
        manager.addToNewArray(token);
        if (token.data.equals("$$")) {
            token = R10(token); //ODL
           manager.addToNewArray(token);
            R14(token); // STATEMENT LIST
        }

//


            // }
            else {
//                System.out.println("THIS IS WHERE THE ERROR WOULD HAVE BEEN");
//                System.out.println("this is the token: " + token.toString());
//            System.out.println("else else else");
//                printError(token, "$$");
//                manager.tempNextToken(-2);
//                manager.tempNextToken(-1);
//                manager.tempNextToken(0);
//                manager.tempNextToken(1);
            }






//        manager.printTokens();

    }


    //OFD
    //OPT FUNCTION DEFINITION EMPTY
    public Lexer.Token R2(Lexer.Token token) {
        String s = "Opt Function Definitions> ::= <Function Definitions>     |  <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token); //might need to remove these dubplicates
        if (flag) {
            System.out.println(s);
        }

        token = manager.getNextToken();
        token.addToRules(s);
        manager.addToNewArray(token);


        //check to see if the next lexeme is the function keyword
        if (!(token.data.equals("function"))) {
            return manager.getCurrentToken(); //if it isnt, return the NEXT token after that
        }
//
//        if (token.data.equals ("$$")) {
//            R29(manager.getCurrentToken());
//        }


        //if it does equal the word FUNCTION, then we go to r3 to check for function definitions
        return R3(token);
    }

    // FUNCTION DEFINITION
    public Lexer.Token R3(Lexer.Token token) {

//        System.out.println("ALL OLD TOKENS");
//        System.out.println("ALL OLD TOKENS");
//        System.out.println("ALL OLD TOKENS");
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        manager.printOldTokenArray();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("ALL OLD TOKENS");
//        System.out.println("ALL OLD TOKENS");
        String s = "<Function Definitions>  ::= <Function> | <Function> <Function Definitions>   ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }


        //send it to the functon rule
        R4(token);//FUNCTION
        //send to FUNC  EMPTY
        return R4EMPTY(token);
    }

    //function
    public void R4(Lexer.Token token) {
        String s = "Function> ::= function  <Identifier>   ( <Opt Parameter List> )  <Opt Declaration List>  <Body>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        manager.getCurrentToken();
        token = manager.getNextToken(); // get the NEXT token whixh is the identifier
//        System.out.println("After calling getNextToken()->"+token.toString());
//        System.out.println("After calling getNextToken()->"+token.toString());
//        System.out.println("After calling getNextToken()->"+token.toString());
//        System.out.println("After calling getNextToken()->"+token.toString());
        System.out.println("setting and ggetting r13 from r4");
        token = R13(token);//IDS // set the token equal to the output of the ID function
//        System.out.println("After calling R13(): token->"+token.toString());
//        System.out.println("After calling R13(): token->"+token.toString());
//        System.out.println("After calling R13(): token->"+token.toString());
//        System.out.println("After calling R13(): token->"+token.toString());
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("TOKEN->" + token.toString());
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        token = manager.getNextToken();

        if (!token.data.equals("(")) {
            printError(token, "(");
        }
        //OPL
        token = R5(token);//OPL OPT PARAMETER LUST
        showprints();
        if (!token.data.equals(")")) {
            printError(token, ")");
        }
        token = R10(token);//ODL
        R9(token); // BODY
    }

    private void showprints() {
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
//        System.out.println("inside R4()");
    }

    //public Lexer.Token R4Empty(Lexer.Token token)
    public Lexer.Token R4EMPTY(Lexer.Token token) {
        String s = "<Function Definitions>' ::= <Function Definitions> | <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = manager.getNextToken();
        if (token.type == Lexer.TokenType.KEYWORD) {
            return token;
        } else {
            return R3(token);//function definitions
        }
    }

    //if the parameter list didnt end, and continues to the next line, then we send it to R5
    //if the parameter list continues
    //OPT PARAMETER LIST OPL EMPTY
    public Lexer.Token R5(Lexer.Token token) {
        String s = "Opt Parameter List> ::=  <Parameter List>    |     <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = manager.getNextToken(); //get the new latest token
        token.addToRules(s);
        manager.addToNewArray(token);
//        System.out.println("inside R5()");
//        System.out.println("inside R5()");
        System.out.println("inside R5()");
        manager.tempNextToken(-1);
        manager.tempNextToken(0);
        System.out.println("TOKEN->"+token.toString());
        manager.tempNextToken(1);


        if (token.type != Lexer.TokenType.IDENTIFIER) {
//            return R6(manager.getNextToken());
            return token;
        }
        token = R6(token);
        manager.addToNewArray(token);
//        return R6(token);//parameter list
        return token;
    }

    //R6() is for when the parameter list keeps going and stays on the same line like this par1, par2, par3
    public Lexer.Token R6(Lexer.Token token) {
        String s = "Parameter List>  ::=  <Parameter>    |     <Parameter> , <Parameter List>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        R7(token);//PARAMETER FUNCITON
        return R5(manager.getNextToken()); //PARAMETER EMPTY FUNCTION SO R5
    }


    //Parameter
    public void R7(Lexer.Token token) {
        String s = "Parameter> ::=  <IDs >  <Qualifier> ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        System.out.println(currentToken.toString());
        token = R13(token);
        //send to Qualifier function
        R8(token);//Qualifier function


    }

    //Qualifier
    public void R8(Lexer.Token token) {
        String s = "Qualifier> ::= int     |    boolean    |  real ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("int") || token.data.equals("boolean") || token.data.equals("real")) {
            return; //break
        } else {
            printError(token, "NUMBER TYPE");//send in the token to print out the error
        }
    }

    //BODY
    public void R9(Lexer.Token token) {
        String s = "<Body>  ::=  {  < Statement List>  }";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (!token.data.equals("{")) {
            printError(token, "{"); //make it print expected {
        }
        token = manager.getNextToken();
        token = R14(token); //satement list
        //WHAT does r14 return after calling it?
        if (!token.data.equals("}")) {
            printError(token, "}"); //print out token and expected: }
        }
    }

    //OPT Declaration List
    //ODL ODL ODL ODL ODL ODL ODL
    public Lexer.Token R10(Lexer.Token token) {
        String s = "<Opt Declaration List> ::= <Declaration List> | <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = manager.getNextToken();
        if (token.data.equals("int") || token.data.equals("boolean") || token.data.equals("real")) {
            return R11(token);
        } else {
            return token;
            //            printError(manager.getCurrentToken(), "R10 FUNCTION BOI");
        }
    }

    //declaration list
    public Lexer.Token R11(Lexer.Token token) {
        String s = "<Declaration List>  := <Declaration> ;     |      <Declaration> ; <Declaration List>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = R12(token);//DECLARATION
//        System.out.println("inside R11()");
//        System.out.println("inside R11()");
//        System.out.println("inside R11()");
        if (!token.data.equals(";")) {
            printError(token, ";");
        }
        return R11Empty(manager.getNextToken());
    }

    //DECLARATION LIST <EMPTY>
    public Lexer.Token R11Empty(Lexer.Token token) {
        String s = "<Declaration List> ::= <Declaration List>  |  <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("int") || token.data.equals("boolean") || token.data.equals("real")) {
            return R11(token);
        } else {
            return token;
        }
    }

    //Declaration
    public Lexer.Token R12(Lexer.Token token) {
        String s = "<Declaration> ::=   <Qualifier > <IDs> ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        R8(token);//QUALIFIERS
        //start with INT
        //from int to low
        token = manager.getNextToken(); //token now = "low"
        System.out.println("calling r13 from r12");
        return R13(token); // IDENTIFIERS
    }

    //Identifiers
    public Lexer.Token R13(Lexer.Token token) {
        String s = "<IDs> ::=     <Identifier>    | <Identifier>, <IDs>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        System.out.println("This is our token while in R13()->"+token.toString());
        if (!(token.type.equals(Lexer.TokenType.IDENTIFIER))) {
            System.out.println("this is WHERE THE ERROR IS");
            System.out.println("This is our token: "+token.toString());
            printError(token, "identifier");
//            int away = -1;
//            System.out.println("the token " + away  + " away from it is: " + manager.tempNextToken(-1));
            manager.tempNextToken(-1);
        }
//        System.out.println("Tokentype was not IDENTIFIER:");
//        System.out.println("tOKEN: " + token.toString());
//
        System.out.println("CALLING R13EMPTY()");
        token = R13Empty(token);
//        System.out.println("AFTER R13EMPTY()->" + token.toString());
        manager.addToNewArray(token);
        //return R13Empty(token);
        return  token;

    }

    //Ids continue
    private Lexer.Token R13Empty(Lexer.Token token) {
        String s = "<IDs> ::=     <Identifier>    | <Identifier>, <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = manager.getNextToken();
        System.out.println("TESTINGG");
        System.out.println("TESTINGG");
        System.out.println("TESTINGG");
        System.out.println(token.toString());
//        System.out.println("INSIDE R13EMPTY()->"+token.toString());
        if (token.data.equals(",")) {
            return R13(manager.getNextToken());//calling ID function
        }
        else {
//            return token;
            return R13(manager.getNextToken());
        }
    }

    private void printError(Lexer.Token token, String error) {
        System.out.println("Error at line number " + token.lineNumber + ": expected " + error + " but got " + token.data);
    }

    //Statement list
    public Lexer.Token R14(Lexer.Token token) {
        String s = "<Statement List> ::=   <Statement>   | <Statement> <Statement List>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        R15(token);//Statement //what does r15 do then?
        return R14Empty(manager.getNextToken());
    }

    //if the statement list continues
    private Lexer.Token R14Empty(Lexer.Token token) {
        String s = "<Statement List> ::= <Statement List>  |  <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("{") || token.data.equals("identifier")
                || token.data.equals("if") || token.data.equals("return")
                || token.data.equals("put") || token.data.equals("get") || token.data.equals("while")) {
            token = R14(token);
            manager.addToNewArray(token);
            return token;
//            return R14(token);//statement list
        } else {
            return token;
        }
    }


    //Statement
    // WILL NEED TO CHECK AGAIN
    public void R15(Lexer.Token token) {
        String s = "<Statement> ::=   <Compound>  |  <Assign>  |   <If>  |  <Return>   | <Print>   |   <Scan>   |  <While> ";
        token.addToRules(s);
        manager.addToNewArray(token);

        System.out.println("R15()->TOKEN:"+token.toString());

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("{")) {
            //compound
            R16(token);
        } else if (token.type == Lexer.TokenType.IDENTIFIER) //might have to change back to IDENTIFIER
        {
            System.out.println("------");
            System.out.println(token.toString());
            System.out.println("------");
            //assign
            R17(token);
        } else if (token.data.equals("if")) {
            //if
            R18(token);
        } else if (token.data.equals("return")) {
            //return
            R19(token);
        } else if (token.data.equals("put")) {
            //print
            R20(token);
        } else if (token.data.equals("get")) {
            //scan
            R21(token);
        } else if (token.data.equals("while")) {
            //while
            R22(token);
        } else {
            printError(token, "{ or identifier or if or return or put or get or whileD");
        }
    }

    //COMPOUND
    public void R16(Lexer.Token token) {
        String s = "<Compound> ::=   {  <Statement List>  } ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        //so we gave it the token that is {, then we overwrote that token by getting the next
        //next one, which ended up being, idk, return and passing it back to r14,
        //and it continues

        token = R14(manager.getNextToken());
//        System.out.println("__________--------------______");
//        System.out.println("__________--------------______");
//        System.out.println("COMPOUND COMPOUND");
//        System.out.println(token.toString());
//        System.out.println("__________--------------______");
//        System.out.println("__________--------------______");
        manager.addToNewArray(token);
        if (!token.data.equals("}")) {
            printError(token, "}");
        }
    }

    //ASSIGN
    public void R17(Lexer.Token token) {
        String s = "<Assign> ::=     <Identifier> = <Expression> ;";
        token.addToRules(s);
        manager.addToNewArray(token);
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("TESTING TESTING TESTING TESTING");
//        token.printRules();//not the original token from the array, passed by reference
//        System.out.println("TESTING TESTING TESTING TESTING");
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("===============================");
        if (flag) {
            System.out.println(s);
        }
        System.out.println("token: "+ token);
        token = manager.getNextToken();
        System.out.println("new : "+ token);
        token.addToRules(s);
        manager.addToNewArray(token);
        if (!token.data.equals("=")) {
            System.out.println("im right here lmao");
            printError(token, "=");
        }
        token = R25(manager.getNextToken());//Expression
        manager.addToNewArray(token);
        System.out.println("inside R17()");
        System.out.println("inside R17()");
        System.out.println("inside R17()");
        System.out.println("inside R17()");
        if (!token.data.equals(";")) {
            System.out.println("THIS IS WHERE THE ERROR IS");
            printError(token, ";");
            manager.tempNextToken(-3);
            manager.tempNextToken(-2);
            manager.tempNextToken(-1);
            manager.tempNextToken(0);
            manager.tempNextToken(1);
            manager.tempNextToken(2);
        }
    }

    //IF
    public void R18(Lexer.Token token) {
        String s = "<If> ::=     if  ( <Condition>  ) <Statement>   IF";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        System.out.println("R18 COMPARINGLEXEMES");
//        System.out.println("R18 COMPARINGLEXEMES");
//        System.out.println("R18 COMPARINGLEXEMES");

        compareLexemes("(");//CHECK THIS FUCNCTION
        R23(token);//CONDITION FUNCTION
        System.out.println("R18()");
//        token = manager.getNextToken();//DELETE SOON
        compareLexemes(")");
//        System.out.println("CALLING R15");
        System.out.println("CALLING R15 FROM R18");
        R15(manager.getNextToken());//STATEMENT
        System.out.println("CALLING R18FI");
        R18FI(manager.getNextToken());//IF FI
    }

    public void R18FI(Lexer.Token token) {
        String s = "if  ( <Condition>  ) <Statement>   else  <Statement>  fi ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("fi")) {
            return;
        } else if (token.data.equals("else")) {
            System.out.println("CALLING R15 FROMR18FI");
            R15(manager.getNextToken());//STATEMENT
            System.out.println("R18fi COMPARINGLEXEMES");
//            System.out.println("R18fi COMPARINGLEXEMES");
//            System.out.println("R18fi COMPARINGLEXEMES");

            compareLexemes("fi");//CHECKLEXEMES
        }

    }

    //loohahead
    private void compareLexemes(String data) {
//        System.out.println("COMAARING LEXEMES LLOOL");
//        System.out.println("COMAARING LEXEMES LLOOL");
        System.out.println("COMAARING LEXEMES LLOOL");
        System.out.println("CurrentToken:   " + manager.getCurrentToken().toString());
        Lexer.Token comparer = manager.getNextToken();//inceremetnts by 1 so currentIndex+1
        System.out.println("nowCURRENTTOKEN:    "+manager.getCurrentToken().toString());
//        manager.currentIndex--;
        if (!comparer.data.equals(data)) { //compare "fi" to whatever the string n data is
            printError(comparer, data);
        }
    }

    //RETURN
    public void R19(Lexer.Token token) {
        String s = "<Return> ::=  return ; |  return <Expression> ;";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        R19_SEMICOLON(token);
        return;
    }


    private void R19_SEMICOLON(Lexer.Token token) {
        String s = "<Return>' ::= ; | <Expression>;";
        token.addToRules(s);
        manager.addToNewArray(token);
        if (flag) {System.out.println(s);}
        token = manager.getNextToken();
        token.addToRules(s);
        manager.addToNewArray(token);
        if (token.data.equals(";")) {
            return;
        } else {
//            token = R25(token); //EXPRESSION
            token = R13(token); //EXPRESSION
            manager.addToNewArray(token);
        }
        System.out.println("inside R19SEMICOLON()");
        if (!token.data.equals(";")) {
            printError(token, ";");
        }
    }

    //Print
    public void R20(Lexer.Token token) {
        String s = "<Print> ::=    put ( <Expression>);";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        System.out.println("R20 COMPARINGLEXEMES");
//        System.out.println("R20 COMPARINGLEXEMES");
        System.out.println("R20 COMPARINGLEXEMES");
        compareLexemes("(");
        token = R25(manager.getNextToken());
        manager.addToNewArray(token);
        System.out.println("R20 COMPARINGLEXEMES");
//        System.out.println("R20 COMPARINGLEXEMES");

        if (!token.data.equals(")")) {
            printError(token, ")");
        }
//        System.out.println("R20 COMPARINGLEXEMES");
        System.out.println("R20 COMPARINGLEXEMES");
        compareLexemes(";");
    }

    //SCAN
    public void R21(Lexer.Token token) {
        String s = "<Scan> ::=    get ( <IDs )";
        token.addToRules(s);
        manager.addToNewArray(token);

//        System.out.println(currentToken.toString());
        if (flag) {
            System.out.println(s);
        }
//        System.out.println("R21 COMPARINGLEXEMES");
//        System.out.println("R21 COMPARINGLEXEMES");
        System.out.println("R21 COMPARINGLEXEMES");

        compareLexemes("(");

        System.out.println("calling r13 from r21");
        token = R13(manager.getNextToken());//IDS
        manager.addToNewArray(token);
        if (!token.data.equals(")")) { //changed on wednesday
            printError(token, ")");
        }
//        System.out.println("R21 COMPARINGLEXEMES");
        System.out.println("R21 COMPARINGLEXEMES");

        compareLexemes(";");
    }


    //WHILE
    public void R22(Lexer.Token token) {
        String s = "<While> ::=  while ( <Condition>  )  <Statement>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
//        System.out.println("R22 COMPARINGLEXEMES");
//        System.out.println("R22 COMPARINGLEXEMES");
        System.out.println("R22 COMPARINGLEXEMES");

        compareLexemes("(");
        token = R23(token); //CONDITION FUNCTION
        manager.addToNewArray(token);
        if (!token.data.equals(")")) {
            printError(token, ")");

        }
        System.out.println("CALLING R15 FROM R22()");
        //Statement
        R15(manager.getNextToken());
        return;

    }

    //Condition
    public Lexer.Token R23(Lexer.Token token) {
        String s = "<Condition> ::=     <Expression>  <Relop>   <Expression>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = R25(manager.getNextToken());//EXPRESSION
        manager.addToNewArray(token);
        R24(token);//RELOP
        return R25(manager.getNextToken());//RETURN NEXTtOKEN
    }

    //RELOP
    public void R24(Lexer.Token token) {
        String s = "<Relop> ::=        ==   |   !=    |   >     |   <    |  <=   |    =>        ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("==") ||
                token.data.equals("!=") ||
                token.data.equals(">") ||
                token.data.equals("<") ||
                token.data.equals(">=") ||
                token.data.equals("<=")) {
            return;
        }
    }

    //EXPRESSION
    public Lexer.Token R25(Lexer.Token token) {
        String s = "<Expression>  ::=    <Expression> + <Term>    | <Expression>  - <Term>    |    <Term>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        Lexer.Token newToken = R26TERM(token);
        return R25EMPTY(newToken);
    }

    //EXPRESSION EMPTY
    private Lexer.Token R25EMPTY(Lexer.Token token) {
        String s = "<Expression> ::= + <Term> <Expression> | - <Term> <Expression> | <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("+") || token.data.equals("-")) {
            token = R26TERM(manager.getNextToken());
            manager.addToNewArray(token);
            return R25EMPTY(token);
        } else {
            return token;
        }
    }


    //TERM
    public Lexer.Token R26TERM(Lexer.Token token) {
        String s = "<Term>    ::=      <Term>  *  <Factor>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = R27(token);//FACTOR
        manager.addToNewArray(token);
        return R26(token);//TERM EMPTY
    }

    //TERM EMPTY
    public Lexer.Token R26(Lexer.Token token) {
        String s = "<Term>    ::=      <Term>  *  <Factor>     |   <Term>  /  <Factor>     |     <EMPTY>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("*") || token.data.equals("/")) {
            token = R27(manager.getNextToken());//FACTOR
            manager.addToNewArray(token);
            return R26(token);
        } else {
            return token;
        }
    }

    //FACTOR
    public Lexer.Token R27(Lexer.Token token) {
        String s = "<Factor> ::=      -  <Primary>    |    <Primary>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        if (token.data.equals("-")) {
            token = R28(manager.getNextToken());
            manager.addToNewArray(token);
//            return R28(manager.getNextToken());//PRIMARY
            return token;
        } else {
            token = R28(token);
            manager.addToNewArray(token);
//            return R28(token);
            return token;
        }//PRIMARY
    }

    //PRIMARY
    public Lexer.Token R28(Lexer.Token token) {
        String s = "<Primary> ::=     <Identifier>  |  <Integer>  |   <Identifier>  ( <IDs> )   |   ( <Expression> )   |  ";
        token.addToRules(s);
        manager.addToNewArray(token);

//        Lexer.Token returnvar = token;
        if (flag) {
            System.out.println(s);
        }
        //if token.data.equals("identifier")
        if (token.type == Lexer.TokenType.IDENTIFIER) {
            token = R28EMPTY(token);
            manager.addToNewArray(token);
            return token;
//            return R28EMPTY(token);//PRIMARY EMPTY
        } else if (token.data.equals("(")) {
            token = R25(manager.getNextToken());//EXPRESSION
            manager.addToNewArray(token);
            System.out.println("INSIDE R28()");
            System.out.println("INSIDE R28()");
            System.out.println("INSIDE R28()");

            if (!token.data.equals(")")) {
                printError(token, ")");//expected )
            } else {
                return manager.getNextToken();
            }
        } else if (token.data.equals("int")
                || token.data.equals("real")
                || token.data.equals("true")
                || token.data.equals("false")
                || token.type == Lexer.TokenType.NUMBER) {
            token = manager.getNextToken();
            token.addToRules(s);
            manager.addToNewArray(token);
        } else {
            printError(token, "NUMBER"); // could be part of the issue, neext to test soon
        }
        return token;
    }

    //if the IDS end is empty
    public Lexer.Token R28EMPTY(Lexer.Token token) {
        String s = "<Primary> ::= ( <IDs> ) | <Empty>";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
        token = manager.getNextToken();
        token.addToRules(s);
        manager.addToNewArray(token);
        System.out.println("INSIDE R28EMPTY");
        System.out.println("INSIDE R28EMPTY");
        System.out.println("INSIDE R28EMPTY");

        if (!token.data.equals("(")) {
            return token;
        } else {
            token = R13(manager.getNextToken()); //IDENTIFIER
            manager.addToNewArray(token);
        }
        System.out.println("INSIDE R28EMPTY V2");
        System.out.println("INSIDE R28EMPTY V2");

        if (!token.data.equals(")")) {
            printError(token, ")");
        } else {
            token = manager.getNextToken();
        }
        return token;
    }

    //EMPTY
    public void R29(Lexer.Token token) {
        String s = "<Empty>   ::=   ";
        token.addToRules(s);
        manager.addToNewArray(token);

        if (flag) {
            System.out.println(s);
        }
    }

    // ArrayList to LinkedList
    public static <T> List<T> convertALtoLL(List<T> aL) {

        // Return the converted LinkedList
        return new LinkedList<>(aL);
    }

}