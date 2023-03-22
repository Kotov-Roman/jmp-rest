package com.epam;

import com.epam.exceptions.ResourceNotFoundException;
import com.epam.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription get(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Subscription.class.getSimpleName(), id));
    }

    @Override
    public Subscription create(Subscription subscription) {
        subscription.setStartDate(LocalDate.now());
        subscription.setUser(userService.get(subscription.getUserId()));

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(Subscription subscription) {
        Subscription subscriptionFromDb = subscriptionRepository.findById(subscription.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(Subscription.class.getSimpleName(), subscription.getId()));

        subscriptionFromDb.setUser(userService.get(subscription.getUserId()));
//      it's not clear if start date should be updated, but sounds like not
//      case like "user gifted his subscription to another user"

        return subscriptionRepository.save(subscriptionFromDb);
    }

    @Override
    public void delete(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new ResourceNotFoundException(Subscription.class.getSimpleName(), id);
        }
        subscriptionRepository.deleteById(id);
    }
}
