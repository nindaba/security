package com.yadlings.security.Controllers;

import com.yadlings.security.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class securityLevels {
    @RestController
    @RequestMapping("/v1")
    class versionOne{
        @Autowired
        private UserService userService;
        @GetMapping("/l1")
        public ResponseEntity<?> levelOne(){
            return new ResponseEntity<>("Level One of V1", HttpStatus.OK);
        }
        @GetMapping("/delete")
        public ResponseEntity<?> levelTwo(){
            return new ResponseEntity<>(userService.delete(), HttpStatus.OK);
        }
        @GetMapping("/retrieve")
        public ResponseEntity<?> levelThree(){
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        }
        @GetMapping("/save")
        public ResponseEntity<?> levelFour(){
            userService.initializeUsers();
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
    }
    @RestController
    @RequestMapping("/v2")
    class versionTwo{
        @GetMapping("/l1")
        public ResponseEntity<?> levelOne(){
            return new ResponseEntity<>("Level One of V2", HttpStatus.OK);
        }
        @GetMapping("/l2")
        public ResponseEntity<?> levelTwo(){
            return new ResponseEntity<>("Level Two of V2", HttpStatus.OK);
        }
        @GetMapping("/l3")
        public ResponseEntity<?> levelThree(){
            return new ResponseEntity<>("Level Three of V2", HttpStatus.OK);
        }
    }
}
