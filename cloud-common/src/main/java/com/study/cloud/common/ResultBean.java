package com.study.cloud.common;

import com.study.cloud.common.eume.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultBean implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public static ResultBean success(){
        return new ResultBean(10000, "success",null);
    }
    public ResultBean message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultBean data(Object data){
        this.setData(data);
        return this;
    }

    public static ResultBean error(){
        return new ResultBean(-1, null,null);
    }
    public static ResultBean error(Integer code){
        return new ResultBean(code, null, null);
    }
    public static ResultBean error(Integer code,String message){
        return new ResultBean(code, message, null);
    }

    public static ResultBean error(ErrorCode errorCode){
        return new ResultBean(errorCode.getCode(), errorCode.getMessage(), null);
    }


}
