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
                    int updateID = Integer.parseInt(id_str);

                    // レコードの存在チェック
                    getRecordExists(updateID);

                    // コメントとタイトルを読み込み、分割
                    System.out.println("更新したいタイトルとコメントをコンマで区切って入力してください");
                    String[] titleComment = splitComma(br.readLine());

                    // タイトルとコメントの長さチェック
                    rangeCheck(titleComment[0], titleComment[1]);

                    // レコードを更新
                    updateRecord(updateID, titleComment[0], titleComment[1]);
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
     * 存在しないときは例外発生
     * 
     * @param updateID 更新するレコードのID
     * @return true,false
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     * @throws Exception    指定されたレコードが存在しない時
     */
    public static void getRecordExists(int updateID) throws SQLException, Exception {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?");
        ps.setInt(1, updateID);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new Exception("※該当するIDが存在しません");
        }
    }

    /**
     * 読み取った文字列をコンマでタイトルとコメントに分割 
     * 文字列にコンマがないときは例外発生
     * 
     * @param tc 読み取った文字列
     * @return タイトル、コメント
     * @throws Exception 文字列にコンマがない時
     */
    public static String[] splitComma(String tc) throws Exception {
        if (!tc.contains(",")) {
            throw new Exception("※タイトルとコメントを正しく入力してください");
        }
        String[] titleComment = tc.split(",", 2);
        return titleComment;
    }

    /**
     * タイトルとコメントの長さをチェック   
     * 0 < タイトル < 32    
     * 0 < コメント < 256 (null　OK)
     * 
     * @param title　タイトル
     * @param comment　コメント
     */
    public static void rangeCheck(String title, String comment) throws Exception {
        if (title.length() < 1 || title.length() > 32) {
            throw new Exception("※タイトルが範囲外です");
        }
        if (comment.length() > 256) {
            throw new Exception("※コメントが長すぎます");
        }
    }

    /**
     * 指定されたIDのレコードを更新
     * 
     * @param updateID 更新するレコードのID
     * @param title    更新したいタイトル
     * @param comment  更新したいコメント
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void updateRecord(int updateID, String title, String comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("update tasks set (title, comment) = (?, ?) where id = ?;");
        ps.setString(1, title);
        ps.setString(2, comment);
        ps.setInt(3, updateID);
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