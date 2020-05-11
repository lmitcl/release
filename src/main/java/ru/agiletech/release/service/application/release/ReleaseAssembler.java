package ru.agiletech.release.service.application.release;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.agiletech.release.service.domain.release.Release;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReleaseAssembler {

    private final ModelMapper modelMapper;

    ReleaseDTO writeDTO(Release release){
        var releaseDTO = modelMapper.map(release, ReleaseDTO.class);
        var id = release.releaseId();
        Set<String> tasks = release.tasks();
        var status = release.status();


        releaseDTO.setId(id);
        releaseDTO.setStatus(status);
        releaseDTO.setVersion(release.version());
        releaseDTO.setDescription(release.description());

        return releaseDTO;
    }

}
