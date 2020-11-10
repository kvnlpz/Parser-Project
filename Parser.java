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



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    //like up here something like this
    public   TokenListManager manager;

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
    public Parser(ArrayList<Lexer.Token> token, PrintStream o) throws IOException {

        this.o = o;
        // Store current System.out before assigning a new value
        PrintStream console = System.out;

        // Assign o to output stream
        System.setOut(o);


        manager = new TokenListManager(token);

        tokenArrayList = token;
        tokensLinkedList = convertALtoLL(tokenArrayList);
        flag = true;
//        System.out.println(manager.getCurrentToken().toString());
//        System.out.println(manager.getNextToken());
//        System.out.println(manager.getNextToken());
//        System.out.println(manager.getNextToken());
        R1();
    }

    public void R1() {
        if(flag) {
            System.out.println("Rat20F>  ::=   <Opt Function Definitions>   $$  <Opt Declaration List>  <Statement List>  $$");
        }

            Lexer.Token token = new Lexer.Token(Lexer.TokenType.IDENTIFIER, "", 0);
            token = R2(token); //OFD
            if(!token.data.equals("$$")){
                printError(token, "$$");
            }
            token = R10(token); //ODL
            token = R14(token); // STATEMENT LIST
            if(!token.data.equals("$$")){
                printError(token, "$$");
            }

    }

    //OPT FUNCTION DEFINITION EMPTY
    public Lexer.Token R2(Lexer.Token token) {
        if(flag){ System.out.println("Opt Function Definitions> ::= <Function Definitions>     |  <Empty>");}
        token = manager.getCurrentToken();
        if(!manager.getCurrentToken().data.equals("function")){
            return manager.getCurrentToken();
        }

        if(manager.getCurrentToken().data.equals("$$"))
        {
            R29(manager.getCurrentToken());
        }


        return R3(token);
    }

    // FUNCTION DEFINITION
    public Lexer.Token R3(Lexer.Token token) {
        if(flag){ System.out.println("Function Definitions>  ::= <Function> | <Function> <Function Definitions>   ");}
        R4(token);//FUNCTION
        //send to FUNC  EMPTY
        return R4EMPTY(token);
    }

    //function
    public void R4(Lexer.Token token) {
        if(flag){ System.out.println("Function> ::= function  <Identifier>   ( <Opt Parameter List> )  <Opt Declaration List>  <Body>"); }
//        manager.getCurrentToken();
        token = manager.getNextToken();
        token = R13(token);//IDS
        if(!token.data.equals("(")){printError(token, "Missing left parenthesis '('");}
        //OPL
        token = R5(token);
        if(!token.data.equals(")")){ printError(token, "Missing right parenthesis ')'");}
        token = R10(token);//ODL
        R9(token);
    }

    //public Lexer.Token R4Empty(Lexer.Token token)
    public Lexer.Token R4EMPTY(Lexer.Token token){
        if(flag){System.out.println("<Function Definitions>' ::= <Function Definitions> | <Empty>");}
        token = manager.getNextToken();
        if(token.data.equals("function")){
            return  token;
        }
        else {
            return R3(token);//function definitions
        }
    }

    //if the parameter list didnt end, and continues to the next line, then we send it to R5
    //if the parameter list continues
    //OPT PARAMETER LIST OPL EMPTY
    public Lexer.Token R5(Lexer.Token token) {
        if(flag){System.out.println("Opt Parameter List> ::=  <Parameter List>    |     <Empty>");}
        token = manager.getNextToken(); //get the new latest token
        if (token.type != Lexer.TokenType.IDENTIFIER) {
//            return R6(manager.getNextToken());
            return token;
        }
        return R6(token);//parameter list

    }

    //R6() is for when the parameter list keeps going and stays on the same line like this par1, par2, par3
    public Lexer.Token R6(Lexer.Token token) {
        if(flag){System.out.println("Parameter List>  ::=  <Parameter>    |     <Parameter> , <Parameter List>");}
        R7(manager.getCurrentToken());//PARAMETER FUNCITON
        return R5(manager.getNextToken()); //PARAMETER EMPTY FUNCTION SO R5
    }



    //Parameter
    public void R7(Lexer.Token token) {
        if(flag){System.out.println("Parameter> ::=  <IDs >  <Qualifier> ");}
//        System.out.println(currentToken.toString());
        token = R13(token);
        //send to Qualifier function
        R8(token);//Qualifier function


    }
    //Qualifier
    public void R8(Lexer.Token token) {
        if (flag) {System.out.println("Qualifier> ::= int     |    boolean    |  real ");}
        if (token.data.equals("int")||token.data.equals("boolean")|| token.data.equals("real")) {
            return; //break
        } else {
            printError(manager.getCurrentToken(),"NUMBER TYPE");//send in the token to print out the error
        }
    }

    //BODY
    public void R9(Lexer.Token token) {
        if (flag) { System.out.println("<Body>  ::=  {  < Statement List>  }");}
        if(!token.data.equals("{")){
            printError(token, "{"); //make it print expected {
        }
        token = manager.getNextToken();
        token = R14(token); //satement list
        if(token.data.equals("}")){
            printError(token, "}"); //print out token and expected: }
        }
    }

    //OPT Declaration List
    //ODL ODL ODL ODL ODL ODL ODL
    public Lexer.Token R10(Lexer.Token token) {
        if (flag) {System.out.println("<Opt Declaration List> ::= <Declaration List> | <Empty>");}
        token = manager.getNextToken();
        if(token.data.equals("int")|| token.data.equals("boolean")||token.data.equals("real")){
            return R11(token);
        }
        else {
            return token;
    //            printError(manager.getCurrentToken(), "R10 FUNCTION BOI");
        }
    }

    //declaration list
    public Lexer.Token R11(Lexer.Token token) {
        if(flag) {System.out.println("<Declaration List>  := <Declaration> ;     |      <Declaration> ; <Declaration List>");}
        token = R11(token);
        if(!manager.getCurrentToken().data.equals(";"))
        {
            printError(token, ";");
        }
        return  R11Empty(manager.getNextToken());
    }

    //DECLARATION LIST <EMPTY>
    public Lexer.Token R11Empty(Lexer.Token token){
        if(flag){System.out.println("<Declaration List> ::= <Declaration List>  |  <Empty>");}
        if(token.data.equals("int")|| token.data.equals("boolean")||token.data.equals("real")){
            return R11(token);
        }
        else{
            return token;
        }
    }

    //Declaration
    public Lexer.Token R12(Lexer.Token token) {
        if(flag){System.out.println("<Declaration> ::=   <Qualifier > <IDs> ");}
        R8(manager.getCurrentToken());//QUALIFIER
        token = manager.getNextToken();
        return R13(token);
    }

    //Identifiers
    public Lexer.Token R13(Lexer.Token token) {
        if(flag){System.out.println("<IDs> ::=     <Identifier>    | <Identifier>, <IDs>");}
        if(manager.getCurrentToken().type != Lexer.TokenType.IDENTIFIER){
            printError(manager.getCurrentToken(), "identifier");
        }
        return  R13Empty(token);
    }

    private Lexer.Token R13Empty(Lexer.Token token) {
        if(flag){System.out.println("<IDs> ::=     <Identifier>    | <Identifier>, <Empty>");}
        token = manager.getNextToken();
        if(token.data.equals(",")){
            return R13(manager.getNextToken());//calling ID function
        }
        else {
            return token;
        }
    }
    private void printError(Lexer.Token token, String error){
        System.out.println("Error at line number " + token.lineNumber + ": expected " + error + " but got " + token.data);
    }

    //Statement list
    public Lexer.Token R14(Lexer.Token token) {
        if(flag){System.out.println("<Statement List> ::=   <Statement>   | <Statement> <Statement List>");}
        R15(token);//Statement
        return R14Empty(manager.getNextToken());
    }

    //if the statement list continues
    private Lexer.Token R14Empty(Lexer.Token token) {
        if(flag){System.out.println("<Statement List> ::= <Statement List>  |  <Empty>");}
        if(token.data.equals("{")||token.data.equals("identifier")
                ||token.data.equals("if") ||token.data.equals("return")
                ||token.data.equals("put")||token.data.equals("get")||token.data.equals("while")){
            return R14(token);//statement list
        }
        else { return token;}
    }


    //Statement
    // WILL NEED TO CHECK AGAIN
    public void R15(Lexer.Token token) {
        if(flag){System.out.println("<Statement> ::=   <Compound>  |  <Assign>  |   <If>  |  <Return>   | <Print>   |   <Scan>   |  <While> ");}
        if(manager.getCurrentToken().data.equals("{"))
        {
            //compound
            R16(manager.getCurrentToken());
        }
        else if(manager.getCurrentToken().type == Lexer.TokenType.IDENTIFIER)
        {
            //assign
            R17(manager.getCurrentToken());
        }
        else if(manager.getCurrentToken().data.equals("if"))
        {
            //if
            R18(manager.getNextToken());
        }
        else if(manager.getCurrentToken().data.equals("return"))
        {
            //return
            R19(manager.getNextToken());
        }
        else if(manager.getCurrentToken().data.equals("put"))
        {
            //print
            R20(manager.getCurrentToken());
        }

        else if(manager.getCurrentToken().data.equals("get"))
        {
            //scan
            R21(manager.getCurrentToken());
        }
        else if(manager.getCurrentToken().data.equals("while"))
        {
            //while
            R22(manager.getNextToken());
        }
        else
        {
//
            printError(manager.getCurrentToken(),"{ or OR IDENTIFIER OR KEYWORD");
        }
    }

    //COMPOUND
    public void R16(Lexer.Token token) {


        if(flag){
            System.out.println("<Compound> ::=   {  <Statement List>  } ");
        }
        if(manager.getCurrentToken().data.equals("(")){
            R14(manager.getNextToken());
        }
    }
    //ASSIGN
    public void R17(Lexer.Token token) {


        if(flag){
            System.out.println("<Assign> ::=     <Identifier> = <Expression> ;");
        }

        if(manager.getCurrentToken().data.equals("=="))
        {
            R25(manager.getNextToken());
        }
        else{
            printError(manager.getCurrentToken(), "==");
        }

    }

    //IF
    public void R18(Lexer.Token token  ) {


        if(flag){
            System.out.println("<If> ::=     if  ( <Condition>  ) <Statement>   fi   |   ");
        }

        compareLexemes("(");
        R23(token);
        compareLexemes(")");
        R15(manager.getNextToken());
        R18FI(manager.getNextToken());


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
            compareLexemes("fi");
        }

    }

    //loohahead
    private void compareLexemes(String data) {
        Lexer.Token comparer = manager.getNextToken();
        if(!comparer.data.equals(data)){//compare "fi" to whatever the string n data is
            printError(comparer, data);
        }
    }

    //RETURN
    public void R19(Lexer.Token token) {


        if(flag){

            System.out.println("<Return> ::=  return ; |  return <Expression> ;");
        }

        R19_SEMICOLON(manager.getCurrentToken());

    }

    private void R19_SEMICOLON(Lexer.Token token) {


        if (flag){
            System.out.println("<Return>' ::= ; | <Expression>;");
        }

        token = manager.getNextToken();
        if(token.data.equals(";")){
            return;
        }
        else {
            token = R25(token);

        }
        if(!token.data.equals(";")){
            printError(token, ";");
        }
    }

    //Print
    public void R20(Lexer.Token token) {


        if (flag){
            System.out.println("<Print> ::=    put ( <Expression>);");
        }
        if(manager.getCurrentToken().data.equals("("))
        {
            R25(manager.getNextToken());
        }
        else{
            printError(manager.getCurrentToken(), "(");
        }

    }

    public void R21(Lexer.Token currentToken) {
        System.out.println(currentToken.toString());

        if(flag){
            System.out.println("<Scan> ::=    get ( <IDs> );");
        }
        if(manager.getCurrentToken().data.equals("("))
        {
            R13(manager.getNextToken());
        }
        else {
            printError(manager.getCurrentToken(), "(");
        }
    }



    public void R22(Lexer.Token token) {


        if(flag){

            System.out.println("<While> ::=  while ( <Condition>  )  <Statement>  ");
        }
        if(manager.getCurrentToken().data.equals("(")){
            R23(token);
            if(manager.getCurrentToken().data.equals(")"))
            {
                R15(manager.getNextToken());
            }
        }
    }

    //Condition
    public Lexer.Token R23(Lexer.Token token) {
        if(flag){
            System.out.println("<Condition> ::=     <Expression>  <Relop>   <Expression>");
        }

        token = R25(manager.getNextToken());
        R24(token);
        return R25(manager.getNextToken());

    }


    public void R24(Lexer.Token token) {

        if(flag)
        {
            System.out.println("<Relop> ::=        ==   |   !=    |   >     |   <    |  <=   |    =>        ");
        }
        if (manager.getCurrentToken().data.equals("==")  || manager.getCurrentToken().data.equals("^=")  ||
                manager.getCurrentToken().data.equals(">") ||
                manager.getCurrentToken().data.equals("<") ||
                manager.getCurrentToken().data.equals(">=") ||
                manager.getCurrentToken().data.equals("<="))
        {
            return;
        }
        else
        {
            printError(manager.getCurrentToken(), "equality"); // change
        }

    }


    //EXPRESSION
    public Lexer.Token R25(Lexer.Token token) {
        if(flag){
            System.out.println("<Expression>  ::=    <Expression> + <Term>    | <Expression>  - <Term>    |    <Term>");

        }

        Lexer.Token newToken  = R26TERM(token);
        return R25EMPTY(newToken);

    }

    private Lexer.Token R25EMPTY(Lexer.Token token) {
        if(flag){
            System.out.println("<Expression> ::= + <Term> <Expression> | - <Term> <Expression> | <Empty>");
        }

        if(token.data.equals("+")||token.data.equals("-")){
            token = R26TERM(manager.getNextToken());
            return R25EMPTY(token);
        }
        else {
            return token;
        }

    }

    public Lexer.Token R26TERM(Lexer.Token token){
        if(flag){
            System.out.println("<Term>    ::=      <Term>  *  <Factor>");
        }

        token = R27(token);
        return R26(token);
    }

    //term
    public Lexer.Token R26(Lexer.Token token) {
        if(flag){
            System.out.println("<Term>    ::=      <Term>  *  <Factor>     |   <Term>  /  <Factor>     |     <Factor>");

        }
        if(token.data.equals("*")|| token.data.equals("/")){
            token = R27(manager.getNextToken());
        }
        return token;
    }

    //FACTOR
    public Lexer.Token R27(Lexer.Token token) {
        if(flag){
            System.out.println("<Factor> ::=      -  <Primary>    |    <Primary>");

        }
        if(token.data.equals("-")){
            return R28(manager.getNextToken());
        }
        else{
            return R28(token);
        }

    }

    //PRIMARY
    public Lexer.Token R28(Lexer.Token token) {
        Lexer.Token returnvar = token;
        if(flag){
            System.out.println("<Primary> ::=     <Identifier>  |  <Integer>  |   <Identifier>  ( <IDs> )   |   ( <Expression> )   |  ");

        }
        if(token.data.equals("identifier")){
            returnvar = R28EMPTY(token);
        } else if (token.data.equals("(")) {

            token = R25(manager.getNextToken());
            if(!token.data.equals(")")){
                printError(token, ")");//expected )
            }
            else{
                returnvar = manager.getNextToken();

            }
        }
        else if(token.data.equals("int")
                || token.data.equals("real")
                || token.data.equals("true")
                || token.data.equals("false")){
            returnvar = manager.getNextToken();
        }else{
            printError(token, "NUMBER");
        }

        return returnvar;
    }
    //if the IDS end is empty
    public Lexer.Token R28EMPTY(Lexer.Token token) {
        if (flag) {
            System.out.println("<Primary> ::= ( <IDs> ) | <Empty>");
        }
        token = manager.getNextToken();
        if (!token.data.equals("(")) {
            return token;
        } else {
            token = R13(manager.getNextToken());
        }
        if (!token.data.equals(")")) {
            printError(token, ")");
        } else {
            token = manager.getNextToken();
//            return manager.getNextToken();
        }
        return token;
    }

    //EMPTY
    public void R29(Lexer.Token token) {


        if(flag){
            System.out.println("<Empty>   ::=   ");
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