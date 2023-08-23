package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException {

    private int position;
    private RowOrColumn type;
    private final String failMessage;

    public OutsideCellRangeException(){
        failMessage = "The move is outside of the cell range";
    }

    public OutsideCellRangeException(int pos, RowOrColumn ty){
        position = pos;
        type = ty;
        failMessage = position + " in " + type + " is outside of the cell range ";
    }

    public OutsideCellRangeException(int row, int col, int pos, RowOrColumn ty){
        super(row, col);
        position = pos;
        type = ty;
        failMessage = position + " in " + type + " is outside of the cell range " + super.getRow() + "*" + super.getColumn() + " (ROW * COLUMN)";
    }

    public String toString(){
        return this.getClass().getName() + ": " + failMessage;
    }
}
