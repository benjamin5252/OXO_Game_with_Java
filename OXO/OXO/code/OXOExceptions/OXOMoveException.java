package OXOExceptions;

public class OXOMoveException extends Exception
{
    private int rowNumber;
    private int columnNumber;
    private final String failMessage;

    public OXOMoveException()
    {
        failMessage = "There is a OXOMoveException in the code";
    }

    public OXOMoveException(int row, int column)
    {
        failMessage = "There is a OXOMoveException in the code";
        rowNumber = row;
        columnNumber = column;
    }
    
    protected int getRow()
    {
        return rowNumber;
    }

    protected int getColumn()
    {
        return columnNumber;
    }

    public String toString(){
        return this.getClass().getName() + ": " + failMessage;
    }

}
