package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
//@NoArgsConstructor
@Builder
//@Data
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String country;
    private Integer age;
    private Boolean isDeleted = Boolean.FALSE;

    public Employee(Employee employee) {
    }

    public Employee(String name, String country, String email) {

    }

    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


//    эксперименты с поиском баги с гетСетерами
//    public Set<Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(Set<Address> addresses) {
//        this.addresses = addresses;
//    }
//
//    public Set<Photo> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(Set<Photo> photos) {
//        this.photos = photos;
//    }
//
//    public Set<FindBagga> getFindBaggas() {
//        return findBaggas;
//    }
//
//    public void setFindBaggas(Set<FindBagga> findBaggas) {
//        this.findBaggas = findBaggas;
//    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Set<FindBagga> findBaggas = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Set<Family> families = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Set<PhotoByHands> photoByHands = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    public Employee(String name, String country, Boolean isDeleted) {
        this.name = name;
        this.country = country;
        this.isDeleted = isDeleted;
    }

    public Employee() {
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
//                ", email='" + email + '\'' +
//                ", isDeleted=" + isDeleted +
                '}';
    }


}
