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


public class Parser {

    public static Lexer lexer;
    public static String table[];
    public static String symbolsArry[];
    public static String nonTerminalArray[];


    // class constructor
    public Parser() {

    }


    public void R1() {
        System.out.println("Rat20F>  ::=   <Opt Function Definitions>   $$  <Opt Declaration List>  <Statement List>  $$");

    }

    public void R2() {
        System.out.println("Opt Function Definitions> ::= <Function Definitions>     |  <Empty>");

    }

    public void R3() {
        System.out.println("Function Definitions>  ::= <Function> | <Function> <Function Definitions>   ");

    }

    public void R4() {
        System.out.println("Function> ::= function  <Identifier>   ( <Opt Parameter List> )  <Opt Declaration List>  <Body>");

    }

    public void R5() {
        System.out.println("Opt Parameter List> ::=  <Parameter List>    |     <Empty>");

    }

    public void R6() {
        System.out.println("Parameter List>  ::=  <Parameter>    |     <Parameter> , <Parameter List>");

    }

    public void R7() {
        System.out.println("Parameter> ::=  <IDs >  <Qualifier> ");

    }

    public void R8() {
        System.out.println("Qualifier> ::= int     |    boolean    |  real ");

    }

    public void R9() {
        System.out.println("<Body>  ::=  {  < Statement List>  }");

    }

    public void R10() {
        System.out.println("<Opt Declaration List> ::= <Declaration List>   |    <Empty>");

    }

    public void R11() {
        System.out.println("<Declaration List>  := <Declaration> ;     |      <Declaration> ; <Declaration List>");

    }

    public void R12() {
        System.out.println("<Declaration> ::=   <Qualifier > <IDs>                   ");

    }

    public void R13() {
        System.out.println("<IDs> ::=     <Identifier>    | <Identifier>, <IDs>");

    }

    public void R14() {
        System.out.println("<Statement List> ::=   <Statement>   | <Statement> <Statement List>");

    }

    public void R15() {
        System.out.println("<Statement> ::=   <Compound>  |  <Assign>  |   <If>  |  <Return>   | <Print>   |   <Scan>   |  <While> ");

    }

    public void R16() {
        System.out.println("<Compound> ::=   {  <Statement List>  } ");

    }

    public void R17() {
        System.out.println("<Assign> ::=     <Identifier> = <Expression> ;");

    }

    public void R18() {
        System.out.println("<If> ::=     if  ( <Condition>  ) <Statement>   fi   |   ");

        //ALSO

        System.out.println("             if  ( <Condition>  ) <Statement>   else  <Statement>  fi ");

    }

    public void R19() {
        System.out.println("<Return> ::=  return ; |  return <Expression> ;");


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

    public void R29() {
        System.out.println("<Empty>   ::= ");


    }


}