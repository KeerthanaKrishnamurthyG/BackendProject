package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.exception.InvalidTokenException;
import com.example.usermicroservice.exception.SessionNotFountError;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.exception.WrongPassWord;
import com.example.usermicroservice.models.Role;
import com.example.usermicroservice.models.Session;
import com.example.usermicroservice.models.SessionStatus;
import com.example.usermicroservice.models.User;

import com.example.usermicroservice.repository.SessionRepository;
import com.example.usermicroservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.*;

@Service

public class AuthServices {
private UserRepository userRepository;
private SessionRepository sessionRepository;
private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthServices(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SessionRepository sessionRepository) {

        this.userRepository=userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public ResponseEntity<UserDto> login(String emailId, String password) throws WrongPassWord, UserNotFoundExceptions {


        Optional<User> loginUser=userRepository.findByEmailId(emailId);
        User user=loginUser.get();
        if (loginUser.isEmpty()){
            throw  new UserNotFoundExceptions("User not Valid");
        }

        if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new WrongPassWord("Enter the Correct password");
        }

        Long id=user.getId();

        //jwt gitup jwt signed with HMAC
        // Create a test key suitable for the desired HMAC-SHA algorithm:
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();
        //payload
        // String message = "Hello world";

//        String message = "{\n" +
//                "  \"email\": \"google@gmail.com\",\n" +
//                "  \"roles\": [\n" +
//                "    \"student\",\n" +
//                "    \"ta\"\n" +
//                "  ],\n" +
//                "  \"expiry\": \"31stJan2024\"\n" +
//                "}";
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("email", user.getEmailId());
        jsonMap.put("roles", List.of(user.getRoles()));
        jsonMap.put("createdAt", new Date());
        jsonMap.put("expiryAt", DateUtils.addDays(new Date(), 30));


// Create the compact JWS: signature token
//        String jws = Jwts.builder().content(content, "text/plain").signWith(key, alg).compact();

// Parse the compact JWS:
//        content = Jwts.parser().verifyWith(key).build().parseSignedContent(jws).getPayload();
//
//        assert message.equals(new String(``content, StandardCharsets.UTF_8));

//       sessionRepository.countUserSession(emailId);
//        content -> claims
        String jws = Jwts.builder()
                .claims(jsonMap)
                .signWith(key,alg)
                .compact();
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + jws);
//
//        if ((sessionRepository.findById(id).stream().count()==1) ) {
//            return new ResponseEntity<>(UserDto.convertToUserDto(user),HttpStatus.OK);
//        }
        Session session = new Session();
        session.setUsername(user);
        Date date = new Date();
        session.setSessionStatus(SessionStatus.ACTIVE);
//           String sessionToken = RandomStringUtils.randomAlphanumeric(30);

        session.setToken(jws);

        session.setCreatedAT(date);
        session.setExpiredAt(DateUtils.addDays(date, 100));
        sessionRepository.save(session);
        System.out.println("reached Login");


        return new ResponseEntity<>(UserDto.convertToUserDto(user),headers, HttpStatus.OK);
    }
public ResponseEntity<Void> userLogout(String emailId,String token) throws InvalidTokenException {

    Optional<Session> currentToken=sessionRepository.findSessionByToken(token);
    if (currentToken.isEmpty()){
        throw  new InvalidTokenException("Invalid token Exceptions");
    }
    Session session=currentToken.get();
    session.setSessionStatus(SessionStatus.ENDED);
    sessionRepository.save(session);

//        sessionRepository.delete(session);
    return ResponseEntity.ok().build();
}
//    public ResponseEntity<Void> logout(String token, Long userId) {
//
//        return null;
//    }

    public UserDto signUp(String emailId, String password) {
        System.out.println("reached signUP");


        User user=new User();

        user.setEmailId(emailId);
        user.setPassword(bCryptPasswordEncoder.encode(password));


        User savedUser= userRepository.save(user);
      System.out.println(savedUser.getEmailId() +" "+ savedUser.getPassword());


        return UserDto.convertToUserDto(user);
    }

    public SessionStatus validate(String token) throws SessionNotFountError{
        Optional<Session> optionalSession=sessionRepository.findSessionByToken(token);
        if (optionalSession.isEmpty()){
            throw new SessionNotFountError("Session not fount error");
        }

        Session session = optionalSession.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }

        Date currentTime = new Date();
        if (session.getExpiredAt().before(currentTime)) {
            return SessionStatus.ENDED;
        }

        //JWT Decoding.
        Jws<Claims> jwsDecodeClaims=Jwts.parser().build().parseSignedClaims(token);

        // Map<String, Object> -> Payload object or JSON
        String email = (String) jwsDecodeClaims.getPayload().get("email");
        List<Role> roles = (List<Role>) jwsDecodeClaims.getPayload().get("roles");
        Date createdAt = (Date) jwsDecodeClaims.getPayload().get("createdAt");

//        if (restrictedEmails.contains(email)) {
//            //reject the token
//        }

        return SessionStatus.ACTIVE;


    }

    public UserDto changePassWord(String emailId, String password) throws UserNotFoundExceptions {
//        return userService.changeUserPassword(emailId,password);
        Optional<User> user=userRepository.findByEmailId(emailId);
        User currentUser=user.get();
        if(user.isEmpty()){
            throw new  UserNotFoundExceptions("user not found");
        }

        currentUser.setPassword(bCryptPasswordEncoder.encode(password));

        return UserDto.convertToUserDto(currentUser);
    }

    public UserDto forgotUserPassword(String emailId) {
        return null;
    }
}
