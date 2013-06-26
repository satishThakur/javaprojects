package com.alcatel.snmp.mib.parser;

import java.util.Arrays;
import java.util.LinkedList;

public class LeafNode extends TreeNode{

	public LeafNode(int _oid, Object _value) {
		super(_oid);
		value = _value;		
	}

	public Object getValue() {
		return value;
	}
	private Object value;

}

class TreeNode {
	private int oid;

	public TreeNode(int _oid) {
		oid = _oid;
	}

	public int getOid() {
		return oid;
	}

	public TreeNode getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addChild(TreeNode child) {
		// TODO Auto-generated method stub

	}
}

class ObjectTypeNode extends TreeNode{

	public ObjectTypeNode(int _oid) {
		super(_oid);

	}

	private LinkedList<TreeNode> children;

	public void addChild(TreeNode node) {
		children.addLast(node);
	}


}

class DataTree {

	private TreeNode rootNode = new ObjectTypeNode(1);
	
	public void addNode(int[] oid, Object value){
		addNode(rootNode, oid, value);
	}


	public void addNode(TreeNode parent, int[] oid, Object value) {
		if(oid.length > 1) {
			TreeNode child = parent.getChild(oid[0]);
			if(child == null) {
				child = new ObjectTypeNode(oid[0]);
				parent.addChild(child);
				addNode(child, Arrays.copyOfRange(oid, 1, oid.length -1), value);
			}
		}else {
			TreeNode node = new LeafNode(oid[0], value);
			parent.addChild(node);
		}

	}




}
