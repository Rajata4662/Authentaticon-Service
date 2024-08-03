package dev.rajat.User.service.Controller;

import dev.rajat.User.service.DTO.CreateRoleDTO;
import dev.rajat.User.service.DTO.SetUserRolesRequestDTO;
import dev.rajat.User.service.DTO.UserRequestDTO;
import dev.rajat.User.service.Models.Role;
import dev.rajat.User.service.Models.User;
import dev.rajat.User.service.Service.RoleService;
import dev.rajat.User.service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<UserRequestDTO> getUser(@PathVariable("id") Long userId)
        {
         UserRequestDTO user = userService.getUser(userId);
         return new ResponseEntity<>(user,HttpStatus.OK);

        }


        @PostMapping("/{id}/roles")
         public ResponseEntity<UserRequestDTO> createUser(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDTO requestDTO)
        {
            UserRequestDTO user = userService.createUser(userId,requestDTO.getRole());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
}
