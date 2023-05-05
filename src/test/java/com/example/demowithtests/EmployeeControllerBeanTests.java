package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.web.employee.EmployeeControllerBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EmployeeControllerBean.class)
@DisplayName("Employee Controller Tests")
//@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class EmployeeControllerBeanTests {

//        01.05.23  after updating

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/users")
    @WithMockUser(roles = "ADMIN")
    public void createPassTest() throws Exception {
        var response = new EmployeeRequestDto();
        response.name = "Mike";
        response.email = "mail@mail.com";
        var employee = Employee.builder().id(1).name("Mike").email("mail@mail.com").build();

        when(service.create(any(Employee.class))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                // .andExpect(jsonPath("$.id", is(1)));
                .andReturn();

        verify(service).create(any());
    }

    @Test
    @DisplayName("Entity POST /api/users")
    @WithMockUser(roles = "ADMIN")
    public void testEntitySave() throws Exception {
        var employeeToBeReturn = Employee.builder()
                .id(1)
                .name("Mark")
                .country("France").build();
        doReturn(employeeToBeReturn).when(service).create(any());
        when(this.service.create(any(Employee.class))).thenReturn(employeeToBeReturn);
        // Execute the POST request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/api/usersS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employeeToBeReturn));
        mockMvc
                .perform(mockRequest)
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andReturn().getResponse();

        verify(this.service, times(1)).create(any(Employee.class));
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("GET /api/users/{id}")
    @WithMockUser(roles = "USER")
    public void getPassByIdTest() throws Exception {
        var response = new EmployeeResponseDto();
        var employee = Employee.builder()
                .id(1)
                .name("Mike")
                .build();

        when(service.getById(String.valueOf(1))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = get("/api/users/1");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Mike")));

        verify(service).getById(String.valueOf(anyInt()));
    }

    @Test
    @DisplayName("PUT /api/users/{id}")
    @WithMockUser(roles = "ADMIN")
    public void updatePassByIdTest() throws Exception {
        var response = new EmployeeResponseDto();

        var employee = Employee.builder().id(1).build();

        when(service.updateById(eq(1), any(Employee.class))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        verify(service).updateById(eq(1), any(Employee.class));
    }

    @Test
    @DisplayName("PATCH /api/users/{id}")
    @WithMockUser(roles = "ADMIN")
    public void deletePassTest() throws Exception {

        doNothing().when(service).removeById(1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .patch("/api/users/1");

        mockMvc.perform(mockRequest)
                .andExpect(status().isNoContent());

        verify(service).removeById(1);
    }

    @Test
    @DisplayName("GET /api/users/p")
    @WithMockUser(roles = "USER")
    public void getUsersPageTest() throws Exception {

        var employee1 = Employee.builder().id(1).name("John").country("US").build();
        var employee2 = Employee.builder().id(2).name("Jane").country("UK").build();
        var employee3 = Employee.builder().id(3).name("Bob").country("US").build();
        List<Employee> list = Arrays.asList(employee1, employee2, employee3);
        Page<Employee> employeesPage = new PageImpl<>(list);
        Pageable pageable = PageRequest.of(0, 5);

//        when(service.getAllWithPagination(eq(pageable))).thenReturn(employeesPage);

        MvcResult result = mockMvc.perform(get("/api/users/p")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andReturn();

//        verify(service).getAllWithPagination(eq(pageable));

        String contentType = result.getResponse().getContentType();
        assertNotNull(contentType);
        assertTrue(contentType.contains(MediaType.APPLICATION_JSON_VALUE));
        String responseContent = result.getResponse().getContentAsString();
        assertNotNull(responseContent);
    }

}

//    01.05.23  before updating

//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @MockBean
//    EmployeeRepository employeeRepository;
//
////    @Autowired
////    WebApplicationContext applicationContext;
////
////    @Before
////    public void setup(){
////        MockitoAnnotations.initMocks(this);
////        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
////    }
//
////    @Order(1)
////        @Ignore
//    @Test
//    public void createEmployee_success() throws Exception {
//        Employee employee = Employee.builder()
//                .name("John")
//                .build();
//
//        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(employee));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.firstName", is("John")));
//    }
//
////        @Ignore
////    @Order(2)
//    @Test
//    public void getEmployeeById_success() throws Exception {
//
//        Employee employee = Employee.builder()
//                .name("Mark")
//                .country("France")
//                .build();
//
//        Mockito.when(employeeRepository.findById(Integer.valueOf(employee.getId()))).thenReturn(java.util.Optional.of(employee));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.firstName", is("Mark")));
//    }
//
//    //    @Ignore
//    @Test
//    public void getAllEmployees_success() throws Exception {
//
//        Employee employee = Employee.builder()
//                .name("Mark")
//                .country("France")
//                .build();
//
//        List<Employee> records = new ArrayList<>(Arrays.asList(employee));
//
//        employeeRepository.save(employee);
//
//        Mockito.when(employeeRepository.findAll()).thenReturn(records);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[2].firstName", is("Mark")));
//    }
//}