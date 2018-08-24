package repository.utils;

public class ConditionTuple {

  private Condition[] conditions;

  public ConditionTuple(Condition... conditions) {
    this.conditions = conditions;
  }

  public Condition[] getConditions() {
    return conditions;
  }

  public String getConditionString() {
    if (conditions == null || conditions.length == 0)
      return "";
    if (conditions.length == 1) {
      return conditions[0].getStringValue();
    }
    String s = "(";
    for (int i=0; i<conditions.length; i++) {
      s += conditions[i].getStringValue();
      if (i < conditions.length - 1)
        s += " or ";
    }
    s += ")";
    return s;
  }
}
