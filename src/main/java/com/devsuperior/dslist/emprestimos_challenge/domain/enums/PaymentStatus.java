package com.devsuperior.dslist.emprestimos_challenge.domain.enums;

public enum PaymentStatus implements EnumStatusDesignExtensible {

    PENDING(false),
    APPROVED(true),
    REJECTED(true);

    private final boolean finalStatus;

    PaymentStatus(boolean finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public boolean isFinal() {
        return finalStatus;
    }
}
