package liwei.data_structure.sequencelist;

import liwei.data_structure.linkedlist.MyNodeTest;

public class Main
{
    public static void main(String[] args)
    {
        SequenceType slType = new SequenceType();
        MyNodeTest[] array = new MyNodeTest[]{
                new MyNodeTest("L", "government", 1), new MyNodeTest("Q", "Qi", 2), new MyNodeTest("W", "Wi", 3), new MyNodeTest("E", "Ei", 4),
                new MyNodeTest("R", "Ri", 5)
        };
        for (int i = 0; i < array.length; i++)
        {
            slType.AddEnd(array[i]);
        }
        System.out.println("初始化顺序表完毕...");
        slType.Print();
        System.out.println("\n................................................................\n");

        System.out.println("顺序表指定位置插入数据...");
        slType.Insert(1, new MyNodeTest("X1", "successive", 6));
        slType.Print();
        System.out.println("\n................................................................\n");

        System.out.println("顺序表末尾插入数据...");
        slType.AddEnd(new MyNodeTest("X2", "ash", 1));
        slType.Print();
        System.out.println("\n................................................................\n");

        System.out.println("顺序表删除下标为4的数据...");
        slType.Delete(4);
        slType.Print();
        System.out.println("\n................................................................\n");

    }
}
