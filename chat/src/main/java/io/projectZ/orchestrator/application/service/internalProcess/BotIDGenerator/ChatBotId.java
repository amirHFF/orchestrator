package io.projectZ.orchestrator.application.service.internalProcess.BotIDGenerator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:27 AM
*/

import java.util.Objects;

public final class ChatBotId {
    private static final String PATTERN = "^BOT-[a-z]+-[a-z]+-[a-z0-9]{4}$";

    private final String value;

    public ChatBotId(String value) {
        if (value == null || !value.matches(PATTERN)) {
            throw new IllegalArgumentException("Invalid ChatBot ID format: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatBotId)) return false;
        return Objects.equals(value, ((ChatBotId) o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
