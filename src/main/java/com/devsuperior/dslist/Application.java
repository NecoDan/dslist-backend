package com.devsuperior.dslist;

import com.devsuperior.dslist.taxes.services.delivery.PagamentoDeliveryService;
import com.devsuperior.dslist.utils.objs_complexos.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {

    @Autowired
    private PagamentoDeliveryService pagamentoDeliveryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // --------------------------------------------------------------------------------------------------
        final Recipient recipient = new Recipient(UUID.randomUUID().toString(), "Qualquer Pessoa");

        // --------------------------------------------------------------------------------------------------
        //        NotificationV2 notificationV2 = new NotificationV2("titulo", "mensagem", recipient);
        //        System.out.println(notificationV2);
        //
        //        // --------------------------------------------------------------------------------------------------
        //        NotificationV1 notificationV1 = new NotificationV1();
        //        notificationV1.setTitle("Titulo");
        //        notificationV1.setMessage("Mensagem");
        //        notificationV1.setRecipient(recipient);
        //        notificationV1.
        //
        //        System.out.println(notificationV1);

        System.out.println("Resultado = " + pagamentoDeliveryService.calcularPrecoFinal(300, "SC"));
    }
}
