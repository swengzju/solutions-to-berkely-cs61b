
public class BadTransactionException extends Exception {
    public int transAmount;
    public BadTransactionException(int badAmount) {
        super("Invalid transaction amount: " + badAmount);
        
        transAmount = badAmount;/// ???
  }
}
