import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LargeMultiplier {
  public static String multiply(String num1, String num2) {
    List<String> adds = new ArrayList<>();
    for (int i = 0; i < num2.length(); i++) {
      StringBuffer buff = new StringBuffer();
      int rem = 0;
      for (int j = 0; j < num1.length(); j++) {
        int dig1 = num1.charAt(num1.length() - j - 1) - '0';
        int dig2 = num2.charAt(num2.length() - i - 1) - '0';
        int ans = (dig1 * dig2) + rem;
        rem = ans / 10;
        buff = buff.append(ans % 10);
      }
      adds.add(rem + buff.reverse().toString());
    }
    return addAfterPadding(adds);
  }

  private static String addAfterPadding(List<String> adds) {
    boolean available = adds.size() > 0;
    int col = 0;
    int rem = 0;
    StringBuffer ans = new StringBuffer();
    while (available) {
      available = false;
      int sum = rem;
      for (int i = 0; i < adds.size(); i++) {
        String num = adds.get(i);
        int len = num.length();
        int dig = 0;
        if (col >= i && col < i + len) {
          dig = num.charAt(len - (col - i) - 1) - '0';
          available = true;
        }
        sum += dig;
      }
      col++;
      ans.append(sum % 10);
      rem = sum / 10;
    }

    String mult = rem + ans.reverse().toString();
    String sub = mult.replaceFirst("^(0*)", "");

    return sub.length() > 0 ? sub : "0";
  }

  public static void main(String[] args) {
    for (int i = 10; i < 500; i++) {
      for (int j = 10; j < 500; j++) {
        BigInteger num1 = new BigInteger(i, new Random());
        BigInteger num2 = new BigInteger(j, new Random());
        BigInteger multiply = num1.multiply(num2);
        String multiply1 = LargeMultiplier.multiply(num1.toString(), num2.toString());
        if (!multiply.toString().equals(multiply1)) {
          System.out.println("Error on test case: " + num1.toString() + "," + num2.toString());
        }
      }
    }
  }
}
