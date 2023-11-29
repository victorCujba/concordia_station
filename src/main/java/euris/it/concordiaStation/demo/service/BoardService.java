package euris.it.concordiaStation.demo.service;

import euris.it.concordiaStation.demo.data.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> findAll();

    Board insert(Board board);

    Board update(Board board);

    Board findById(Long idBoard);

    Boolean deleteById(Long idBoard);
}
