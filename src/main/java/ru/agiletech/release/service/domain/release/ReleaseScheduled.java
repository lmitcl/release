package ru.agiletech.release.service.domain.release;

import lombok.Getter;
import ru.agiletech.release.service.domain.supertype.DomainEvent;
import ru.agiletech.release.service.domain.task.TaskId;

import java.util.Date;

@Getter
public class ReleaseScheduled extends DomainEvent {

    private ReleaseId releaseId;
    private TaskId taskId;

    protected ReleaseScheduled(Date      occurredOn,
                               String    name,
                               ReleaseId releaseId,
                               TaskId    taskId) {
        super(occurredOn, name);

        this.releaseId  = releaseId;
        this.taskId     = taskId;
    }


}
