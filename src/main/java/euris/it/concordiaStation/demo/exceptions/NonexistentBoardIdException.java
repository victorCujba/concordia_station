package euris.it.concordiaStation.demo.exceptions;

public class NonexistentBoardIdException extends RuntimeException {

    public NonexistentBoardIdException() {
        super("Board with passed id does not exist in data base. Please insert valid board id!!!");
    }

    public NonexistentBoardIdException(String message) {
        super(message);
    }
}
