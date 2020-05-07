package ru.agiletech.release.service.application.task;

public interface CommandProcessor<T extends Command> {

    void process(T command);

}
