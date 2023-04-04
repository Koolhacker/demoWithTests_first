package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.IdNotFoundException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.WrongTypeIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(Passport passport) {
        log.debug("*** method create >>>  START ");
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAll() {
        log.debug("*** method getAll >>>  START ");
        return passportRepository.findAll();
    }

    @Override
    public Passport getById(String id) {
        Passport passport;
        log.debug("*** method getById >>>  START ");
        try {
            passport = passportRepository.findById(Integer.valueOf(id))
                    .orElseThrow(ResourceNotFoundException::new);
        } catch (IllegalArgumentException ex) {
            throw new WrongTypeIdException();
        }
        log.debug("*** method getById >>>  FINISH ");
        return passport;
    }

    @Override
    public Passport updateById(String id, Passport passport) {
        log.debug("*** method updateById >>>  START ");
        try {
            return passportRepository.findById(Integer.valueOf(id)).map(entity -> {
                entity.setFirstName(passport.getFirstName());
                entity.setSecondName(passport.getSecondName());
                return passportRepository.save(entity);
            }).orElseThrow(IdNotFoundException::new);
        } catch (NumberFormatException exception) {
            log.debug("*** method updateById  >>>  CATCH WRONG TYPE ID EXCEPTION ");
            throw new WrongTypeIdException();
        }
    }
}
