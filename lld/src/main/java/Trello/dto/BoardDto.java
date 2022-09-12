package Trello.dto;

import Trello.enums.Privacy;

import java.util.List;

public class BoardDto {
    private String id;
    private String name;
    private Privacy privacy;
    private String url;
    private List<String> membersUserId;
    private List<String> listsId;

    public BoardDto(String id, String name, Privacy privacy, String url, List<String> membersUserId, List<String> listsId) {
        this.id = id;
        this.name = name;
        this.privacy = privacy;
        this.url = url;
        this.membersUserId = membersUserId;
        this.listsId = listsId;
    }

    public BoardDto(String name) {
        this.name = name;
        this.privacy=Privacy.PUBLIC;
    }

    public BoardDto(String boardId, String name,Privacy privacy) {
        this.id = boardId;
        this.name = name;
        this.privacy = privacy;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getMembersUserId() {
        return membersUserId;
    }

    public List<String> getListsId() {
        return listsId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMembersUserId(List<String> membersUserId) {
        this.membersUserId = membersUserId;
    }

    public void setListsId(List<String> listsId) {
        this.listsId = listsId;
    }
}
