import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String[] tokens;
//    String commentStart = "/*";
//    String commentEnd = "*/";
//    String int_ID = "int";
//    String real_ID = "real"; //must have a . in the number
//    String if_keyword = "if";
//    String else_keyword = "else";
//    String fi_keyword = "fi";
//    String while_keyword = "while";
//    String return_keyword = "return";
//    String get_keyword = "get";
//    String put_keyword = "put";

    public static PrintStream o;

    static {
        try {
            o = new PrintStream(new File("A.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Store current System.out before assigning a new value
    public static PrintStream console = System.out;

    // Assign o to output stream
//        System.setOut(o);

    public static void main(String[] args) throws IOException {
        StringBuilder fileContents = new StringBuilder();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                System.out.println(line);
                fileContents.append(line);
//                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FIle not found.");
            e.printStackTrace();
        }
//        String input = "11 + 22 - 33";
        String input = String.valueOf(fileContents);
        Lexer lex = new Lexer(input);

//        FileWriter file = new FileWriter("output.txt");
        // Create tokens and print them
        ArrayList<Lexer.Token> tokens = Lexer.lexFunc(input);

        Parser parser = new Parser(tokens, o);


        System.out.println("-----------------------------");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|     ----====lexer====----  |");
        System.out.println("|     ----====lexer====----  |");
        System.out.println("|     ----====lexer====----  |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("|                            |");
        System.out.println("-----------------------------");

        for (Lexer.Token token : tokens) {
//            System.out.println(token);
//            file.write(token.toString() + "\n");
            System.out.println("  ");
            System.out.println("  ");
            System.out.println("Token: "+token.type + " Lexeme: " + token.data);
            token.printRules();

        }
//        file.close();
    }


}






