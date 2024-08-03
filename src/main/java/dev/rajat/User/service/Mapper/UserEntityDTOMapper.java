package dev.rajat.User.service.Mapper;

import dev.rajat.User.service.DTO.UserRequestDTO;
import dev.rajat.User.service.Models.User;

public class UserEntityDTOMapper {

    public static UserRequestDTO getUserDTOfromUserEntity(User user)
    {
    UserRequestDTO userRequestDTO = new UserRequestDTO();
    userRequestDTO.setEmail(user.getEmail());
    userRequestDTO.setRole(user.getRoles());
     return userRequestDTO;

    }


}
