package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.entity.UserEntity
import com.effectivenorth.meritmoney.repository.UserRepository
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Mock
    var mockRepository: UserRepository = Mockito.mock(UserRepository::class.java)

    lateinit var controller: UserController

    @Autowired
    lateinit var  mockMvc: MockMvc

    val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
    val user2 = UserEntity(UUID.randomUUID(), "Steve", "Smith")
    val users = mutableListOf(user1, user2)


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        controller = UserController(mockRepository)

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build() //.setMessageConverters(converter).build();

        `when`(mockRepository.findAll()).thenReturn(users)
    }

    @Test
    fun `GET with no parameters returns all elements`() {

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].id", equalTo(user1.id.toString())))
                .andExpect(jsonPath("$[0].forename", equalTo(user1.forename)))
                .andExpect(jsonPath("$[0].surname", equalTo(user1.surname)))
                .andExpect(jsonPath("$[1].id", equalTo(user2.id.toString())))
                .andExpect(jsonPath("$[1].forename", equalTo(user2.forename)))
                .andExpect(jsonPath("$[1].surname", equalTo(user2.surname)))
    }
}