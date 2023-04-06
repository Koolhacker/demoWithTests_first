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
        passport.setIsFree(Boolean.TRUE);
        Passport createPassport = passportRepository.save(passport);
        log.debug("*** method create >>>  FINISH ");
        return createPassport;
    }

    @Override
    public List<Passport> getAll() {
        log.debug("*** method getAll >>>  START ");
        List<Passport> getAllPassport = passportRepository.findAll();
        log.debug("*** method getAll >>>  FINISH ");
        return getAllPassport;
    }

    @Override
    public Passport getById(Integer id) {
        Passport passport;
        log.debug("*** method getById >>>  START ");
        try {
            passport = passportRepository.findById(id)
                    .orElseThrow(ResourceNotFoundException::new);
        } catch (IllegalArgumentException ex) {
            throw new WrongTypeIdException();
        }
        log.debug("*** method getById >>>  FINISH ");
        return passport;
    }

    @Override
    public Passport updateById(Integer id, Passport passport) {
        log.debug("*** method updateById >>>  START ");
        try {
            return passportRepository.findById(id).map(entity -> {
                entity.setFirstName(passport.getFirstName());
                entity.setSecondName(passport.getSecondName());
                return passportRepository.save(entity);
            }).orElseThrow(IdNotFoundException::new);
        } catch (NumberFormatException exception) {
            log.debug("*** method updateById  >>>  CATCH WRONG TYPE ID EXCEPTION ");
            throw new WrongTypeIdException();
        }
    }

    @Override

    public void fillPassports() {
        log.debug("*** SERVICE method fillPassports >>>  START ");
        for(int i=0; i<=4; i++){
            create(new Passport());
        }
        log.debug("*** SERVICE method fillPassports >>>  FINISH ");
    }
}
