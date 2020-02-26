package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable {
    @ManyToMany
    @Getter
    @Setter
    @JsonIdentityReference
    private Set<Player> players = new HashSet<>();

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter
    @Setter
    private List<Round> rounds = new ArrayList<Round>();

    @Getter
    @Setter
    private int numRounds = 10;

    @Getter@Setter
    private Boolean hasEllen = false;

    @NotNull
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    private Player leader;

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    @JsonIgnore
    private Map<Player, Stat> playerStats = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private GameStatus gameStatus;

    @ManyToMany
    @Getter
    @Setter
    @JsonIgnore
    private Set<Player> readyPlayers = new HashSet<>();

}
