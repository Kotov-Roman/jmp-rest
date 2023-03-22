package com.epam;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAll();

    Subscription get(Long id);

    Subscription create(Subscription subscription);

    Subscription update(Subscription subscription);

    void delete(Long id);
}
