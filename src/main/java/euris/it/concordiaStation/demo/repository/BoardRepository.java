package euris.it.concordiaStation.demo.repository;

import euris.it.concordiaStation.demo.data.dto.BoardDTO;
import euris.it.concordiaStation.demo.data.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    String FIND_BOARD_BY_ID_TRELLO = "SELECT * FROM board WHERE board.id_trello = :id_trello";

    @Query(value = FIND_BOARD_BY_ID_TRELLO, nativeQuery = true)
    Board findByIdTrello(@Param("id_trello") String idTrello);
}
