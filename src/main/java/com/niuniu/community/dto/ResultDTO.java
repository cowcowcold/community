package com.niuniu.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private String message;
    private Integer code;
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

}
