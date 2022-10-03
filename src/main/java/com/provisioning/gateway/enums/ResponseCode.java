package com.provisioning.gateway.enums;


public enum ResponseCode {

    NOT_FOUND(001, "Not Found"),
    UPDATE_FAILED(002, "Update Failed"),
    DELETE_FAILED(003, "Delete Failed");

    private final int value;
    private final String reasonPhrase;

    private ResponseCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }


    public String toString() {
        return this.value + " " + this.name();
    }

}