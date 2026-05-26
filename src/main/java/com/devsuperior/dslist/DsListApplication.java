package com.devsuperior.dslist;

import com.devsuperior.dslist.taxes.services.delivery.PagamentoDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsListApplication implements CommandLineRunner {

    @Autowired
	private PagamentoDeliveryService pagamentoDeliveryService;

    public static void main(String[] args) {
        SpringApplication.run(DsListApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Resultado = " + pagamentoDeliveryService.calcularPrecoFinal(300, "SC"));
    }
}
