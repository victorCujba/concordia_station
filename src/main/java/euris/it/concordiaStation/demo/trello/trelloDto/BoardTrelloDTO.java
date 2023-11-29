package euris.it.concordiaStation.demo.trello.trelloDto;

import euris.it.concordiaStation.demo.data.dto.BoardDTO;
import euris.it.concordiaStation.demo.data.archetype.TrelloDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardTrelloDTO implements TrelloDto {


    private String id;
    private String name;
    private String url;

    @Override
    public BoardDTO toDto() {
        return BoardDTO.builder()
                .idTrello(id)
                .title(name)
                .url(url)
                .dateLastActivity(LocalDateTime.now())
                .build();
    }
}
