/*
* File: InvalidTokenException.java
* Author: John Kucera
* Date: February 5, 2020
* Purpose: This java program is meant to accompany P2GUI.java. It is a user
* defined checked exception that handles situations where expression input
* includes characters that are not among 0 to 9, +, -, *, /.
*/

// Constructor
public class InvalidTokenException extends Exception {
    public InvalidTokenException(String token) {
        super(token);
    }
}