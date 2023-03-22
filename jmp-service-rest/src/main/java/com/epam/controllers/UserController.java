package com.epam.controllers;

import com.epam.SubscriptionResponseDto;
import com.epam.User;
import com.epam.UserRequestDto;
import com.epam.UserResponseDto;
import com.epam.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ConversionService conversionService;
    private final UserService userService;

    @Autowired
    public UserController(ConversionService conversionService, UserService userService) {
        this.conversionService = conversionService;
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a user",
            tags = {"users"},
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Some resource is not found")}
    )
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        User user = conversionService.convert(userRequestDto, User.class);
        UserResponseDto dto = conversionService.convert(userService.create(user), UserResponseDto.class);
        return ResponseEntity.created(URI.create("/users/" + dto.getId()))
                .body(dto);
    }

    @PutMapping
    @Operation(summary = "Update a user",
            tags = {"users"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User is not found")}
    )
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        UserResponseDto dto = conversionService.convert(userService.update(user), UserResponseDto.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user",
            tags = {"users"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User is not found")}
    )
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User is deleted");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id",
            tags = {"users"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User is not found")}
    )
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        User user = userService.get(id);
        UserResponseDto dto = conversionService.convert(userService.create(user), UserResponseDto.class);

        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find all users",
            tags = {"users"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
                    @ApiResponse(responseCode = "204", description = "Users were not found",
                            content = @Content(schema = @Schema()))})
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAll()
                .stream()
                .map(user -> conversionService.convert(user, UserResponseDto.class))
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }
}

