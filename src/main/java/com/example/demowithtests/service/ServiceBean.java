package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.DataAbsentException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.ResourceWasDeletedException;
import com.example.demowithtests.util.WrongTypeIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service


public class ServiceBean implements Service {

    private final Repository repository;


    @Override
    public Employee create(Employee employee)  {
        if (employee.getName() == null || employee.getEmail() == null || employee.getCountry() == null) {
            log.info("Not enough data. HttpStatus - " + HttpStatus.BAD_REQUEST);
            throw new DataAbsentException();
        }
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(String id) {
        try { Integer valueOfId = Integer.valueOf(id);
        var employee = repository.findById(valueOfId)
//                 .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                //  при отсутствии возвращает статус 500
                .orElseThrow(ResourceNotFoundException::new); //  при отсутствии возвращает статус 404
        if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }
        return employee;
    } catch (IllegalArgumentException ex) {
            throw new WrongTypeIdException();
        }
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id).map(entity -> {
            entity.setName(employee.getName());
            entity.setEmail(employee.getEmail());
            entity.setCountry(employee.getCountry());
            return repository.save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        var employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        employee.setIsDeleted(true);
//        repository.delete(employee);
        repository.save(employee);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();

    }

    @Override
    public List<Employee> processor() {
        log.info("replaceNull -> start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        log.info("replaceNull before replace= {} ", replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        log.info("replaceNull after replace= {} ", replaceNull);
        log.info("replaceNull -> end");
        return repository.saveAll(replaceNull);
    }

    @Override
    public void fillData() {
        for (int i = 0; i <= 1000; i++) {
            Employee employee = new Employee("Hillel", "Ukraine", Boolean.FALSE);
            repository.save(employee);
        }
    }

    @Override
    public void updateDateById(Integer startId, Integer endId) {
        List<Employee> oldList = repository.findEmployeeById(startId, endId);
        for (Employee tmp : oldList) {
            tmp.setCountry("Madagascar");
        }
        repository.saveAll(oldList);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = repository.findEmployeeByCountry(country);
        log.info(employees.toString());
        mailSender(extracted(employees), text);
        return employees;
    }

    private static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        for (Employee emp : employees) {
            emails.add(emp.getEmail());
        }
        return emails;
    }

    public void mailSender(List<String> emails, String text) {
        log.info(text);
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        List<Employee> employees = repository.findEmployeeByAddresses(city);
        log.info(employees.toString());
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCountryAndCity(String country, String city, String text) {
        List<Employee> employees = repository.findEmployeeByCountryAndCity(country, city);
        log.info("---it`s work---" + employees);
        mailSender(extracted(employees), text);
        return employees;
    }
}
