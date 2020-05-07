package ru.agiletech.release.service.input.ports.presentation.hateoas;

import lombok.experimental.UtilityClass;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import ru.agiletech.release.service.application.release.ReleaseDTO;
import ru.agiletech.release.service.input.ports.presentation.ReleaseResource;

@UtilityClass
public class LinksUtil {

    public void addLinks(ReleaseDTO releaseDTO){
        addSelfLink(releaseDTO);
        addAllReleasesLink(releaseDTO);
        addStatusLink(releaseDTO);
        addScheduleLink(releaseDTO);
    }

    private void addSelfLink(ReleaseDTO releaseDTO){
        releaseDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReleaseResource.class)
                .getRelease(releaseDTO.getId()))
                .withSelfRel());
    }

    private void addAllReleasesLink(ReleaseDTO releaseDTO){
        releaseDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReleaseResource.class)
                .getAllreleases())
                .withRel("allReleases"));
    }

    private void addStatusLink(ReleaseDTO releaseDTO){
        releaseDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReleaseResource.class)
                .changeStatus(releaseDTO.getId(),null))
                .withRel("status"));
    }

    private void addScheduleLink(ReleaseDTO releaseDTO){
        releaseDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReleaseResource.class)
                .scheduleSprint(releaseDTO.getId(),null))
                .withRel("schedule"));
    }


}
