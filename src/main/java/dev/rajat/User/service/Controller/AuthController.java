package dev.rajat.User.service.Controller;

import dev.rajat.User.service.DTO.LoginRequestDTO;
import dev.rajat.User.service.DTO.SignInRequestDTO;
import dev.rajat.User.service.DTO.UserRequestDTO;
import dev.rajat.User.service.Models.Session;
import dev.rajat.User.service.Models.User;
import dev.rajat.User.service.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    ResponseEntity<UserRequestDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        return  authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());


    }

    @PostMapping("/signUp")
    ResponseEntity<UserRequestDTO> singIn(@RequestBody SignInRequestDTO signInRequestDTO)
    {
        System.out.println("Hello");
        UserRequestDTO user = authService.singIn(signInRequestDTO.getEmail(),signInRequestDTO.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/logOut/{id}")
    ResponseEntity<Void> logOut(@PathVariable("id") Long id,@RequestHeader("token") String token)
    {
       return  authService.logOut(id,token);


    }

  /*  @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id") Long userId, @RequestHeader("token") String token) {
        return authService.logOut(userId,token);
    }*/
   /* @DeleteMapping"/{signUp}")
    ResponseEntity<UserRequestDTO> logOut(@RequestBody SignInRequestDTO signRequestDTOA)
    {
        UserRequestDTO user = authService.logOut(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }*/
  @GetMapping("/session")
    ResponseEntity<List<Session>> getAllSession()
    {
        return  authService.getAllsession();


    }
   @GetMapping("/user")
    ResponseEntity<List<User>> getAllUser()
    {
        return  authService.getAllUser();


    }




}
