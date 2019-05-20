import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Knowledge2 {
    public static void main(String[] args) {
        try {
            // ファイル読み込み
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(args[0]));
            // ファイル書き込み
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(args[1]));

            // ファイルを読み込み、別ファイルへ書き込み
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
            
            bis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            System.out.println("読み込むファイルが見つかりません");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ファイルを指定してください。");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}