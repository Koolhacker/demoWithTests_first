package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.Mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
//@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    public Controller(Service service, Mapper map) {
        this.service = service;
        this.mapper = map;
    }

    private final Service service ;
    private final Mapper mapper ;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
//        log.info("+++ with dto Start +++");

       Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = mapper.employeeToEmployeeDto(service.create(employee));

//        log.info("+++ with dto Finish +++");
        return dto;
        //service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable String id) {
        log.info("+++  Start +++");
        Employee employee = service.getById(id);
        log.info("+++  Step 2  +++");
        EmployeeReadDto dto = mapper.employeeToEmployeeReadDto(employee);

        return dto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return service.updateById(id, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        service.processorIsDeletedReplaceNull();
    }

    @GetMapping("/changeAge")
    @ResponseStatus(HttpStatus.OK)
    public void processorAgeSet() {
        service.processorAgeSet();
    }


    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestParam String country, @RequestParam String text) {
        service.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        service.sendEmailByCity(city, text);
    }

    @PostMapping("/sendEmailByCountryAndCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountryAndCity(@RequestParam String country, @RequestParam String city, @RequestParam String text) {
        service.sendEmailByCountryAndCity(country, city, text);
    }

    @GetMapping("/fillData")
    @ResponseStatus(HttpStatus.OK)
    public void fillData() {
        service.fillData();
//        log.info("Data successful add");
    }

    @GetMapping("/fillDataHw24")
    @ResponseStatus(HttpStatus.OK)
    public void fillDataHw24(@RequestParam int size, @RequestBody Employee employee) {
        service.fillDataHW24(size, employee);
//        log.info("Data successful add");
    }

//    @PutMapping("/updateDateById")
//    @GetMapping("/updateDateById")
    @PatchMapping("/updateDateById")
    @ResponseStatus(HttpStatus.OK)
    public void updateDateById(@RequestParam Integer startId, @RequestParam Integer endId,
                               @RequestParam String country) {
        service.updateDateById(startId, endId, country);
    }

    @PatchMapping("/updateCountryByIdRandom")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountryByIdRandom() {
        service.updateCountryByIdRandom();
    }
    @PatchMapping("/updateCountryById")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountryById(@RequestParam Integer startId, @RequestParam Integer endId,
                                  @RequestParam String country) {
        service.updateCountryById(startId,endId,country);
    }
}
