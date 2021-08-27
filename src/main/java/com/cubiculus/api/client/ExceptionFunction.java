package com.cubiculus.api.client;

public interface ExceptionFunction<V, R> {
    
    R apply(V value) throws Exception;
    
}
