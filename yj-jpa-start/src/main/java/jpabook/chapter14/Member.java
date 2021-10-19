package jpabook.chapter14;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Convert(converter = BooleanToYnConverter.class, attributeName = "vip")
public class Member {
    @Id
    private Long id;
    private String username;
    private boolean vip;
}
