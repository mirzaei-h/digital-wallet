package com.digipay.wallet.controllers;

import com.digipay.wallet.mappers.TransactionMapper;
import com.digipay.wallet.models.dto.MoneyTransfer;
import com.digipay.wallet.models.dto.TransactionDto;
import com.digipay.wallet.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping("/chargeWallet")
    public void chargeWallet(@Valid @RequestBody TransactionDto transactionDto) {
        transactionService.chargeWallet(transactionMapper.dtoToEntityConvertor(transactionDto));
    }

    @PostMapping("/withdrawWallet")
    public void withdrawWallet (@Valid @RequestBody TransactionDto transactionDto){
        transactionService.withdraw(transactionMapper.dtoToEntityConvertor(transactionDto));
    }

    @PostMapping("/moneyTransfer")
    public void moneyTransfer (@Valid @RequestBody MoneyTransfer moneyTransfer){
        transactionService.moneyTransfer(moneyTransfer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionDto>> findTransactionsByWallet(@Valid @PathVariable Long id){
        return ResponseEntity.ok(transactionMapper.entityToDtoConvertor(transactionService.findByWallet(id)));
    }


}
