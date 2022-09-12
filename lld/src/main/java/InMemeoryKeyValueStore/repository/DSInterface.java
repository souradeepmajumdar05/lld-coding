package InMemeoryKeyValueStore.repository;

import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.models.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface DSInterface {
    public Value findOne(Key key);
    public List<Key> search(String attributeKey, String attributeValue);
    public boolean save(Key key, Value listOfAttributePairs) throws Exception;
    public void delete(Key key);
    public Map<Key, Value> findAll();
}
