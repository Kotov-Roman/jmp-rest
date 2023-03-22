package com.epam.converters;

import com.epam.Subscription;
import com.epam.SubscriptionRequestDto;
import com.epam.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    @Override
    public Subscription convert(SubscriptionRequestDto dto) {
        User user = User.builder()
                .id(dto.getUserId())
                .build();

        return Subscription.builder()
                .id(dto.getId())
                .user(user)
                .build();
    }
}
