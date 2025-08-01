package by.kostya.mapper;

import by.kostya.dao.CompanyRepository;
import by.kostya.dto.UserCreateDto;
import by.kostya.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final CompanyRepository companyRepository;
    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .personInfo(object.personInfo())
                .username(object.userName())
                .role(object.role())
                .company(companyRepository.findById(object.companyId()).orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
