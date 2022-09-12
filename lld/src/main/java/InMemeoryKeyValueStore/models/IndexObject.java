package InMemeoryKeyValueStore.models;

import java.util.List;

public class IndexObject {
    private final List<String> listOfIndexingKeys;

    public IndexObject(List<String> keys) {
        this.listOfIndexingKeys = keys;
    }

    public List<String> getListOfIndexingKeys() {
        return listOfIndexingKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexObject that = (IndexObject) o;

        return listOfIndexingKeys.equals(that.listOfIndexingKeys);
    }

    @Override
    public int hashCode() {
        return listOfIndexingKeys.hashCode();
    }
}
