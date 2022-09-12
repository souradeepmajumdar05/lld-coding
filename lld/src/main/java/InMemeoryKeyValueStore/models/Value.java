package InMemeoryKeyValueStore.models;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Value {
    private List<Pair<String, String>> listOfPairs ;

    public List<Pair<String, String>> getValueMap() {
        return listOfPairs;
    }

    public Value() {
        this.listOfPairs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return  listOfPairs
                .stream()
                .map(a -> String.valueOf(a.getKey()) + ":" + String.valueOf(a.getValue()))
                .collect(Collectors.joining(","));

    }
}
