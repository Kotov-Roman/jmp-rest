package com.epam.controllers;

import com.epam.Subscription;
import com.epam.SubscriptionRequestDto;
import com.epam.SubscriptionResponseDto;
import com.epam.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
public class ServiceController {
    private final ConversionService conversionService;
    private final SubscriptionService subscriptionService;

    public ServiceController(ConversionService conversionService, SubscriptionService subscriptionService) {
        this.conversionService = conversionService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @Operation(summary = "Create a subscription",
            tags = {"subscriptions"},
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Some resource is not found")}
    )
    public ResponseEntity<SubscriptionResponseDto> createSubscription(
            @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = conversionService.convert(subscriptionRequestDto, Subscription.class);
        SubscriptionResponseDto dto = conversionService.convert(
                subscriptionService.create(subscription), SubscriptionResponseDto.class);

        return ResponseEntity.created(URI.create("/subscriptions/" + dto.getId()))
                .body(dto);
    }

    @PutMapping
    @Operation(summary = "Update an existing subscription",
            tags = {"subscriptions"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Subscription or resource is not found")}
    )
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(
            @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = conversionService.convert(subscriptionRequestDto, Subscription.class);
        SubscriptionResponseDto dto = conversionService.convert(
                subscriptionService.update(subscription), SubscriptionResponseDto.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing subscription",
            tags = {"subscriptions"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "404", description = "Subscription is not found")}
    )
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        subscriptionService.delete(id);
        return ResponseEntity.ok("Subscription is deleted");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a subscription by id",
            tags = {"subscriptions"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Subscription is not found")}
    )
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
        Subscription subscription = subscriptionService.get(id);
        SubscriptionResponseDto dto = conversionService.convert(
                subscriptionService.create(subscription), SubscriptionResponseDto.class);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Find all subscriptions",
            tags = {"subscriptions"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionResponseDto.class)))),
                    @ApiResponse(responseCode = "204", description = "Subscriptions were not found",
                            content = @Content(schema = @Schema()))}
    )
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        List<SubscriptionResponseDto> subscriptions = subscriptionService.getAll()
                .stream()
                .map(subscription -> conversionService.convert(subscription, SubscriptionResponseDto.class))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(subscriptions);
    }
}
