import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("����(-10000�`10000)�̉��������Z���󔒂ŋ�؂��ē��͂��Ă�������");
        System.out.println("���Z�L���́u+(�a)�v�u-(��)�v�ł�");
        System.out.println("��������̉��Z���s���܂�");
        String input = br.readLine();
        String[] param = input.split(" ");
        int sum = calc(param, 0, param.length);
        System.out.println(sum);
    }

    // ���͂��ꂽ���̌v�Z
    public static int calc(String[] param, int start, int end) {

        String mark = ""; // ���Z�L��
        int x = 0; // ���͒l
        int sum = 0; // ���v�l 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) { // ���͒l��"+"��"-"�̏ꍇ
                mark = param[i];
            } else if (param[i].equals("(")) { // ���͒l��"("�̏ꍇ
                int rightParen = 0; // ")"

                try {
                    rightParen = searchRightParen(param, i); // ")"��������
                } catch (Exception e) { // "()"�̐�������������΃G���[
                    System.out.println("()�𐳂������͂��Ă�������");
                    System.exit(0);
                }

                // "()"�����v�Z
                sum = calc1(mark, sum, calc(param, i + 1, rightParen));

                i = rightParen; // ")"�̎����烋�[�v�ĊJ    
            } else { // ���͒l��"+"�A"-"�A"("�ȊO�̏ꍇ
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) { // ���͂��ꂽ��������������΃G���[
                    System.out.println("�����𐳂������͂��Ă�������");
                    System.exit(0);
                }

                if (x < -10000 || x > 10000) { // ���͒l��-10000~10000�łȂ���΃G���[
                    System.out.println("��-10000�`10000�œ��͂��Ă�������");
                    System.exit(0);
                }

                // ���v�l�̌v�Z
                sum = calc1(mark, sum, x);

            }
        }
        return sum;
    }

    // �l�̉��Z�A���Z
    public static int calc1(String mark, int sum, int x) {

        // ���Z�L���ɂ���Čv�Z��sum�ɑ��
        switch (mark) {
        case "":
            sum = x;
            break;
        case "+":
            sum += x;
            break;
        case "-":
            sum -= x;
            break;
        }

        if (sum < -10000 || sum > 10000) { // �v�Z�r����-10000�`10000���z������G���[
            System.out.println("���v�Z�r����-10000�`10000�͈̔͊O�ɂȂ�܂���");
            System.exit(0);
        }

        return sum;
    }

    // ")"��������
    public static int searchRightParen(String[] param, int leftparan) throws Exception {

        int i = leftparan;
        int parencount = 0; // "()"�̐����J�E���g

        // "("���玮�̍Ō�܂Ń��[�v��")"��T��
        for (; i < param.length; i++) {
            if (param[i].equals("(")) { // "("���łĂ�����count+1
                parencount++;
            } else if (param[i].equals(")")) { // ")"���łĂ�����count-1
                parencount--;
            }

            if (parencount == 0) { // count��0(=�Ή�����")"���݂���)��return
                return i;
            }
        }

        throw new Exception(); // count��0�łȂ���Η�O����
    }
}