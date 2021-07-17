package jpabook.start;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
	name = "BOARD_SEQ_GENERATOR",
	table = "MY_SEQUENCES",
	pkColumnValue = "BOARD_SEQ", allocationSize = 1
)
public class BoardWithTable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
	generator = "BOARD_SEQ_GENERATOR")
	private Long id;
}
