package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException {
    private char character;
    private RowOrColumn type;
    private final String failMessage;
    public InvalidIdentifierCharacterException(){
        failMessage = "The input identifier include invalid character. It need to be in a form like a3, B4, ... ";
    }

    public InvalidIdentifierCharacterException(char cha, RowOrColumn ty){
        character = cha;
        type = ty;
        failMessage = "The " + cha + " in input identifier is invalid for " + ty + ". It need to be in a form like a3, B4, ... ";
    }

    public String toString(){
        return this.getClass().getName() + ": " + failMessage;
    }
}
