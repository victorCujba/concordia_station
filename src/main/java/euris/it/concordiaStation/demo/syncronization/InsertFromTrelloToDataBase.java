package euris.it.concordiaStation.demo.syncronization;

import euris.it.concordiaStation.demo.data.dto.BoardDTO;
import euris.it.concordiaStation.demo.data.model.Board;
import euris.it.concordiaStation.demo.exceptions.NoBoardExistOnTrelloException;
import euris.it.concordiaStation.demo.repository.BoardRepository;
import euris.it.concordiaStation.demo.service.BoardService;
import euris.it.concordiaStation.demo.trello.service.BoardTrelloService;
import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/syncs")
public class InsertFromTrelloToDataBase {

    private BoardTrelloService boardTrelloService;
    private BoardService boardService;
    private BoardRepository boardRepository;

    @PostMapping("/insert-board-from-trello-to-db")
    public BoardDTO insertBoard(@RequestParam String id) {
        BoardTrelloDTO boardTrelloDTO = boardTrelloService.getBoardFromTrelloByIdBoard(id);
        BoardDTO boardDTO = boardTrelloDTO.toDto();
        Board board = boardDTO.toModel();
        return boardService.insert(board).toDto();
    }

    @PostMapping("/insert-board-from-db-to-trello")
    public List<BoardDTO> insertBoardFromDb() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardDTO> boardDTOS = boardList.stream().map(Board::toDto).toList();

        boardDTOS.forEach(boardDTO -> boardTrelloService.insertBoardFromDbToTrello(boardDTO.getTitle()));//.replaceAll("\\s", "")));
        return boardDTOS;
    }

    @PostMapping("/insert-all-boards-from-trello-to-db")
    public List<BoardDTO> insertAllBoards() {
        List<BoardTrelloDTO> boardTrelloDTOS = boardTrelloService.getAllBoardsFromTrello();
        List<BoardDTO> boardDTOS = boardTrelloDTOS.stream().map(BoardTrelloDTO::toDto).toList();

        if (!boardDTOS.isEmpty()) {
            boardDTOS.forEach(boardDTO -> {
                boardDTO.setUrl(SetUrlToBoardsFromTrello(boardDTO.getIdTrello()));
                if (boardRepository.findByIdTrello(boardDTO.getIdTrello()) == null) {
                    boardService.insert(boardDTO.toModel());
                } else {
                    boardDTO.setId(boardRepository.findByIdTrello(boardDTO.getIdTrello()).getId());
                    boardService.update(boardDTO.toModel());
                }
            });
            return boardDTOS;
        } else {
            throw new NoBoardExistOnTrelloException();
        }
    }

    private String SetUrlToBoardsFromTrello(String idTrello) {
        BoardTrelloDTO boardTrelloDTO = boardTrelloService.getBoardFromTrelloByIdBoard(idTrello);
        return boardTrelloDTO.getUrl();
    }


}
