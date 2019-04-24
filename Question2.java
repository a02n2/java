import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Question2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("����(-10000�`10000)�̎l�����Z���󔒂ŋ�؂��ē��͂��Ă�������");
        System.out.println("���Z�L���́u+(�a)�v�u-(��)�v�u*(��)�v�u/(��)�v�ł�");
        String input = br.readLine();
        String[] param = input.split(" ");

        short x = 0;
        short y = 0;

        try {
            x = Short.parseShort(param[0]);         //short�^�ɕϊ�
            y = Short.parseShort(param[2]);
        } catch (NumberFormatException n) {
            System.out.println("-10000�`10000�œ��͂��Ă�������");
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("������������͂��Ă�������");
            return;
        }

        if (x > 10000 || x < -10000 || y > 10000 || y < -10000) {       //-10000~10000�łȂ���΃G���[
            System.out.println("-10000�`10000�œ��͂��Ă�������");
            return;
        }

        // ���͂��ꂽ���Z�L���ɕ����Čv�Z�A�o��
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
                System.out.println("�O�ł͊���܂���");
                return;
            }
            break;
        default:        //�@�ςȋL�������͂��ꂽ��G���[
            System.out.println("������������͂��Ă�������");       
            return;
        }
    }
}