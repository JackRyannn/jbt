package com.yly.jbt;

import lombok.Data;

@Data
public class QueryRespBo {
    Data data;
    class Data{
        Record[] records;
        class Record{
            String patientNo;
            String realName;
            String mobile;
            String relationship;
        }
    }
}
