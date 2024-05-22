package com.yly.jbt;

import lombok.Data;

@Data
public class AgreeBo {
    String signingApplyNo;
    Integer applyStatus=2;
    int[] tagNos = new int[0];
}
