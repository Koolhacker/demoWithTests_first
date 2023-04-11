package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Workplace;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.repository.WorkplaceRepository;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@AllArgsConstructor
@Slf4j
@Service

public class EmployeeServiceBean implements EmployeeService {
    private final WorkplaceRepository workplaceRepository;

    private final EmployeeRepository employeeRepository;
    private final PassportRepository passportRepository;

    private final WorkplaceService workplaceService;

    private final PassportService passportService;


//    private static final Logger log = Logger.getLogger(ServiceBean.class.getName());

    @Override
    public Employee create(Employee employee) {
//        if (employee.getName() == null || employee.getEmail() == null || employee.getCountry() == null) {
//            log.info("Not enough data. HttpStatus - " + HttpStatus.BAD_REQUEST);
//
////            log.info("getAllEmployeeCountry() - end: countries = {}", countries);
//            throw new DataAbsentException();
//        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(String id) {
        try {
            Integer valueOfId = Integer.valueOf(id);
            var employee = employeeRepository.findById(valueOfId)
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
        return employeeRepository.findById(id).map(entity -> {
            entity.setName(employee.getName());
            entity.setEmail(employee.getEmail());
            entity.setCountry(employee.getCountry());
            return employeeRepository.save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        var employee = employeeRepository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        employee.setIsDeleted(true);
//        repository.delete(employee);
        employeeRepository.save(employee);
    }

    @Override
    public void removeAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public List<Employee> processorIsDeletedReplaceNull() {
        log.debug("replaceNull -> start");
        List<Employee> replaceNull = employeeRepository.findEmployeeByIsDeletedNull();
        log.debug("replaceNull before replace= {} ", replaceNull);
        for (Employee aliasEmp : replaceNull) {
            aliasEmp.setIsDeleted(Boolean.FALSE);
        }
        log.debug("replaceNull after replace= {} ", replaceNull);
        log.debug("replaceNull -> end");
        return employeeRepository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> processorAgeSet() { // if age is absent - set to all "25"
        log.info("processorSetAge START");
        List<Employee> changeAge = employeeRepository.findEmployeeByAgeForSet();
        for (Employee aliasEmp : changeAge) {
            if (aliasEmp.getAge() == null) {
                aliasEmp.setAge(25);
            }
        }
        return employeeRepository.saveAll(changeAge);
    }

    @Override
    public void fillData() {
        for (int i = 0; i <= 10; i++) {
            Employee employee = new Employee("Hillel", "Ukraine", Boolean.FALSE);
            employeeRepository.save(employee);
        }
    }

    @Override
    public void fillDataHW24(int size, Employee employee) {
        for (int i = 0; i <= (size - 1); i++) {
            Employee tempEmployee = new Employee();
            tempEmployee.setName(employee.getName());
            tempEmployee.setEmail(employee.getEmail());
            tempEmployee.setCountry(employee.getCountry());
            employeeRepository.save(tempEmployee);
        }
    }

    @Override
    public void updateDateById(Integer startId, Integer endId, String country) {
        List<Employee> tempList = employeeRepository.findEmployeeById(startId, endId);
        for (Employee tmp : tempList) {
            tmp.setCountry(country);
        }
        employeeRepository.saveAll(tempList);
    }

    @Override
    public void updateCountryById(Integer startId, Integer endId, String country) {
        List<Employee> tempList = employeeRepository.findEmployeeByIdForUpdateCountry(startId, endId, country);
        for (Employee tmp : tempList) {
            tmp.setCountry(country);
        }
        employeeRepository.saveAll(tempList);
    }


    // hw 24 task 1
    @Override
    public void updateCountryByIdRandom() {
        List<Employee> tempList = employeeRepository.findAll();
        for (Employee empTemp : tempList) {
            empTemp.setCountry(countryRandomGenerator());
        }
        employeeRepository.saveAll(tempList);
    }

    private static String countryRandomGenerator() {
        List<String> countries = new ArrayList<>();
        countries.add("Ukraine");
        countries.add("USA");
        countries.add("Great Britain");
        countries.add("France");
        countries.add("Germany");
        countries.add("Italy");
        countries.add("Denmark");
        countries.add("Poland");
        countries.add("Zimbabwe");
        countries.add("Qatar");
        Random rand = new Random();
        int r = rand.nextInt(10);
        return countries.get(r);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = employeeRepository.findEmployeeByCountry(country);
        log.info(" SERVICE " + employees.toString());
        mailSender(checkIsFree(employees), text);
        return employees;
    }

    private static List<String> checkIsFree(List<Employee> employees) {
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
        List<Employee> employees = employeeRepository.findEmployeeByAddresses(city);
        log.info(employees.toString());
        mailSender(checkIsFree(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCountryAndCity(String country, String city, String text) {
        List<Employee> employees = employeeRepository.findEmployeeByCountryAndCity(country, city);
        log.info("  SERVICE ---it`s work---" + employees);
        mailSender(checkIsFree(employees), text);
        return employees;
    }

    //hw 27
    @Override
    public List<Employee> findEmployeeWhoChangedCountry(String country, String text) {
        List<Employee> employeeList = employeeRepository.findEmployeeWhoChangedCountry(country);
        mailSender(checkIsFree(employeeList), text);
        return employeeList;
    }

    @Override
    public Employee addPassport(Integer employeeId, Integer passportId) {
        log.debug("*** SERVICE method addPassport >>>  START ");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(WrongTypeIdException::new);
        Passport passport = passportRepository.findById(passportId).orElseThrow(WrongTypeIdException::new);
        employee.setPassport(passport);
        if (passport.getIsFree() == Boolean.FALSE) {
            log.debug("*** SERVICE method addPassport  >>> EXCEPTION ");
            throw new WrongDataException();
        }
        log.debug("*** SERVICE method addPassport BEFORE SAVING >>>  FINISH ");
        employeeRepository.save(employee);
        log.debug("*** SERVICE method addPassport AFTER SAVING >>>  FINISH ");
        return employee;
    }

    @Override
    public Employee addPassport(Integer employeeId) {
        log.debug("*** SERVICE method addPassport >>>  START ");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(WrongTypeIdException::new);
        checkIsFree();
        employee.setPassport(passportRepository
                .findAll().stream()
                .filter(f -> (f.getIsFree()))
                .findFirst().orElseThrow(IdNotFoundException::new));
        employee.getPassport().setIsFree(Boolean.FALSE);
        employeeRepository.save(employee);
        log.debug("*** SERVICE method addPassport >>>  FINISH ");
        return employee;
    }

    private void checkIsFree() {
        log.debug("*** SERVICE method checkIsFree >>>  START ");
        if (passportRepository.getQuantityFreePassports() <= 5) {
            passportService.fillPassports();
        }
        log.debug("*** SERVICE method checkIsFree >>>  FINISH ");
    }

    @Override
    public Employee addWorkplace(Integer employeeId, Integer workplaceId) {
        log.debug("*** SERVICE method addWorkplace >>>  START ");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(IdNotFoundException::new);
        Workplace workplace = workplaceService.getById(workplaceId);
        checkIsfull(workplaceId);
        employee.getWorkplaces().add(workplace);
        catchingException(employee);
        log.debug("*** SERVICE method addWorkplace >>>  FINISH ");
        return employee;
    }

    private void catchingException(Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (RuntimeException e) {
            log.debug("*** SERVICE method addWorkplace >>>  CATCH EXCEPTION ");
            throw new WrongDataException();
        }
    }

    private void checkIsfull(Integer workplaceId) {
        if ((workplaceRepository.getUsersAtWorkplace(workplaceId)) >= (workplaceService.getById(workplaceId).getCapacity()))
            throw new WorkplaceFullException();
    }
}

