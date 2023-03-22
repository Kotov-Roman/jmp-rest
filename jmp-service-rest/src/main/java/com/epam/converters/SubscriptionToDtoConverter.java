package com.epam.converters;

import com.epam.Subscription;
import com.epam.SubscriptionResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class SubscriptionToDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {
    @Override
    public SubscriptionResponseDto convert(Subscription subscription) {
        String startDate = subscription.getStartDate().format(DateTimeFormatter.ISO_DATE);

        return SubscriptionResponseDto.builder()
                .userId(subscription.getUserId())
                .startDate(startDate)
                .id(subscription.getId())
                .build();
    }
}
