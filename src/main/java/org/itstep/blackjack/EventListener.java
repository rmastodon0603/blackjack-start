package org.itstep.blackjack;

public interface EventListener<T> {
    void handle(T data);
}
