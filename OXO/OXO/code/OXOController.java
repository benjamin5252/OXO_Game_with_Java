import OXOExceptions.*;

import java.util.Locale;

class OXOController
{
    OXOModel gameModel;
    int move_count = 0;

    public OXOController(OXOModel model)
    {
        gameModel = model;
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(0));
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        int y;
        int x;

        //Because there is already a try...catch in OXOView, so only throw exceptions here
        //If the identifier got wrong length, throw InvalidIdentifierLengthException
        if(command.length() != 2){
            throw new InvalidIdentifierLengthException(command.length());
        }

        //If the input first char is not between a - i (maximum board size is 9), throw InvalidIdentifierCharacterException
        if(command.toLowerCase().charAt(0) < 'a' || command.toLowerCase().charAt(0) > 'z'){
            throw new InvalidIdentifierCharacterException(command.charAt(0), RowOrColumn.ROW);
        }

        //If the input second char is not between 1 - 9 (maximum board size is 9), throw InvalidIdentifierCharacterException
        if(command.charAt(1) < '1' || command.charAt(1) > '9'){
            throw new InvalidIdentifierCharacterException(command.charAt(1), RowOrColumn.COLUMN);
        }

        //interpret the command
        y = command.toLowerCase().charAt(0) - 'a';
        x = command.charAt(1) - '1';



        //If the input move get outside of the board, throw OutsideCellRangeException
        if(y > (gameModel.getNumberOfRows() - 1)){
            throw new OutsideCellRangeException(gameModel.getNumberOfRows(), gameModel.getNumberOfColumns(), y+1, RowOrColumn.ROW);
        }else if(x > (gameModel.getNumberOfColumns() - 1)){
            throw new OutsideCellRangeException(gameModel.getNumberOfRows(), gameModel.getNumberOfColumns(), x+1, RowOrColumn.COLUMN);
        }

        //If the input move get into the cell already taken, throw CellAlreadyTakenException
        if(gameModel.getCellOwner(y, x) != null){
            throw new CellAlreadyTakenException();
        }

        //If the outcome of the game is not revealed, update the next move; If not, skip it
        if(gameModel.getWinner() == null && !gameModel.isGameDrawn()){
            gameModel.setCellOwner(y, x, gameModel.getCurrentPlayer());
            move_count ++;
            //check if there is a winner or not
            checkWin();

            //change the current user
            if(gameModel.getCurrentPlayer() == gameModel.getPlayerByNumber(0)){
                gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(1));
            }else{
                gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(0));
            }

            //check if the game end in drawn or not
            if(move_count >= gameModel.getNumberOfRows() * gameModel.getNumberOfColumns()){
                gameModel.setGameDrawn();
            }
        }
    }

    public void checkWin(){
        checkRow();
        checkCol();
        checkDia();
    }


    //If there is a win in row, set the corresponding winner.
    public void checkRow(){
        int[] cell = new int[2];
        OXOPlayer player;
        for(int j = 0; j < gameModel.getNumberOfRows(); j++){
            cell[0] = 0;
            cell[1] = 0;
            for(int i = 0; i < gameModel.getNumberOfColumns(); i++){
                player = gameModel.getCellOwner(j,i);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }else{
                    cell[0] = 0;
                    cell[1] = 0;
                }
            }
        }
    }

    //If there is a win in column, set the corresponding winner.
    public void checkCol(){
        int[] cell = new int[2];
        OXOPlayer player;
        for(int i = 0; i < gameModel.getNumberOfColumns(); i++){
            cell[0] = 0;
            cell[1] = 0;
            for(int j = 0; j < gameModel.getNumberOfRows(); j++){
                player = gameModel.getCellOwner(j,i);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }else{
                    cell[0] = 0;
                    cell[1] = 0;
                }
            }
        }
    }

    //If there is a win in inclined direction, set the corresponding winner.
    public void checkDia(){
        int[] cell = new int[2];
        OXOPlayer player;
        int y;
        int x;
        for(int i = 0; i < gameModel.getNumberOfColumns(); i++){
            cell[0] = 0;
            cell[1] = 0;
            y = 0;
            x = i;
            //Finding from the top of the board
            while( y >= 0 && y < gameModel.getNumberOfRows() && x >= 0 && x < gameModel.getNumberOfColumns()){
                player = gameModel.getCellOwner(y,x);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }else{
                    cell[0] = 0;
                    cell[1] = 0;
                }
                x ++;
                y ++;
            }

            cell[0] = 0;
            cell[1] = 0;
            y = 0;
            x = i;
            while( y >= 0 && y < gameModel.getNumberOfRows() && x >= 0 && x < gameModel.getNumberOfColumns()){
                player = gameModel.getCellOwner(y,x);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }else{
                    cell[0] = 0;
                    cell[1] = 0;
                }
                x --;
                y ++;
            }
        }

        //Finding from the bottom of the board
        for(int i = 0; i < gameModel.getNumberOfColumns(); i++){
            cell[0] = 0;
            cell[1] = 0;
            y = gameModel.getNumberOfRows() - 1;
            x = i;
            while( y >= 0 && y < gameModel.getNumberOfRows() && x >= 0 && x < gameModel.getNumberOfColumns()){
                player = gameModel.getCellOwner(y,x);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }
                x ++;
                y --;
            }

            cell[0] = 0;
            cell[1] = 0;
            y = 0;
            x = i;
            while( y >= 0 && y < gameModel.getNumberOfRows() && x >= 0 && x < gameModel.getNumberOfColumns()){
                player = gameModel.getCellOwner(y,x);
                if( player == gameModel.getPlayerByNumber(0) ){
                    cell[0] ++;
                    cell[1] = 0;
                    if( cell[0] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(0));
                        break;
                    }
                }else if( player == gameModel.getPlayerByNumber(1) ){
                    cell[1] ++;
                    cell[0] = 0;
                    if(cell[1] >= gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.getPlayerByNumber(1));
                        break;
                    }
                }
                x --;
                y --;
            }
        }
    }

}

