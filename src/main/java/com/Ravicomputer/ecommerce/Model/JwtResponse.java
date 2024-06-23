package com.Ravicomputer.ecommerce.Model;

import lombok.Builder;

@Builder
public class JwtResponse {
    private String jwtToken;
    private String userNAme;
    private String name;
    private Boolean verified;
    private long userId;

    public JwtResponse() {
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public JwtResponse(String jwtToken, String userNAme, String name, Boolean verifed, long userId) {
        this.jwtToken = jwtToken;
        this.userNAme = userNAme;
        this.name=name;
        this.verified=verifed;
        this.userId=userId;


    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserNAme() {
        return userNAme;
    }

    public void setUserNAme(String userNAme) {
        this.userNAme = userNAme;
    }
}
