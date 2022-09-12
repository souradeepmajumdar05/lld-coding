package Trello.models;

import Trello.enums.Privacy;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private String id;
    private String name;
    private Privacy privacy;
    private String url;
    private List<User> members;
    private List<TLists> lists;

    public Board(String id, String name, Privacy privacy, String url, List<User> members, List<TLists> lists) {
        this.id = id;
        this.name = name;
        this.privacy = privacy;
        this.url = url;
        this.members = members;
        this.lists = lists;
    }

    public Board(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<TLists> getLists() {
        return lists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<User> getMembersUserId() {
        return members;
    }

    public void setMembersUserId(List<User> users) {
        this.members = users;
    }

    public List<TLists> getListsId() {
        return lists;
    }

    public void setListsId(List<TLists> lists) {
        this.lists = lists;
    }

    //{"id": "5da1583ec25d2a7e246b0375", "name": "work@tech", "privacy": "PUBLIC"}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id:").append(id)
          .append(", name:").append(name)
          .append(", privacy:").append(privacy);
        if(url!=null){
            sb.append(", url:").append(url);
        }
        if(members !=null){
            sb.append(", members:").append(members.stream().map(s->String.valueOf(s.toString())).collect(Collectors.joining(",")));
        }
        if(lists!=null){
            sb.append(", lists:").append(lists.stream().map(s->String.valueOf(s.toString())).collect(Collectors.joining(",")));
        }
        sb.append("}");
        return sb.toString();
    }
}
