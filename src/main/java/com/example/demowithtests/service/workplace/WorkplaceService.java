package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Workplace;

import java.util.List;

public interface WorkplaceService {
    Workplace create(Workplace workplace) ;

    List<Workplace> getAll();

    Workplace getById(Integer id);
}
