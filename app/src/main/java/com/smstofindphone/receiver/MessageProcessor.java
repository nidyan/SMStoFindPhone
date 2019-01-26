package com.smstofindphone.receiver;

/**
 * Process the Messages Received.
 */
public interface MessageProcessor<T> {
    void process(T message);
}
