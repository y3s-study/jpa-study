package com.y3sstudy.skhelloshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
