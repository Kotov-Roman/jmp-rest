package com.epam;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAll();

    Subscription get(Long id);

    Subscription create(SubscriptionRequestDto subscriptionRequestDto);

    Subscription update(SubscriptionRequestDto subscriptionRequestDto);

    void delete(Long id);
}
