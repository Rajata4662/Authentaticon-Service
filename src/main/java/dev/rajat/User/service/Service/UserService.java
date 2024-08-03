package dev.rajat.User.service.Service;

import dev.rajat.User.service.DTO.UserRequestDTO;
import dev.rajat.User.service.Models.Role;
import dev.rajat.User.service.Models.User;
import dev.rajat.User.service.Repository.RoleRepository;
import dev.rajat.User.service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class UserService {
   @Autowired
   UserRepository userRepository;

   @Autowired
   RoleRepository roleRepository;

    public UserRequestDTO getUser(long userId)
    {
      Optional<User>userOptional= userRepository.findById(userId);
      if(userOptional.isEmpty())
      {
          return null;
      }
        User user = userOptional.get();
      return UserRequestDTO.from(user);


    }

   public UserRequestDTO createUser(long userId, List<Long> RoleId)
   {
       Optional<User> userOptional = userRepository.findById(userId);
       if(userOptional.isEmpty())
       {
           return null;
       }
Set<Role> role = roleRepository.findAllByIdIn(RoleId);
User user = userOptional.get();
 user.setRoles(role);
 User savedUser = userRepository.save(user);
 return UserRequestDTO.from(savedUser);



   }

}
