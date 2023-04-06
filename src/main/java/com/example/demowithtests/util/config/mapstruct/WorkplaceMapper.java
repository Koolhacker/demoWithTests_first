package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {

    Workplace WorkplaceRequestDtoToWorkplace(WorkplaceRequestDto workplaceRequestDto);

    Workplace WorkplaceResponseDtoToWorkplace(WorkplaceResponseDto workplaceResponseDto);

    WorkplaceRequestDto workplaceToWorkplaceRequestDto(Workplace workplace) ;

    WorkplaceResponseDto workplaceToWorkplaceResponseDto(Workplace workplace);
}
