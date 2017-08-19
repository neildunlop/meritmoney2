package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.entity.UserEntity
import com.effectivenorth.meritmoney.repository.UserRepository
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter




@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Mock
    var mockRepository: UserRepository = Mockito.mock(UserRepository::class.java)

    lateinit var controller: UserController

    @Autowired
    lateinit var mockMvc: MockMvc

    val converter = MappingJackson2HttpMessageConverter()

    val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
    val user2 = UserEntity(UUID.randomUUID(), "Steve", "Smith")
    val users = mutableListOf(user1, user2)


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)


        controller = UserController(mockRepository)

        converter.objectMapper = ObjectMapper().registerModule(KotlinModule())
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(converter).build()


        `when`(mockRepository.findAll()).thenReturn(users)
        `when`(mockRepository.findOne(user1.id)).thenReturn(user1)
        `when`(mockRepository.save(user1)).thenReturn(user1)
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

    @Test
    fun `GET with UUID returns specific element`() {

        mockMvc.perform(get("/api/v1/users/${user1.id}"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$.id", equalTo(user1.id.toString())))
                .andExpect(jsonPath("$.forename", equalTo(user1.forename)))
                .andExpect(jsonPath("$.surname", equalTo(user1.surname)))
    }

    @Test
    fun `GET with non-existent UUID returns item not found exception`() {

        mockMvc.perform(get("/api/v1/users/${UUID.randomUUID()}"))
                .andExpect(status().isNotFound)
                .andExpect(status().reason("Item not found."))
    }

    @Test
    fun `DELETE with UUID removes specific element`() {

        mockMvc.perform(delete("/api/v1/users/${user1.id}"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$.id", equalTo(user1.id.toString())))
                .andExpect(jsonPath("$.forename", equalTo(user1.forename)))
                .andExpect(jsonPath("$.surname", equalTo(user1.surname)))
    }

    @Test
    fun `POST with duplicate user returns Duplicate Item Exception`() {

        val content = converter.objectMapper.writeValueAsString(user1);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict)
                .andExpect(jsonPath("$.id", equalTo(user1.id.toString())))
                .andExpect(jsonPath("$.forename", equalTo(user1.forename)))
                .andExpect(jsonPath("$.surname", equalTo(user1.surname)))
    }

//    @Test
//    fun `PUT with valid user updates existing user`() {
//
//        val content = converter.objectMapper.writeValueAsString(user1);
//
//        mockMvc.perform(post("/api/v1/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated)
//                .andExpect(jsonPath("$.id", equalTo(user1.id.toString())))
//                .andExpect(jsonPath("$.forename", equalTo(user1.forename)))
//                .andExpect(jsonPath("$.surname", equalTo(user1.surname)))
//    }
}