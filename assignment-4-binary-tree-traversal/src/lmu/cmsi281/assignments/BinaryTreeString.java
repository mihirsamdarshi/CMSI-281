package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Stack;

class PreOrderTraversal {

    public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNodeString> tempStorage = new Stack<BinaryTreeNodeString>();
        tempStorage.push(root);

        while (!tempStorage.isEmpty()) {
            BinaryTreeNodeString currentNode = tempStorage.peek();
            path.add(currentNode.getData());
            tempStorage.pop();

            if (currentNode.getLeft() != null) {
                tempStorage.push(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                tempStorage.push(currentNode.getRight());
            }
        }
    }

    public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        } 

        path.add(root.getData()); 

        traverseRecursive(root.getLeft(), path); 
        traverseRecursive(root.getRight(), path); 

    }
}

class InOrderTraversal {

    public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNodeString> tempStorage = new Stack<BinaryTreeNodeString>();
        BinaryTreeNodeString currentNode = root;

        while (currentNode != null || tempStorage.size() > 0) {
            while (currentNode !=  null) {
                tempStorage.push(currentNode); 
                currentNode = currentNode.getLeft(); 
            }

            currentNode = tempStorage.pop(); 
            path.add(currentNode.getData());
            currentNode = currentNode.getRight(); 
        }
    }

    public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        }

        traverseRecursive(root.getLeft(), path);
        path.add(root.getData());
        traverseRecursive(root.getRight(), path);
    }
}

class PostOrderTraversal {

    public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNodeString> tempStorage = new Stack<BinaryTreeNodeString>();
        tempStorage.push(root);
        BinaryTreeNodeString prevNode = null;

        while (!tempStorage.isEmpty()) {
            BinaryTreeNodeString currentNode = tempStorage.peek();
            
            //helped by GeeksForGeeks, I had the most problem with this one

            if (prevNode == null || prevNode.getLeft() == currentNode || prevNode.getRight() == currentNode) {
                if (currentNode.getLeft() != null) {
                    tempStorage.push(currentNode.getLeft());
                } else if (currentNode.getRight() != null) {
                    tempStorage.push(currentNode.getRight());
                } else {
                    path.add(currentNode.getData());
                    currentNode = tempStorage.pop();
                }
            } else if (currentNode.getLeft() == prevNode) {
                if (currentNode.getRight() != null) 
                    tempStorage.push(currentNode.getRight()); 
                else { 
                    tempStorage.pop(); 
                    path.add(currentNode.getData()); 
                }
            } else if (currentNode.getRight() == prevNode) {
                tempStorage.pop();
                path.add(currentNode.getData());
            }
            prevNode = currentNode;
        }
    }

    public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
        if (root == null) {
            return;
        }

        traverseRecursive(root.getLeft(), path);
        traverseRecursive(root.getRight(), path);
        path.add(root.getData());
    }
}

class DepthFirstSearch {

    public Boolean searchIterative(
            BinaryTreeNodeString root, String value, ArrayList<String> path) {
        // TODO: Implement an iterative (using control structures e.g. loops) 
        // depth first search, if the value exists then return true, else false
        // Store the value of the nodes visited in path, which should be the same nodes 
        // as depth first traversal/pre order traversal if the value is missing
        if (root == null) {
            return false;
        }

        Stack<BinaryTreeNodeString> tempStorage = new Stack<BinaryTreeNodeString>();
        tempStorage.push(root);

        while (!tempStorage.isEmpty()) {
            BinaryTreeNodeString currentNode = tempStorage.pop();

            if (currentNode.getData().equals(value)) {
                return true;
            }

            if (currentNode.getRight() != null) {
                tempStorage.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                tempStorage.push(currentNode.getLeft());
            }
        }

        return false;

    }


    public Boolean searchRecursive(
            BinaryTreeNodeString root, String value, ArrayList<String> path) {
        // TODO: Implement a recursive depth first search, 
        // if the value exists then return true, else false
        // Store the value of the nodes visited in path, which should be the same nodes 
        // as depth first traversal/pre order traversal if the value is missing
        if (root == null) {
            return false;
        }

        path.add(root.getData());

        if (root.getData().equals(value)) {
            return true;
        }

        if (searchRecursive(root.getLeft(), value, path) || searchRecursive(root.getRight(), value, path)) {
            return true;
        }

        return false;

    }
}

class BreadthFirstSearch {

