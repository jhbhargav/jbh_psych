package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends User {
    @NotBlank
    @Getter
    @Setter
    private String alias;
    @Getter
    @Setter
    private String psychFaceURL;
    @Getter
    @Setter
    private String picURL;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Stat stats = new Stat();

    @ManyToMany(mappedBy = "players")
    @Getter
    @Setter
    private Set<Game> games = new HashSet<>();
}