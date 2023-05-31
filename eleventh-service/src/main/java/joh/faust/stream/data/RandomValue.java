package joh.faust.stream.data;

public class RandomValue {

    private final int value;
    private final int id;

    public RandomValue() {
        this(0, 0);
    }

    public RandomValue(int value, int id) {
        this.value = value;
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RandomValue{" +
                "value=" + value +
                ", id=" + id +
                '}';
    }
}
