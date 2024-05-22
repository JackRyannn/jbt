package com.yly.jbt;

import lombok.Data;

@Data
public class ReceiveQueryRespBo {
    Data data;
    class Data{
        Record[] records;
        class Record{
            String patientNo;
            String patientName;
            String signingApplyNo;
            String applyStatus;
        }
    }
}
