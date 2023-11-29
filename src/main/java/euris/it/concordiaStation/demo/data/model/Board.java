package euris.it.concordiaStation.demo.data.model;

import euris.it.concordiaStation.demo.data.dto.BoardDTO;
import euris.it.concordiaStation.demo.data.archetype.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "board")
public class Board implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trello")
    private String idTrello;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "date_last_activity")
    private LocalDateTime dateLastActivity;

    @OneToMany(mappedBy = "board")
    private List<BoardList> lists = new ArrayList<>();

    @Override
    public BoardDTO toDto() {
        return BoardDTO.builder()
                .id(id)
                .idTrello(idTrello)
                .title(title)
                .url(url)
                .dateLastActivity(LocalDateTime.now())
                .build();
    }
}
