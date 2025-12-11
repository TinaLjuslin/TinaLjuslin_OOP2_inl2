package com.ljuslin.exception;

/**
 * Main exception for this project
 *
 * @author Tina Ljuslin
 */
public abstract class LjuslinException extends Exception {
    public LjuslinException(String message) {
        super(message);
    }
}
