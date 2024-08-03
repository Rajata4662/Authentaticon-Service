package dev.rajat.User.service.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Entity(name="users")
@Getter
@Setter
//@Entity
public class User extends BaseModel{

    String name;
    String email;
    String password;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();


}
