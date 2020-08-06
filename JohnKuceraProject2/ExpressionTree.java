/*
* File: ExpressionTree.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java program is meant to accompany P2GUI.java and is responsible
* for properly reading the input that is put through the GUI and constructing
* an Expression Tree from the postfix expression. After the tree is built,
* the 3-address instructions are written to Output.txt along with the total
* number of nodes in the tree. The infix expression is returned to the GUI to be
* displayed.
*/

// import of necessary java classes
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;
import java.util.EmptyStackException;
import java.io.IOException;
import java.util.regex.*;

// ExpressionTree class
public class ExpressionTree {
    
    // Initializing Variables
    private Stack<TreeNode> tokenStack = new Stack<>();
    private OperatorNode myRoot = null;
    
    // Method for determining number of tree nodes
    int treeSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        else {
            return 1 + treeSize(root.left) + treeSize(root.right);
        }
    }
        
    // Tokenizing the postfix input and pushing the tokens onto stack
    public String readInput(String postfix) throws EmptyStackException, InvalidTokenException, IOException {
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            // Pattern to match operands and operators
            Pattern operandChar = Pattern.compile("[0-9]+");
            Pattern operatorChar = Pattern.compile("[*/+-]");

            // Invalid Token
            if (!token.matches(String.valueOf(operandChar)) && !token.matches(String.valueOf(operatorChar))) {
                throw new InvalidTokenException(token);
            }
            // Operand
            if (token.matches(String.valueOf(operandChar))) {
                tokenStack.push(new OperandNode(token));
            }
            // Operator
            else if (token.matches(String.valueOf(operatorChar))) {
                myRoot = new OperatorNode(token, tokenStack.pop(), tokenStack.pop());
                tokenStack.push(myRoot);
            } // end of else if
        } // end of For
        
        // Initializing print 3-address to output file
        myRoot.runPostOrder();
        BufferedWriter bwriter = new BufferedWriter(new FileWriter("Output.txt", true));
            bwriter.append("Total nodes in the tree: " + treeSize(myRoot) + "\n\n");
            bwriter.close();

        // Returning infix expression
        return tokenStack.pop().inOrder();
    } // end of method
} // end of class
