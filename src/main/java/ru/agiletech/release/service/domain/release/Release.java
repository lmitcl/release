package ru.agiletech.release.service.domain.release;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import ru.agiletech.release.service.domain.sprint.SprintId;
import ru.agiletech.release.service.domain.supertype.AggregateRoot;
import ru.agiletech.release.service.domain.task.TaskId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "releases")
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Release extends AggregateRoot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="release_id"))
    })
    private ReleaseId       releaseId;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="id", column=@Column(name="sprint_id"))
//    })
//    private SprintId        sprintId;

    @Embedded
    private String          version;

    @Enumerated(EnumType.STRING)
    private Status          status;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column (name="task_id"))
    })
    private Set<TaskId>     tasks;

    @Embedded
    private ReleasePeriod   releasePeriod;
    @Embedded
    private String          description;


    Release(ReleaseId       releaseId,
            String          version,
            Status          status,
            Set<TaskId>     tasks,
            String          description) {
        this.releaseId      = releaseId;
        this.version        = version;
        this.tasks          = tasks;
        this.status         = status;
        this.description    = description;
    }

    public void changeStatus(Status status){
        if(status == Status.UNKNOWN)
            throw new IllegalArgumentException("Unknown status");

        this.status = status;
    }

    public long daysOfRelease(){
        if(status == Status.UNRELEASED)
            return this.releasePeriod.calculateDays();

        return ReleasePeriod.ZERO_DAYS;
    }

    public LocalDate startDateOfReleasePeriod(){
        if(status == Status.UNRELEASED)
            return this.releasePeriod.getCreateDate();

        return ReleasePeriod.EMPTY_DATE;
    }

    public LocalDate endDateOfReleasePeriod(){
        if(status == Status.UNRELEASED)
            return this.releasePeriod.getReleaseDate();

        return ReleasePeriod.EMPTY_DATE;
    }

    public ReleaseScheduled schedule(TaskId taskId){
        if(this.status == Status.UNRELEASED)
            throw new UnsupportedOperationException("Невозможно спланировать завершенный релиз.");

        this.tasks.add(taskId);

        String eventName = ReleaseScheduled.class.getName();

        return new ReleaseScheduled(new Date(),
                eventName,
                this.releaseId,
                taskId);
    }

    public void addTask(TaskId taskId){
        this.tasks.add(taskId);
    }

    public String status(){
        return this.status.getName();
    }

    public String releaseId(){
        return this.releaseId.getId();
    }

    public String description(){
        return this.description;
    }

    public Set<String> tasks(){
        if(CollectionUtils.isNotEmpty(this.tasks)){
            Set<String> tasks = new HashSet<>();
            this.tasks.forEach(task -> tasks.add(task.getId()));

            return tasks;
        }

        return Collections.emptySet();
    }


    public static Release create(String          version,
                                 String          description){
        ReleaseId releaseId = ReleaseId.identifyRelease();

        return new Release(releaseId,
                version,
                Status.UNRELEASED,
                new HashSet<>(),
                description);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        Release release = (Release) object;

        return Objects.equals(releaseId,
                release.releaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseId);
    }

    @RequiredArgsConstructor
    public enum Status{
        RELEASED("RELEASED"),
        UNRELEASED("UNRELEASED"),
        ARCHIVED("ARCHIVED"),
        UNKNOWN("UNKNOWN");

        @Getter
        private final String name;

        public static Status fromName(String name){
            for(Status status: Status.values()){
                if(status.getName().equals(name))
                    return status;
            }

            return UNKNOWN;
        }

    }

}
