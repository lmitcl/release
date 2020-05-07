package ru.agiletech.release.service.application.release;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.release.service.domain.DomainEventPublisher;
import ru.agiletech.release.service.domain.release.Release;
import ru.agiletech.release.service.domain.release.ReleaseId;
import ru.agiletech.release.service.domain.release.ReleaseRepository;
import ru.agiletech.release.service.domain.release.ReleaseScheduled;
import ru.agiletech.release.service.domain.task.TaskId;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {

    private final DomainEventPublisher<ReleaseScheduled>    releaseScheduledEventPublisher;
    private final ReleaseRepository                         releaseRepository;
    private final ReleaseAssembler                          releaseAssembler;

    @Override
    @Transactional
    public ReleaseDTO createRelease(ReleaseDTO rawRelease) {
        log.info("Create release");

        Release release = Release.create(rawRelease.getVersion(),
                rawRelease.getDescription());

        String id = release.releaseId();

        log.info("Release with id {} has been created", id);
        releaseRepository.save(release);

        log.info("Release with id {} has been saved", id);

        return releaseAssembler.writeDTO(release);
    }

    @Override
    public void changeStatus(String id,
                             String rawStatus) {
        log.info("Change status");

        ReleaseId releaseId = ReleaseId.identifyReleaseFrom(id);
        Release release = releaseRepository.releaseOfId(releaseId);

        release.changeStatus(Release.Status.fromName(rawStatus));
        log.info("Status has been changed for release with id {}", releaseId);

        releaseRepository.save(release);
        log.info("Release with id {} has been saved", releaseId);

    }

    @Override
    public void scheduleSprint(String id, String rawTaskId) {
        log.info("Schedule release with id{}", id);

        ReleaseId releaseId = ReleaseId.identifyReleaseFrom(id);
        Release release = releaseRepository.releaseOfId(releaseId);

        TaskId taskId = TaskId.identifyTaskFrom(rawTaskId);

        ReleaseScheduled event = release.schedule(taskId);
        releaseScheduledEventPublisher.publish(Collections.singletonList(event));

        log.info("Release with id{} has been scheduled", release.releaseId());

        releaseRepository.save(release);
    }

    @Override
    public ReleaseDTO searchRelease(String id) {
        log.info("Search release with id {}", id);

        ReleaseId releaseId = ReleaseId.identifyReleaseFrom(id);
        Release release = releaseRepository.releaseOfId(releaseId);

        log.info("Release with id {} has been found", id);

        return releaseAssembler.writeDTO(release);
    }

    @Override
    public Set<ReleaseDTO> searchAllReleases() {
        log.info("Search all created releases");

        Set<Release> releases = releaseRepository.allReleases();
        Set<ReleaseDTO> releaseDTOS = new HashSet<>();

        if(CollectionUtils.isNotEmpty(releases))
            releases.forEach(task -> releaseDTOS.add(releaseAssembler.writeDTO(task)));

        log.info("All created releases have been found");

        return releaseDTOS;
    }

}
