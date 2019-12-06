package com.liwei.linkedlist;

import com.liwei.MyNodeTest;

public class Main
{
    public static void main(String[] args)
    {
        LType ltyp = new LType();
        MyNodeTest[] array = new MyNodeTest[]{
                new MyNodeTest("L", "government", 1), new MyNodeTest("Q", "Qi", 2), new MyNodeTest("W", "Wi", 3), new MyNodeTest("E", "Ei"
                , 4), new MyNodeTest("R", "Ri", 5)
        };
        for (int i = 0; i < array.length; i++)
        {
            ltyp = ltyp.AddFirst(ltyp, array[i]);
        }
        System.out.println("初始化链表完毕...");
        ltyp.Print(ltyp);
        System.out.println("\n................................................................\n");

        System.out.println("链表删除Key为'Q'的元素...");
        ltyp.Delete(ltyp, "Q");
        ltyp.Print(ltyp);
        System.out.println("\n................................................................\n");

        System.out.println("链表插入元素...");
        ltyp.Insert(ltyp, "E", new MyNodeTest("I", "Insert", 111));
        ltyp.Print(ltyp);
        System.out.println("\n................................................................\n");
    }
}
