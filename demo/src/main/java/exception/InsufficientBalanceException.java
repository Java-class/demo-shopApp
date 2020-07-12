package exception;

public class InsufficientBalanceException extends  Exception {
    @Override
    public String getMessage() {
        return "اعتبار کافی نیست.";
    }
}
