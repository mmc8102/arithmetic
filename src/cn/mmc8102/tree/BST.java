package cn.mmc8102.tree;

import java.util.Comparator;

/**
 * @author wangli
 * @Date: 2021/8/15 19:49
 * 二叉搜索树
 */
public class BST<E> extends BinaryTree {

    private Comparator comparator;

    public BST() {
    }

    public BST(Comparator comparator) {
        this.comparator = comparator;
    }



    public void add(E element){
        elementNotNullCheck(element);
        //如果根节点为空 初始化
        if(root == null){
            root = new Node<>(element, null);
            size++;
            return;
        }

        //查找父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null){
            cmp = compare(element, node.element);
            parent = node;
            if(cmp > 0){
                node = node.right;
            }else if(cmp < 0){
                node = node.left;
            }else{
                node.element = element;
            }
        }
        //看看插入到父节点哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if(cmp > 0){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }
        size++;
    }

    private int compare(E e1, E e2){
        if(comparator != null){
            return comparator.compare(e1, e2);
        }
        return ((Comparable)e1).compareTo(e2);
    }

    public void remove(E element){
        remove(node(element));
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element, node.element);
            if(cmp == 0){
                return node;
            }else if(cmp < 0){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return null;
    }

    private void remove(Node<E> node){
        if(node == null){
            return;
        }
        size--;
        //度为2的节点
        if(node.hasTwoChildren()){
            //找到后继节点
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
            /*if(newNode.parent.left == newNode){
                newNode.parent.left = null;
            }else {
                newNode.parent.right = null;
            }
            newNode.parent = null;*/
        }
        //删除node节点(node节点的度必然是1或0)
        Node<E> replacement = node.left != null ? node.left : node.right;
        if(replacement != null){
            //node是度为1的节点
            replacement.parent = node.parent;
            if(node.parent == null){
                //node是度为1的节点 并且是根节点
                root = replacement;
            } else if(node == node.parent.left){
                node.parent.left = replacement;
            }else{
                node.parent.right = replacement;
            }
        }else if(node.parent == null){
            //叶子节点 并且是跟节点
            root = null;
        }else {
            //叶子节点 但不是根节点
            if(node == node.parent.left){
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }
        }

    }

    public boolean contains(E element){
        return node(element) != null;
    }

    public void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }

//    /**
//     * 前序遍历
//     */
//    public void preorderTraversal(){
//        preorderTraversal(root);
//    }
//
//    public void preorderTraversal(Node<E> node){
//        if(node == null){
//            return;
//        }
//        System.out.println(node.element);
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//    }
//
//    /**
//     * 中序遍历
//     */
//    public void inorderTraversal(){
//        inorderTraversal(root);
//    }
//
//    public void inorderTraversal(Node<E> node){
//        if(node == null){
//            return;
//        }
//        inorderTraversal(node.left);
//        System.out.println(node.element);
//        inorderTraversal(node.right);
//    }
//
//    /**
//     * 后序遍历
//     */
//    public void postorderTraversal(){
//        postorderTraversal(root);
//    }
//
//    public void postorderTraversal(Node<E> node){
//        if(node == null){
//            return;
//        }
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//        System.out.println(node.element);
//    }
//
//    /**
//     * 层序遍历
//     */
//    public void levelOrderTraversal(){
//        levelOrderTraversal(root);
//    }
//
//    public void levelOrderTraversal(Node<E> node){
//        if(node == null){
//            return;
//        }
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(node);
//        while (!queue.isEmpty()){
//            Node<E> poll = queue.poll();
//            System.out.println(poll.element);
//            if(poll.left != null){
//                queue.offer(poll.left);
//            }
//            if(poll.right != null){
//                queue.offer(poll.right);
//            }
//        }
//    }




}
