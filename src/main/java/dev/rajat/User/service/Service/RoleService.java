package dev.rajat.User.service.Service;

import dev.rajat.User.service.DTO.CreateRoleDTO;
import dev.rajat.User.service.Models.Role;
import dev.rajat.User.service.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public Role createRole(String name){
        Role role = new Role();
        role.setRole("name");
         Role roleout = roleRepository.save(role);
         return roleout;


    }
}
