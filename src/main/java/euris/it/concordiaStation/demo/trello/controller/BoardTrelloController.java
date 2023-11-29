package euris.it.concordiaStation.demo.trello.controller;

import euris.it.concordiaStation.demo.repository.BoardRepository;
import euris.it.concordiaStation.demo.trello.service.impl.BoardTrelloServiceImpl;
import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/trello-boards")
public class BoardTrelloController {

    private BoardTrelloServiceImpl boardTrelloService;
    private BoardRepository boardRepository;

    @GetMapping("/v1")
    public BoardTrelloDTO getBoardFromTrelloById(@RequestParam String id) {
        return boardTrelloService.getBoardFromTrelloByIdBoard(id);
    }
}
