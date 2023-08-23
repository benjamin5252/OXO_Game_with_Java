package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException{
    private int length;
    private final String failMessage;
    public InvalidIdentifierLengthException(){
        failMessage = "The length of the identifier is invalid. The length need to be 2.";
    }

    public InvalidIdentifierLengthException(int length){
        failMessage = "The input identifier length " + length + " is invalid. The length need to be 2.";
    }

    public String toString(){
        return this.getClass().getName() + ": " + failMessage;
    }
}
