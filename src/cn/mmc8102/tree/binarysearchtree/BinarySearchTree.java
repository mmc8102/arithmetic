package cn.mmc8102.tree.binarysearchtree;

import cn.mmc8102.tree.binarysearchtree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangli
 * @Date: 2021/8/15 19:49
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){

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

    }

    public boolean contains(E element){
        return false;
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

    public void preorder(Visitor visitor){
        if(visitor == null){
            return;
        }
        preorder(root, visitor);
    }

    public void preorder(Node<E> node, Visitor visitor){
        if(root == null || visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(Visitor visitor){
        if(visitor == null){
            return;
        }
        inorder(root, visitor);
    }

    public void inorder(Node<E> node, Visitor visitor){
        if(root == null || visitor.stop){
            return;
        }
        preorder(node.left, visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.right, visitor);
    }

    public void postorder(Visitor visitor){
        if(visitor == null){
            return;
        }
        postorder(root, visitor);
    }

    public void postorder(Node<E> node, Visitor visitor){
        if(root == null || visitor.stop){
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    public void levelOrder(Visitor visitor){
        if(root == null || visitor == null){
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.visit(node.element)){
                return;
            }
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    public int height(){
        return height2(root);
    }

    public int height2(Node<E> root){
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        //每一层的元素数量
        int levelSize = 1;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            if(levelSize == 0){
                //即将访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    public int height(Node<E> node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 判断是否是完全二叉树
     * @return
     */
    public boolean isComplate(){
        if(root == null){
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()){
                return false;
            }

            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){
                return false;
            }

            if(node.right != null){
                queue.offer(node.right);
            }else {
                //后面的所有节点都是叶子节点
                leaf = true;
            }
        }

        return true;
    }



    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * 如果返回true,就停止遍历
         * @param element
         * @return
         */
        boolean visit(E element) {
            return false;
        }
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
}
