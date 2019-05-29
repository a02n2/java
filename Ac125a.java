import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac125a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("A=�r�X�P�b�g������鎞��,B=A�b�Ԃɍ����r�X�P�b�g�̖���,T=�N������");
        System.out.println("ABT���󔒂ŋ�؂��ē��͂��Ă�������(1 <= A,B,T <= 20)");
        String str = br.readLine();
        String[] timePieces = str.split(" ", 3);

        if (timePieces.length != 3) {
            System.out.println("���l�𐳂�����؂��ē��͂��Ă�������");
            System.exit(0);
        }

        // 3�����������̂�String�^����int�^�ɕϊ������
        int seconds = stringToInt(timePieces[0]);
        int pieces = stringToInt(timePieces[1]);
        int time = stringToInt(timePieces[2]);

        // ����`�F�b�N
        rangeCheck(seconds, "A");
        rangeCheck(pieces, "B");
        rangeCheck(time, "T");

        // ��������̂��v�Z���A�����r�X�P�b�g�̖������v�Z
        int sumPieces = pieces * (time / seconds);
        System.out.println(sumPieces);
    }

    /**
     * �ėp�����d������ׂ�String����int�^�ɕϊ� �i�ϊ����s����exit�j
     * 
     * @param timePieces �r�X�P�b�g������鎞��,A�b�Ԃɍ����r�X�P�b�g�̖���,�N������
     * @return �ϊ������l
     */
    public static int stringToInt(String timePieces) {
        int timePieces_int = 0;
        try {
            timePieces_int = Integer.parseInt(timePieces);
        } catch (NumberFormatException e) {
            System.out.println("���l�𐳂������͂��Ă�������");
            System.exit(0);
        }
        return timePieces_int;
    }

    /**
     * ����̃`�F�b�N���s��
     * 1 <= �r�X�P�b�g������鎞��,A�b�Ԃɍ����r�X�P�b�g�̖���,�N������ <=20
     * 
     * @param num �r�X�P�b�g������鎞��,A�b�Ԃɍ����r�X�P�b�g�̖���,�N������
     * @param text �`�F�b�N�Ώۂ̃^�C�g��
     */
    public static void rangeCheck(int num, String text) {
        if (num < 1 || num > 20) {
            System.out.println(text + "���͈͊O�ł�");
            System.exit(0);
        }
    }
}