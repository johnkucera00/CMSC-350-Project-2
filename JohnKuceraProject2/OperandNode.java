/*
* File: OperandNode.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java program is meant to accompany P2GUI.java and holds a
* constructor for Operand Nodes. They are assigned a value but no left/right
* nodes because all operands are leaves on the Expression Tree.
*/

// Constructor
public class OperandNode extends TreeNode {
    OperandNode(String value) {
        super(value, null, null);
    }
}
