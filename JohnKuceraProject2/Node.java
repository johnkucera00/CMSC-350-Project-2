/*
* File: Node.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This Java Interface is meant to accompany P2GUI.java and ensures that
* each node has a value and can be properly traversed through.
*/

// import of necessary java classes
import java.io.IOException;

// Node class
public interface Node {
    String value = "";
    void runPostOrder() throws IOException;
    String postOrder() throws IOException;
    String inOrder();
}
