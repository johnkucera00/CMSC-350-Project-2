/*
* File: P2GUI.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java program creates a GUI for accepting a postfix expression
* of unsigned integers. After the Constrcut Tree button is pressed, the GUI
* returns the infix expression for the input postfix expression.
*/

// import of necessary java classes
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.EmptyStackException;

// P2GUI Class
public class P2GUI extends JFrame {
    // Instance Variables
    private String postfix;
    private String infix;
    private ExpressionTree expressionTree = new ExpressionTree();
    
    // Window Components
    private static JLabel inputLbl = new JLabel("Enter Postfix Expression");
    private static JLabel infixLbl = new JLabel("Infix Expression");
    private static JTextField inputTxt = new JTextField(null, 20);
    private static JTextField infixTxt = new JTextField(null, 25);
    private static JButton constructBtn = new JButton("Construct Tree");
    
    // Construct Button Listener
    class ConstructBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Text Field -> Listener
            postfix = inputTxt.getText();
            try {
                if (postfix.isEmpty()) {
                    throw new NullPointerException();
                }
                else {
                    infix = (expressionTree.readInput(postfix));
                    infixTxt.setText(infix);
                } // end of else
            } // end of outer try
            
            // Handling invalid input
            catch (NullPointerException ex) { // Empty Input
                JOptionPane.showMessageDialog(null, 
                    "Please enter a Postfix Expression.");
            }
            catch (EmptyStackException ex) {  // Misplaced operators or operands
                JOptionPane.showMessageDialog(null,
                    "Your Postfix Expression is invalid due to misplaced Operators or Operands. Please try again.");
            }
            catch (IOException ex) { // Failure in file writing
                JOptionPane.showMessageDialog(null,
                    "File writing has failed or been interrupted.");
            }
            catch (InvalidTokenException ex) { // Invalid Token
                JOptionPane.showMessageDialog(null,
                    "Invalid Token: " + ex.getMessage());
            } // end of catch
        } // end of method
    } // end of listener

    // GUI Creation
    public P2GUI() {
        // Frame Creation
        super("John's Three Address Generator"); // Titling frame
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panels
        JPanel postfixPanel = new JPanel(); // Postfix
        postfixPanel.add(inputLbl);
        postfixPanel.add(inputTxt);
        
        JPanel constructPanel = new JPanel(); // Construct Button
        constructPanel.add(constructBtn);
        constructBtn.addActionListener(new ConstructBtnListener());
        
        JPanel infixPanel = new JPanel(); // Infix
        infixPanel.add(infixLbl);
        infixPanel.add(infixTxt);
        infixTxt.setEditable(false);
                
        // Layout and main panel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(postfixPanel);
        main.add(constructPanel);
        main.add(infixPanel);
        add(main);
    } // end of GUI Creation

    // Main method
    public static void main(String[] args) {
        P2GUI gui = new P2GUI();
    } // end of main method
} // end of class
