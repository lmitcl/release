package ru.agiletech.release.service.domain.release;

import java.util.Set;

public interface ReleaseRepository {

    void save(Release release);

    Set<Release> allReleases();

    Release releaseOfId(ReleaseId releaseId);

}
