package com.devsuperior.dslist;

import com.devsuperior.dslist.taxes.services.delivery.PayDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DslistApplication implements CommandLineRunner {

    @Autowired
	private PayDeliveryService payDeliveryService;

    public static void main(String[] args) {
        SpringApplication.run(DslistApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Resultado = " + payDeliveryService.price(300, "SC"));
    }
}
