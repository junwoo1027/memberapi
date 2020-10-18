package com.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateMemberDto {

    @NotEmpty
    private String username;
}
