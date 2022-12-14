package com.digipay.wallet.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto{

    private Integer version;
//    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:ss a")
    private Date createDate;
//    @JsonFormat(pattern="yyyy-MMM-dd hh:mm:ss a")
    private Date updateDate;

}
