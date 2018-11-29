package net.eleven.finance.web.rest;

import java.util.Date;

/**
 * Created by eleven on 25.11.2018.
 */

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {

        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}