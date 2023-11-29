package euris.it.concordiaStation.demo.service.impl;

import euris.it.concordiaStation.demo.data.model.Board;
import euris.it.concordiaStation.demo.exceptions.IdMustBeNullException;
import euris.it.concordiaStation.demo.exceptions.IdMustNotBeNullException;
import euris.it.concordiaStation.demo.repository.BoardRepository;
import euris.it.concordiaStation.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board insert(Board board) {
        if (board.getId() != null) {
            throw new IdMustBeNullException();
        }
        return boardRepository.save(board);
    }

    @Override
    public Board update(Board board) {
        if (board.getId() == null) {
            throw new IdMustNotBeNullException();
        }
        return boardRepository.save(board);
    }

    @Override
    public Board findById(Long idBoard) {
        return boardRepository.findById(idBoard).orElse(Board.builder().build());
    }

    @Override
    public Boolean deleteById(Long idBoard) {
        boardRepository.deleteById(idBoard);
        return boardRepository.findById(idBoard).isEmpty();
    }
}
