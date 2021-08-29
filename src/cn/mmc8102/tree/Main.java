package cn.mmc8102.tree;

import cn.mmc8102.tree.printer.BinaryTrees;

/**
 * @author wangli
 * @Date: 2021/8/21 10:05
 */
public class Main {

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7,  9, 8, 11,  12
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println();
        System.out.println(bst.height());
        System.out.println();
        //bst.levelOrderTraversal();
        bst.levelOrder(new BST.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return element == 9 ? true : false;
            }
        });
        System.out.println();
        //bst.predecessor();

    }
}
