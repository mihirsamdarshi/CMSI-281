package lmu.cmsi281.assignments;

import java.util.ArrayList;

class HeapNodeInt {
    private int data;
    private int index;
    
    public HeapNodeInt(int element, int n) {
        data = element;
        index = n;
    }
    
    public int getData() {
        return data;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setData(int element) {
        data = element;
    }
    
    public void setIndex(int n) {
        index = n;
    }
}

public class MaxHeapInt {
    private ArrayList<HeapNodeInt> heap;
    
    public MaxHeapInt() {
        heap = new ArrayList<HeapNodeInt>();
    }
    
    public HeapNodeInt getRoot() {
        if (heap.size() == 0) {
            return null;
        }
        return heap.get(0);
    }
    
    public HeapNodeInt getLeft(HeapNodeInt node) {
        int nodeIndex = node.getIndex();
        int leftIndex = (2*nodeIndex)+1;
        if (leftIndex > heap.size()) {
            return null;
        } else {
            return heap.get(leftIndex);
        }
    }
    
    public HeapNodeInt getRight(HeapNodeInt node) {
        int nodeIndex = node.getIndex();
        int rightIndex = (2*nodeIndex)+2;
        if (rightIndex > heap.size()) {
            return null;
        } else {
            return heap.get(rightIndex);
        }
    }
    
    public HeapNodeInt getParent(HeapNodeInt node) {
        int nodeIndex = node.getIndex();
        int parentIndex = (int)Math.floor((nodeIndex-1)/2);
        if (parentIndex < 0) {
            return null;
        } else {
            return heap.get(parentIndex);
        }
    }
    
    public void addNode(int element) {
        HeapNodeInt node = new HeapNodeInt(element, heap.size());
        heap.add(node);
        while (node.getData() > getParent(node).getData()) {
            swap(node, getParent(node));
        }
    }
    
    public void deleteNode(int element) {

        HeapNodeInt deletionNode = heap.get(0);
        
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).getData() == element) {
                deletionNode = heap.get(i);
                break;
            }
            if (i == heap.size()-1) {
                return;
            }
        }
        
        HeapNodeInt lastNode = heap.get(heap.size()-1);

        swap(deletionNode, lastNode);
        heap.remove(heap.get(heap.size()-1));

        while (maxChild(lastNode) != null && lastNode.getData() < maxChild(lastNode).getData()) {
            swap(lastNode, maxChild(lastNode));
        }
    }
    
    HeapNodeInt maxChild(HeapNodeInt node) {
        if (getLeft(node) != null || getRight(node) != null) {
            if (getRight(node) == null) {
                return getLeft(node);
            } else {
                if (getLeft(node).getData() >= getRight(node).getData()) {
                    return getLeft(node);
                } else if (getRight(node).getData() > getLeft(node).getData()){
                    return getRight(node);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
    
    
    void swap(HeapNodeInt nodeA, HeapNodeInt nodeB) {
        int tempIndex = nodeB.getIndex();
        int tempTwoIndex = nodeA.getIndex();
        nodeA.setIndex(tempIndex);
        nodeB.setIndex(tempTwoIndex);
        heap.set(nodeA.getIndex(), nodeA);
        heap.set(nodeB.getIndex(), nodeB);
    }
    
    public ArrayList<Integer> toArray() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int index = 0; index < heap.size(); index++) {
            arr.add(heap.get(index).getData());
        }
        return arr;
    }
    
    public static void main(String[] args) {
        MaxHeapInt maxHeap = new MaxHeapInt();
        maxHeap.addNode(35);
        maxHeap.addNode(33);
        maxHeap.addNode(42);
        maxHeap.addNode(10);
        maxHeap.addNode(14);
        maxHeap.addNode(19);
        maxHeap.addNode(27);
        maxHeap.addNode(44);
        maxHeap.addNode(26);
        maxHeap.addNode(31);
        System.out.println(maxHeap.toArray().toString());
        maxHeap.deleteNode(44);
        System.out.println(maxHeap.toArray().toString());
        maxHeap.deleteNode(31);
        System.out.println(maxHeap.toArray().toString());
    }
}
