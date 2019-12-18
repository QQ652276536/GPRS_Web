package liwei.data_structure.stack;

import liwei.data_structure.MyNodeTest;

public class Main
{
    public static void main(String[] args)
    {

        StackType stackType = new StackType();
        MyNodeTest[] array = new MyNodeTest[]{
                new MyNodeTest("L", "government", 1), new MyNodeTest("Q", "Qi", 2), new MyNodeTest("W", "Wi", 3), new MyNodeTest("E", "Ei"
                , 4), new MyNodeTest("R", "Ri", 5)
        };
        for (int i = 0; i < array.length; i++)
        {
            stackType.Push(stackType, array[i]);
        }
        System.out.println("栈初始化完毕...");
        stackType.Print(stackType);
        System.out.println("\n................................................................\n");
    }

}
