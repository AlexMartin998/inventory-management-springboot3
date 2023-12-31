package com.alex.inventorymanagement.addresses.controller;

import com.alex.inventorymanagement.addresses.dto.AddressRequestDto;
import com.alex.inventorymanagement.addresses.dto.AddressResponseDto;
import com.alex.inventorymanagement.addresses.service.AddressService;
import com.alex.inventorymanagement.users.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping
    public ResponseEntity<AddressResponseDto> create(@Validated @RequestBody AddressRequestDto addressRequestDto, Authentication authentication) {
        String authUserEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        return new ResponseEntity<>(addressService.create(addressRequestDto, authUserEmail), HttpStatus.CREATED);
    }

    @GetMapping("/auth-user")
    public ResponseEntity<?> findAllByAuthUser(Authentication authentication) {
        String authUserEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        return ResponseEntity.ok(addressService.findAllByAuthUser(authUserEmail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findOne(id));
    }

}
