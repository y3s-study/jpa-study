package jpabook.ch10.jpql;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NamedStoredProcedureQuery(
	name = "multiply",
	procedureName = "proc_multiply",
	parameters = {
		@StoredProcedureParameter(name = "inParam", mode = ParameterMode.IN, type = Integer.class),
		@StoredProcedureParameter(name = "outParam", mode = ParameterMode.OUT, type = Integer.class)
	}
)
@NamedNativeQuery(
	name = "Member.memberWithOrderCount",
	query = "SELECT M.ID, AGE, NAME, TEAM_ID, I.ORDER_COUNT " +
		"FROM MEMBER M " +
		"LEFT JOIN " +
		"    (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
		"    FROM ORDERS O, MEMBER IM " +
		"    WHERE O.MEMBER_ID = IM>ID) I " +
		"ON M.ID = I.ID",
	resultSetMapping = "memberWithOrderCount"
)
@SqlResultSetMapping(name = "memberWithOrderCount",
	entities = {@EntityResult(entityClass = Member.class)},
	columns = {@ColumnResult(name = "ORDER_COUNT")}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity(name = "Member")
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String username;

	private Integer age;

	@ManyToOne
	private Team team;

	@OneToMany(mappedBy = "member")
	private List<Order> orders;

	public static Member create(String username, Integer age) {
		return new Member(username, age);
	}

	private Member(String username, Integer age) {
		this.username = username;
		this.age = age;
	}
}
