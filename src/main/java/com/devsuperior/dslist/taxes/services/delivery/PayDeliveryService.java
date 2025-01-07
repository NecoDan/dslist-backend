package com.devsuperior.dslist.taxes.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayDeliveryService {

    @Autowired
    TaxService taxService;

    @Autowired
    DeliveryService deliveryService;

    public PayDeliveryService(TaxService taxService, DeliveryService deliveryService) {
        this.taxService = taxService;
        this.deliveryService = deliveryService;
    }

    public double price(double productValue, String state) {
        final double deliveryValue = deliveryService.fee(state);
        final double taxValue = taxService.performTaxCalculation(productValue);
        return productValue + deliveryValue + taxValue;
    }
}
