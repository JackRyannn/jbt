package com.yly.jbt;

import com.dtflys.forest.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public interface NetService {

    @MyHeaders
    @Post("https://api.yzyhealth.com/urApi/patient/add")
    String sendMsg(@JSONBody String body);

    @MyHeaders
    @Post("https://api.yzyhealth.com/urApi/patient/queryPage")
    String query(@JSONBody String body);

    @MyHeaders
    @Post("https://api.yzyhealth.com/urApi/patient/delete")
    String delete(@JSONBody String body);

    @MyHeaders
    @Post("https://api.yzyhealth.com/urApi/patient/updateDefaultStatus")
    String setDefault(@JSONBody String body);

    @MyHeaders
    @Post("https://api.yzyhealth.com/urApi/doctorTeamPatientSigning/apply")
    String apply(@JSONBody String body);


    @AppHeaders
    @Post("https://api.yzyhealth.com/drApi/workHome/queryNewPatient")
    String receiveQuery(@JSONBody String body);

    @AppHeaders
    @Post("https://api.yzyhealth.com/drApi/doctorTeamPatientSigning/agree")
    String agree(@JSONBody String body);

    @AppHeaders
    @Post("https://api.yzyhealth.com/drApi/doctorTeamPatientSigning/update")
    String update(@JSONBody String body);

}
