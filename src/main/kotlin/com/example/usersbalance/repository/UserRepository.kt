package com.example.usersbalance.repository

import com.example.usersbalance.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository :  JpaRepository<UserEntity, Int>