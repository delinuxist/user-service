package com.tradingengine.userservice.enums;

public enum QueueRoutingKeys {
USER_ROUTE_KEY("user_route_key");

    private final String key;
    QueueRoutingKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
