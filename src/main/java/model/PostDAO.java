package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList; // 可変長配列を表すクラス
import java.util.Date;
import java.util.List;

// データベースとの接続
public class PostDAO {
	private static final String DB_UEL = "jdbc:oracle:thin:System/orapas@//localhost:1521/XEPDB1";
	
	// getPosts()メソッド
	public List<Post> getPosts() {
		List<Post> posts = new ArrayList<>();
		
		try {
			// oracle JDBCドライバーをロード
			Class.forName("oracle.jdbc.OracleDriver");
			
            // データベースに接続
			Connection conn = DriverManager.getConnection(DB_UEL);
			
			// sqlクエリ
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts ORDER BY created_at DESC");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// データベースからデータ取得
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Date createdAt = rs.getDate("created_at");
				
				// 取得したデータを使ってpostオブジェクトを作成し、リストに追加
				Post post = new Post(id, name, text, createdAt);
				posts.add(post);
			}
		
			// リソースの解放
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 投稿リストを返す
		return posts;
	}
	
	//createPost()メソッド
	public void createPost(String name, String text) {
		try {
			// oracle JDBCドライバ―をロード
			Class.forName("oracle.jdbc.OracleDriver");
			
			// データベースに接続
			Connection conn = DriverManager.getConnection(DB_UEL);
			
			// sqlクエリの準備
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO posts (name, text) VALUES (?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, text);
			
			// sqlクエリ実行
			stmt.executeUpdate();
			
			// リソースの解放
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}