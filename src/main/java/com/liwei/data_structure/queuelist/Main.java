package com.liwei.data_structure.queuelist;

import com.liwei.data_structure.MyNodeTest;

public class Main
{
    public static void main(String[] args)
    {
        QueueType queueType = new QueueType();
        MyNodeTest[] array = new MyNodeTest[]{
                new MyNodeTest("L", "government", 1), new MyNodeTest("Q", "Qi", 2), new MyNodeTest("W", "Wi", 3), new MyNodeTest("E", "Ei"
                , 4), new MyNodeTest("R", "Ri", 5)
        };
        for (int i = 0; i < array.length; i++)
        {
            queueType.InQueue(queueType, array[i]);
        }
        System.out.println(String.format("队列初始化完毕,队列长度%s:...", queueType.GetLength(queueType)));
        System.out.println("取出元素打印...");
        queueType.Print(queueType);
        System.out.println(String.format("队列取出元素打印完毕,队列长度%s:...", queueType.GetLength(queueType)));
        System.out.println("\n................................................................\n");
    }
}
