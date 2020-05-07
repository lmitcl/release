package ru.agiletech.release.service.application.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.release.service.domain.release.Release;
import ru.agiletech.release.service.domain.release.ReleaseId;
import ru.agiletech.release.service.domain.release.ReleaseRepository;
import ru.agiletech.release.service.domain.task.TaskId;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleToReleaseCommandProcessor
        implements CommandProcessor<ScheduleToReleaseCommand>{

    private final ReleaseRepository releaseRepository;

    @Override
    @Transactional
    public void process(ScheduleToReleaseCommand command) {
        log.info("Add task in release");

        ReleaseId releaseId = ReleaseId.identifyReleaseFrom(command.getReleaseId());
        Release release = releaseRepository.releaseOfId(releaseId);

        TaskId taskId = TaskId.identifyTaskFrom(command.getTaskId());

        release.addTask(taskId);

        log.info("Task id {} has been add for release {}", command.getTaskId(),
                command.getReleaseId());

        releaseRepository.save(release);
        log.info("Release {} has been saved", command.getReleaseId());
    }

}
