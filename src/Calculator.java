/**
 * Takes the string input from a call and
 * converts into an Equation that can be solved.
 *
 * @author beneathTwo
 * @version 12.13.2021
 */
public class Calculator
{
    // check Equation class for errors
    static private String str;
    static private final char[] opList = { '^', '*', '/', '+', '-' };

    public static double string(String s, int f, Boolean equal)
    {
        str = s;
        // initialize equation with first part of str
        Equation eq = convert(f);

        for (int l = eq.getLE(); l < str.length() && l >= 0; l = eq.getLE())
        {
            str = eq.getAnswer() + " " + str.substring(l); // create new string
            if (str.length() < 3 || convert(0).getLE() < 0)
            {
                System.out.print(equal ? "= " : "");
                return eq.getAnswer();
            }
            // System.out.println(str);
            eq.set(convert(0)); // start from beginning again
        }

        if (equal && eq.getLE() > 0)
            System.out.print("= ");

        return eq.getAnswer();
    }

//    public static void setGlobalString(String s)
//    {
//        str = s;
//    }

    public static Equation convert(final int f)
    {
        if (str.length() < 3)
            return new Equation(-4); // not large enough to fit equation

        // firstElement = index[0], lastElement = index[1]
        int[] index = indexDouble(f);
        if (index[0] < 0)
            return new Equation(index[0]);
        String subStr = str.substring(index[0], index[1]);
        // System.out.println(subStr)
        double n1 = Double.parseDouble(subStr);

        index[0] = indexChar(opList, index[1]);
        if (index[0] < 0)
            return new Equation(index[0]);
        char op = str.charAt(index[0]);
        // System.out.println(op);

        if (++index[0] >= str.length()) // ignore op
            return new Equation(-3);

        index = indexDouble(index[0]);
        if (index[0] < 0)
            return new Equation(index[0]);
        subStr = str.substring(index[0], index[1]);
        // System.out.println(subStr);
        double n2 = Double.parseDouble(subStr);

        return new Equation(n1, n2, op, index[1]);
    }

    private static int[] indexDouble(final int f)
    {
        if (f >= str.length() || f < 0)
            return new int[]{-1};

        char c; // index character
        for (int i = f; i < str.length(); ++i)
        {
            c = str.charAt(i);
            if (c == '.' || c == '-' || Character.isDigit(c))
            {
                int l = i;
                if (c == '-' && ++l < str.length()) // adjust for negative symbol
                        c = str.charAt(l);

                boolean digit = Character.isDigit(c), digits = digit;
                for (int dec = 0; l < str.length() && (digit || c == '.'); ++l)
                {
                    digits = digit || digits; // Are there digits?
                    if (c == '.' && ++dec > 1)
                        if (digits) // make sure there are digits to go with decimal
                            return new int[]{i, --l}; // remove extra decimal
                        else
                        {
                            i = l - 1; // make second decimal the new first
                            dec = 1; // ignore decimal before
                        }

                    c = str.charAt(l);
                    digit = Character.isDigit(c);
                }

                if (digits)
                    return !digit ? new int[]{i, --l} : new int[]{i, l}; // removes inessential info
                else
                    return new int[]{-3}; // lone '-' or '.'
            }
            else if (searchList(opList, c))
                return new int[]{-3};
        }

        return new int[]{-3};  // no numbers found
    }

    private static boolean searchList(char[] list, final int s)
    {
        for (char c : list)
            if (c == s)
                return true;

        return false;
    }

    private static int indexChar(char[] search, final int f)
    {
        if (f > str.length() || f < 0)
            return -1;  // start out of range

        for (int i = f; i < str.length(); ++i)
            if (searchList(search, str.charAt(i)))
                return i;

        return -2;  // no character found
    }
}