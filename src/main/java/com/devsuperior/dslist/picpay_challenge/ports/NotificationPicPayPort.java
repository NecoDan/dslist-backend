package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.domain.User;

public interface NotificationPicPayPort {
    void sendNotification(User user, String message);
}
