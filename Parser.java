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


import sun.net.www.content.text.PlainTextInputStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    //like up here something like this
    public  static  TokenListManager manager;

    public static Lexer lexer;
    public static ArrayList<Lexer.Token> tokenArrayList;
    public static String table[];
    public static String symbolsArry[];
    public static String nonTerminalArray[];
    public boolean flag;
    List<Lexer.Token> tokensLinkedList;


    // class constructor
    public Parser(ArrayList<Lexer.Token> token) {
        //h it worked LMAO

        tokenArrayList = token;
        tokensLinkedList = convertALtoLL(tokenArrayList);
//        System.out.println(tokenArrayList);
//        System.out.println(tokensLinkedList);
        flag = true;
//        TokenListManager tokenListManager = new TokenListManager(tokenArrayList);
 //       Lexer.Token test = tokenListManager.getNextToken();
  //      System.out.println(tokenListManager.getNextToken());
   //     while (test != null){
  //          test = tokenListManager.getNextToken();
   //         System.out.println(test);

//        }
    }

    public void R1() {
        if(flag){
            System.out.println("Rat20F>  ::=   <Opt Function Definitions>   $$  <Opt Declaration List>  <Statement List>  $$");
        //let me know on discord if you can see what im showing
            // alright so i started off with R1() but to finish R! i need to finish r3

        }

    }
    //make it return a token instead of the token manager
    //OPT FUNCTION DEFINITION EMPTY
    public Lexer.Token R2(Lexer.Token token) {
        if(flag){
            System.out.println("Opt Function Definitions> ::= <Function Definitions>     |  <Empty>");

        }
        manager.getCurrentToken();
        if(!manager.getCurrentToken().data.equals("function")){
            return manager.getCurrentToken();
        }

        //function defniiition
        return R3(token); //but fo finish r2 i need to finish r3
    }

    //OPT FUNCTION DEFINITION
    public Lexer.Token R3(Lexer.Token token) {
        if(flag){
            System.out.println("Function Definitions>  ::= <Function> | <Function> <Function Definitions>   ");
        }
        R4(token); //and fo finish r3 i need to finish r4...
        //send to OPT FUCTION EMPTY
        return R2(token);//TODO
    }

    //function
    //OPT DECLARATION LIST
    public void R4(Lexer.Token token) {
        if(flag){
            System.out.println("Function> ::= function  <Identifier>   ( <Opt Parameter List> )  <Opt Declaration List>  <Body>");
        }

        manager.getCurrentToken();
        token = manager.getNextToken();
        token = R13(token);

        if(token.data != "("){
            printError(token);//make it actually print info out
        }
        token = R5(token);

        if(token.data != ")"){
            printError(token);//print out that we expected )
        }

        token = R10(token);
        R9(token);



        //TODO() FINISH THIS, COMPARE TO THE LINK SENT ON DISCORD

    }

    //if the parameter list didnt end, and continues to the next line, then we send it to R%
    //if the parameter list continues
    //OPT PARAMETER LIST
    public Lexer.Token R5(Lexer.Token token) {
        if(flag){
            System.out.println("Opt Parameter List> ::=  <Parameter List>    |     <Empty>");
        }


        token = manager.getNextToken(); //get the new latest token

        if (manager.getCurrentToken().type != Lexer.TokenType.IDENTIFIER) {
            return R6(manager.getNextToken());
        }
        return R6(token); // this needs to be like R6 i think or r4 or r5

    } //done

    //R6() is for when the parameter list keeps going and stays on teh same line like this par1, par2, par3
    public Lexer.Token R6(Lexer.Token token) {
        if(flag){
            System.out.println("Parameter List>  ::=  <Parameter>    |     <Parameter> , <Parameter List>");
        }
        R7(manager.getCurrentToken());
        return R5(manager.getNextToken()); //send in the next token
    } //done
    //Parameter
    public void R7(Lexer.Token currentToken) {
        if(flag){
            System.out.println("Parameter> ::=  <IDs >  <Qualifier> ");
        }
        //send to Qualifier function
         R8(manager.getNextToken());


    } //done
    //Qualifier
    public void R8(Lexer.Token token) {
        if (flag) {
            System.out.println("Qualifier> ::= int     |    boolean    |  real ");
        }
        if (manager.getCurrentToken().type == Lexer.TokenType.NUMBER) {
            return; //break
        } else {
            printError(manager.getCurrentToken());//send in the token to print out the error
            //TODO() finish the error printing function
        }
    } //done

    //BODY
    //SATEMENT LIST
    public void R9(Lexer.Token token) {
        if (flag) {
            System.out.println("<Body>  ::=  {  < Statement List>  }");
        }
        if(token.data != "{"){
            printError(token); //make it print expected {
        }
        token = manager.getNextToken();

    }

    //OPT Declaration List
    public Lexer.Token R10(Lexer.Token token) {
        if (flag) {
            System.out.println("<Opt Declaration List> ::= <Declaration List>   |    <Empty>");
        }

        if(manager.getCurrentToken().type == Lexer.TokenType.KEYWORD) {
            R11(manager.getNextToken());

        }
        else {
            printError(manager.getCurrentToken());
        }
        return token;
    }

    //declartion list
    public void R11(Lexer.Token token) {

    if(flag) {
        System.out.println("<Declaration List>  := <Declaration> ;     |      <Declaration> ; <Declaration List>");
    }
//    token =

    }

    public void R12() {
        System.out.println("<Declaration> ::=   <Qualifier > <IDs>                   ");

    }

    //Identifiers
    public Lexer.Token R13(Lexer.Token token) {
        if(flag){
            System.out.println("<IDs> ::=     <Identifier>    | <Identifier>, <IDs>");
        }
        if(manager.getCurrentToken().type != Lexer.TokenType.IDENTIFIER){
            printError(manager.getCurrentToken());
        }
            return  R13Empty(token);
    }

    private Lexer.Token R13Empty(Lexer.Token token) {
        if(flag){
            System.out.println("<IDs> ::=     <Identifier>    | <Identifier>, <Empty>");
        }
        token = manager.getNextToken();
        if(manager.getCurrentToken().data.equals(",")){
            return R13(manager.getNextToken()); //this part might be wrong. because I am calling getNextToken twice, we'll see

        }
        else {
            return token; //placeholder, change later
        }
    }

    private void printError(Lexer.Token token){

    }

    //Statement list
    public Lexer.Token R14(Lexer.Token token) {
    if(flag){
            System.out.println("<Statement List> ::=   <Statement>   | <Statement> <Statement List>");
        }

        R15(token);
        return R14Empty(token);

    }

    //if the statement list continues

    private Lexer.Token R14Empty(Lexer.Token token) {
        if(flag){
            System.out.println("<Statement List> ::= <Statement List>  |  <Empty>");

        }
        if(token.data.equals("{")||token.data.equals("identifier")
                ||token.data.equals("if") ||token.data.equals("return")
                ||token.data.equals("put")||token.data.equals("get")||token.data.equals("while")){
            return R14(token);

        }
        else {
            return token;
        }
    }


    //Statement
    // WILL NEED TO CHECK AGAIN
    public void R15(Lexer.Token token) {
        if(flag){
            System.out.println("<Statement> ::=   <Compound>  |  <Assign>  |   <If>  |  <Return>   | <Print>   |   <Scan>   |  <While> ");
        }

        if(manager.getCurrentToken().data.equals("{"))
        {
            R16(); //MIGHT HAVE TO CORRECT THIS LATER
        }
        else if(manager.getCurrentToken().data.equals("identifier"))
        {
            R17();
        }
        else if(manager.getCurrentToken().data.equals("if"))
        {
                R18();
        }
        else if(manager.getCurrentToken().data.equals("return"))
        {
            R19();
        }
        else if(manager.getCurrentToken().data.equals("put"))
        {
            R20();
        }

        else if(manager.getCurrentToken().data.equals("get"))
        {
            R21();
        }
        else if(manager.getCurrentToken().data.equals("while"))
        {
            R22();
        }
        else
        {
            printError(manager.getCurrentToken());
        }
    }

    //COMPOUND
    public void R16() {
        System.out.println("<Compound> ::=   {  <Statement List>  } ");

    }
    //ASSIGN
    public void R17() {
        System.out.println("<Assign> ::=     <Identifier> = <Expression> ;");

    }

    //IF
    public void R18(Lexer.Token token  ) {
        if(flag){
            System.out.println("<If> ::=     if  ( <Condition>  ) <Statement>   fi   |   ");
        }



    }

    public void R18FI(Lexer.Token token){
        if(flag){
            System.out.println("             if  ( <Condition>  ) <Statement>   else  <Statement>  fi ");
        }
        if(token.data.equals("fi")){
            return;
        }

        else if(token.data.equals("else")){
            R15(manager.getNextToken());
            compareLexemes(token.data);
        }

    }

    private void compareLexemes(String data) {
        Lexer.Token comparer = manager.getNextToken();
        if(!comparer.data.equals(data)){
            printError(comparer); //why
        }
    }

    //RETURN
    public void R19(Lexer.Token token) {
        if(flag){

            System.out.println("<Return> ::=  return ; |  return <Expression> ;");
        }

        R19_SEMICOLON();

    }

    public void R20() {
        System.out.println("<Print> ::=    put ( <Expression>);");


    }

    public void R21() {
        System.out.println("<Scan> ::=    get ( <IDs> );");

    }

    public void R22() {
        System.out.println("<While> ::=  while ( <Condition>  )  <Statement>  ");

    }

    public void R23() {
        System.out.println("<Condition> ::=     <Expression>  <Relop>   <Expression>");

    }

    public void R24() {
        System.out.println("<Relop> ::=        ==   |   !=    |   >     |   <    |  <=   |    =>        ");

    }

    public void R25() {
        System.out.println("<Expression>  ::=    <Expression> + <Term>    | <Expression>  - <Term>    |    <Term>");

    }

    public void R26() {
        System.out.println("<Term>    ::=      <Term>  *  <Factor>     |   <Term>  /  <Factor>     |     <Factor>");

    }

    public void R27() {
        System.out.println("<Factor> ::=      -  <Primary>    |    <Primary>");
        System.out.println("                                <Real>  |   true   |  false                        ");

    }

    public void R28() {
        System.out.println("<Primary> ::=     <Identifier>  |  <Integer>  |   <Identifier>  ( <IDs> )   |   ( <Expression> )   |  ");


    }

    //EMPTY
//DONE I THINK
    public void R29() {
        if(flag){
            System.out.println("<Empty>   ::= 	");
        }



    }




    // ArrayList to LinkedList
    public static <T> List<T> convertALtoLL(
            List<T> aL)
    {

        // Return the converted LinkedList
        return new LinkedList<>(aL);
    }

}