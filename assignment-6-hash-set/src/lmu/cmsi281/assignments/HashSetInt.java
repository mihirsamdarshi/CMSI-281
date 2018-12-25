package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Random;

class HashSetIntOp {

    private static void sort(ArrayList<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    private static void quickSort(ArrayList<Integer> arr, int left, int right) {
        if (arr == null || arr.size() == 0) {
            return;
        }
        if (left >= right) {
            return;
        }
        int pivotIndex = (left + right)/2;
        int i = left;
        int j = right;
        while (i <= j) {
            while (arr.get(i) < arr.get(pivotIndex)) {
                i++;
            }
            while (arr.get(j) > arr.get(pivotIndex)) {
                j--;
            }
            if (i <= j) {
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                i++;
                j--;
            }
        }
        quickSort(arr, left, j);
        quickSort(arr, i, right);
    }

    public static HashSetInt union(HashSetInt hashSetA, HashSetInt hashSetB) {
        HashSetInt union = new HashSetInt();
        ArrayList<Integer> combined = new ArrayList<Integer>();
        combined.addAll(hashSetA.toList());
        combined.addAll(hashSetB.toList());
        sort(combined);
        for (int i = 0; i < combined.size(); i++) {
            union.add(combined.get(i));
        }
        return union;
    }

    public static HashSetInt intersection(HashSetInt hashSetA, HashSetInt hashSetB) {
        HashSetInt intersection = new HashSetInt();
        ArrayList<Integer> combined = new ArrayList<Integer>();
        combined.addAll(hashSetA.toList());
        combined.addAll(hashSetB.toList());
        sort(combined);
        for (int i = 0; i < combined.size()-1; i++) {
            if (combined.get(i) == combined.get(i+1)) {
                combined.remove(i+1);
                intersection.add(combined.get(i));
            }
        }
        return intersection;
    }

    public static HashSetInt difference(HashSetInt hashSetA, HashSetInt hashSetB) {
            if (hashSetB.toList().size() > hashSetA.toList().size()) {
                HashSetInt temp = hashSetA;
                hashSetA = hashSetB;
                hashSetB = temp;
            }
            HashSetInt difference = new HashSetInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.addAll(hashSetA.toList());
            list.removeAll(hashSetB.toList());
            for (int i = 0; i < list.size(); i++) {
                difference.add(list.get(i));
            }
            return difference;
        }
}

public class HashSetInt {

    private int N_BUCKET = (int)Math.pow(2, 15);
    private ArrayList<LinkedListInt> hashSet;
    private double seed;

    public HashSetInt() {
        hashSet = new ArrayList<LinkedListInt>(N_BUCKET);
        for (int i = 0; i < N_BUCKET; i++) {
            hashSet.add(new LinkedListInt());
        }
        Random rand = new Random();
        seed = rand.nextInt() * rand.nextDouble();
    }

    private int hash(int element) {
        double x = Math.abs(element * seed);
        double d = x - Math.floor(x);
        int index = (int)Math.floor(d * N_BUCKET); 
        return index;
    }

    public void add(int element) {
        int index = hash(element);
        LinkedListInt list = hashSet.get(index);
        if (!list.contains(element)) {
            list.add(element);
        }
    }

    public void remove(int element) {
        int index = hash(element);
        LinkedListInt list = hashSet.get(index);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == element) {
                list.remove(i);
                break;
            }
        }
    }

    public boolean contains(int element) {
        int index = hash(element);
        LinkedListInt list = hashSet.get(index);
        if (list.contains(element)) {
            return true;
        }
        return false;
    }

    public ArrayList<Integer> toList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < hashSet.size(); i++) {
            LinkedListInt chain = hashSet.get(i);
            for (int j = 0; j < chain.size(); j++) {
                list.add(chain.get(j));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        HashSetInt hashSetA = new HashSetInt();
        HashSetInt hashSetB = new HashSetInt();

        hashSetA.add(1);
        hashSetA.add(2);
        hashSetA.add(3);
        hashSetA.add(4);
        hashSetA.add(5);
        hashSetB.add(3);
        hashSetB.add(4);
        hashSetB.add(5);
        hashSetB.add(6);
        hashSetB.add(7);

        System.out.println(hashSetA.toList());
        System.out.println(hashSetB.toList());

        HashSetInt hashSetAuB = HashSetIntOp.union(hashSetA, hashSetB);
        HashSetInt hashSetAiB = HashSetIntOp.intersection(hashSetA, hashSetB);
        HashSetInt hashSetAdB = HashSetIntOp.difference(hashSetA, hashSetB);

        System.out.println(hashSetAuB.toList());	// [7, 6, 5, 4, 3, 2, 1]
        System.out.println(hashSetAiB.toList());	// [3, 4, 5]
        System.out.println(hashSetAdB.toList());	// [1, 2]
    }
}
