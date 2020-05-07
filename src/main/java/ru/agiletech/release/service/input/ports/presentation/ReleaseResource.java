package ru.agiletech.release.service.input.ports.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agiletech.release.service.application.release.ReleaseDTO;
import ru.agiletech.release.service.application.release.ReleaseService;
import ru.agiletech.release.service.input.ports.presentation.hateoas.LinksUtil;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "REST-ресурс модели вывода релизов agile команды")
public class ReleaseResource {

    private final ReleaseService releaseService;

    @PostMapping(value = "/releases")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать версию релиза", code = 201)
    public ReleaseDTO createRelease(@Valid @RequestBody ReleaseDTO releaseDTO){
        var createdRelease = releaseService.createRelease(releaseDTO);
        LinksUtil.addLinks(createdRelease);

        return createdRelease;
    }

    @GetMapping(value = "/releases/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти релиз по идентификатору")
    public ReleaseDTO getRelease(@PathVariable String id){
        var release = releaseService.searchRelease(id);
        LinksUtil.addLinks(release);

        return release;
    }

    @GetMapping(value = "/releases")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти все релизы")
    public Set<ReleaseDTO> getAllreleases(){
        Set<ReleaseDTO> releases = releaseService.searchAllReleases();

        if(CollectionUtils.isNotEmpty(releases))
            releases.forEach(LinksUtil::addLinks);

        return releases;
    }

    @PutMapping(value = "/releases/{id}/status")
    @ApiOperation(value = "Изменить статус релиза")
    public ResponseEntity<Void> changeStatus(@PathVariable(name = "id")         String releaseId,
                                             @RequestParam                      String status){

        releaseService.changeStatus(releaseId, status);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/releases/{id}/tasks")
    @ApiOperation(value = "Добавить задачу в релиз")
    public ResponseEntity<Void> scheduleSprint(@PathVariable(name = "id") String releaseId,
                                               @RequestParam              String taskId){

        releaseService.scheduleSprint(releaseId, taskId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
