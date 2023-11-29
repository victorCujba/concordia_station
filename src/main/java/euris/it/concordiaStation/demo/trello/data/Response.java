package euris.it.concordiaStation.demo.trello.data;

import com.google.gson.annotations.SerializedName;
import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

    private String id;
    private String aaId;
    private Boolean activityBlocked;
    private String avatarHash;
    private String avatarUrl;
    private String bio;
    private List<BoardTrelloDTO> boards;

}
