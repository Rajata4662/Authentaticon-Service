package dev.rajat.User.service.Service;

import dev.rajat.User.service.DTO.SignInRequestDTO;
import dev.rajat.User.service.DTO.UserRequestDTO;
import dev.rajat.User.service.Exception.InvalidCreditinalsException;
import dev.rajat.User.service.Exception.InvalidTokenException;
import dev.rajat.User.service.Exception.UserNotFoundException;
import dev.rajat.User.service.Mapper.UserEntityDTOMapper;
import dev.rajat.User.service.Models.Session;
import dev.rajat.User.service.Models.SessionStatus;
import dev.rajat.User.service.Models.User;
import dev.rajat.User.service.Repository.SessionRepository;
import dev.rajat.User.service.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SessionRepository sessionRepository;


    public UserRequestDTO singIn(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        return UserRequestDTO.from(savedUser);

    }

    public ResponseEntity<UserRequestDTO> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found exception");
        }
        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCreditinalsException("Crediatinal not found exception");
        }
     // String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",user.getId());
        map.put("roles",user.getRoles());
        map.put("createdAt",new Date());
        map.put("expiryDate",new Date(LocalDate.now().plusDays(3).toEpochDay()));
       String token = Jwts.builder().claims(map).signWith(key,alg).compact();




       // String token = JwtClaimsSet.builder().claims(json).build();


        Session session = new Session();
        session.setUser(user);
        session.setLoginAt(new Date());
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        sessionRepository.save(session);
        UserRequestDTO userRequestDTO = UserEntityDTOMapper.getUserDTOfromUserEntity(user);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);
        return new ResponseEntity<>(userRequestDTO, headers, HttpStatus.OK);

    }

    public ResponseEntity<Void> logOut(Long userId, String token) {
        Optional<Session> session = sessionRepository.findByTokenAndUser_Id(token, userId);
        Session sessionOut = session.get();
        sessionOut.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(sessionOut);
        return  ResponseEntity.ok().build();

    }

    public ResponseEntity<List<Session>> getAllsession() {
        List<Session> session= sessionRepository.findAll();

        return   ResponseEntity.ok(session);

    }
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user= userRepository.findAll();

        return   ResponseEntity.ok(user);

    }

    public SessionStatus validate(String token,Long UserId) throws InvalidTokenException {
    Optional<Session>session = sessionRepository.findByTokenAndUser_Id(token,UserId) ;
    if(session.isEmpty() || session.get().getSessionStatus().equals(SessionStatus.ENDED))
        {
            throw new InvalidTokenException("Token is invalid");
        }

       return SessionStatus.ENDED;
    }
}
