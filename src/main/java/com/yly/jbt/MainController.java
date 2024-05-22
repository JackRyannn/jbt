package com.yly.jbt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Resource
    NetService netService;

    @PostConstruct
    public void main() {
//      addAndApply();
        receive();
    }


    public void addAndApply() {
        Gson gson = new Gson();
        String filePath = "/Users/m/Downloads/jbt/src/main/java/com/yly/jbt/test.txt";
        List<PersonBo> bos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                PersonBo bo = new PersonBo();
                String[] ss = line.split("\t");
                bo.setName(ss[0]);
                bo.setId(ss[1]);
                bo.setTel(ss[2]);
                if (ss[3].equals("男")) {
                    bo.setSex("1");
                } else {
                    bo.setSex("2");
                }
//                bo.setAddress(ss[4]);
                bo.setBirthday(bo.getId().substring(6, 10) + "-" + bo.getId().substring(10, 12) + "-" + bo.getId().substring(12, 14));
                bo.setTerritory(bo.getId().substring(0, 6));

                System.out.println(bo.name);
                System.out.println(bo.getSex());
                System.out.println(bo.getBirthday());
                System.out.println(bo.getTerritory());

                //构造网络请求
                netService.sendMsg("{\n" +
                        "  \"nation\": \"01\",\n" +
                        "  \"realName\": \"" + bo.getName() + "\",\n" +
                        "  \"avatar\": \"\",\n" +
                        "  \"mobile\": \"" + bo.getTel() + "\",\n" +
                        "  \"certType\": 1,\n" +
                        "  \"idCardNumber\": \"" + bo.getId() + "\",\n" +
                        "  \"birthday\": \"" + bo.getBirthday() + "\",\n" +
                        "  \"sex\": \"" + bo.getSex() + "\",\n" +
                        "  \"relationship\": \"7\",\n" +
                        "  \"address\": \"" + bo.getAddress() + "\",\n" +
                        "  \"isDefault\": 1,\n" +
                        "  \"territory\": \"" + bo.getTerritory() + "\"\n" +
                        "}");
            }
            Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        QueryBo queryBo = new QueryBo();
        queryBo.setPageNo(1);
        queryBo.setPageSize(500);
        String queryStr = gson.toJson(queryBo);
        QueryRespBo respBo = gson.fromJson(netService.query(queryStr), QueryRespBo.class);


        for (QueryRespBo.Data.Record record : respBo.getData().records) {
            if (record.relationship.equals("1")) {
                System.out.println("申请-跳过本人");
                continue;
            }
            ApplyBo applyBo = new ApplyBo();
            applyBo.setDoctorTeamNo("DT2301161620168754556");
            applyBo.setPatientNo(record.patientNo);
            System.out.println("申请-" + record.realName);
            System.out.println(netService.apply(gson.toJson(applyBo)));
        }


        System.out.println("开始删除");

        for (QueryRespBo.Data.Record record : respBo.getData().records) {
            if (record.relationship.equals("1")) {
                System.out.println("删除-跳过本人");
                DefaultBo defaultBo = new DefaultBo();
                defaultBo.setPatientNo(record.patientNo);
                netService.setDefault(gson.toJson(defaultBo));
                continue;
            }
            DeleteBo deleteBo = new DeleteBo();
            deleteBo.setPatientNo(record.patientNo);
            System.out.println("删除-" + record.realName);
            System.out.println(netService.delete(gson.toJson(deleteBo)));
        }
    }

    public void receive() {
        Gson gson = new Gson();
        ReceiveQueryBo queryBo = new ReceiveQueryBo();
        queryBo.setPageNo(1);
        queryBo.setPageSize(500);
        String queryStr = gson.toJson(queryBo);
        String retStr = netService.receiveQuery(queryStr);
        System.out.println(retStr);
        ReceiveQueryRespBo respBo = gson.fromJson(retStr, ReceiveQueryRespBo.class);

        for (ReceiveQueryRespBo.Data.Record record : respBo.getData().records) {
            System.out.println("接收：" + record.patientName);
            AgreeBo agreeBo = new AgreeBo();
            agreeBo.setSigningApplyNo(record.signingApplyNo);
            System.out.println(netService.agree(gson.toJson(agreeBo)));
            UpdateBo updateBo = new UpdateBo();
            updateBo.setPatientNo(record.patientNo);
            System.out.println(netService.update(gson.toJson(updateBo)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
