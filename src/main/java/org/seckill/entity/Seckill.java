package org.seckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Seckill {

    private long id;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;


    @Override
    public String toString() {
        return "Seckill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
