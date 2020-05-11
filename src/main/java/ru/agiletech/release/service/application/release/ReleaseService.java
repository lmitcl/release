package ru.agiletech.release.service.application.release;

import java.time.LocalDate;
import java.util.Set;

public interface ReleaseService {

    ReleaseDTO createRelease(ReleaseDTO releaseDTO);

    void changeStatus(String id,
                      String rawStatus,
                      LocalDate rawCreateDate,
                      LocalDate rawReleaseDate);

    void scheduleRelease(String id,
                        String rawTaskId);

    ReleaseDTO searchRelease(String id);

    Set<ReleaseDTO> searchAllReleases();

}
