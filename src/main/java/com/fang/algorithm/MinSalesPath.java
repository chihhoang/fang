
package com.fang.algorithm;
/*
SalesPath
[Pramp]
SalesPathis a path from the root to a leaf in the tree, is called a Sales Path. 
The cost of a Sales Path is the sum of the costs for every node in the path from the root.
Find the minimum Sales Path
*/
class MinSalesPath {

    static class Node {
        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = null;
            parent = null;
        }
    }

    static class SalesPath {
        int getCheapestCost(Node rootNode) {
            // your code goes here
            if (rootNode == null)
                return 0;

            if (rootNode.children == null || rootNode.children.length == 0)
                return rootNode.cost;

            int minCost = Integer.MAX_VALUE;
            for (Node child : rootNode.children) {
                int cost = getCheapestCost(child);
                if (cost < minCost)
                    minCost = cost;
            }

            return minCost + rootNode.cost;
        }
    }

    /*********************************************
     * Driver program to test above method *
     *********************************************/

    public static void main(String[] args) {
        SalesPath sp = new SalesPath();

        Node node0 = new Node(0);
        Node node5 = new Node(5);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        node0.children = new Node[] { node5, node3, node6 };

        Node node4 = new Node(4);
        node5.children = new Node[] { node4 };

        Node node2 = new Node(2);
        node3.children = new Node[] { node2 };

        Node node1 = new Node(1);
        node6.children = new Node[] { node1 };

        System.out.println(sp.getCheapestCost(node0)); // 5

        // test node 10
        Node node10 = new Node(10);
        node2.children = new Node[] { node10 };

        /*
              0 
            5 3 6 
            4 2 1 
              10
         */

        System.out.println(sp.getCheapestCost(node0)); // 7
        System.out.println(sp.getCheapestCost(null)); // 0
    }
}
