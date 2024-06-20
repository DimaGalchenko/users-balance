package com.example.usersbalance.service.impl

import com.example.usersbalance.repository.UserRepository
import com.example.usersbalance.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun setUsersBalance(userBalances: Map<Int, Int>) {
        val users = userRepository.findAllById(userBalances.keys);
        users.forEach { user ->
            user.balance = userBalances.getOrDefault(user.id, user.balance)
        }
        userRepository.saveAll(users);
    }
}