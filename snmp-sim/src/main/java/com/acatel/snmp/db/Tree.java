package com.acatel.snmp.db;

import java.util.Arrays;

public class Tree {

	public static TreeNode getRootNode(int oid) {
		return new NonLeafNode(oid, null);
	}

	public static class TreeNode {

		private int m_oid;

		TreeNode m_next;

		TreeNode m_leftChild;

		TreeNode m_parent;

		public TreeNode(int _oid, TreeNode _next, TreeNode _leftChild) {
			m_oid = _oid;
			m_next = _next;
			m_leftChild = _leftChild;
		}

		public int getOid() {
			return m_oid;
		}

		public TreeNode getNextNode() {
			return m_next;
		}

		public void setParent(TreeNode node) {
			m_parent = node;
		}

		public TreeNode getParent() {
			return m_parent;
		}

		public void addOid(int[] oids, Object value) {
			if(oids.length == 1) {
				TreeNode leafNode = ceateLeafNode(oids[0], this, value);
				addChild(leafNode);
			}else{
				int oid = oids[0];
				TreeNode node = getChild(oid);
				if(node == null) {
					//create new node and attach
					TreeNode nonLeafNode = createNonLeafNode(oids[0], this);
					addChild(nonLeafNode);
					int[] subOid = Arrays.copyOfRange(oids, 1, oids.length -1); 
					nonLeafNode.addOid(subOid, value);
				}else {
					//already have the child add it to child
					int[] subOid = Arrays.copyOfRange(oids, 1, oids.length -1); 
					node.addOid(subOid, value);
				}			
			}
		}

		private TreeNode createNonLeafNode(int i, TreeNode treeNode) {

			return new NonLeafNode(i, treeNode);
		}

		private TreeNode ceateLeafNode(int i, TreeNode treeNode, Object value) {

			return new LeafNode(i, treeNode, value);
		}

		public TreeNode addChild(TreeNode node) {
			if(m_leftChild == null) {
				m_leftChild = node;
				return m_leftChild;
			}else {
				return addChildToLink(null,m_leftChild, node);

			}
		}

		public TreeNode getChild(int oid) {
			if(m_leftChild == null) return null;
			else return getChild(m_leftChild, oid);
		}

		private TreeNode getChild(TreeNode leftChild, int oid) {
			if(leftChild == null) 
				return null;
			else if(leftChild.m_oid == oid) return leftChild;
			else return getChild(leftChild.m_next, oid);		
		}

		private TreeNode addChildToLink(TreeNode preNode,TreeNode currentNode, TreeNode node) {
			if(currentNode == null) {
				node.m_next = preNode.m_next;
				preNode.m_next = node;

			}else {
				if(currentNode.m_oid < node.m_oid) {
					addChildToLink(currentNode, currentNode.m_next, node);
				}else if(currentNode.m_oid == node.m_oid){
					node.m_next = preNode.m_next;
					preNode.m_next = node;
					currentNode.m_parent = null;

				}else {
					if(preNode == null) {
						node.m_next = currentNode;
						node.m_parent = currentNode.m_parent;
						node.m_parent.m_leftChild = node;
					}else {
						preNode.m_next = node;
						node.m_next = currentNode;
					}
				}
			}
			return node;
		}

		
	}
	
	static class LeafNode extends TreeNode {

		Object m_value;

		public LeafNode(int _oid, TreeNode parent,Object value) {
			super(_oid, null, null);
			setParent(parent);
			m_value = value;			
		}

		public Object getValue() {
			return m_value;
		}

	}

	static class NonLeafNode extends TreeNode {

		public NonLeafNode(int _oid, TreeNode parent) {
			super(_oid, null, null);
			setParent(parent);
		}

	}
}
