package com.example.usersbalance.controller

import com.example.usersbalance.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @PostMapping("/set-users-balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun setUsersBalance(@RequestBody usersBalance: Map<Int, Int>) {
        userService.setUsersBalance(usersBalance);
    }
}