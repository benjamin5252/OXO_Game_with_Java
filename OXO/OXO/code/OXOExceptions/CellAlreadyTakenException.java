package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException{
    private final String failMessage;
    public CellAlreadyTakenException(){
        failMessage = "The cell of this move is already taken.";
    }

    public String toString(){
        return this.getClass().getName() + ": " + failMessage;
    }

}
