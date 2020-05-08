package ru.agiletech.release.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.release.service.Application;
import ru.agiletech.release.service.application.release.ReleaseDTO;
import ru.agiletech.release.service.domain.release.Release;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("releases")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ReleaseTest {

    private Release release;

    @Test
    public void testCreateRelease(){
        this.release = Release.create("1.0.0", "test");

        ReleaseDTO releaseDTO = new ReleaseDTO();

        assertNotNull(releaseDTO.getId());
        assertNotNull(releaseDTO.getVersion());
        assertNotNull(releaseDTO.getStatus());
        assertNotNull(releaseDTO.getCreateDate());
        assertNotNull(releaseDTO.getReleaseDate());
        assertNotNull(releaseDTO.getDescription());

    }

}
