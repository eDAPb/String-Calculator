import java.util.Scanner;

/**
 * A calculator that reads a string and returns
 * the corresponding answer on the equation
 * within that string. Additionally, it has the option
 * to set the accuracy. Does not follow PEMDAS.
 *
 * @author beneathTwo
 * @version 12.15.21
 */

public class Main
{
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        String in;

        String acc = "%.2f\n";

        boolean exit = false;
        while (!exit)
        {
            in = kbd.nextLine();
            if (in.equals("exit"))
                exit = true;
            else if (in.equals("acc"))
            {
                System.out.print("Set accuracy: ");
                int val = (int)kbd.nextDouble();
                acc = (val > 0 && val < 7) ? "%." + val + "f\n" : acc;
                kbd.nextLine();
            }
            else
                System.out.printf(acc, Calculator.string(in, true));
        }
    }
}