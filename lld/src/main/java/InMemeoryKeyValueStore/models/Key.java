package InMemeoryKeyValueStore.models;

public class Key {
    private final String  key;

    public String getKey() {
        return key;
    }

    public Key(String  key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key1 = (Key) o;

        return key.equals(key1.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
