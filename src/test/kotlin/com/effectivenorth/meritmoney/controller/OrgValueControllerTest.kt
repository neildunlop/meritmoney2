package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.entity.OrgValueEntity
import com.effectivenorth.meritmoney.repository.OrgValueRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class OrgValueControllerTest {

    @Mock
    var mockRepository: OrgValueRepository = Mockito.mock(OrgValueRepository::class.java)

    lateinit var controller: OrgValueController
    val baseUrl = "/api/v1/values"

    @Autowired
    lateinit var mockMvc: MockMvc

    val converter = MappingJackson2HttpMessageConverter()

    val user1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Be Smart.")
    val user2 = OrgValueEntity(UUID.randomUUID(), "Gets things done", "Get things done.")
    val users = mutableListOf(user1, user2)


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        controller = OrgValueController(mockRepository)

        converter.objectMapper = ObjectMapper().registerModule(KotlinModule())
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(converter).build()
    }

    @Test
    fun `GET with no parameters returns all elements`() {

        Mockito.`when`(mockRepository.findAll()).thenReturn(users)

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Any>(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", equalTo(user1.id.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo(user1.title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.equalTo(user1.description)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.equalTo(user2.id.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.equalTo(user2.title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.equalTo(user2.description)))
    }

    @Test
    fun `GET with UUID returns specific element`() {

        Mockito.`when`(mockRepository.findOne(user1.id)).thenReturn(user1)

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/${user1.id}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(user1.id.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(user1.title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(user1.description)))
    }

    @Test
    fun `GET with non-existent UUID returns item not found exception`() {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+ "/${UUID.randomUUID()}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(MockMvcResultMatchers.status().reason("Item not found."))
    }

    @Test
    fun `DELETE with UUID removes specific element`() {

        //TODO: Not sure about this.. the entity is not in the repository.
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/${user1.id}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `POST with new user is successful`() {

        Mockito.`when`(mockRepository.save(user1)).thenReturn(user1)

        val content = converter.objectMapper.writeValueAsString(user1);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(user1.id.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(user1.title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(user1.description)))
    }

//    @Test
//    fun `POST with duplicate user returns Duplicate Item Exception`() {
//
//        val content = converter.objectMapper.writeValueAsString(user1);
//
//        mockMvc.perform(post(baseUrl)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict)
//                .andExpect(jsonPath("$.id", equalTo(user1.id.toString())))
//                .andExpect(jsonPath("$.title", equalTo(user1.title)))
//                .andExpect(jsonPath("$.description", equalTo(user1.description)))
//    }

    @Test
    fun `PUT with valid user updates existing user`() {

        Mockito.`when`(mockRepository.save(user1)).thenReturn(user1)

        val content = converter.objectMapper.writeValueAsString(user1)

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(user1.id.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(user1.title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(user1.description)))
    }
}