package com.cubiculus.api.client;

/**
 * Cubiculus REST client generic exception.
 * 
 * @author jan
 *
 */
public class ClientException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(String message) {
        super(message);
    }

}
