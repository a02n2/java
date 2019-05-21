import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Knowledge3 {
    public static void main(String[] args) {

        if (args.length > 1) {
            System.out.println("ファイルは一つしか指定できません。");
            System.exit(0);
        }

        try {
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "SJIS"));

            List<String> list = new ArrayList<String>();

            // ファイルの中身を一行ずつ読み込み、リストへ格納
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }

            br.close();

            // 　順番に出力
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            // 　逆順に出力
            for (int j = (list.size() - 1); j >= 0; j--) {
                System.out.println(list.get(j));
            }

            // 　"あいうえお"が格納されている行番号を出力
            if (list.contains("あいうえお")){  
                System.out.println((list.indexOf("あいうえお") + 1) + "行目");  
            } else {
                System.out.println("「あいうえお」はファイルに存在しません");
            }

        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりません。");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ファイルを一つ指定してください。");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}