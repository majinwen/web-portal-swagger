//package com.toutiao.appV2.apiimpl;
//
//import java.util.List;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.constraints.*;
//import javax.validation.Valid;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.Callable;
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T06:22:01.831Z")
//
//@Controller
//public class UserApiController implements UserApi {
//
//    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
//
//    private final ObjectMapper objectMapper;
//
//    private final HttpServletRequest request;
//
//    @org.springframework.beans.factory.annotation.Autowired
//    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }
//
//    public Callable<ResponseEntity<Void>> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User body) {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<Void>> createUsersWithArrayInput(@ApiParam(value = "List of user object" ,required=true )  @Valid @RequestBody List<User> body) {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<Void>> createUsersWithListInput(@ApiParam(value = "List of user object" ,required=true )  @Valid @RequestBody List<User> body) {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<Void>> deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("username") String username) {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<User>> getUserByName(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ",required=true) @PathVariable("username") String username) {
//        return new Callable<ResponseEntity<User>>() {
//            @Override
//            public ResponseEntity<User> call() {
//                String accept = request.getHeader("Accept");
//                if (accept != null && accept.contains("application/xml")) {
//                    try {
//                        return new ResponseEntity<User>(objectMapper.readValue("<User>  <id>123456789</id>  <username>aeiou</username>  <firstName>aeiou</firstName>  <lastName>aeiou</lastName>  <email>aeiou</email>  <password>aeiou</password>  <phone>aeiou</phone>  <userStatus>123</userStatus></User>", User.class), HttpStatus.NOT_IMPLEMENTED);
//                    } catch (IOException e) {
//                        log.error("Couldn't serialize response for content type application/xml", e);
//                        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                if (accept != null && accept.contains("application/json")) {
//                    try {
//                        return new ResponseEntity<User>(objectMapper.readValue("{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"password\" : \"password\",  \"userStatus\" : 6,  \"phone\" : \"phone\",  \"id\" : 0,  \"email\" : \"email\",  \"username\" : \"username\"}", User.class), HttpStatus.NOT_IMPLEMENTED);
//                    } catch (IOException e) {
//                        log.error("Couldn't serialize response for content type application/json", e);
//                        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<String>> loginUser(@NotNull @ApiParam(value = "The user name for login", required = true) @Valid @RequestParam(value = "username", required = true) String username,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password) {
//        return new Callable<ResponseEntity<String>>() {
//            @Override
//            public ResponseEntity<String> call() {
//                String accept = request.getHeader("Accept");
//                if (accept != null && accept.contains("application/xml")) {
//                    try {
//                        return new ResponseEntity<String>(objectMapper.readValue("aeiou", String.class), HttpStatus.NOT_IMPLEMENTED);
//                    } catch (IOException e) {
//                        log.error("Couldn't serialize response for content type application/xml", e);
//                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                if (accept != null && accept.contains("application/json")) {
//                    try {
//                        return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
//                    } catch (IOException e) {
//                        log.error("Couldn't serialize response for content type application/json", e);
//                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<Void>> logoutUser() {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//    public Callable<ResponseEntity<Void>> updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("username") String username,@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body) {
//        return new Callable<ResponseEntity<Void>>() {
//            @Override
//            public ResponseEntity<Void> call() {
//                String accept = request.getHeader("Accept");
//                return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
//            }
//        };
//    }
//
//}
