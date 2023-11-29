package euris.it.concordiaStation.demo.trello.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import euris.it.concordiaStation.demo.trello.data.Response;
import euris.it.concordiaStation.demo.trello.service.BoardTrelloService;
import euris.it.concordiaStation.demo.trello.trelloDto.BoardTrelloDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static euris.it.concordiaStation.demo.trello.utils.TrelloConstants.*;

@AllArgsConstructor
@Service
public class BoardTrelloServiceImpl implements BoardTrelloService {

    private Gson gson;
    private HttpClient httpClient;

    private BoardTrelloServiceImpl() {
        this.gson = new Gson();
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    @SneakyThrows
    public BoardTrelloDTO getBoardFromTrelloByIdBoard(String id) {

        URI targetURI = new URI(buildUrlGetBoardFromTrelloByIdBoard(id));

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(targetURI)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        BoardTrelloDTO boardTrelloDTO = gson.fromJson(response.body(), BoardTrelloDTO.class);
        System.out.println(boardTrelloDTO);
        return boardTrelloDTO;
    }

    @Override
    @SneakyThrows
    public List<BoardTrelloDTO> getAllBoardsFromTrello() {

        URI targetURI = new URI(buildUrlGetAllBoardsFromTrello());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(targetURI)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<BoardTrelloDTO>>() {
        }.getType();
        Response trelloResponse = gson.fromJson(response.body(), Response.class);
        List<BoardTrelloDTO> boards = trelloResponse.getBoards();

//        List<BoardTrelloDTO> boardTrelloDTOS = gson.fromJson(response.body(), listType);
        //     System.out.println(boardTrelloDTOS);
        //  return boardTrelloDTOS;

        System.out.println(boards);
        return boards;
    }


    @Override
    @SneakyThrows
    public void insertBoardFromDbToTrello(String name) {

        URI targetURI = new URI(buildUrlPostBoardFromDbToTrello(name));


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(targetURI)
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(name))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        String responseBody = response.body();

        System.out.println("HTTP Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

    }

    private String buildUrlGetAllBoardsFromTrello() {
        return UriComponentsBuilder.fromHttpUrl(URL_API_TRELLO_GET_ALL_BOARDS)
                .buildAndExpand(KEY_VALUE, TOKEN_VALUE).toString();
    }

    private String buildUrlPostBoardFromDbToTrello(String name) {
        String encodedName = UriComponentsBuilder.fromUriString(name).encode().build().toString();
        return UriComponentsBuilder.fromHttpUrl(URL_API_TRELLO_POST_NEW_BOARD_ON_TRELLO)
                .buildAndExpand(encodedName, KEY_VALUE, TOKEN_VALUE).toString();
    }

    private String buildUrlGetBoardFromTrelloByIdBoard(String id) {
        return UriComponentsBuilder.fromHttpUrl(URL_API_TRELLO_GET_BOARD_BY_ID_BOARD)
                .buildAndExpand(id, KEY_VALUE, TOKEN_VALUE).toString();
    }
}

