package me.sam.updates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateText {
    String text;

    String datetime;

    UpdateType updateType;

    DateTimeFormatter dtf;

    LocalDateTime now;

    public UpdateText(UpdateType updateType, String text) {
        this.text = text;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.datetime = dtf.format(now);
        this.updateType = updateType;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UpdateType getUpdateType() {
        return this.updateType;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
