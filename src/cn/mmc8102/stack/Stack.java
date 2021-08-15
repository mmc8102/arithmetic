package cn.mmc8102.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangli
 * @Date: 2021/8/14 16:42
 * 栈
 */
public class Stack<E> {
    private List<E> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public Boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.add(e);
    }

    public E pop() {
        check();
        return list.remove(size() - 1);
    }

    public E top() {
        check();
        return list.get(size() - 1);
    }

    private void check(){
        if(size() == 0){
            throw new RuntimeException("stack is empty");
        }
    }
}
