package jpabook.chapter9;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    // 값 타입을 위한 메소드를 정의할 수 있다.
    public boolean isWork(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }
}
