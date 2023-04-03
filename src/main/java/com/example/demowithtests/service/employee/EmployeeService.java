package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee) ;

    List<Employee> getAll();

    Employee getById(String id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

   List<Employee> processorIsDeletedReplaceNull();

   List<Employee> processorAgeSet();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    List<Employee> sendEmailByCountryAndCity(String country, String city, String text);

    // hw 27
    List<Employee> findEmployeeWhoChangedCountry(String country, String text);

    void fillData();

    void fillDataHW24(int size, Employee employee);

    void updateDateById(Integer startId, Integer endId, String country);

    void updateCountryById(Integer startId, Integer endId, String country);

    void updateCountryByIdRandom();

    Employee addPassport(Integer employeeId, Integer passportId);

}
