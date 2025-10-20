package davidemancini.U5_W3_D1.exceptions;

public class MyBadRequestException extends RuntimeException {
    public MyBadRequestException(String message) {
        super(message);
    }
}
