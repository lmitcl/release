package ru.agiletech.release.service.domain.release;

import lombok.*;
import ru.agiletech.release.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReleaseId implements ValueObject {

    private String id;

    public static ReleaseId identifyRelease(){
        String id = UUID.randomUUID().toString();

        return new ReleaseId(id);
    }

    public static ReleaseId identifyReleaseFrom(String id){
        return new ReleaseId(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        ReleaseId releaseId = (ReleaseId) object;
        return Objects.equals(id,
                releaseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
