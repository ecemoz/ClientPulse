package com.yildiz.clientpulse.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum ActionType {
    LOGIN,
    LOGOUT,
    APPLY_LOAN,
    FORM_SUBMIT,
    FAIL_LOGIN,
    VIEW_PAGE,
    UPDATE_PROFILE,
    DELETE_ACCOUNT,
    CLICK;

    @JsonCreator
    public static ActionType fromString(String value) {
        return ActionType.valueOf(value.toUpperCase(Locale.ROOT));
    }
}