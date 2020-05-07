package ru.agiletech.release.service.application.release;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Представление модели участника agile команды")
public class ReleaseDTO extends RepresentationModel<ReleaseDTO> {

    @ApiModelProperty(value = "Идентификатор участника команды")
    private String      id;

    @NotEmpty
    @ApiModelProperty(position = 1, required = true, value = "Версия релиза")
    private String      version;

    @NotEmpty
    @ApiModelProperty(position = 2, required = true, value = "Статус")
    private String      status;

    @NotEmpty
    @ApiModelProperty(position = 3, required = true, value = "Завершенные задачи")
    private Set<String> tasksDone;

    @ApiModelProperty(position = 4, value = "Задачи в работе")
    private Set<String> tasksInProgress;

    @NotEmpty
    @ApiModelProperty(position = 5, required = true, value = "Задачи к выполнению")
    private Set<String> tasksToDo;

    @ApiModelProperty(position = 6, value = "Дата создания версии")
    private LocalDate   createDate;

    @ApiModelProperty(position = 7, value = "Дата вывода релиза")
    private LocalDate   releaseDate;

    @ApiModelProperty(position = 8, value = "Описание версии")
    private String      description;



}
