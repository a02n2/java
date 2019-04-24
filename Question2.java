import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Question2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("®”(-10000`10000)‚ÌŽl‘¥‰‰ŽZ‚ð‹ó”’‚Å‹æØ‚Á‚Ä“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        System.out.println("‰‰ŽZ‹L†‚Íu+(˜a)vu-(·)vu*(Ï)vu/(¤)v‚Å‚·");
        String input = br.readLine();
        String[] param = input.split(" ");

        short x = 0;
        short y = 0;

        try {
            x = Short.parseShort(param[0]);         //shortŒ^‚É•ÏŠ·
            y = Short.parseShort(param[2]);
        } catch (NumberFormatException n) {
            System.out.println("-10000`10000‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("³‚µ‚¢Ž®‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            return;
        }

        if (x > 10000 || x < -10000 || y > 10000 || y < -10000) {       //-10000~10000‚Å‚È‚¯‚ê‚ÎƒGƒ‰[
            System.out.println("-10000`10000‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            return;
        }

        // “ü—Í‚³‚ê‚½‰‰ŽZ‹L†‚É•ª‚¯‚ÄŒvŽZAo—Í
        switch (param[1]) {     
        case "+":
            System.out.println(x + y);
            break;
        case "-":
            System.out.println(x - y);
            break;
        case "*":
            System.out.println(x * y);
            break;
        case "/":
            try {
                System.out.println(x / y + "..." + x % y);
            } catch (ArithmeticException e) {
                System.out.println("‚O‚Å‚ÍŠ„‚ê‚Ü‚¹‚ñ");
                return;
            }
            break;
        default:        //@•Ï‚È‹L†‚ª“ü—Í‚³‚ê‚½‚çƒGƒ‰[
            System.out.println("³‚µ‚¢Ž®‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");       
            return;
        }
    }
}