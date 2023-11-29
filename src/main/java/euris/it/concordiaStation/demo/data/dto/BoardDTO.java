package euris.it.concordiaStation.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import euris.it.concordiaStation.demo.data.archetype.Dto;
import euris.it.concordiaStation.demo.data.model.Board;
import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDTO implements Dto {

    private Long id;
    private String idTrello;
    private String title;
    private String url;
    private LocalDateTime dateLastActivity;

    @Override
    public Board toModel() {
        return Board.builder()
                .id(id)
                .idTrello(idTrello)
                .title(title)
                .url(url)
                .dateLastActivity(LocalDateTime.now())
                .build();
    }

    @Override
    public BoardTrelloDTO toTrelloDto() {
        return BoardTrelloDTO.builder()
                .id(idTrello)
                .name(title)
                .url(url)
                .build();
    }
}
