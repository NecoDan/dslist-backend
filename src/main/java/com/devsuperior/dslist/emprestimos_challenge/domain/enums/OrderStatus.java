package com.devsuperior.dslist.emprestimos_challenge.domain.enums;

public enum OrderStatus implements EnumStatusDesignExtensible {

    CREATED(true),
    PAID(true),
    CANCELLED(false);

    private final boolean finalStatus;

    OrderStatus(boolean finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public boolean isFinal() {
        return finalStatus;
    }
}
