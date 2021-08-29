package cn.mmc8102.tree;

import cn.mmc8102.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangli
 * @Date: 2021/8/29 10:34
 * 二叉树
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    public void preorder(BST.Visitor visitor){
        if(visitor == null){
            return;
        }
        preorder(root, visitor);
    }

    public void preorder(Node<E> node, BST.Visitor visitor){
        if(root == null || visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(BST.Visitor visitor){
        if(visitor == null){
            return;
        }
        inorder(root, visitor);
    }

    public void inorder(Node<E> node, BST.Visitor visitor){
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

    public void postorder(BST.Visitor visitor){
        if(visitor == null){
            return;
        }
        postorder(root, visitor);
    }

    public void postorder(Node<E> node, BST.Visitor visitor){
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

    public void levelOrder(BST.Visitor visitor){
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


    /**
     * 前驱节点
     * @return
     */
    protected Node<E> predecessor(Node<E> node){
        if(node == null){
            return null;
        }
        if(node.left != null){
            //前驱节点在左子树中  left.right.right.right...
            node = node.left;
            while (node.right != null){
                node = node.right;
            }
        }else {
            //从父节点 祖父节点中寻找
            while (node.parent != null && node == node.parent.left){
                node = node.parent;
            }
            node = node.parent;
        }

        return node;
    }


    /**
     * 后继节点
     * @return
     */
    protected Node<E> successor(Node<E> node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            //后继节点在右子树中  right.left.left.left...
            node = node.right;
            while (node.left != null){
                node = node.left;
            }
        }else {
            //从父节点 祖父节点中寻找
            while (node.parent != null && node == node.parent.right){
                node = node.parent;
            }
            node = node.parent;
        }

        return node;
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

    protected static class Node<E> {
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
