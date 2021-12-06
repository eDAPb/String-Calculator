/**
 * Takes the string input from a call and
 * converts into an Equation that can be solved.
 *
 * @author beneathTwo
 * @version 12.06.2021
 */
public class Calculator
{
    // Check Equation class for errors.
    static private String str;
    static private final char[] opList = { '^', '*', '/', '+', '-' };

    public static double calcStr(String s, int f, Boolean equal)
    {
        str = s;

        // Initialize equation with first part of str.
        Equation a = convert(f);
        double total = a.getAnswer(); // Get the answer for the first equation.
        // System.out.println(total);

        for (int l = a.getLE(); l < str.length() && l >= 0; l = a.getLE())
        {
            str = total + " " + str.substring(l); // Create new string.
            if (str.length() < 3) // Check it can fit an equation.
                return total;
            // System.out.println(str);
            a.set(convert(0)); // Start from beginning again.
            total = a.getAnswer();
            // System.out.println(total);
        }

        if (equal && a.getLE() > 0)
            System.out.print("= ");
        return total;
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
        // System.out.println(subStr);
        double n1 = Double.parseDouble(subStr);

        index[0] = indexChar(opList, index[1]);
        if (index[0] < 0)
            return new Equation(index[0]);
        char op = str.charAt(index[0]);
        // System.out.println(op);

        index = indexDouble(index[0] + 1);
        if (index[0] < 0)
            return new Equation(index[0]);
        subStr = str.substring(index[0], index[1]);
        // System.out.println(subStr);
        double n2 = Double.parseDouble(subStr);

        return new Equation(n1, n2, op, index[1]);
    }

    private static int[] indexDouble(final int f)
    {
        final int len = str.length();

        if (f >= len || f < 0)  // start out of range
            return new int[]{-1, 0};

        char c; // current character
        for (int i = f; i < len; ++i)
        {
            c = str.charAt(i);
            if (Character.isDigit(c) || c == '.' || c == '-')
            {
                // System.out.print("indexDouble: " + c + " ");
                if (c == '-' && ++i < len) // adjust for negative number
                    c = str.charAt(i);

                int l = i, dec = 0;
                while ((Character.isDigit(c) || c == '.') && ++l < len)
                {
                    if (c == '.')
                        if (++dec > 1)
                            if ((l - i) > 1 && str.charAt(l - 1) != '.') // second condition checks ".." exception
                                return new int[]{i, l}; // early termination
                            else
                                return new int[]{-3}; // no numbers found

                    c = str.charAt(l);
                    // System.out.print(c + " ");
                }

                if ((l - i) < 2)
                {
                    char lChar = str.charAt(l - 1);
                    if (lChar == '.') // decimal not attached to number
                        return new int[]{-3};
                    else if (lChar == '-' && !Character.isDigit(c)) // lone negative char
                        return new int[]{-3};
                }

                return new int[]{i, l}; // final number
            }
        }

        return new int[]{-3}; // no numbers found
    }

    private static int indexChar(char[] search, final int f)
    {
        final int len = str.length();

        if (f >= len || f < 0)
            return -1;  // start out of range

        for (int i = f; i < len; ++i)
            for (char c : search)
                if (str.charAt(i) == c)
                    return i;

        return -2;  // no character found
    }
}