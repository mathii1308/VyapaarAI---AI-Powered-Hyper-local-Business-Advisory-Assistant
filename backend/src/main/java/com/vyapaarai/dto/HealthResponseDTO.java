package com.vyapaarai.dto;

public class HealthResponseDTO {
    private String status;
    private String message;
    private String database;
    private String version;
    private String timestamp;

    public HealthResponseDTO() {
    }

    public HealthResponseDTO(String status, String message, String database, String version, String timestamp) {
        this.status = status;
        this.message = message;
        this.database = database;
        this.version = version;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

