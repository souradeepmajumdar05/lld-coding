package LibraryManagementSystem.models;

public class IndexObject {
    private String attribute;
    private String value;

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public IndexObject(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexObject that = (IndexObject) o;

        if (!attribute.equals(that.attribute)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = attribute.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IndexObject{" +
                "attribute='" + attribute + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
