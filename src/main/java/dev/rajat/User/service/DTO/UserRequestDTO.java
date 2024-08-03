package dev.rajat.User.service.DTO;

import dev.rajat.User.service.Models.Role;
import dev.rajat.User.service.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserRequestDTO {
    private String email;
    private String name;
    private Set<Role> role = new HashSet<>();
   public static UserRequestDTO from(User user)
    {
        UserRequestDTO userRequestdto = new  UserRequestDTO();
        userRequestdto.setEmail(user.getEmail());
        userRequestdto.setRole(user.getRoles());
        return userRequestdto;
    }




}
