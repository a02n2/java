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
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5432/" + dbname, username, password);

            // テーブルに登録されているすべての行を表示
            selectAll();

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("更新するIDを入力してください");
                String id = br.readLine();

                // String型からint型に変換
                int updateID = 0;
                try {
                    updateID = getUpdateID(id);
                } catch (NumberFormatException e) {
                    System.out.println("※IDを正しく入力してください");
                    continue;
                }
                // レコードの存在チェック
                if (!getRecordExists(updateID)) {
                    continue;
                }

                System.out.println("更新したいタイトルとコメントをコンマで区切って入力してください");
                String titleComment[] = {};
                try {
                    // コメントとタイトルを読み込み、分割
                    titleComment = splitTC(br.readLine());
                } catch (Exception e) {
                    System.out.println("タイトルとコメントを正しく入力してください");
                    continue;
                }
                // タイトルとコメントの長さチェック
                if (!rangeCheck(titleComment[0], 32, "タイトル") || !rangeCheck(titleComment[1], 256, "コメント")){   
                    continue;
                }
                // レコードを更新
                updateRecord(updateID, titleComment[0], titleComment[1]);

                // 更新を終了するか
                System.out.println("更新を終了しますか(0で終了)");
                String str = br.readLine();
                if (continueFlg(str)) {
                    continue;
                }
                break;
            }
            // テーブルに登録されているすべての行を表示
            selectAll();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
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
     * 汎用性を重視する為にStringからint型に変換
     * 
     * @param id 更新するレコードのIDの数字列
     * @return 更新するレコードのID
     * @throws NumberFormatException 数字以外が入力されたときのエラー
     */
    public static int getUpdateID(String id) throws NumberFormatException {
        return Integer.parseInt(id);
    }

    /**
     * レコードが存在するかチェック   
     * 存在しない(false),存在する(true)
     * 
     * @param updateID 更新するレコードのID
     * @return true,false
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static boolean getRecordExists(int updateID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?");
        ps.setInt(1, updateID);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("※該当するIDは登録されていません");
            return false;
        }
        return true;
    }

    /**
     * 読み取った文字列をコンマでタイトルとコメントに分割
     * 文字列にコンマがないときは例外発生
     * 
     * @param tc 読み取った文字列
     * @return タイトル、コメント
     * @throws Exception 文字列にコンマがない時
     */
    public static String[] splitTC(String tc) throws Exception {
        if(!tc.contains(",")){
            throw new Exception();
        }
        String[] titleComment = tc.split(",", 2);
        return titleComment;
    }

    /**
     * タイトルとコメントの長さをチェック   
     * 0 < タイトル < 32   
     * 0 < コメント　< 256
     * 
     * @param titleComment　タイトル、コメント
     * @param length　チェック対象の最大の長さ
     * @param text　チェック対象のタイトル
     */
    public static boolean rangeCheck(String titleComment, int length, String txt) {
        if (titleComment.length() < 1 || titleComment.length() > length) {
            System.out.println("※" + txt + "が範囲外です");
            return false;
        }
        return true;
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
        PreparedStatement ps = conn.prepareStatement("update tasks set title = ?,comment = ? where id = ?;");
        ps.setString(1, title);
        ps.setString(2, comment);
        ps.setInt(3, updateID);
        ps.executeUpdate();
    }

    /**
     * 削除を終了するのか    
     * 0で終了(false)、0以外続行(true)
     * 
     * @param str 0かそれ以外の文字
     * @return true,false
     */
    public static boolean continueFlg(String str) {
        if (str.equals("0")) {
            System.out.println("終了します");
            return false;
        }
        System.out.println("続行します");
        return true;
    }
}