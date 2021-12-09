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
        String in = "";

        String acc = "%.2f\n";
        while (!in.equals("exit"))
        {
            in = kbd.nextLine();

            if (in.equals("acc"))
            {
                System.out.print("Set accuracy: ");
                int val = kbd.nextInt();
                acc = val > 0 ? "%." + val + "f\n" : acc;
                kbd.nextLine();
                continue;
            }

            System.out.printf(acc, Calculator.string(in, 0, true));
        }
    }
}