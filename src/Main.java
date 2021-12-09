import java.util.Scanner;

/**
 * A calculator that reads a string and returns
 * the corresponding answer on the equation
 * within that string. Does not follow PEMDAS.
 *
 * @author beneathTwo
 * @version 12.09.21
 */

public class Main
{
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        String in = kbd.nextLine();

        String acc = "%.2f\n";
        while (!in.equals("exit"))
        {
            System.out.printf(acc, Calculator.calcStr(in, 0, true));
            in = kbd.nextLine();

            if (in.equals("acc"))
            {
                System.out.print("Set accuracy: ");
                acc = "%." + kbd.nextInt() + "f\n";
            }
        }
    }
}