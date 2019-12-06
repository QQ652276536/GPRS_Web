package com.liwei.stack;

import com.liwei.MyNodeTest;

/**
 * 栈结构,后进先出,只有栈顶元素是可以访问的.
 */
public class StackType
{
    private static final int MAXLEN = 50;
    private MyNodeTest[] data = new MyNodeTest[MAXLEN + 1];
    private int top = 0;

    public void Print(StackType stackType)
    {
        while (top > 0)
        {
            System.out.println(stackType.data[stackType.top--]);
        }
    }

    public boolean JudgeIsNull()
    {
        return top == 0 ? true : false;
    }

    public int Push(StackType stackType, MyNodeTest data)
    {
        if (stackType.top + 1 > MAXLEN)
        {
            System.out.println("栈溢出!");
            return -1;
        }
        stackType.data[++stackType.top] = data;
        return 1;
    }

    public MyNodeTest Pop(StackType stackType)
    {
        if (stackType.top == 0)
        {
            System.out.println("栈为空!");
            return null;
        }
        return stackType.data[stackType.top--];
    }

    public MyNodeTest Peak(StackType stackType)
    {
        if (stackType.top == 0)
        {
            System.out.println("栈为空!");
            return null;
        }
        return stackType.data[stackType.top];
    }
}
