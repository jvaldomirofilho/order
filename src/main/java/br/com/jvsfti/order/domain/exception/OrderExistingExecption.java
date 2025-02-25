package br.com.jvsfti.order.domain.exception;

public class OrderExistingExecption extends RuntimeException {

    private final String message;

    public OrderExistingExecption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
