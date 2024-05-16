package com.yly.jbt;

import com.dtflys.forest.annotation.Headers;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})

@Headers({"Host: api.yzyhealth.com",
        "Connection: keep-alive",
//            "Content-Length: 223",
        "xweb_xhr: 1",
        "nonce: ${nonce}",
        "ticket: ${ticket}",
        "t: ${t}",
        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/6.8.0(0x16080000) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.4(0x13080414)XWEB/31011",
        "sign: ${sign}",
        "Content-Type: application/json",
        "Accept: */*",
        "Sec-Fetch-Site: cross-site",
        "Sec-Fetch-Mode: cors",
        "Sec-Fetch-Dest: empty",
        "Referer: https://servicewechat.com/wx121aee2d75720db5/84/page-frame.html",
        "Accept-Encoding: gzip, deflate, br",
        "Accept-Language: zh-CN,zh;q=0.9"})
public @interface MyHeaders {

}
