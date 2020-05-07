package ru.agiletech.release.service.input.ports.eventbus;

public interface EventSubscriber<T> {

    void onEvent(T serializedEvent);

}
