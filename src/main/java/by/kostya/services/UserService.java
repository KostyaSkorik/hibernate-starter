package by.kostya.services;

import by.kostya.dao.UserRepository;
import by.kostya.dto.UserCreateDto;
import by.kostya.dto.UserReadDto;
import by.kostya.dto.UserUpdateDto;
import by.kostya.entity.User;
import by.kostya.mapper.UserCreateMapper;
import by.kostya.mapper.UserReadMapper;
import by.kostya.mapper.UserUpdateMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final UserUpdateMapper userUpdateMapper;

    public boolean update(UserUpdateDto userUpdateDto){
        User userEntity = userUpdateMapper.mapFrom(userUpdateDto);
        var maybeUser = userRepository.findById(userEntity.getId());
        maybeUser.ifPresent(userRepository::update);
        if (maybeUser.isPresent()) {
            userRepository.update(userEntity);
            return true;
        }
        return false;
    }

    public Long create(UserCreateDto userDto){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        var validationResult = validator.validate(userDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        User userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id){
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }

}
