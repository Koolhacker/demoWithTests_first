package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.config.mapstruct.WorkplaceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workplace", description = "Workplace API")
public class WorkplaceControllerBean implements WorkplaceController {

    private final WorkplaceService workplaceService;
    private final WorkplaceMapper workplaceMapper;

    @Override
    public WorkplaceResponseDto saveWorkplace(WorkplaceRequestDto workplaceRequestDto) {
        Workplace workplace = workplaceMapper.WorkplaceRequestDtoToWorkplace(workplaceRequestDto);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.workplaceToWorkplaceResponseDto(workplaceService.create(workplace));
        return workplaceResponseDto;
    }

    @Override
    public List<WorkplaceResponseDto> getAllWorkplaces() {
        List <Workplace> workplaceList = workplaceService.getAll();
        List <WorkplaceResponseDto> workplaceResponseDtoList = new ArrayList<>();
        for(Workplace workplace : workplaceList){
            workplaceResponseDtoList.add(workplaceMapper.workplaceToWorkplaceResponseDto(workplace));
        }
        return workplaceResponseDtoList;
    }

    @Override
    public WorkplaceResponseDto getWorkplaceById(Integer id) {
        Workplace workplace = workplaceService.getById(id);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.workplaceToWorkplaceResponseDto(workplace);
        return workplaceResponseDto;
    }
}
