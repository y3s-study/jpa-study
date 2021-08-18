package jpabook.ch9.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Period {
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;
}
