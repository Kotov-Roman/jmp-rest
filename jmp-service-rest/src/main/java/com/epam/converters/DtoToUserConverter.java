package com.epam.converters;

import com.epam.User;
import com.epam.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto dto) {
        LocalDate birthday = LocalDate.parse(dto.getBirthday(), DateTimeFormatter.ISO_DATE);
        return User.builder()
                .id(dto.getId())
                .birthday(birthday)
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }
}
