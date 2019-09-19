package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController
 */
@RestController
public class RoleController {

   @RequestMapping("/role")
   public String role(){
       return "role";
   }
}
