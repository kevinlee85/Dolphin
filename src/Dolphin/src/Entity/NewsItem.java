package Dolphin.src.Entity;

//NewsItem is a sub-class of ImageAndText which can be used by the News function, it involves six fields.
public class NewsItem extends ImageAndText {
	private String newsId = null;
	private String newsContent = null;

	public NewsItem() {

	}

	public NewsItem(String imageId, String title, String brief, String date) {
		super(imageId, title, brief, date);
	}

	public NewsItem(String id, String imageId, String title, String brief,
			String date, String content) {
		super(imageId, title, brief, date);
		this.newsId = id;
		this.newsContent = content;
	}

	public void setId(String id) {
		this.newsId = id;
	}

	public void setContent(String content) {
		this.newsContent = content;
	}

	public String getId() {
		return newsId;
	}

	public String getContent() {
		return newsContent;
	}
}
