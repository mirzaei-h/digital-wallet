package com.digipay.wallet.models.dto;

import com.digipay.wallet.base.BaseDto;
import com.digipay.wallet.models.enums.WalletStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletDtoWithoutBalance extends BaseDto {

    private Long walletId;
    @NotBlank(message = "Name can't be blank")
    private String walletName;
    private WalletStatus status;

}
