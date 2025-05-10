package com.wtwlc.web_experiment.common;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private int code;       // 状态码：0表示成功，非0表示出错
    private T data;         // 返回的实际数据，泛型支持各种类型
    private String message; // 提示信息，比如 "成功" 或 "账号密码错误"
}
