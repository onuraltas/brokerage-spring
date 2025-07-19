package com.brokerage.spring.exceptions;

import com.brokerage.spring.enums.ExceptionMessage;
import lombok.Data;

@Data
public class BrokerageException extends RuntimeException {

    private String code;

    public BrokerageException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BrokerageException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getErrorMessage());
        this.code = exceptionMessage.getErrorCode();
    }

    public BrokerageException(ExceptionMessage exceptionMessage, Object... values) {
        super(exceptionMessage.getErrorMessage().formatted(values));
    }

}
