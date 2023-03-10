package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.io.IOException;
import java.util.List;

public interface Service {

    Employee create(Employee employee) ;

    List<Employee> getAll();

//    Employee getById(Integer id);

    Employee getById(String id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

   List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    List<Employee> sendEmailByCountryAndCity(String country, String city, String text);

    void fillData();

    void updateDateById(Integer startId, Integer endId);

}
