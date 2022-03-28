package library;

public class Author {
	private int authorid;
	private String authorname;
	private String authorsurname;

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getAuthorsurname() {
		return authorsurname;
	}

	public void setAuthorsurname(String authorsurname) {
		this.authorsurname = authorsurname;
	}

	@Override
	public String toString() {
		return authorid + " " + authorname + " " + authorsurname;
	}

}
