package euris.it.concordiaStation.demo.trello.utils;

public interface TrelloConstants {
    String ID_BOARD_VALUE = "652d2b859eb15cc70091e00c";
    String KEY_VALUE = "213560f7b27f60bd0ad351666fb73db8";
    String TOKEN_VALUE = "ATTA9fa44759b8168ac67d5c020ef75db8d3372ed9e1dabcd52e2d277cd97152ef1eEA976AA0";

    String URL_API_TRELLO_GET_BOARD_BY_ID_BOARD = "https://api.trello.com/1/boards/{id}?key={APIKey}&token={APIToken}";
    String URL_API_TRELLO_POST_NEW_BOARD_ON_TRELLO = "https://api.trello.com/1/boards/?name={name}&key={APIKey}&token={APIToken}";
    String URL_API_TRELLO_GET_ALL_BOARDS = "https://api.trello.com/1/members/me?boards=open&key={APIKey}&token={APIToken}&name=cujbavictor";
}
