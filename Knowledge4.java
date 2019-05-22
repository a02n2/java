import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

public class Knowledge4 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("ファイルは一つ指定してください");
            System.exit(0);
        }

        try {
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "SJIS"));

            Map<String, String> map = new LinkedHashMap<String, String>();

            // ファイルの中身を一行ずつ読み込み、空白で分割、マップへ格納
            String str;
            while ((str = br.readLine()) != null) {
                String[] keyValue = str.split(" ", 2);

                map.put(keyValue[0], keyValue[1]);
            }
            
            br.close();

            // マップに格納されているすべてのキーと値を出力
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }

            // 　キー「TOKYO」の値を出力
            if (map.containsKey("TOKYO")) {
                System.out.println("TOKYO=>" + map.get("TOKYO"));
            } else {
                System.out.println("「TOKYO」は存在しません");
            }

        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりません");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}