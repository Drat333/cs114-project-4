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

		root = doInsert(data, root);
    }

    private Node<E> doInsert(E data, Node<E> node){

		if (node == null){
			return new Node<E>(data);
		} else {
			if (data.compareTo(node.data) <= 0){
				node.left = doInsert(data, node.left);
				return node;
			} else {
				node.right = doInsert(data, node.right);
				return node;
			}
		}
    }

    public Iterator<E> iterator() {

        return null;
    }

    public void remove(E data) {

		root = doRemove(data, root);
    }
	
	public Node<E> doRemove(E data, Node<E> node){

		if (node == null){
			return node;
		} 
		if (data.compareTo(node.data) == 0){							//if data is found, remove
			if (node.left == null && node.right == null){				//if node is a leaf
				return node = null;
			} else if(node.left == null && node.right != null){			//if node has 1 child
				node = node.left;
				node.left = null;
				return node;
			} else {													//if node has 2 children
				Node<E> iop = findIOP(node);
				node = iop;
				iop = doRemove(data, iop);
				return node;
			}
		} else if(data.compareTo(node.data) < 0){						//if data is smaller than node, go left
			return doRemove(data, node.left);
		} else if(data.compareTo(node.data) > 0){						//if data is larger than node, go right
			return doRemove(data, node.right);
		}
		return node;
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
