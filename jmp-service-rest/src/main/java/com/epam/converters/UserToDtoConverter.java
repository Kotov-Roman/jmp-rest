package com.epam.converters;

import com.epam.User;
import com.epam.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserToDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User user) {
        String birthday = user.getBirthday().format(DateTimeFormatter.ISO_DATE);
        return UserResponseDto.builder()
                .id(user.getId())
                .birthday(birthday)
                .surname(user.getSurname())
                .name(user.getName())
                .build();
    }
}
