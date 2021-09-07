package jpabook.ch10.querydsl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchParam {
	private String name;
	private Integer price;
}
