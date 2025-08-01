package by.kostya.mapper;

import by.kostya.dao.CompanyRepository;
import by.kostya.dto.UserUpdateDto;
import by.kostya.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUpdateMapper implements Mapper<UserUpdateDto, User> {
    private final CompanyRepository companyRepository;
    @Override
    public User mapFrom(UserUpdateDto object) {
        return User.builder()
                .personInfo(object.personInfo())
                .username(object.userName())
                .role(object.role())
                .company(companyRepository.findById(object.companyId()).orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
