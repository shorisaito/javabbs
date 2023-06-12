package model;

import java.util.Date;

//postクラスの定義
public class Post {
	private int id;
	private String name;
	private String text;
	private Date createdAt;

	// コンストラクタ
	public Post(int id, String name, String text, Date createdAt) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.createdAt = createdAt;
	}
	
	// ターゲットメソッド
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

}
