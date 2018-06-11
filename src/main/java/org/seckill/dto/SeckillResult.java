package org.seckill.dto;

import lombok.Data;

@Data
public class SeckillResult <T> {

    private boolean success;
    private T data;
    private String error;


    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean succes, String error) {
        this.success = succes;
        this.error = error;
    }


}
