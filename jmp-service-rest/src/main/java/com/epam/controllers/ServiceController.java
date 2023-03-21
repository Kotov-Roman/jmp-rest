package com.epam.controllers;

import com.epam.SubscriptionRequestDto;
import com.epam.SubscriptionResponseDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return null;
    }

    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return null;
    }

    public void deleteSubscription(Long id) {
    }

    public SubscriptionResponseDto getSubscription(Long id) {
        return null;
    }

    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return null;
    }
}
