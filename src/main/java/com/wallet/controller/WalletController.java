package com.wallet.controller;

import com.wallet.dto.WalletDto;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDto>> create (@Valid @RequestBody WalletDto dto, BindingResult result){

        Response<WalletDto> response = new Response<>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Wallet wallet = service.save(convertDtoToEntity(dto));

        response.setData(convertEntityToDto(wallet));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet convertDtoToEntity(WalletDto dto){
        Wallet wallet = new Wallet();
        wallet.setName(dto.getName());
        wallet.setValue(dto.getValue());

        return wallet;
    }

    private WalletDto convertEntityToDto(Wallet wallet){
        WalletDto dto = new WalletDto();
        dto.setName(wallet.getName());
        dto.setValue(wallet.getValue());

        return dto;
    }
}
