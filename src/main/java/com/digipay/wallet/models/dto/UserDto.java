package com.digipay.wallet.models.dto;

import com.digipay.wallet.base.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private Long userId;
    private String username;
    @NonNull
    @NotBlank(message = "New password is mandatory")
    private String password;
}
