package dev.rajat.User.service.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role  extends BaseModel{

    private String role;


}
