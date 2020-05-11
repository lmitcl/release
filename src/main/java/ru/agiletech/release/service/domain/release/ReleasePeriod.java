package ru.agiletech.release.service.domain.release;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import ru.agiletech.release.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Embeddable
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReleasePeriod implements ValueObject {

    @Transient
    static long ZERO_DAYS = 0;
    @Transient
    static LocalDate EMPTY_DATE = null;

    private LocalDate createDate;

    private LocalDate releaseDate;

    static ReleasePeriod between(LocalDate createDate,
                                 LocalDate releaseDate){
        return new ReleasePeriod(createDate,
                releaseDate);
    }

    public long calculateDays(){
        Period period = Period.between(this.createDate, this.releaseDate);

        return period.getDays();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        ReleasePeriod releasePeriod = (ReleasePeriod) object;

        return Objects.equals(createDate, releasePeriod.createDate) &&
                Objects.equals(releaseDate, releasePeriod.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate, releaseDate);
    }

}
