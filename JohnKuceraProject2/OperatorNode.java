/*
* File: OperatorNode.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java program is meant to accompany P2GUI.java and holds a
* constructor for Operator Nodes. They are assigned a value and nodes
* on their left/right.
*/

// Constructor
public class OperatorNode extends TreeNode {
    OperatorNode(String value, TreeNode left, TreeNode right) {
        super(value, left, right);   
    }
}
