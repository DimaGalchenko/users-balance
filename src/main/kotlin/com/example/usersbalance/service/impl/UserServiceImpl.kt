package com.example.usersbalance.service.impl

import com.example.usersbalance.repository.CustomUserRepository
import com.example.usersbalance.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: CustomUserRepository) : UserService {

    @Transactional
    override fun setUsersBalance(userBalances: Map<Int, Int>) {
        userRepository.updateUserBalances(userBalances)
    }
}