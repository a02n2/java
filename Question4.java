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
        int sum = roop(param, 0, param.length);
        System.out.println(sum);
    }

    /**
     * ���͂��ꂽ�����n�߂���I���܂Ń��[�v���܂��B
     * �v�Z���s���ʃ��\�b�h���Ăяo���A�ŏI�̍��v�l��Ԃ��܂��B
     * @param param ���͂��ꂽ��
     * @param start ���̎n�܂�
     * @param end ���̏I���
     * @return �ŏI�̍��v�l
     */
    public static int roop(String[] param, int start, int end) {

        String mark = "";   // ���Z�L��
        int x = 0;          // ���͒l
        int sum = 0;        // ���v�l 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) { 
                mark = param[i];
            } else if (param[i].equals("(")) { 
                int closeParen = 0; 

                try {
                    closeParen = searchCloseParen(param, i);    // ")"��������
                } catch (Exception e) {     // "()"�̐�������������΃G���[
                    System.out.println("()�𐳂������͂��Ă�������");
                    System.exit(0);
                }

                // "()"���̍��v�l
                sum = calc(mark, sum, roop(param, i + 1, closeParen));

                i = closeParen;    // ")"�̎����烋�[�v�ĊJ    
            } else { 
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) {     // ���͂��ꂽ��������������΃G���[
                    System.out.println("�����𐳂������͂��Ă�������");
                    System.exit(0);
                }

                // ���v�l
                sum = calc(mark, sum, x);

            }
        }
        return sum;
    }

    /**
     * �v�Z�r���̍��v�l�ɐ��l�����Z�܂��͌��Z���܂��B
     * @param mark ���͂��ꂽ���Z�L��
     * @param sum �v�Z�r���̍��v�l
     * @param x�@���͂��ꂽ���l
     * @return�@���݂̍��v�l�ɐ��l�����Z�܂��͌��Z��������
     */
    public static int calc(String mark, int sum, int x) {

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

        if (sum < -10000 || sum > 10000) {      // -10000�`10000���z������G���[
            System.out.println("��-10000�`10000�͈̔͊O�ɂȂ�܂���");
            System.exit(0);
        }

        return sum;
    }

    /**
     * �J�n���ʂɑΉ�����I�����ʂ�T���܂��B
     * @param param ���͂��ꂽ��
     * @param openParen �J�n����
     * @return �I������
     * @throws Exception ()�̐�������Ȃ���
     */
    public static int searchCloseParen(String[] param, int openParen) throws Exception {

        int i = openParen;
        int parenCount = 0; // "()"�̐����J�E���g

        // "("���玮�̍Ō�܂Ń��[�v���A�Ή�����")"��������(count��0�ɂȂ�)��return
        for (; i < param.length; i++) {
            if (param[i].equals("(")) { 
                parenCount++;
            } else if (param[i].equals(")")) { 
                parenCount--;
            }

            if (parenCount == 0) { 
                return i;
            }
        }

        throw new Exception(); // count��0�łȂ���Η�O����
    }
}