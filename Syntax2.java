public class Syntax2 {
    public static void main(String[] args) throws Exception {

        // NullPointerException null�Q��
        try {
            String str = null;
            System.out.println(str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ArrayIndexOutOfBoundsException �z��͈̔͊O�Q��
        try {
            int[] i = { 1 };
            System.out.println(i[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ClassCastException �݊����̂Ȃ��I�u�W�F�N�g�^�̕ϊ�
        try {
            Object obj = "123";
            System.out.println((Integer) obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}