package com.epam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String birthday;
}
