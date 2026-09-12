package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/16/2026 - 12:50 AM
*/

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileEventDto extends EventDTO{
  private String id;
  private long occurredAt;
  private String realmId;
  private boolean userEvent;

  private Map<String , String> details;

  private UserInfo userInfo;

  public boolean isUserEvent() {
    return userEvent;
  }

  public void setUserEvent(boolean userEvent) {
    this.userEvent = userEvent;
  }

  public String getRealmId() {
    return realmId;
  }

  public void setRealmId(String realmId) {
    this.realmId = realmId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(long occurredAt) {
    this.occurredAt = occurredAt;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public Map<String, String> getDetails() {
    return details;
  }

  public void setDetails(Map<String, String> details) {
    this.details = details;
  }

  @Override
  public String toString() {
    return "EventDTO{" +
            "id='" + id + '\'' +
            ", occurredAt=" + occurredAt +
            ", realmId='" + realmId + '\'' +
            ", details=" + details +
            ", userInfo=" + userInfo +
            '}';
  }
}

