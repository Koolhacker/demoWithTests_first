package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);


    @Query(value = "select * from users", nativeQuery = true)  // 71 к employee делает за 19/20 сек
    List<Employee> findAll();

    List<Employee> findEmployeeByIsDeletedNull();
    @Query(value = "select * from users", nativeQuery = true)
    List<Employee> findEmployeeByAgeForSet();

    @Query(value = "select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(String country);

//    @Query(value = "select * from users join addresses on users.id = addresses.employee_id where city = city", nativeQuery = true)

    @Query(value = "select e from Employee e join e.addresses a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

//   @Query(value = "select * from users join addresses on users.id = addresses.employee_id where addresses.country = country and addresses.city = city", nativeQuery = true)
   @Query(value = "select * from users join addresses on users.id = addresses.employee_id where users.country = ?1 and addresses.city = ?2", nativeQuery = true)

   List<Employee> findEmployeeByCountryAndCity(String country, String city);

//    то что делали на уроке
    @Query(value = "select * from users where id between ?1 and ?2", nativeQuery = true)
    List<Employee> findEmployeeById(Integer startId, Integer endId);  //  Patch 50 к   1 раз 14 сек  последующие 9 сек
                                                                      //   Put 50 к    1 раз 14 сек  последующие 8 сек
                                                                      //   Get 50 к    1 раз 14 сек  последующие 8 сек
       // hw 24
    @Query(value = "select * from users where users.country <> ?3 and id between ?1 and ?2", nativeQuery = true)
    List<Employee> findEmployeeByIdForUpdateCountry(Integer startId, Integer endId, String country);
                                                                     //   Patch 50 к   1 раз 13 сек  последующие 0.1 сек


}
