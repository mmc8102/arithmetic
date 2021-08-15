package cn.mmc8102.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangli
 * @Date: 2021/8/14 21:47
 */
public class Queue<E> {
    private List<E> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void enQueue(E e){
        list.add(e);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){
        return list.get(0);
    }


}
