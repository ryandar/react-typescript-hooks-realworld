package com.laraforum.controller;

import com.laraforum.authentication.JwtProvider;
import com.laraforum.authorization.RequirePermissions;
import com.laraforum.authorization.RequireRoles;
import com.laraforum.model.Article;
import com.laraforum.model.Token;
import com.laraforum.model.User;
import com.laraforum.model.dao.UserWithEmailAndPassWord;
import com.laraforum.service.TokenService;
import com.laraforum.service.UserService;
import com.laraforum.service.impl.RoleServiceImpl;
import com.laraforum.service.impl.TokenServiceImpl;
import com.laraforum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * user register/login function
 */

@RestController
@RequestMapping("api/users")
public class UserContoller {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private RoleServiceImpl roleService;


    /**
     * POST /api/users
     * {
     * "user":{
     * "username": "Jacob",
     * "email": "jake@jake.jake",
     * "password": "jakejake"
     * }
     * }
     */

    @GetMapping("test")
    public String fuck() {
        return "fuck";
    }

    // TODO shoud remember the path problem
    @PostMapping("signin")
    public String save(@RequestBody User user) {
        String userName = user.getUserName();
        // basic authorization
        // bwlow line is for test
        roleService.findByRowNumber(1);
        System.err.println("roleService 是：" + roleService
                + " | " + roleService.findByRowNumber(1)
        );
        // also for test
        System.out.println("tokenservice 是：" + tokenService);
        tokenService.findByToken("f");
        user.setRoles(user.getRoles() + "1");
        user.getPermissions().add(1);
        System.out.println("userservice is" + userService);
        userService.save(user);
        System.out.println("jwtProvider is " + jwtProvider);


        String jwtToken = jwtProvider.createToken(user.getUserName());
        // write token to repository
        Date now = new Date();
//        Token token = new Token(user, jwtToken, now);
//        tokenService.save(token);
        // return the result
        return "test";
        // return jwtToken;

    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody UserWithEmailAndPassWord user) {
        String email = user.getEmail();
        String passWord = user.getPassWord();
        return new ResponseEntity<>(userService.LoginWithUserEmail(email, passWord), HttpStatus.OK);
    }


    // @RequestHeader(value="AuthUser") String userName
    // header cannot work

    @GetMapping("current")
    public @ResponseBody
    String getCurrentUser(
            HttpServletRequest httpServletRequest, @RequestHeader HttpHeaders httpHeaders) {

        Map<String, String> headerMap = httpHeaders.toSingleValueMap();

        System.out.println(headerMap);
        return (String) httpServletRequest.getAttribute("AuthUser");


    }

    @Transactional
    @PostMapping("logout")
    public void logout(HttpServletRequest httpServletRequest) {
        // remove jwt token from database
        String userName = (String) httpServletRequest.getAttribute("AuthUser");
        User user = userService.findByUserName(userName);
        System.out.println("user name is " + userName);
        tokenService.deleteToken(user);

    }

    // TODO
//    @GetMapping("get/favorite")
//    public List<Article> findArticlesFavoritedByUser(HttpServletRequest httpServletRequest) {
//        String userName = (String) httpServletRequest.getAttribute("AuthUser");
//
//    }

}
