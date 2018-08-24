package repository.utils;

public class Condition {

  private int index;
  private ConditionType type;
  private String stringValue;
  private Object value;

  public Condition(int index, ConditionType type, String stringValue, Object value) {
    this.index = index;
    this.type = type;
    this.stringValue = stringValue;
    this.value = value;
  }

  public int getIndex() {
    return index;
  }

  public ConditionType getType() {
    return type;
  }

  public String getStringValue() {
    return stringValue;
  }

  public Object getValue() {
    return value;
  }
}
