package com.epam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
}
