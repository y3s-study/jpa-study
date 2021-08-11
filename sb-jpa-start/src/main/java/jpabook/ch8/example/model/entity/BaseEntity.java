package jpabook.ch8.example.model.entity;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	private LocalDate createdDate;
	private LocalDate lastModifiedDate;
}
