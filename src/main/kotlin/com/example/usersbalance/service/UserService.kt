package com.example.usersbalance.service

interface UserService {
    fun setUsersBalance(userBalances: Map<Int, Int>);
}