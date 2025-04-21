package com.fef.dell.useCases;

import com.fef.dell.InterfaceAdapter.dto.RegisterStartupDto;
import com.fef.dell.domain.entity.StartupEntity;
import com.fef.dell.domain.repository.StartupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterStartupUseCase {
    StartupRepository startupRepository;
    public RegisterStartupUseCase(StartupRepository startupRepository){
        this.startupRepository = startupRepository;
    }

    public List<StartupEntity> register(RegisterStartupDto startupDto){
        if(startupRepository.size() >= 8){
            throw new RuntimeException("There's already 8 Startups registered");
        }

        StartupEntity startup = new StartupEntity(startupDto.getName(),startupDto.getSlogan(),startupDto.getFoundationDate());
        startupRepository.add(startup.getId(), startup);
        return startupRepository.getAll();
    }

    public void remove(int id){startupRepository.remove(id);}
}
