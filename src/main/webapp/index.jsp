<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Post" %>
<!DOCTYPE html>
<html>
<head>
<title>掲示板</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel="stylesheet" href="./css/post.css">
</head>
<body>
<h1>掲示板</h1>
	<div class="post-wrapper">
			<h2>入力欄</h2>
		<form action="posts" method="post">
			<div class="post-item">
				<label for="name">名前</label><br>
				<input type="text" name="name" class="name-box" id="name" required><br>
				<p id="name-error-msg" class="error-msg">エラー：名前を入力してください。</p>
			</div>
			<div class="post-item">
				<label for="text">コメント</label><br>
				<textarea name="text" class="textarea" id="text" required></textarea><br>
				<p id="text-error-msg" class="error-msg">エラー：コメントを入力してください。</p>
			</div>
			<input type="submit" value="投稿する" class="submit-btn" disabled>
		</form>
		
		<script>
			// 名前とコメントの入力欄の要素を取得
		 	const nameInput = document.getElementById("name");
			const textInput = document.getElementById("text");

			// 送信ボタンの要素を取得
			const submitBtn = document.querySelector(".submit-btn");

			// エラーメッセージの要素を取得
			const nameErrorMsg = document.getElementById("name-error-msg");
			const textErrorMsg = document.getElementById("text-error-msg");

			// 名前とコメントの入力欄の値が変更されたときにvalidateInputs関数を呼び出す
			nameInput.addEventListener("input", validateInputs);
			textInput.addEventListener("input", validateInputs);

			// 名前とコメントの入力欄の値が変更されたときに呼ばれる関数
			function validateInputs() {
				// 名前を入力チェック
			   if (nameInput.value.trim() !== "") {
			     nameErrorMsg.style.display = "none"; // エラーメッセージ非表示
			   } else {
			     nameErrorMsg.style.display = "block"; // エラーメッセージ表示
			   }
			
			   if (textInput.value.trim() !== "") {
			     textErrorMsg.style.display = "none"; // エラーメッセージ非表示
			   } else {
			     textErrorMsg.style.display = "block"; // エラーメッセージ表示
			   }

				// 名前とコメントが入力されている場合、送信ボタンを有効にする
			   if (nameInput.value.trim() !== "" && textInput.value.trim() !== "") {
			     submitBtn.disabled = false; // 送信ボタンを有効化
			   } else {
			     submitBtn.disabled = true; // 送信ボタンを無効化
			   }
			}
		</script>
	</div>
	<div class="display-wrapper">
		<h2>コメント一覧</h2>
			<!-- postsオブジェクト取得 -->
			<%for (Post post : (List<Post>) request.getAttribute("posts")) { %>
				<div class="display-display">
					<p class="name-display">名前：<%= post.getName() %>　投稿日：<%= post.getCreatedAt() %></p>
					<p class="text-display"><%= post.getText() %></p>
				</div>
			<% } %>
		</div>
	</div>
</body>
</html>