package com.apply.result;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResult  implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private String token; //数据

//    public static  Result success() {
//        Result result = new Result<T>();
//        result.code = 1;
//        return result;
//    }

    public static LoginResult success(String token) {
        LoginResult result = new LoginResult();
        result.token = token;
        result.code = 1;
        return result;
    }

//    public static <T> Result<T> error(String msg) {
//        Result result = new Result();
//        result.msg = msg;
//        result.code = 0;
//        return result;
//    }

}