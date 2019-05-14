public class Syntax2 {
    public static void main(String[] args) throws Exception {

        // NullPointerException null参照
        try {
            String str = null;
            System.out.println(str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ArrayIndexOutOfBoundsException 配列の範囲外参照
        try {
            int[] i = { 1 };
            System.out.println(i[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ClassCastException 互換性のないオブジェクト型の変換
        try {
            Object obj = "123";
            System.out.println((Integer) obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}