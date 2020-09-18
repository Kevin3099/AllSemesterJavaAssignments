package utility;
import java.util.Scanner;


public class ScannerInput {
    public static int readNextInt(String prompt) {
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        } while (true);
    }

    public static String readNextString(String prompt) {
        Scanner Scanner = new Scanner(System.in);
        System.out.print(prompt);
        return Scanner.nextLine();
    }

    public static char readNextChar(String prompt) {
        do {
            Scanner scanner = new Scanner(System.in);

                System.out.print(prompt);
                return scanner.next().charAt(0);
        }
        while (true);
    }
}