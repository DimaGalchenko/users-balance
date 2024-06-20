package com.example.usersbalance.repository

interface CustomUserRepository {
    fun updateUserBalances(userBalances: Map<Int, Int>)
}