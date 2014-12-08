/*
 *
 *  BinarySearchTree.java by Adrian Zugaj
 *  CS144-H01 Fall 2014
 *
 */

import java.util.Iterator;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

    private Node<E> findIOP(Node<E> curr) {

        for (curr = curr.left; curr.right != null; curr = curr.right);

        return curr;
    }

    public void insert(E data) {

	doInsert(data, root);
        /*Node<E> temp = new Node<E>(data);

        if (root == null) {
            root = temp;
        }
        else {
            Node<E> curr = root;

            while (true) {
                if (data.compareTo(curr.data) <= 0) {		//if data less than/equal to left
                    if (curr.left != null) {
                        curr = curr.left;
                    }						//if left is null, insert. else, check left tree
                    else {
                        curr.left = temp;
                        break;
                    }
                }
                else {						//if data less than right
                    if (curr.right != null) {
                        curr = curr.right;
                    }						//if right is null, insert. else, check right tree
                    else {
                        curr.right = temp;
                        break;
                    }
                }
            }
        }*/
    }

    private void doInsert(E data, Node<E> node){

	if (node == null){
	    Node<E> newNode = new Node<E>(data);
	    node = newNode;
	}
	else {
	    if (data.compareTo(node.data) <= 0){
		doInsert(data, node.left);
	    }
	    else {
		doInsert(data, node.right);
	    }
	}
	return;
    }

    public Iterator<E> iterator() {

        return null;
    }

    public void remove(E data) {

        if (root != null) {
            if (data.compareTo(root.data) == 0) {
                if (root.left == null || root.right == null) {
                    root = root.left != null ? root.left : root.right;
                }
                else {
                    Node<E> iop = findIOP(root);
                    E temp = root.data;
                    root.data = iop.data;
                    iop.data = temp;

                    if (root.left == iop) {
                        root.left = root.left.left;
                    }
                    else {
                        Node<E> curr = root.left;
                        while (curr.right != iop) {
                            curr = curr.right;
                        }
                        curr.right = curr.right.left;
                    }
                }
            }
            else {
                Node<E> curr = root;
                int cmp;
                while (true) {
                    cmp = data.compareTo(curr.data);
                    if (cmp < 0 && curr.left != null && data.compareTo(curr.left.data) != 0) {
                        curr = curr.left;
                    }
                    else if (cmp > 0 && curr.right != null && data.compareTo(curr.right.data) != 0) {
                        curr = curr.right;
                    }
                    else {
                        break;
                    }
                }

                if (cmp < 0 && curr.left != null) {
                    if (curr.left.left == null || curr.left.right == null) {
                        curr.left = curr.left.left != null ? curr.left.left : curr.left.right;
                    }
                    else {
                        Node<E> iop = findIOP(curr.left);
                        E temp = curr.left.data;
                        curr.left.data = iop.data;
                        iop.data = temp;

                        if (curr.left.left == iop) {
                            curr.left.left = curr.left.left.left;
                        }
                        else {
                            Node<E> node = curr.left.left;
                            while (node.right != iop) {
                                node = node.right;
                            }
                            node.right = node.right.left;
                        }
                    }
                }
                else if (cmp > 0 && curr.right != null) {
                    if (curr.right.left == null || curr.right.right == null) {
                        curr.right = curr.right.left != null ? curr.right.left : curr.right.right;
                    }
                    else {
                        Node<E> iop = findIOP(curr.right);
                        E temp = curr.right.data;
                        curr.right.data = iop.data;
                        iop.data = temp;

                        if (curr.right.left == iop) {
                            curr.right.left = curr.right.left.left;
                        }
                        else {
                            Node<E> node = curr.right.left;
                            while (node.right != iop) {
                                node = node.right;
                            }
                            node.right = node.right.left;
                        }
                    }
                }
            }
        }
    }

    public boolean search(E data) {

        Node<E> curr = root;

        while (curr != null) {
            if (data.compareTo(curr.data) == 0) {
                return true;
            }
            else if (data.compareTo(curr.data) < 0) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        return false;
    }
}
