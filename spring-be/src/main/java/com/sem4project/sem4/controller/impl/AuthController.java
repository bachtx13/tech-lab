package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.dto.dtomodel.TokenDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.service.UserService;
import com.sem4project.sem4.util.JwtUtil;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "Auth", description = "Authentication API")
@RestController
@RequestMapping(value = "/auth")
@PermitAll
@AllArgsConstructor
@CrossOrigin
public class AuthController{
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json")})
            )
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> login(@RequestBody @Valid LoginRequest loginRequest) {
        userService.login(loginRequest);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        Date expiresIn = jwtUtil.getExpirationDateFromToken(token);
        List<RoleDto> roles = userDetails.getAuthorities()
                .stream().map(e -> (RoleDto)RoleDto.builder().name(e.getAuthority()).build()).toList();
        TokenDto tokenDto = TokenDto.builder()
                .jwtToken(token)
                .roles(roles)
                .expiresIn(expiresIn)
                .build();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization",
                "Bearer " + token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(
                        ResponseObject.builder()
                                .message("Login success")
                                .data(tokenDto)
                                .build()
                );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Register success")
                                .data(true)
                                .build()
                );
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> logout() {
        userService.logout();
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Logout success")
                                .data(true)
                                .build()
                );
    }
//    @RequestMapping(value = "/getRole", method = RequestMethod.POST)
//    public ResponseEntity<ResponseObject> getRole(){
//
//    }
}
