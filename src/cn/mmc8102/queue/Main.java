package cn.mmc8102.queue;

/**
 * @author wangli
 * @Date: 2021/8/14 16:54
 */
public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
