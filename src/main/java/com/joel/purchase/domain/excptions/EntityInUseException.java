package com.joel.purchase.domain.excptions;

public class EntityInUseException extends BusinessException {

    public EntityInUseException(String message) {
        super(message);
    }
}
