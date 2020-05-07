package ru.agiletech.release.service.application.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleToReleaseCommand implements Command{

    private String releaseId;
    private String taskId;

}
