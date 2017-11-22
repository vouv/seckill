CREATE DATABASE seckill;

use seckill;

CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品id' ,
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number INT COMMENT '库存数量',
  start_time TIMESTAMP COMMENT '秒杀开始时间',
  end_time TIMESTAMP NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT ='库存表';

#初始化数据

INSERT INTO seckill(name, number, start_time, end_time)
    VALUES
      ('1000元秒杀iphone6',100,'2017-07-27 00:00:00','2017-07-28 00:00:00'),
      ('1000元秒杀iphone6',100,'2017-07-27 00:00:00','2017-07-28 00:00:00'),
      ('1000元秒杀iphone6',100,'2017-07-27 00:00:00','2017-07-28 00:00:00'),
      ('1000元秒杀iphone6',100,'2017-07-27 00:00:00','2017-07-28 00:00:00');

CREATE TABLE success_killed (
  seckill_id BIGINT NOT NULL COMMENT '商品id',
  user_phone BIGINT NOT NULL COMMENT 'phone',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '',
  create_time TIMESTAMP NOT NULL ,
  PRIMARY KEY (seckill_id,user_phone),
  KEY idx_create_time(create_time)
)DEFAULT CHARSET=utf8 COMMENT ='秒杀成功表';





