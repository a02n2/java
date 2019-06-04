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
            // 指定されたレコードを削除
            deleteRecord();
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
            System.out.println("\n");
        }
    }

    /**
     * 指定されたIDのレコードを削除
     * 
     * @throws IOException 入出力エラー
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void deleteRecord() throws IOException, SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks where id = ?;");

        while (true) {

            // 削除するレコードのIDを取得
            int deleteID = getDeleteID();

            // 入力されたIDのレコードを削除
            ps.setInt(1, deleteID);
            ps.executeUpdate();

            // 削除を終了するかしないか
            if (deleteEnd()) {
                break;
            }
        }
    }

    /**
     * 削除するレコードのIDを取得
     * 
     * @return 削除するレコードのID
     * @throws IOException 入出力エラー
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static int getDeleteID() throws IOException, SQLException {
        int deleteID = 0;
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?;");

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("削除するレコードのIDを入力してください");
            String str = br.readLine();

            try {
                // 汎用性を重視する為にStringからint型に変換
                deleteID = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("※削除するレコードのIDを正しく入力してください");
                continue;
            }

            // 入力されたIDが存在するかチェック
            ps.setInt(1, deleteID);
            ResultSet rs = ps.executeQuery();

            boolean isExists = rs.next();
            if (!isExists) {
                System.out.println("※該当するIDは登録されていません");
                continue;
            }

            break;
            
        }
        return deleteID;
    }

    /**
     * 削除を終了するのか
     * 0で終了(true)、0以外続行(false)
     * 
     * @return true,false
     * @throws IOException 入出力エラー
     */
    public static boolean deleteEnd() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("入力を終了しますか？(0で終了)");
        String str = br.readLine();

        if (str.equals("0")) {
            System.out.println("終了します");
            return true;
        } else {
            System.out.println("続行します");
            return false;
        }
    }
}