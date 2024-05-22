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
        "nonce: ${app.nonce}",
        "ticket: ${app.ticket}",
        "t: ${t}",
        "User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 Html5Plus/1.0 (Immersed/47) uni-app",
        "sign: ${app.sign}",
        "Content-Type: application/json",
        "Accept: */*",
        "Accept-Encoding: gzip, deflate, br",
        "Accept-Language: zh-CN,zh;q=0.9"})
public @interface AppHeaders {

}
