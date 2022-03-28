package library;

public class Member {
	private int memberid;
	private String username;
	private String membername;
	private String membersurname;
	private String password;
	private String address;
	private String phone;
	private String membertype;

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMembersurname() {
		return membersurname;
	}

	public void setMembersurname(String membersurname) {
		this.membersurname = membersurname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMembertype() {
		return membertype;
	}

	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}

	@Override
	public String toString() {
		return memberid + " " + username+" "+ membername + " " + membersurname + " " + phone + " " + membertype;
	}

}
