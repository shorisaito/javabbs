package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; // HTTPリクエストに対して処理を行うクラス
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.PostDAO;

// PostServletクラスを呼び出すURL
@WebServlet("/posts")

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDAO;
	
	// 初期化時にインスタンスを作成するinit()メソッド
	public void init() {
		postDAO = new PostDAO();
	}
	
	// 投稿一覧の表示を行うdoGet()メソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		// PostDAOを使用して投稿一覧を取得
		List<Post> posts = postDAO.getPosts();
		
		// 投稿一覧をリクエスト属性に設定
		request.setAttribute("posts", posts);
		
		// index.jspにフォワードして投稿一覧を表示
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	// 新しい投稿の作成を行うdoPostメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//リクエストの文字エンコーデイング
		request.setCharacterEncoding("UTF-8");
		
		// フォームから投稿の名前と内容を取得
		String name = request.getParameter("name");
		String text = request.getParameter("text");
		
		// postDAOを使用して新しい投稿を作成
		postDAO.createPost(name, text);
		
		//投稿一覧にリダイレクトする
		response.sendRedirect(request.getContextPath() + "/posts");
	}

}
