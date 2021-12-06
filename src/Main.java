import java.util.Scanner;

/**
 * A calculator that reads a string and returns
 * the corresponding answer on the equation
 * within that string. Does not follow PEMDAS.
 *
 * @author beneathTwo
 * @version 12.06.21
 */

public class Main
{
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        String in = kbd.nextLine();

        while (!in.equals("exit"))
        {
            System.out.print(Calculator.calcStr(in, 0, true) + "\n");
            in = kbd.nextLine();
        }
    }
}