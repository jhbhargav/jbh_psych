package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "players")
public class Player extends User {
    @NotBlank
    @Getter
    @Setter
    private String alias;

    @Getter
    @Setter
    @URL
    private String psychFaceURL;

    @Getter
    @Setter
    @URL
    private String picURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private Stat stat = new Stat();

    @ManyToMany(mappedBy = "players")
    @JsonIdentityReference
    @Getter
    @Setter
    private Set<Game> games = new HashSet<>();

    public Player(){

    }

    private Player(Builder obj){
        setAlias(obj.alias);
        setEmail(obj.email);
        setSaltedHashedPassword(obj.saltedHashedPassword);
        setPicURL(obj.picURL);
        setPsychFaceURL(obj.psychFaceURL);
    }

    public static class Builder{
        @NotBlank
        private String alias;
        private String psychFaceURL;
        private String picURL;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String saltedHashedPassword;

        public Builder(){

        }

        public Builder alias(String alias){
            this.alias = alias;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder saltedHashedPassword(String saltedHashedPassword){
            this.saltedHashedPassword = saltedHashedPassword;
            return this;
        }
        public Builder psychFaceURL(String psychFaceURL){
            this.psychFaceURL = psychFaceURL;
            return this;
        }
        public Builder picURL(String picURL){
            this.picURL = picURL;
            return this;
        }
        public Player build(){
            return new Player(this);

        }
    }
}
