package euris.it.concordiaStation.demo.trello.service;

import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;

import java.util.List;

public interface BoardTrelloService {
    BoardTrelloDTO getBoardFromTrelloByIdBoard(String id);

    List<BoardTrelloDTO> getAllBoardsFromTrello();

    void insertBoardFromDbToTrello(String name);
}
