package cn.mmc8102.stack;

/**
 * @author wangli
 * @Date: 2021/8/14 16:54
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.size());
        System.out.println(stack.top());

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        /*System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.top());*/
    }
}
