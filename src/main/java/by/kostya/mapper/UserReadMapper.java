package by.kostya.mapper;

import by.kostya.dto.UserReadDto;
import by.kostya.entity.User;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User,UserReadDto> {
    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return new UserReadDto(object.getId(),
                object.getPersonInfo(),
                object.getUsername(),
                object.getRole(),
                companyReadMapper.mapFrom(object.getCompany()));
    }
}
