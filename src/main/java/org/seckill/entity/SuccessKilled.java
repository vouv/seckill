package org.seckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SuccessKilled {
    private long seckillId;

    private long userPhone;

    private short state;

    private Date createTime;

    /**
     * 变通
     * 多对一
     */
    private Seckill seckill;


    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }

}
