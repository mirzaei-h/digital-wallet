package com.digipay.wallet.models.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoneyTransfer {

    private Double amount;
    private String receiverUsername;

    @NonNull
    @NotBlank
    private String sanderWallet;

    @NonNull
    @NotBlank
    private String receiverWallet;
}
