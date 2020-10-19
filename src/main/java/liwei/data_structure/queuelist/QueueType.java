package liwei.data_structure.queuelist;

import liwei.data_structure.linkedlist.MyNodeTest;

/**
 * 队列结构,队头只能删除,队尾只能新增,先进先出.
 */
public class QueueType
{
    private static final int MAXLEN = 15;
    private MyNodeTest[] data = new MyNodeTest[MAXLEN];
    //队头,队尾
    private int head, tail;

    public int GetLength(QueueType queue)
    {
        return queue.tail - queue.head;
    }

    public MyNodeTest OutQueue(QueueType queue)
    {
        if (JudgeIsEmtpy(queue))
        {
            System.out.println("队列已空!");
            return null;
        }
        else
        {
            return queue.data[queue.head++];
        }
    }

    public int InQueue(QueueType queue, MyNodeTest data)
    {
        if (JudgeIsFull(queue))
        {
            System.out.println("队列已满!");
            return -1;
        }
        else
        {
            queue.data[queue.tail++] = data;
            return 1;
        }
    }

    public boolean JudgeIsFull(QueueType queue)
    {
        return queue.tail == MAXLEN ? true : false;
    }

    public boolean JudgeIsEmtpy(QueueType queue)
    {
        return queue.head == queue.tail ? true : false;
    }

    public void Print(QueueType queue)
    {
        while (queue.head < queue.tail)
        {
            System.out.println(queue.OutQueue(queue));
        }
    }
}
