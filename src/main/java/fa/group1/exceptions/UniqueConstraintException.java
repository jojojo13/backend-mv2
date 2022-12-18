package fa.group1.exceptions;

public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException(String msg){
        super(msg);
    }
}
