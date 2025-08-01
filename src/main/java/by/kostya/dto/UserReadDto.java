package by.kostya.dto;

import by.kostya.entity.PersonInfo;
import by.kostya.entity.Role;
public record UserReadDto (Long id,
                           PersonInfo personInfo,
                           String userName,
                           Role role,
                           CompanyReadDto companyReadDto){
}
