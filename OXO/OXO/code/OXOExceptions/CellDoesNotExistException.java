package OXOExceptions;

public class CellDoesNotExistException extends OXOMoveException {
    public CellDoesNotExistException(){

    }

    public CellDoesNotExistException(int row, int col){
        super(row, col);
    }
}
