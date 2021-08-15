package cn.mmc8102.tree.binarysearchtree;

import java.util.Comparator;

/**
 * @author wangli
 * @Date: 2021/8/15 19:49
 */
public class BinarySearchTree<E> {
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
                return;
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

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
