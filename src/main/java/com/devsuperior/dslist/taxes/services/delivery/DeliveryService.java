package com.devsuperior.dslist.taxes.services.delivery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private static final double DELIVERY_FEE_VALUE_SP = 10.0;

    private static final double DELIVERY_FEE_VALUE_SC = 20.0;

    public double fee(String state) {
        if (StringUtils.equalsIgnoreCase("SP", state)) {
            return DELIVERY_FEE_VALUE_SP;
        }
        return DELIVERY_FEE_VALUE_SC;
    }
}
