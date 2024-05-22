package com.yly.jbt;

import lombok.Data;

@Data
public class ReceiveQueryBo {
    Integer pageNo;
    Integer pageSize;
    Integer applyStatus = 1;
}
