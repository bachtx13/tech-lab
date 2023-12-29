package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.User;
import com.sem4project.sem4.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "User API")
@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
@CrossOrigin
public class UserController extends BaseController<User, UserDto> {
    private final UserService userService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getInfo() {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Get info success")
                                .data(userService.getUserInfo())
                                .build()
                );
    }
    @RequestMapping(value = "/updateUserInfo/{id}", method = RequestMethod.PUT)
    @PreAuthorize("authentication.principal.id == #id or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateUserInfo(@PathVariable UUID id, @RequestBody @Valid UserInfoDto userInfoDto) {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Update info success")
                                .data(userService.updateUserInfo(id, userInfoDto))
                                .build()
                );
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                     @RequestParam(defaultValue = "1") Integer pageNumber,
                                                     @RequestParam(required = false) Integer pageSize,
                                                     @RequestParam(required = false) String sortBy,
                                                     @RequestParam(required = false) String sortType
    ) {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Get all user success")
                                .data(userService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }

    @Override
    protected ResponseEntity<ResponseObject> getById(UUID id) {
        return null;
    }

    @Override
    protected ResponseEntity<ResponseObject> create(UserDto userDto) {
        return null;
    }

    @Override
    protected ResponseEntity<ResponseObject> update(UUID id, UserDto userDto) {
        return null;
    }

    @Override
    protected ResponseEntity<ResponseObject> updateDisable(UUID id) {
        return null;
    }

    @Override
    protected ResponseEntity<ResponseObject> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }
}
