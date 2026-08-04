package com.devsuperior.dslist.controllers.users_jpa;

import com.devsuperior.dslist.adapter.in.http.controllers.user_jpa.UserController;
import com.devsuperior.dslist.users_jpa.dto.UserDTO;
import com.devsuperior.dslist.users_jpa.ports.UserPort;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPort userPort;

    private List<UserDTO> userList;
    private Page<UserDTO> userPage;
    private UserDTO user;

    @BeforeEach
    void setUp() {
        user = new UserDTO(1L, "John Doe", "john.doe@example.com", BigDecimal.valueOf(1234.56));
        userList = Arrays.asList(
                user,
                new UserDTO(2L, "Jane Doe", "jane.doe@example.com", BigDecimal.valueOf(2234.60))
        );
        userPage = new PageImpl<>(userList, PageRequest.of(0, 10), userList.size());

        Mockito.when(userPort.findAll()).thenReturn(userList);
        Mockito.when(userPort.findAllPageable(any(Pageable.class))).thenReturn(userPage);
        Mockito.when(userPort.findById(eq(1L))).thenReturn(user);
    }

//    @Test
    void findAll_ShouldReturnListOfUsers() throws Exception {
        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(userList.size()))
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].name").value(user.getName()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));
    }

//    @Test
    void findAllPageable_ShouldReturnPageOfUsers() throws Exception {
        mockMvc.perform(get("/users/pages")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(userList.size()))
                .andExpect(jsonPath("$.content[0].id").value(user.getId()))
                .andExpect(jsonPath("$.content[0].name").value(user.getName()))
                .andExpect(jsonPath("$.content[0].email").value(user.getEmail()));
    }

//    @Test
    void findById_ShouldReturnUser_WhenIdExists() throws Exception {
        mockMvc.perform(get("/users/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    //@Test
    void findById_ShouldReturnNotFound_WhenIdDoesNotExist() throws Exception {
        final var expectedMessage = "User not found! 404 Not Found!";
        Mockito.when(userPort.findById(eq(99L))).thenThrow(new RuntimeException(expectedMessage));

        mockMvc.perform(get("/users/{id}", 99L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}