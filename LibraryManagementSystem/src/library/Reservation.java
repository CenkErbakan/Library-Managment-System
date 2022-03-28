package library;

import java.util.Date;

public class Reservation {
	private int reservationid;
	private Date reservationdate;
	private int bookid;
	private int memberid;
	private Date deliverydate;
	private Date deliverydateactual;
	private int rate;

	public int getReservationid() {
		return reservationid;
	}

	public void setReservationid(int reservationid) {
		this.reservationid = reservationid;
	}

	public Date getReservationdate() {
		return reservationdate;
	}

	public void setReservationdate(Date reservationdate) {
		this.reservationdate = reservationdate;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	public Date getDeliverydateactual() {
		return deliverydateactual;
	}

	public void setDeliverydateactual(Date deliverydateactual) {
		this.deliverydateactual = deliverydateactual;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return reservationid + " " + reservationdate + " " + bookid + " " + memberid + " " + deliverydate + " "
				+ deliverydateactual + " " + rate;
	}

}
