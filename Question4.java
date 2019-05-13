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
        int sum = loopCalc(param, 0, param.length);
        System.out.println(sum);
    }

    /**
     * �ϐ�param�Ɋi�[����Ă���v�Z����ϐ�start����J�E���^�[���ϐ�end�ɒB����܂Ń��[�v
     * �ϐ�param���牉�Z�L���A���l���擾 ()�����݂���΍ċA�Ăяo�����s���A���()���̏���������
     * ���Z���Z�̏�����calc���Ăяo��
     * 
     * @param param ���͂��ꂽ��
     * @param start ���̎n�܂�
     * @param end   ���̏I���
     * @return ���v�l
     */
    public static int loopCalc(String[] param, int start, int end) {

        String mark = ""; // ���Z�L��
        int x = 0; // ���͒l
        int sum = 0; // ���v�l 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) {
                mark = param[i];
            } else if (param[i].equals("(")) {
                int closeParen = 0;

                try {
                    closeParen = searchCloseParen(param, i); // ")"��������
                } catch (Exception e) { // ()���΂ɂȂ��Ă��Ȃ���΃G���[
                    System.out.println("()�𐳂������͂��Ă�������");
                    System.exit(0);
                }

                // "()"���̍��v�l
                try {
                    sum = calc(mark, sum, loopCalc(param, i + 1, closeParen));
                } catch (Exception e) { // -10000�`10000�𒴂���ƃG���[
                    System.out.println("��-10000�`10000�͈̔͊O�ɂȂ�܂���");
                    System.exit(0);
                }

                i = closeParen; // ")"�̎����烋�[�v�ĊJ    
            } else {
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) { // ���͂��ꂽ��������������΃G���[
                    System.out.println("�����𐳂������͂��Ă�������");
                    System.exit(0);
                }

                // ���v�l
                try {
                    sum = calc(mark, sum, x);
                } catch (Exception e) { // -10000�`10000�𒴂���ƃG���[
                    System.out.println("��-10000�`10000�͈̔͊O�ɂȂ�܂���");
                    System.exit(0);
                }
            }
        }
        return sum;
    }

    /**
     * �v�Z�r���̍��v�l�ɐ��l�����Z�܂��͌��Z
     * 
     * @param mark           ���͂��ꂽ���Z�L��
     * @param sum            �v�Z�r���̍��v�l
     * @param x�@���͂��ꂽ���l @return�@���݂̍��v�l�ɐ��l�����Z�܂��͌��Z��������
     * @throws Exception -10000����10000�𒴂�����
     */
    public static int calc(String mark, int sum, int x) throws Exception {

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

        if (sum < -10000 || sum > 10000) { // -10000�`10000���z�������O����
            throw new Exception();
        }

        return sum;
    }

    /**
     * �J�n���ʂɑΉ�����I�����ʂ�T��
     * 
     * @param param     ���͂��ꂽ��
     * @param openParen �J�n����
     * @return �I������
     * @throws Exception ()���΂ɂȂ��Ă��Ȃ���
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