package ru.agiletech.release.service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agiletech.release.service.domain.release.Release;
import ru.agiletech.release.service.domain.release.ReleaseId;

import java.util.Optional;

public interface ReleaseDAO extends JpaRepository<Release, Long> {

    Optional<Release> findByReleaseId(ReleaseId releaseId);

}
