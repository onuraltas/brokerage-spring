package com.brokerage.spring.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    GENERAL_EXCEPTION("10001", "General Exception"),
    RECORD_NOT_FOUND("10002", "Record not found"),
    CONSTRAINT_VIOLATION("10003", "Constraint violation"),
    ASSET_NOT_FOUND("10011", "Asset not found"),
    ASSET_NOT_ENOUGH("10012", "Asset not enough"),
    PARAM_ASSET_NOT_FOUND("10013", "%s asset not found"),
    PARAM_ASSET_NOT_ENOUGH("10014", "%s asset not enough"),
    ORDER_CANNOT_BE_CANCELLED("10020", "Order cannot be cancelled"),
    ORDER_SIDE_IS_FALSE("10021", "Order side is false"),
    ORDER_DOES_NOT_BELONG_TO_THIS_USER("10022", "Order does not belong to this user"),
    TRY_ASSET_CANNOT_BE_TRADED("10023", "TRY asset cannot be traded"),
    UNKNOWN_ASSET("10024", "Unknown asset (%s)");

    private final String errorCode;
    private final String errorMessage;

}
