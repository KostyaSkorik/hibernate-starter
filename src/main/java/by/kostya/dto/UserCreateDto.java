package by.kostya.dto;

import by.kostya.entity.PersonInfo;
import by.kostya.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonInfo personInfo,
        @NotNull
        String userName,
        Role role,
        Integer companyId) {
}
