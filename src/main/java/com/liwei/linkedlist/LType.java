package com.liwei.linkedlist;

import com.liwei.MyNodeTest;

public class LType
{
    public MyNodeTest data = new MyNodeTest();
    public LType head;

    /**
     * 删除节点
     *
     * @param head
     * @param key
     * @return
     */
    public int Delete(LType head, String key)
    {
        //要删除的节点,要删除的节点的上一节点
        LType delHead = head, delHeadBefore = head;
        while (delHead != null)
        {
            if (delHead.data.key.compareTo(key) == 0)
            {
                //要删除的节点的上一节点指向要删除的节点的下一节点
                delHeadBefore.head = delHead.head;
                return 1;
            }
            else
            {
                //要删除的节点的上一节点
                delHeadBefore = delHead;
                //要删除的节点的下一节点
                delHead = delHead.head;
            }
        }
        return -1;
    }

    public int GetLenth(LType head)
    {
        int length = 0;
        while (head != null)
        {
            length++;
            head = head.head;
        }
        return length;
    }

    public void Print(LType head)
    {
        while (head != null)
        {
            System.out.println(head.data);
            head = head.head;
        }
    }

    public void Insert(LType head, String key, MyNodeTest data)
    {
        LType node = null;
        if ((node = new LType()) == null)
        {
            System.out.println("内存分配失败!");
        }
        else
        {
            node.data = data;
            LType tempNode = FindNode(head, key);
            //找到要插入节点的位置,也就是哪两个结点之间
            if (tempNode != null)
            {
                //新增的节点指向插入位置的下一结点
                node.head = tempNode.head;
                //插入位置指向新增的节点
                tempNode.head = node;
            }
            else
            {
                System.out.println("未找到正确的插入位置!");
            }
        }
    }

    /**
     * @param head 要查找的链接
     * @param key
     * @return
     */
    public LType FindNode(LType head, String key)
    {
        while (head != null)
        {
            if (head.data.key.compareTo(key) == 0)
            {
                return head;
            }
            head = head.head;
        }
        return null;
    }

    /**
     * 插入头节点
     *
     * @param head 头引用所指向的节点
     * @param data
     * @return
     */
    public LType AddFirst(LType head, MyNodeTest data)
    {
        LType node = null;
        if ((node = new LType()) == null)
        {
            System.out.println("申请内存失败!");
            return null;
        }
        else
        {
            node.data = data;
            //新增的节点指向头引用所指的节点
            node.head = head;
            //头引用指向新增的节点
            head = node;
            return head;
        }
    }
}
