package OXOExceptions;

public class InvalidIdentifierException extends CellDoesNotExistException {
    public InvalidIdentifierException(){

    }

    public InvalidIdentifierException(int row, int col){
        super(row, col);
    }
}
