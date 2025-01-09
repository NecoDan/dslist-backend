package com.devsuperior.dslist.picpay_challenge.adapter;

import com.devsuperior.dslist.picpay_challenge.ports.NotificationPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPicPayAdapter implements NotificationPicPayPort {

    private static final String PATH_TRANSACTION_NOTIFICATION = "/v1/notify";

    @Value("${address.service.base.url.apis.picpay}")
    private String uriApiPicPay;

    private final RestTemplate restTemplate;
}
