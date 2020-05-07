package ru.agiletech.release.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import ru.agiletech.release.service.domain.release.Release;
import ru.agiletech.release.service.domain.release.ReleaseId;
import ru.agiletech.release.service.domain.release.ReleaseRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReleaseRepositoryImpl implements ReleaseRepository {

    private final ReleaseDAO releaseDAO;

    @Override
    public void save(Release release) {
        try{
            releaseDAO.save(release);

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Set<Release> allReleases() {
        try{
            return new HashSet<>(releaseDAO.findAll());

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Release releaseOfId(ReleaseId releaseId) {
        try{
            return releaseDAO.findByReleaseId(releaseId)
                    .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release with id %s is not found", releaseId.getId())));

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

}
