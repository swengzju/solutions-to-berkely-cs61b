public class Counter {
  private boolean[] digits;

  public Counter(int size) {
    digits = new boolean[size];
  }

  public void increment() {
    int i = 0;
    while (digits[i]) {
      digits[i] = false;
      i++;
    }
    digits[i] = true;
  }

  public String toString() {
    String ret = "";
    for (int i = digits.length - 1; i >= 0; i--) {
      ret = ret + (digits[i] ? "1" : "0");
    }
    return ret;
  }

  public static void main(String[] args) {
    Counter c = new Counter(20);

    for (int i = 0; i < 20; i++) {
      c.increment();
      System.out.println(c.toString());
    }
  }
}
