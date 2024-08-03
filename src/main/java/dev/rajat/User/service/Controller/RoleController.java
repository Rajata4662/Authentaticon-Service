package dev.rajat.User.service.Controller;

import dev.rajat.User.service.DTO.CreateRoleDTO;
import dev.rajat.User.service.Models.Role;
import dev.rajat.User.service.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

@PostMapping()
ResponseEntity<Role> createRole(@RequestBody CreateRoleDTO createRoleDTO)
{
    Role role = roleService.createRole(createRoleDTO.getName());
    return  new ResponseEntity<>(role,HttpStatus.OK);

}


}
