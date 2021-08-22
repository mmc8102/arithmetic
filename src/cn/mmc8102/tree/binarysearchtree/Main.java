package cn.mmc8102.tree.binarysearchtree;

import cn.mmc8102.tree.binarysearchtree.printer.BinaryTrees;

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
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println();
        System.out.println(bst.height());
        System.out.println();
        //bst.levelOrderTraversal();
        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return element == 9 ? true : false;
            }
        });


    }
}
