package br.com.jvsfti.order.domain.exception;

public class BussinesOrderExecption extends RuntimeException {

    private final String message;

    public BussinesOrderExecption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
