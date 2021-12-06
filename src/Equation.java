/**
 * Holds the essential parts to an equation,
 * two numbers and the operation.
 *
 * @author beneathTwo
 * @version 12.06.2021
 */
public class Equation
{
    static private final String[] errors = { "Executed successfully.",              // 0
                                                "Starting position out of range.",  // -1
                                                "Character not found.",             // -2
                                                "Numbers not found.",               // -3
                                                "String cannot fit an equation."};  // -4

    private double n1, n2;
    private char op;
    private int lastElement;

    public Equation()
    {
        this(0.0, 0.0, '!',  0);
    }

    public Equation(int err)
    {
        this(0.0, 0.0, '!', err);
    }

    public Equation(double n1, double n2, char op, int l)
    {
        this.n1 = n1;
        this.n2 = n2;
        this.op = op;
        lastElement = l;
    }

    public int getLE()
    {
        return lastElement;
    }

    public void set(Equation eq)
    {
        n1 = eq.n1;
        n2 = eq.n2;
        op = eq.op;
        lastElement = eq.getLE();
    }

    public double getAnswer()
    {
        double[] ans = this.solve();

        if (ans.length > 1)
        {
            // translate error code to position in array
            int out = (int)(ans[1] - (ans[1] * 2));
            System.out.print(errors[out] + " : ");
            return ans[1];
        }
        else
            return ans[0];
    }

    private double[] solve()
    {
        switch (op)
        {
            case '*':
                return new double[]{n1 * n2};
            case '/':
                return new double[]{n1 / n2};
            case '+':
                return new double[]{n1 + n2};
            case '-':
                return new double[]{n1 - n2};
            case '^':
                return new double[]{Math.pow(n1, n2)};
            default:
                return new double[]{0, lastElement}; // LE used as error code here.
        }
    }
}