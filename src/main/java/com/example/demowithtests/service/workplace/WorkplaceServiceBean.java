package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.Workplace;
import com.example.demowithtests.repository.WorkplaceRepository;
import com.example.demowithtests.util.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class WorkplaceServiceBean implements WorkplaceService {

    private WorkplaceRepository workplaceRepository;

    @Override
    public Workplace create(Workplace workplace) {
        log.debug("*** method create >>>  START : workplace = {}" , workplace);
        Workplace createWorkplace = workplaceRepository.save(workplace);
        log.debug("*** method create >>>  FINISH ");
        return createWorkplace;
    }

    @Override
    public List<Workplace> getAll() {
        log.debug("*** method getAll >>>  START ");
        List<Workplace> getAllWorkplace = workplaceRepository.findAll();
        log.debug("*** method getAll >>>  FINISH ");
        return getAllWorkplace;
    }

    @Override
    public Workplace getById(Integer id) {
        log.debug("*** method getById >>>  START : id = {}" , id);
        Workplace getByIdWorkplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        log.debug("*** method getById >>>  FINISH ");
        return getByIdWorkplace;
    }
}
