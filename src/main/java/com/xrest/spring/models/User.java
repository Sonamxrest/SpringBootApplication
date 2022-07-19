package com.xrest.spring.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity<Long> implements Serializable {
    private String name;
    private String username;
    private String password;
    private String profilePic;
}
