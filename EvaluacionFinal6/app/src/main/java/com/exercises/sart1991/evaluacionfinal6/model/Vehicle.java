package com.exercises.sart1991.evaluacionfinal6.model;

import java.io.Serializable;

/**
 * Created by sart1 on 5/9/2017.
 */

public class Vehicle implements Serializable {

    private String registrationNumber;
    private String clientId;

    public Vehicle(String registrationNumber, String clientId) {
        this.registrationNumber = registrationNumber;
        this.clientId = clientId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
