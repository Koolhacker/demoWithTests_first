package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.config.mapstruct.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee API")

public class EmployeeControllerBean implements EmployeeController {
    public EmployeeControllerBean(EmployeeService employeeService, EmployeeMapper map) {
        this.employeeService = employeeService;
        this.employeeMapper = map;
    }

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeRequestDto saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info(" *** method saveEmployee >>>  START");
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeRequestDto);
        EmployeeRequestDto dto = employeeMapper.employeeToEmployeeDto(employeeService.create(employee));
        log.info(" *** method saveEmployee >>>  FINISH ");
        return dto;
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return employeeService.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public EmployeeResponseDto getEmployeeById(@PathVariable String id) {
        log.info("*** method getEmployeeById >>>  START , id = {}", id);
        Employee employee = employeeService.getById(id);
        EmployeeResponseDto dto = employeeMapper.employeeToEmployeeReadDto(employee);
        log.info(" *** method getEmployeeById >>>  FINISH info {} ", employee);
        return dto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeService.updateById(id, employee);
    }

    //Удаление по id
//    @PatchMapping("/users/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void removeEmployeeById(@PathVariable Integer id) {
//        service.removeById(id);
//    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        employeeService.removeAll();
    }

    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        employeeService.processorIsDeletedReplaceNull();
    }

    @GetMapping("/changeAge")
    @ResponseStatus(HttpStatus.OK)
    public void processorAgeSet() {
        employeeService.processorAgeSet();
    }


    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestParam String country, @RequestParam String text) {
        employeeService.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        employeeService.sendEmailByCity(city, text);
    }

    @PostMapping("/sendEmailByCountryAndCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountryAndCity(@RequestParam String country, @RequestParam String city, @RequestParam String text) {
        employeeService.sendEmailByCountryAndCity(country, city, text);
    }

    //hw 27
    @GetMapping("/sendEmailEmployeeWhoChangedCountry")
    @ResponseStatus(HttpStatus.OK)
    public void findEmployeeWhoChangedCountry(@RequestParam String country, @RequestParam String text) {
        employeeService.findEmployeeWhoChangedCountry(country, text);
    }

    @GetMapping("/fillData")
    @ResponseStatus(HttpStatus.OK)
    public void fillData() {
        employeeService.fillData();
//        log.info("Data successful add");
    }

    @GetMapping("/fillDataHw24")
    @ResponseStatus(HttpStatus.OK)
    public void fillDataHw24(@RequestParam int size, @RequestBody Employee employee) {
        employeeService.fillDataHW24(size, employee);
//        log.info("Data successful add");
    }

    //    @PutMapping("/updateDateById")
//    @GetMapping("/updateDateById")
    @PatchMapping("/updateDateById")
    @ResponseStatus(HttpStatus.OK)
    public void updateDateById(@RequestParam Integer startId, @RequestParam Integer endId,
                               @RequestParam String country) {
        employeeService.updateDateById(startId, endId, country);
    }

    @PatchMapping("/updateCountryByIdRandom")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountryByIdRandom() {
        employeeService.updateCountryByIdRandom();
    }

    @PatchMapping("/updateCountryById")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountryById(@RequestParam Integer startId, @RequestParam Integer endId,
                                  @RequestParam String country) {
        employeeService.updateCountryById(startId, endId, country);
    }

    @Override
    public EmployeeResponseDto addPassport(Integer employeeId, Integer passportId) {
        log.info("*** method addPassport >>>  START ");
        Employee employee = employeeService.addPassport(employeeId, passportId);
        log.info("*** method addPassport >>>  FINISH ");
        return employeeMapper.employeeToEmployeeReadDto(employee);
    }

    @Override
    public EmployeeResponseDto addPassportSafely(Integer employeeId, Integer passportId) {
        log.info("*** method addPassportSafely >>>  START ");
        Employee employee = employeeService.addPassport(employeeId, passportId);
        log.info("*** method addPassportSafely >>>  FINISH ");
        return employeeMapper.employeeToEmployeeReadDto(employee);
    }

    @Override
    public EmployeeResponseDto addPassport(Integer employeeId) {
        log.info("*** method addPassport >>>  START ");
        Employee employee = employeeService.addPassport(employeeId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.employeeToEmployeeReadDto(employee);
        log.info("*** method addPassport >>>  FINISH ");
        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto addWorkplace(Integer employeeId, Integer workplaceId) {
        log.info("*** method addWorkplace >>>  START ");
        Employee employee = employeeService.addWorkplace(employeeId,workplaceId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.employeeToEmployeeReadDto(employee);
        log.info("*** method addWorkplace >>>  FINISH ");
        return employeeResponseDto;
    }

    @Override
    public void saveThroughEntityManager(Employee employee) {
        employeeService.saveThroughEntityManager(employee);
    }

    @Override
    public void detachThroughEntityManager(Integer id) {
        employeeService.detachThroughEntityManager(id);
    }

    @Override
    public void removeThroughEntityManager(Integer id) {
        employeeService.removeThroughEntityManager(id);
    }
}
