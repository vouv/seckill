package org.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SeckillApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeckillApplication.class, args);
	}
}
