package com.liwei.sequencelist;

import com.liwei.MyNodeTest;

public class SLType
{
    private static final int MAXLEN = 100;
    private int currentLength = 0;
    private MyNodeTest[] arrayData = new MyNodeTest[MAXLEN];

    public int GetListLength()
    {
        return currentLength;
    }

    public int Delete(int index)
    {
        if (currentLength <= 0)
        {
            System.out.println("顺序表已空!");
            return -1;
        }
        if (index < 0 || index > currentLength - 1)
        {
            System.out.println("删除位置错误!");
            return -1;
        }
        for (int i = index; i < currentLength; i++)
        {
            arrayData[i] = arrayData[i + 1];
        }
        currentLength--;
        return 1;
    }

    /**
     * 添加至尾部
     *
     * @param data
     * @return
     */
    public int AddEnd(MyNodeTest data)
    {
        if (currentLength >= MAXLEN)
        {
            System.out.println("顺序表已满!");
            return -1;
        }
        arrayData[currentLength++] = data;
        return 1;
    }

    public int Insert(int index, MyNodeTest data)
    {
        if (currentLength >= MAXLEN)
        {
            System.out.println("顺序表已满!");
            return -1;
        }
        //不能在起始位置插入cccccccccccccccccccccccccccccccccccccccccccccccccc
        if (index <= 0 || index > currentLength - 1)
        {
            System.out.println("插入位置错误!");
            return -1;
        }
        //以当前存储的长度为循环条件,避免在达到MAXLEN之前出现下标越界
        for (int i = currentLength - 1; i > index; i--)
        {
            arrayData[i + 1] = arrayData[i];
        }
        arrayData[index] = data;
        currentLength++;
        return 1;
    }

    public void Print()
    {
        for (int i = 0; i < currentLength; i++)
        {
            System.out.println(arrayData[i].toString());
        }
    }

}
