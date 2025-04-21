package com.fef.dell.InterfaceAdapter.dto;

import com.fef.dell.domain.entity.StartupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
@AllArgsConstructor()
public class RegisterStartupDto {
    private String name;
    private String slogan;
    private String foundationDate;
    private int id;

}
