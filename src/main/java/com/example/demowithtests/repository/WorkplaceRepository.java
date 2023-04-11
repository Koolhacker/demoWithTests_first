package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {

    @Query(value = "select count (*) from users_workplaces where workplaces_id = ?1", nativeQuery = true)
    Integer getUsersAtWorkplace(Integer workplaceId);
}
