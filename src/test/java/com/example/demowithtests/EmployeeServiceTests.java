package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.employee.EmployeeServiceBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceBean service;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Mark");

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getName()).isSameAs(employee.getName());
        verify(employeeRepository).save(employee);
    }

    @org.junit.jupiter.api.Test
    public void whenGivenId_shouldReturnEmployee_ifFound() {
        Employee employee = new Employee();
        employee.setId(88);

        when(employeeRepository.findById(Integer.valueOf(employee.getId()))).thenReturn(Optional.of(employee));

        Employee expected = service.getById(String.valueOf(employee.getId()));

        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(Integer.valueOf(employee.getId()));
    }

    @org.junit.jupiter.api.Test
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(employeeRepository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(String.valueOf(employee.getId()));
    }

    @org.junit.Test
    public void testSaveEntityManager() {

        Employee employeeTest = this.testEntityManager.persistFlushFind(Employee.builder().name("Test")).build();

        Employee employee = new Employee();
        employee.setName("Mark");
        when(testEntityManager.persistFlushFind(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        Employee created = service.saveThroughEntityManager(employee);
        assertThat(created).isSameAs(employee.getName());
        verify(testEntityManager).persist(employee);
    }
}
