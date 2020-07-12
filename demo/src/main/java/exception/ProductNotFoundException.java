package exception;

public class ProductNotFoundException extends  Exception {

    @Override
    public String getMessage() {
        return "محصول مورد نظر یافت نشد";
    }
}
