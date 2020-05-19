package liwei.calculate;

public class JiTuTongLong
{
    public static void main(String[] args)
    {
        new JiTuTongLong().Exhaustion();
        System.out.println(String.format("12个月后共有%s只兔子.", new JiTuTongLong().Fibonacci(4)));
    }

    public int Fibonacci(int n)
    {
        int t1, t2;
        if (n == 1 || n == 2)
        {
            return 1;
        }
        else
        {
            t1 = Fibonacci(n - 1);
            t2 = Fibonacci(n - 2);
            return t1 + t2;
        }
    }

    public void Exhaustion()
    {
        for (int i = 0; i < 35; i++)
        {
            int j = 35 - i;
            if (i * 2 + j * 4 == 94)
            {
                System.out.println(String.format("鸡有%s只,免有%s只.", i, j));
            }
        }
    }
}
