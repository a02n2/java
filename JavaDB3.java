import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JavaDB3 {
    private static Connection conn = null;

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        String portnumber = "5432";
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + portnumber + "/" + dbname,
                    username, password);

            // テーブルに登録されているすべての行を表示
            selectAll();

            while (true) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("更新するIDを入力してください");
                    String id_str = br.readLine();

                    // String型からint型に変換
                    int id = Integer.parseInt(id_str);

                    // レコードの存在チェック
                    recordExists(id);

                    // コメントとタイトルを読み込み、分割
                    System.out.println("更新したいタイトルとコメントをコンマで区切って入力してください");
                    String[] titleComment = splitComma(br.readLine());

                    // タイトルとコメントの長さチェック
                    rangeCheck(titleComment[0], titleComment[1]);

                    // レコードを更新
                    updateRecord(id, titleComment[0], titleComment[1]);
                    System.out.println("レコードを更新しました");

                    // 更新を終了するか
                    System.out.println("更新を終了しますか(0で終了)");
                    String str = br.readLine();
                    if (continueFlg(str)) {
                        break;
                    }
                } catch (SQLException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    System.out.println("※IDを正しく入力してください");
                    continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            // テーブルに登録されているすべての行を表示
            selectAll();
        } catch (SQLException e) {
            System.out.println(e);
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * テーブルに登録されている全ての行を表示
     * 
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void selectAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id"));
            System.out.print("/");
            System.out.print(rs.getString("title"));
            System.out.print("/");
            System.out.print(rs.getString("comment"));
            System.out.print("\n");
        }
    }

    /**
     * レコードが存在するかチェック 
     * 
     * @param id 更新するレコードのID
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     * @throws Exception    指定されたレコードが存在しない時
     */
    public static void recordExists(int id) throws SQLException, Exception {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new Exception("※該当するIDが存在しません");
        }
    }

    /**
     * 読み取った文字列をコンマでタイトルとコメントに分割 
     * 
     * @param str 読み取った文字列
     * @return タイトル、コメント
     * @throws Exception 文字列にコンマがない時
     */
    public static String[] splitComma(String str) throws Exception {
        if (!str.contains(",")) {
            throw new Exception("※タイトルとコメントを正しく入力してください");
        }
        String[] strings = str.split(",", 2);
        return strings;
    }

    /**
     * タイトルとコメントの長さをチェック   
     * 0 < タイトル < 32    
     * コメント < 256 
     * 
     * @param title　タイトル
     * @param comment　コメント
     * @throws Exception タイトルかコメントの長さが範囲外の時
     */
    public static void rangeCheck(String title, String comment) throws Exception {
        boolean checkFlg = false;
        String titleErr = "";
        String commentErr = "";
        if (title.length() < 1 || title.length() > 32) {
            checkFlg = true;
            titleErr = "※タイトルが範囲外です";
        }
        if (comment.length() > 256) {
            checkFlg = true;
            commentErr = "※コメントが長すぎます";
        }
        if(checkFlg){
            throw new Exception(titleErr + commentErr);
        }
    }

    /**
     * 指定されたIDのレコードを更新
     * 
     * @param id 更新するレコードのID
     * @param title    更新したいタイトル
     * @param comment  更新したいコメント
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void updateRecord(int id, String title, String comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("update tasks set (title, comment) = (?, ?) where id = ?;");
        ps.setString(1, title);
        ps.setString(2, comment);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    /**
     * 削除を終了するのか     
     * 0で終了(true)、0以外続行(false)
     * 
     * @param str 0かそれ以外の文字
     * @return true,false
     */
    public static boolean continueFlg(String str) {
        if (str.equals("0")) {
            System.out.println("終了します");
            return true;
        }
        System.out.println("続行します");
        return false;
    }
}