import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Knowledge1 {
    public static void main(String[] args) {

        try {
            // ファイル読み込み
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(fr));
            
            // ファイル書き込み
            File fw = new File(args[1]);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fw),"UTF-8"));

            // ファイルを読み込み、内容を表示し、異なるファイルへ書き込み
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                bw.write(str);
                bw.write("\r\n");
            }
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("引数としてファイルを指定してください");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}