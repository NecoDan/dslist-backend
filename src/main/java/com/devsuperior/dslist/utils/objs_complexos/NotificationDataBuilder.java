package com.devsuperior.dslist.utils.objs_complexos;

import java.time.LocalDateTime;

public class NotificationDataBuilder {

    private String title;
    private String message;
    private Recipient recipient;
    private boolean highPriority;
    private TypeNotification type;
    private String attachment;
    private LocalDateTime createdAt;

    NotificationDataBuilder() {

    }

    public NotificationDataBuilder title(String title) {
        this.title = title;
        return this;
    }

    public NotificationDataBuilder message(String message) {
        this.message = message;
        return this;
    }

    public NotificationDataBuilder recipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public NotificationDataBuilder highPriority(boolean highPriority) {
        this.highPriority = highPriority;
        return this;
    }

    public NotificationDataBuilder type(TypeNotification type) {
        this.type = type;
        return this;
    }

    public NotificationDataBuilder attachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public NotificationDataBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Notification build(){
        return new Notification(title, message, recipient, highPriority, type, attachment, createdAt);
    }
}
