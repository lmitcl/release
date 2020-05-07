package ru.agiletech.release.service.domain;

import ru.agiletech.release.service.domain.supertype.DomainEvent;

import java.util.List;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(List<T> domainEvents);

}