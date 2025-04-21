package com.fef.dell.domain.enums;

import lombok.Getter;

@Getter
public enum Events {
    PITCH(6),
    BUG(-4),
    TRANSLATION(3),
    INVESTOR(-6),
    NEWS(-8);

    private final int points;

    Events(int points) {
        this.points = points;
    }
}
