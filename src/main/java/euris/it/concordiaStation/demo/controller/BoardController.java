package euris.it.concordiaStation.demo.controller;

import euris.it.concordiaStation.demo.data.dto.BoardDTO;
import euris.it.concordiaStation.demo.data.model.Board;
import euris.it.concordiaStation.demo.exceptions.IdMustBeNullException;
import euris.it.concordiaStation.demo.exceptions.IdMustNotBeNullException;
import euris.it.concordiaStation.demo.exceptions.NonexistentBoardIdException;
import euris.it.concordiaStation.demo.repository.BoardRepository;
import euris.it.concordiaStation.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;
    private BoardRepository boardRepository;

    @GetMapping("/v1")
    public List<BoardDTO> getAllBoards() {
        return boardService.findAll().stream().map(Board::toDto).toList();
    }

    @GetMapping("/v1/{id}")
    public BoardDTO getBoardById(@RequestParam Long id) {
        if (boardRepository.findById(id).isPresent()) {
            return boardService.findById(id).toDto();
        } else {
            throw new NonexistentBoardIdException();
        }

    }

    @PostMapping("/v1")
    public BoardDTO saveBoard(@RequestParam String title) {
        try {
            Board board = Board.builder().title(title).build();
            return boardService.insert(board).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/v1")
    public BoardDTO updateBoard(@RequestBody BoardDTO boardDTO) {
        try {
            Board board = boardDTO.toModel();
            if (boardRepository.findById(board.getId()).isPresent()) {
                return boardService.update(board).toDto();
            } else {
                throw new NonexistentBoardIdException();
            }
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @DeleteMapping("/v1/{id}")
    public Boolean deleteBoard(@PathVariable Long id) {
        if (boardRepository.findById(id).isPresent()) {
            return boardService.deleteById(id);
        } else {
            throw new NonexistentBoardIdException();
        }

    }


}
