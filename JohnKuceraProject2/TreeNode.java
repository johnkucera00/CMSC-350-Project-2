/*
* File: TreeNode.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java program is meant to accompany P2GUI.java and is responsible
* for holding the TreeNode constructor. It also holds methods which generates
* the 3-address instructions, prints those instructions to an Output file, and
* generates the resulting infix expression from each input postifx expression.
*/

// import of necessary java classes
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;

// TreeNode Class
public class TreeNode implements Node {
    
    private String value = "";
    private static int count;
    TreeNode left = null;
    TreeNode right = null;
    
    // TreeNode Constructor
    TreeNode (String value, TreeNode right, TreeNode left) {
        this.value = value;
        this.left = left;
        this.right = right; 
    }
    
    // Method to convert Operator to word
    public String toWord(String value) {
        String word = value;
        switch(value) {
            case "*":
                word = "Mul";
                return word;
            case "/":
                word = "Div";
                return word;
            case "+":
                word = "Add";
                return word;
            case "-":
                word = "Sub";
                return word;
            default:
                return word;
        }  
    }
    
    // Writing 3-address instructions to Output.txt
    private void writeOutput(String instruct) throws IOException {
        File output = new File("Output.txt");
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(output, true));
        bWriter.write(instruct);
        bWriter.newLine();
        bWriter.close(); 
    }
    
    // Initialize postOrder()
    public void runPostOrder() throws IOException {
        count = 0;
        postOrder();
    }
    
    // postOrder() method, generates 3-address instructions
    public String postOrder() throws IOException {

        // Instance variables
        String rLeft= "";
        String rRight = "";
        
        // Recurse through each subtree until it reaches leaf
        if (left.left != null || left.right != null) {
            rLeft = left.postOrder();
        }
        if(right.left != null || right.right != null) {
            rRight = right.postOrder();
        }      
        
        // Properly putting different types of tokens into 3-address instructions
        String displayLeft, displayRight = "";
        Pattern operandChar = Pattern.compile("[0-9]+");
        // Left Node instructions
        if (left.value.matches(String.valueOf(operandChar))) {
            displayLeft = left.value;
        }
        else {
            displayLeft = rLeft;
        }
        // Right Node instructions
        if (right.value.matches(String.valueOf(operandChar))) {
            displayRight = right.value;
        }
        else {
            displayRight = rRight;
        }
        
        // Converting operator into word
        String operator = toWord(this.value);
        
        // Generating 3-address instructions and writing to Output.txt
        String register = "R" + count++;
        String instruct = operator + " " + register + " " + displayLeft + " " + displayRight;  
        writeOutput(instruct);
        return register;
    }

    // inOrder() method for generating infix expression
    public String inOrder() {
        
        // Instance variables
        String iLeft= "";
        String iRight = "";
        String infix = "";
        
        // Recurse through each subtree until it reaches leaf
        if (left.left != null || left.right != null) {
            iLeft = left.inOrder();
        }
        if(right.left != null || right.right != null) {
            iRight = right.inOrder();
        }
        
        // Properly putting different types of tokens into infix expression
        String displayLeft, displayRight = "";
        Pattern operandChar = Pattern.compile("[0-9]+");
        // Left Node instructions
        if (left.value.matches(String.valueOf(operandChar))) {
            displayLeft = left.value;
        }
        else {
            displayLeft = iLeft;
        }
        // Right Node instructions
        if (right.value.matches(String.valueOf(operandChar))) {
            displayRight = right.value;
        }
        else {
            displayRight = iRight;
        }
        
        // Generating infix expression
        infix = "(" + displayLeft + " " + value + " " + displayRight + ")";
        return infix;
    } // end of method
} // end of class
