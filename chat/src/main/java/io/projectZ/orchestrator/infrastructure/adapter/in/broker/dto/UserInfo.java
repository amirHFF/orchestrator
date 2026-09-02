package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 7/14/2026 - 10:19 AM
*/

public class UserInfo {
    private String username;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

