package com.liwei.data_structure.linkedlist;

import com.liwei.data_structure.MyNodeTest;

/**
 * 链表结构,由许多节点构成,每个节点由'数据部分'和'地址部分'组成.
 * <p>
 * 优点:不要求连续存储,因此在保存大量数据时不需要分配一块连续的存储空间.
 * 缺少:浪费存储空间.
 */
public class LinkedType
{
    //数据部分
    public MyNodeTest data = new MyNodeTest();
    //地址部分
    public LinkedType linkedType;

    /**
     * 删除节点
     *
     * @param linkedType
     * @param key
     * @return
     */
    public int Delete(LinkedType linkedType, String key)
    {
        //要删除的节点,要删除的节点的上一节点
        LinkedType delHead = linkedType, delHeadBefore = linkedType;
        while (delHead != null)
        {
            if (delHead.data.key.compareTo(key) == 0)
            {
                //要删除的节点的上一节点指向要删除的节点的下一节点
                delHeadBefore.linkedType = delHead.linkedType;
                return 1;
            }
            else
            {
                //要删除的节点的上一节点
                delHeadBefore = delHead;
                //要删除的节点的下一节点
                delHead = delHead.linkedType;
            }
        }
        return -1;
    }

    public int GetLenth(LinkedType linkedType)
    {
        int length = 0;
        while (linkedType != null)
        {
            length++;
            linkedType = linkedType.linkedType;
        }
        return length;
    }

    public void Print(LinkedType linkedType)
    {
        while (linkedType != null)
        {
            System.out.println(linkedType.data);
            linkedType = linkedType.linkedType;
        }
    }

    public void Insert(LinkedType linkedType, String key, MyNodeTest data)
    {
        LinkedType node = null;
        if ((node = new LinkedType()) == null)
        {
            System.out.println("内存分配失败!");
        }
        else
        {
            node.data = data;
            LinkedType tempNode = FindNode(linkedType, key);
            //找到要插入节点的位置,也就是哪两个结点之间
            if (tempNode != null)
            {
                //新增的节点指向插入位置的下一结点
                node.linkedType = tempNode.linkedType;
                //插入位置指向新增的节点
                tempNode.linkedType = node;
            }
            else
            {
                System.out.println("未找到正确的插入位置!");
            }
        }
    }

    /**
     * @param linkedType 要查找的链接
     * @param key
     * @return
     */
    public LinkedType FindNode(LinkedType linkedType, String key)
    {
        while (linkedType != null)
        {
            if (linkedType.data.key.compareTo(key) == 0)
            {
                return linkedType;
            }
            linkedType = linkedType.linkedType;
        }
        return null;
    }

    /**
     * 插入头节点
     *
     * @param linkedType 头引用所指向的节点
     * @param data
     * @return
     */
    public LinkedType AddFirst(LinkedType linkedType, MyNodeTest data)
    {
        LinkedType node = null;
        if ((node = new LinkedType()) == null)
        {
            System.out.println("申请内存失败!");
            return null;
        }
        else
        {
            node.data = data;
            //新增的节点指向头引用所指的节点
            node.linkedType = linkedType;
            //头引用指向新增的节点
            linkedType = node;
            return linkedType;
        }
    }
}