    public Boolean searchIterative(
            BinaryTreeNodeString root, String value, ArrayList<String> path) {
        // TODO: Implement an iterative (using control structures e.g. loops) 
        // breadth first search, if the value exists then return true, else false
        // Store the value of the nodes visited in path, which should be the same nodes 
        // as breadth first traversal if the value is missing
        if (root == null) {
            return false;
        }

        ArrayList<BinaryTreeNodeString> tempStorage = new ArrayList<BinaryTreeNodeString>();
        tempStorage.add(root);

        while(!tempStorage.isEmpty()) {
            BinaryTreeNodeString currNode = tempStorage.remove(0);
            path.add(currNode.getData());

            if (currNode.getData() == value) {
                return true;
            }

            if (currNode.getLeft() != null) {
                tempStorage.add(currNode.getLeft());
            }

            if (currNode.getRight() != null) {
                tempStorage.add(currNode.getRight());
            }
        }


        return false;
    }
}

public class BinaryTreeString {

    public static void main(String[] args) {
        BinaryTreeNodeString root = new BinaryTreeNodeString("A");
        root.setLeft("B");
        root.setRight("C");
        root.getLeft().setLeft("D");
        root.getLeft().setRight("E");
        root.getRight().setLeft("F");
        root.getRight().setRight("G");
        root.getLeft().getLeft().setLeft("H");
        root.getLeft().getLeft().setRight("I");
        root.getLeft().getRight().setLeft("J");

        ArrayList<String> path = new ArrayList<String>();

        PreOrderTraversal pre = new PreOrderTraversal();
        pre.traverseIterative(root, path);
        System.out.println("Using iterative pre order traversal:");
        // [A, B, D, H, I, E, J, C, F, G]	
        System.out.println(path);
        path.clear();
        pre.traverseRecursive(root, path);
        System.out.println("Using recursive pre order traversal:");
        // [A, B, D, H, I, E, J, C, F, G]
        System.out.println(path);

        InOrderTraversal in = new InOrderTraversal();
        path.clear();
        in.traverseIterative(root, path);
        System.out.println("Using iterative in order traversal:");
        // [H, D, I, B, J, E, A, F, C, G]
        System.out.println(path);
        path.clear();
        in.traverseRecursive(root, path);
        System.out.println("Using recursive in order traversal:");
        // [H, D, I, B, J, E, A, F, C, G]
        System.out.println(path);


        PostOrderTraversal post = new PostOrderTraversal();
        path.clear();
        post.traverseIterative(root, path);
        System.out.println("Using iterative post order traversal:");
        // [H, I, D, J, E, B, F, G, C, A]
        System.out.println(path);
        path.clear();
        post.traverseRecursive(root, path);
        System.out.println("Using recursive post order traversal:");
        // [H, I, D, J, E, B, F, G, C, A]
        System.out.println(path);

        Boolean found;

        BreadthFirstSearch bfs = new BreadthFirstSearch();

        System.out.println("Using iterative breadth first search:");
        path.clear();
        found = bfs.searchIterative(root, "H", path);
        // Searching for H... Found=true
        // path=[A, B, C, D, E, F, G, H]
        System.out.println("Searching for H... " + "Found=" + found);
        System.out.println("path=" + path);

        path.clear();
        found = bfs.searchIterative(root, "G", path);
        // Searching for G... Found=true
        // path=[A, B, C, D, E, F, G]
        System.out.println("Searching for G... " + "Found=" + found);	
        System.out.println("path=" + path);

        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.println("Using iterative depth first search:");
        path.clear();
        found = dfs.searchIterative(root, "H", path);
        // Searching for H... Found=true
        // path=[A, B, D, H]
        System.out.println("Searching for H... " + "Found=" + found);
        System.out.println("path=" + path);

        path.clear();
        found = dfs.searchIterative(root, "G", path);
        // Searching for G... Found=true
        // path=[A, B, D, H, I, E, J, C, F, G]	
        System.out.println("Searching for G... " + "Found=" + found);
        System.out.println("path=" + path);

        path.clear();
        found = dfs.searchIterative(root, "Z", path);
        // Searching for Z... Found=false
        // path=[A, B, D, H, I, E, J, C, F, G]
        System.out.println("Searching for Z... " + "Found=" + found);
        System.out.println("path=" + path);

        System.out.println("Using recursive search:");
        path.clear();
        found = dfs.searchRecursive(root, "H", path);
        // Searching for H... Found=true
        //path=[A, B, D, H]
        System.out.println("Searching for H... " + "Found=" + found);
        System.out.println("path=" + path);

        path.clear();
        found = dfs.searchRecursive(root, "G", path);
        // Searching for G... Found=true
        // path=[A, B, D, H, I, E, J, C, F, G]
        System.out.println("Searching for G... " + "Found=" + found);
        System.out.println("path=" + path);
    }
}
