package euris.it.concordiaStation.demo.exceptions;

public class IdMustBeNullException extends RuntimeException {
    public IdMustBeNullException() {
        super("Id must be null. You sent a dto with id");
    }

    public IdMustBeNullException(String message) {
        super(message);
    }
}
