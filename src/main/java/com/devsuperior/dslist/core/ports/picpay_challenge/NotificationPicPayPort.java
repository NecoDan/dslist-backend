package com.devsuperior.dslist.core.ports.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;

public interface NotificationPicPayPort {
    void sendNotification(UserPicPay userPicPay, String message);
}
