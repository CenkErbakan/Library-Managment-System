package library;

public class Book {
	private int bookid;
	private String bookname;
	private int authorid;
	private int booktypeid;
	private int pressyear;

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public int getBooktypeid() {
		return booktypeid;
	}

	public void setBooktypeid(int booktypeid) {
		this.booktypeid = booktypeid;
	}

	

	public int getPressyear() {
		return pressyear;
	}

	public void setPressyear(int pressyear) {
		this.pressyear = pressyear;
	}

	@Override
	public String toString() {
		return bookid + " " + bookname + " " + authorid + " " + booktypeid + " " + pressyear;
	}

}
