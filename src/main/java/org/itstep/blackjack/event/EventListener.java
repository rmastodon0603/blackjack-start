package org.itstep.blackjack.event;

public interface EventListener<T> {
    void handle(T data);
}
