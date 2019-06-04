import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JavaDB2 {

    private static Connection conn = null;

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5432/" + dbname, username, password);

            // すべての行を表示
            selectAll();

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("削除するレコードのIDを入力してください");
                String id = br.readLine();

                int deleteID = 0;
                // String型からint型に変換
                try {
                    deleteID = getDeleteID(id);
                } catch (NumberFormatException e) {
                    System.out.println("※削除するレコードのIDを正しく入力してください");
                    continue;
                }
                // レコードの存在チェック
                if (!getRecordExists(deleteID)) {
                    continue;
                }
                // 指定されたレコードを削除
                deleteRecord(deleteID);
                
                // 再入力するかどうか
                System.out.println("入力を終了しますか？(0で終了)");
                String str = br.readLine();
                if (continueFlg(str)) {
                    continue;
                }
                break;
            }
            // 削除後のすべての行を表示
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
     * テーブルに登録されているすべての行を表示
     * 
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void selectAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM tasks;");
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
     * @param id 削除するレコードのIDの数字列
     * @return 削除するレコードのID
     * @throws SQLException          データベース・アクセス・エラーまたはその他のエラー
     * @throws NumberFormatException 数字以外が入力されたときのエラー
     */
    public static int getDeleteID(String id) throws SQLException, NumberFormatException {
        int deleteID = Integer.parseInt(id);

        return deleteID;
    }

    /**
     * レコードが存在するかチェック 
     * 存在しない(false),存在する(true)
     * 
     * @param deleteID 削除するレコードのID
     * @return true,false
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static boolean getRecordExists(int deleteID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?;");
        ps.setInt(1, deleteID);
        ResultSet rs = ps.executeQuery();

        boolean isExists = rs.next();
        if (!isExists) {
            System.out.println("※該当するIDは登録されていません");
            return false;
        }
        return true;
    }

    /**
     * 指定されたIDのレコードを削除
     * 
     * @param deleteID 削除するレコードのID
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void deleteRecord(int deleteID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks where id = ?;");
        ps.setInt(1, deleteID);
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