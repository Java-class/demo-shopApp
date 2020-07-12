package exception;

public class UserNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "کاربر مورد نظر یافت نشد.";
    }
}
