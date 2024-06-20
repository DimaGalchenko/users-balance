package com.example.usersbalance

import com.example.usersbalance.entity.UserEntity
import com.example.usersbalance.repository.UserRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class UsersBalanceApplicationTests @Autowired constructor(
	val mockMvc: MockMvc,
	val userRepository: UserRepository
) {
	@BeforeEach
	fun setup() {
		userRepository.deleteAll()
		val users = (1..100).map {
			UserEntity(it, "User$it", 0)
		}
		userRepository.saveAll(users)
	}

	@Test
	fun `should update users balance`() {
		val userBalances = (1..100).associateWith { it * 100 }
		val objectMapper = jacksonObjectMapper()
		val json = objectMapper.writeValueAsString(userBalances)

		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/set-users-balance")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		).andExpect(MockMvcResultMatchers.status().isNoContent)

		val users = userRepository.findAll()
		users.forEach { user ->
			assertEquals(user.balance, userBalances[user.id])
		}
	}

}
