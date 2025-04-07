package com.devsuperior.dslist.utils.objs_complexos;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public record Notification(String title,
                           String message,
                           Recipient recipient,
                           boolean highPriority,
                           TypeNotification type,
                           String attachment,
                           LocalDateTime createdAt) {

    public static NotificationDataBuilder builder(){
        return new NotificationDataBuilder();
    }

    public Notification{
        Objects.requireNonNull(title, "title is required");
        Objects.requireNonNull(message, "message is required");
        Objects.requireNonNull(recipient, "recipient is required");

        type = Optional.ofNullable(type).orElse(TypeNotification.GENERAL);
    }

}
