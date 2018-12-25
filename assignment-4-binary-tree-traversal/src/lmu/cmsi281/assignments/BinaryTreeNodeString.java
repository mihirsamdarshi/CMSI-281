package lmu.cmsi281.assignments;

public class BinaryTreeNodeString {
	private String data;
	private BinaryTreeNodeString left;
	private BinaryTreeNodeString right;
	
	public BinaryTreeNodeString(String element) {
		data = element;
		left = null;
		right = null;
	}
	
	public String getData() {
		return data;
	}
	
	public BinaryTreeNodeString getLeft() {
		return left;
	}
	
	public BinaryTreeNodeString getRight() {
		return right;
	}
	
	public void setLeft(String element) {
		left = new BinaryTreeNodeString(element);
	}
	
	public void setRight(String element) {
		right = new BinaryTreeNodeString(element);
	}
}