package com.example.usersbalance.repository

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CustomUserRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : CustomUserRepository{
    private val CHUNK_SIZE = 100;

    override fun updateUserBalances(userBalances: Map<Int, Int>) {
        val sql = "UPDATE users SET balance = :balance WHERE id = :id"
        userBalances.entries
            .chunked(CHUNK_SIZE)
            .forEach {chunk ->
                val batchValues = chunk.map { (id, balance) ->
                    MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("balance", balance)
                }.toTypedArray()

                jdbcTemplate.batchUpdate(sql, batchValues)
            }
    }
}