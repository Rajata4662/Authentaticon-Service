package dev.rajat.User.service.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {
    public String email;
    public String password;
}
