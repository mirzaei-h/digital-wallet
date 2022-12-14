package com.digipay.wallet.controllers;

import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.mappers.WalletMapper;
import com.digipay.wallet.models.dto.WalletDto;
import com.digipay.wallet.models.dto.WalletDtoWithoutBalance;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final WalletMapper walletMapper;


    @PostMapping
    public void save(@Valid @RequestBody WalletDto walletDto) {
        walletService.save(walletMapper.dtoToEntityConvertor(walletDto));
    }
    @PutMapping
    public void updateWallet(@Valid @RequestBody WalletDtoWithoutBalance walletDto) {
        walletService.update(walletMapper.convertDtoWithoutBalanceToEntity(walletDto));
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> getWallets() {
        return ResponseEntity.ok(walletMapper.entityToDtoConvertor(walletService.findByUser()));
    }

    @PostMapping("/findByExample")
    public ResponseEntity<List<WalletDto>> findByExample(@Valid @RequestBody WalletDto dto) {
        List<WalletEntity> walletEntity = walletService.findByExample(walletMapper.dtoToEntityConvertor(dto));
        return ResponseEntity.ok(walletMapper.entityToDtoConvertor(walletEntity));
    }

}
