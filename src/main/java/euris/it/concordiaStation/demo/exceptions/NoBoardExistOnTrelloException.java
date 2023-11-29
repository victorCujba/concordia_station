package euris.it.concordiaStation.demo.exceptions;

public class NoBoardExistOnTrelloException extends RuntimeException {

    public NoBoardExistOnTrelloException() {
        super("There are no boards on trello. Please try later.");
    }

    public NoBoardExistOnTrelloException(String message) {
        super(message);
    }

}
