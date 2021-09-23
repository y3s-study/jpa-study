package com.y3sstudy.skhelloshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Movie extends Item{
    private String director;
    private String actor;
}
