package library;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Management {

	public static Scanner input;
	public static Member activemember;
	public static ArrayList<BookType> booktypelist;
	public static ArrayList<Author> authorlist;
	public static ArrayList<Member> memberlist;
	public static ArrayList<Book> booklist;
	public static ArrayList<Reservation> reservationlist;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String choise;
		input = new Scanner(System.in);

		booktypelist = FileOperations.readBooktypes();
		authorlist = FileOperations.readAuthors();
		memberlist = FileOperations.readMembers();
		booklist = FileOperations.readBooks();
		reservationlist = FileOperations.readreservations();

		while (true) {
			System.out.println("1. login");
			System.out.println("2. log out");
			System.out.println();
			System.out.print("your choice: ");
			choise = input.nextLine();
			if (choise.equals("1")) {

				if (login() == true) {
					mainmenu();
				} else {
					System.out.println("user name or password is not valid");
				}
			} else if (choise.equals("2")) {
				break;
			}
			else{
				System.out.println("invalid choice please try again");
			}

		}

		// write Lists to files
		FileOperations.writeBooktypes(booktypelist);
		FileOperations.writeAuthors(authorlist);
		FileOperations.writeMembers(memberlist);
		FileOperations.writeBooks(booklist);
		FileOperations.writeReservations(reservationlist);

	}

	public static boolean login() {
		String username;
		String password;

		System.out.print("username : ");
		username = input.nextLine();
		System.out.print("password : ");
		password = input.nextLine();

		Member loginmember = getloginuser(username, password);

		if (loginmember != null) {
			activemember = loginmember;

			return true;
		} else {
			return false;
		}

	}

	public static Member getloginuser(String username, String password) {

		for (Member m : memberlist) {
			if (m.getUsername().equals(username) && m.getPassword().equals(password)) {
				return m;
			}
		}

		return null;
	}

	public static void mainmenu() {
		String choise;
		while (true) {

			if (activemember.getMembertype().equals("admin")) {

				System.out.println("1.reservation operations");
				System.out.println("2.book operations");
				System.out.println("3.member operations");
				System.out.println("4.booktype operations");
				System.out.println("5.author operations");
				System.out.println("6.book advise");
				System.out.println("9.return to login menu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("1")) {
					reservationoperations();
				} else if (choise.equals("2")) {
					bookoperations();
				} else if (choise.equals("3")) {
					memberoperations();
				} else if (choise.equals("4")) {
					booktypeoperations();
				} else if (choise.equals("5")) {
					authoroperations();
				} else if (choise.equals("6")) {
					bookadvise();
				} else if (choise.equals("9")) {
					// return to login menu
					break;
				} else {
					System.out.println("invalid choise");
				}

			} else if (activemember.getMembertype().equals("member")) {

				System.out.println("2.book operations");

				System.out.println("4.booktype operations");
				System.out.println("5.author operations");
				System.out.println("9.return to login menu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("2")) {
					bookoperations();
				} else if (choise.equals("4")) {
					booktypeoperations();
				} else if (choise.equals("5")) {
					authoroperations();
				} else if (choise.equals("9")) {
					// return to login menu
					break;
				} else {
					System.out.println("invalid choise");
				}
			}

		}
	}

	////////////// reservation section
	public static void reservationoperations() {

		if (!activemember.getMembertype().equals("admin")) {
			// if current user is not admin then return to main menu
			return;
		}
		String choise;
		while (true) {
			System.out.println("reservation menu");
			System.out.println("");
			System.out.println("1. book reservation");
			System.out.println("2. book return");
			System.out.println("3. cancel reservation");
			System.out.println("4. List all reservations");
			System.out.println("5. List all datecome reservations");
			System.out.println("6. List all datepassed book reservations");
			System.out.println("9. return to main menu");

			System.out.println();
			System.out.print("your choise: ");
			choise = input.nextLine();

			if (choise.equals("1")) {
				bookReservation();
			} else if (choise.equals("2")) {
				bookReturn();
			} else if (choise.equals("3")) {
				cancelReservation();
			} else if (choise.equals("4")) {
				listAllReservations();
			} else if (choise.equals("5")) {
				listAllDatecomeReservations();
			} else if (choise.equals("6")) {
				listAllDatepassedBookReservations();
			} else if (choise.equals("9")) {
				// return to main menu
				break;
			} else {
				System.out.println("invalid choise");
			}
		}

	}

	public static void bookReservation() {
		Reservation reservation = new Reservation();

		int reservationid;
		int bookid;
		String strreservationdate;
		int memberid;
		String strdeliverydate;

		// String deliverydateactual;
		// int rate;
		try {
			System.out.println("book reservation");
			System.out.println();

			System.out.print("reservationid   :");
			reservationid = input.nextInt();
			input.nextLine();
			System.out.print("reservationdate : ");
			strreservationdate = input.nextLine();

			System.out.print("bookid          :");
			bookid = input.nextInt();

			input.nextLine();

			System.out.print("memberid        :");
			memberid = input.nextInt();
			input.nextLine();

			System.out.print("deliverydate    :");
			strdeliverydate = input.nextLine();

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			Date reservationdate = df.parse(strreservationdate);
			Date deliverydate = df.parse(strdeliverydate);

			// reservationidnin onceden kullanilip kullanilmadigi kontrol edelebilir.

			reservation.setReservationid(reservationid);
			reservation.setReservationdate(reservationdate);
			reservation.setBookid(bookid);
			reservation.setMemberid(memberid);
			reservation.setDeliverydate(deliverydate);

			reservationlist.add(reservation);

		} catch (ParseException e) {

			System.out.println(e.getMessage());

		}

	}

	public static void bookReturn() {

		int id;

		String strdeliverydateactual;
		int rate;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		try {

			String answer;
			System.out.println("book return");
			System.out.print("enter the the id of the reservation  to be updated: ");
			id = input.nextInt();
			input.nextLine();
			Reservation reservation = null;
			for (Reservation r : reservationlist) {
				if (r.getReservationid() == id) {
					reservation = r;
					break;
				}

			}

			if (reservation == null) {
				System.out.println("id is not found");
				return;
			}

			System.out.println("reservation date   : " + df.format(reservation.getReservationdate()));
			System.out.println("bookid             : " + reservation.getBookid());
			System.out.println("memberid           : " + reservation.getMemberid());
			System.out.println("deliverydate       : " + df.format(reservation.getDeliverydate()));
			System.out.println();

			System.out.println("are you sure you want to continue(e/h)?");
			answer = input.nextLine();

			if (answer.toLowerCase().equals("e")) {

				System.out.print("deliverydateactual  :");
				strdeliverydateactual = input.nextLine();
				System.out.print("rate                :");
				rate = input.nextInt();
				input.nextLine();

				Date deliverydateactual = df.parse(strdeliverydateactual);

				reservation.setDeliverydateactual(deliverydateactual);
				reservation.setRate(rate);
			}

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void cancelReservation() {
		int id;
		String answer;
		System.out.println("cancel reservation");
		System.out.print("enter the the id of the reservation  to be canceled: ");
		id = input.nextInt();
		input.nextLine();
		Reservation reservation = null;
		for (Reservation r : reservationlist) {
			if (r.getReservationid() == id) {
				reservation = r;
				break;
			}

		}

		if (reservation == null) {
			System.out.println("id is not found");
			return;
		}

		System.out.println("reservation date   : " + reservation.getReservationdate().toString());
		System.out.println("bookid             : " + reservation.getBookid());
		System.out.println("memberid           : " + reservation.getMemberid());
		System.out.println("deliverydate       : " + reservation.getDeliverydate());

		System.out.println();
		System.out.println("are you sure you want to cancel(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {
			reservationlist.remove(reservation);
		}
	}

	public static void listAllReservations() {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String strreservationdate;
		String strdeliverydate;
		String strdeliverydateactual;
		System.out.println("List all reservations");

		System.out.println(String.format("%-3s %-14s %-6s %-8s %-12s %-18s %-4s", "id", "reservationdate", "bookid",
				"memberid", "deliverydate", "deliverydateactual", "rate"));
		for (Reservation r : reservationlist) {
			strreservationdate = df.format(r.getReservationdate());
			strdeliverydate = df.format(r.getDeliverydate());
			if (r.getDeliverydateactual() != null) {
				strdeliverydateactual = df.format(r.getDeliverydateactual());
			} else {
				strdeliverydateactual = "-";
			}

			System.out.println(
					String.format("%3d %-14s %6d %8d %-12s %-18s %4d", r.getReservationid(), strreservationdate,
							r.getBookid(), r.getMemberid(), strdeliverydate, strdeliverydateactual, r.getRate()));
		}
	}

	public static void listAllDatecomeReservations() {
		System.out.println("List all datecome reservations");
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String strreservationdate;
			String strdeliverydate;
			String strdeliverydateactual;
			System.out.println("List all reservations");

			System.out.println(String.format("%-3s %-14s %-6s %-8s %-12s %-18s %-4s", "id", "reservationdate", "bookid",
					"memberid", "deliverydate", "deliverydateactual", "rate"));
			Date now = new Date();

			String strnow = df.format(now);
			now = df.parse(strnow);

			for (Reservation r : reservationlist) {
				strreservationdate = df.format(r.getReservationdate());
				strdeliverydate = df.format(r.getDeliverydate());

				if (r.getDeliverydateactual() != null) {
					strdeliverydateactual = df.format(r.getDeliverydateactual());
				} else {
					strdeliverydateactual = "-";
				}

				if (r.getDeliverydateactual() == null && r.getDeliverydate().compareTo(now) == 0) {

					System.out.println(String.format("%3d %-14s %6d %8d %-12s %-18s %4d", r.getReservationid(),
							strreservationdate, r.getBookid(), r.getMemberid(), strdeliverydate, strdeliverydateactual,
							r.getRate()));
				}
			}
		} catch (ParseException exp) {
			System.out.println("date parse error");
		} catch (Exception exp) {
			System.out.println("unknown error");
		}
	}

	public static void listAllDatepassedBookReservations() {
		System.out.println("List all datepassed book reservations");

		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String strreservationdate;
			String strdeliverydate;
			String strdeliverydateactual;
			System.out.println("List all reservations");

			System.out.println(String.format("%-3s %-14s %-6s %-8s %-12s %-18s %-4s", "id", "reservationdate", "bookid",
					"memberid", "deliverydate", "deliverydateactual", "rate"));
			Date now = new Date();

			String strnow = df.format(now);
			now = df.parse(strnow);

			for (Reservation r : reservationlist) {
				strreservationdate = df.format(r.getReservationdate());
				strdeliverydate = df.format(r.getDeliverydate());

				if (r.getDeliverydateactual() != null) {
					strdeliverydateactual = df.format(r.getDeliverydateactual());
				} else {
					strdeliverydateactual = "-";
				}

				if (r.getDeliverydateactual() == null && r.getDeliverydate().compareTo(now) < 0) {

					System.out.println(String.format("%3d %-14s %6d %8d %-12s %-18s %4d", r.getReservationid(),
							strreservationdate, r.getBookid(), r.getMemberid(), strdeliverydate, strdeliverydateactual,
							r.getRate()));
				}
			}
		} catch (ParseException exp) {
			System.out.println("date parse error");
		} catch (Exception exp) {
			System.out.println("error");
		}

	}

	//////////////////////// BOOK OPERATION SECTION

	public static void bookoperations() {
		String choise;
		while (true) {
			System.out.println("book operation menu");
			System.out.println();
			if (activemember.getMembertype().equals("admin")) {

				System.out.println("1. add book ");
				System.out.println("2. remove book");
				System.out.println("3. update book");
				System.out.println("4. list all books");
				System.out.println("5. List all books according to book types ");
				System.out.println("6. search book");
				System.out.println("9. return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();
				if (choise.equals("1")) {
					addBook();
				} else if (choise.equals("2")) {
					removeBook();
				} else if (choise.equals("3")) {
					updateBook();
				} else if (choise.equals("4")) {
					listAllBooks();
				} else if (choise.equals("5")) {
					listAllBooksAccordingtoBooktypes();
				} else if (choise.equals("6")) {
					searchBook();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}

			} else if (activemember.getMembertype().equals("member")) {

				System.out.println("4. list all books");
				System.out.println("5. List all books according to book types ");
				System.out.println("6. search book");
				System.out.println("9. return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("4")) {
					listAllBooks();
				} else if (choise.equals("5")) {
					listAllBooksAccordingtoBooktypes();
				} else if (choise.equals("6")) {
					searchBook();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}
			}

		}

	}

	public static void addBook() {

		Book book = new Book();
		int bookid;
		String bookname;
		int authorid;
		int booktypeid;
		int pressyear;

		System.out.println("add book");
		System.out.println();

		System.out.print("bookid       :");
		bookid = input.nextInt();
		input.nextLine();
		System.out.print("bookname     : ");
		bookname = input.nextLine();
		System.out.print("authorid     :");
		authorid = input.nextInt();
		System.out.print("booktypeid   :");
		booktypeid = input.nextInt();
		System.out.print("pressyear    :");
		pressyear = input.nextInt();

		// bookidnin onceden kullanilip kullanilmadigi kontrol edelebilir.

		book.setBookid(bookid);
		book.setBookname(bookname);
		book.setAuthorid(authorid);
		book.setBooktypeid(booktypeid);
		book.setPressyear(pressyear);

		booklist.add(book);

	}

	public static void removeBook() {
		int id;
		String answer;
		System.out.println("remove book");
		System.out.print("enter the the id of the book  to be deleted: ");
		id = input.nextInt();
		input.nextLine();
		Book book = null;
		for (Book b : booklist) {
			if (b.getBookid() == id) {
				book = b;
				break;
			}

		}

		if (book == null) {
			System.out.println("id is not found");
			return;
		}
		System.out.println("book name   : " + book.getBookname());
		System.out.println("author      : " + book.getAuthorid());
		System.out.println("booktype    : " + book.getBookid());
		System.out.println("pressyear   : " + book.getPressyear());

		System.out.println();

		System.out.println("are you sure you want to delete(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {
			booklist.remove(book);
		}
	}

	public static void updateBook() {
		int id;

		String bookname;
		int authorid;
		int booktypeid;
		int pressyear;

		String answer;
		System.out.println("update book");
		System.out.print("enter the the id of the book  to be updated: ");
		id = input.nextInt();
		input.nextLine();
		Book book = null;
		for (Book b : booklist) {
			if (b.getBookid() == id) {
				book = b;
				break;
			}

		}

		if (book == null) {
			System.out.println("id is not found");
			return;
		}
		System.out.println("book name   : " + book.getBookname());
		System.out.println("author      : " + book.getAuthorid());
		System.out.println("booktype    : " + book.getBookid());
		System.out.println("pressyear   : " + book.getPressyear());
		System.out.println();

		System.out.println("are you sure you want to update(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {

			System.out.print("book name      :");
			bookname = input.nextLine();
			System.out.print("authorid       :");
			authorid = input.nextInt();
			System.out.print("booktypeid   :");
			booktypeid = input.nextInt();
			System.out.print("password         :");
			pressyear = input.nextInt();

			book.setBookname(bookname);
			;
			book.setAuthorid(authorid);
			book.setBooktypeid(booktypeid);
			book.setPressyear(pressyear);

		}
	}

	public static void listAllBooks() {
		System.out.println("list all Books");
		System.out.println(
				String.format("%-3s %-20s %-8s %-10s %-9s %-9s", "id", "bookname", "authorid", "booktypeid", "pressyear","totalrate"));
		for (Book b : booklist) {
			
			System.out.println(String.format("%3d %-20s %8d %10d %9d %9d", b.getBookid(), b.getBookname(), b.getAuthorid(),
					b.getBooktypeid(), b.getPressyear(),gettotalrate(b.getBookid())));
		}
	}

	public static int gettotalrate(int bookid) {
		int sum=0;
		for(Reservation r:reservationlist) {
			if(r.getBookid()==bookid) {
				sum=sum+r.getRate();
			}
		}
		
		return sum;
		
	}
	
	public static void listAllBooksAccordingtoBooktypes() {
		System.out.println("List all books according to book types");
		int booktypeid;

		System.out.println("booktypes");
		for (BookType b : booktypelist) {
			System.out.println(b.getBooktypeid() + " " + b.getBooktypename());
		}
		System.out.println();
		System.out.print("Enter the booktypeid : ");
		booktypeid = input.nextInt();
		input.nextLine();

		System.out.println(
				String.format("%-3s %-20s %-8s %-10s %-9s", "id", "bookname", "authorid", "booktypeid", "pressyear"));
		for (Book b : booklist) {
			if (b.getBooktypeid() == booktypeid) {

				System.out.println(String.format("%3d %-20s %8d %10d %9d", b.getBookid(), b.getBookname(),
						b.getAuthorid(), b.getBooktypeid(), b.getPressyear()));
			}
		}

	}

	public static void searchBook() {
		String searchword;

		System.out.println("search Book");
		System.out.print("enter the search word  : ");
		searchword = input.nextLine();
		System.out.println();

		System.out.println(
				String.format("%-3s %-20s %-8s %-10s %-9s", "id", "bookname", "authorid", "booktypeid", "pressyear"));
		for (Book b : booklist) {
			if (b.getBookname().contains(searchword)) {

				System.out.println(String.format("%3d %-20s %8d %10d %9d", b.getBookid(), b.getBookname(),
						b.getAuthorid(), b.getBooktypeid(), b.getPressyear()));
			}
		}

	}

	///////////////////////// Member Operation Section
	public static void memberoperations() {
		if (!activemember.getMembertype().equals("admin")) {
			// if current user is not admin then return to main menu
			return;
		}

		String choise;
		while (true) {
			System.out.println("member menu");
			System.out.println();
			System.out.println("1.add member ");
			System.out.println("2.remove member");
			System.out.println("3.update member");
			System.out.println("4.list all members ");

			System.out.println("9.return to mainmenu");

			System.out.println();
			System.out.print("your choise: ");
			choise = input.nextLine();
			if (choise.equals("1")) {
				addMember();
			} else if (choise.equals("2")) {
				removeMember();
			} else if (choise.equals("3")) {
				updateMember();
			} else if (choise.equals("4")) {
				listAllMembers();
			} else if (choise.equals("9")) {
				// return to main menu
				return;
			} else {
				System.out.println("invalid choise");
			}

		}

	}

	public static void addMember() {
		Member member = new Member();
		int memberid;
		String username;
		String membername;
		String membersurname;
		String password;
		String passwordrepeat;
		String address;
		String phone;
		String membertype;

		System.out.println("add member");
		System.out.println();

		System.out.print("memberid         :");
		memberid = input.nextInt();
		input.nextLine();
		System.out.print("username         : ");
		username = input.nextLine();
		System.out.print("member name      :");
		membername = input.nextLine();
		System.out.print("member surname   :");
		membersurname = input.nextLine();
		System.out.print("password         :");
		password = input.nextLine();
		System.out.print("passwordrepeat   :");
		passwordrepeat = input.nextLine();
		System.out.print("adress           : ");
		address = input.nextLine();
		System.out.print("phone            : ");
		phone = input.nextLine();
		System.out.print("membertype(admin or member): ");
		membertype = input.nextLine();

		// memberidnin onceden kullanilip kullanilmadigi kontrol edelebilir.

		if (password.equals(passwordrepeat)) {

			member.setMemberid(memberid);
			member.setUsername(username);
			member.setMembername(membername);
			member.setMembersurname(membersurname);
			member.setPassword(password);
			member.setAddress(address);
			member.setPhone(phone);
			member.setMembertype(membertype);

			memberlist.add(member);
		}
	}

	public static void removeMember() {
		int id;
		String answer;
		System.out.println("remove member");
		System.out.print("enter the the id of the member  to be deleted: ");
		id = input.nextInt();
		input.nextLine();
		Member member = null;
		for (Member m : memberlist) {
			if (m.getMemberid() == id) {
				member = m;
				break;
			}

		}

		if (member == null) {
			System.out.println("id is not found");
			return;
		}
		System.out.println("user name   : " + member.getUsername());
		System.out.println("member name : " + member.getMembername());
		System.out.println("surname     : " + member.getMembersurname());
		System.out.println();

		System.out.println("are you sure you want to delete(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {
			memberlist.remove(member);
		}
	}

	public static void updateMember() {
		int id;

		String username;
		String membername;
		String membersurname;
		String password;
		String passwordrepeat;
		String address;
		String phone;
		String membertype;

		String answer;
		System.out.println("update member");
		System.out.print("enter the the id of the member  to be updated: ");
		id = input.nextInt();
		input.nextLine();
		Member member = null;
		for (Member m : memberlist) {
			if (m.getMemberid() == id) {
				member = m;
				break;
			}

		}

		if (member == null) {
			System.out.println("id is not found");
			return;
		}
		System.out.println("user name   : " + member.getUsername());
		System.out.println("member name : " + member.getMembername());
		System.out.println("surname     : " + member.getMembersurname());
		System.out.println();

		System.out.println("are you sure you want to update(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {

			System.out.print("member name      :");
			membername = input.nextLine();
			System.out.print("member surname   :");
			membersurname = input.nextLine();
			System.out.print("password         :");
			password = input.nextLine();
			System.out.print("passwordrepeat   :");
			passwordrepeat = input.nextLine();
			System.out.print("adress           : ");
			address = input.nextLine();
			System.out.print("phone            : ");
			phone = input.nextLine();
			System.out.print("membertype(admin or member): ");
			membertype = input.nextLine();

			if (password.equals(passwordrepeat)) {

				member.setMembername(membername);
				member.setMembersurname(membersurname);
				member.setPassword(password);
				member.setAddress(address);
				member.setPhone(phone);
				member.setMembertype(membertype);

			}

		}
	}

	public static void listAllMembers() {
		System.out.println("list all Members");
		System.out.println(String.format("%-3s %-15s %-15s %-15s", "id", "username", "name", "surname"));
		for (Member m : memberlist) {
			System.out.println(String.format("%3d %-15s %-15s %-15s", m.getMemberid(), m.getUsername(),
					m.getMembername(), m.getMembersurname()));
		}

	}

	//////////////////////// BOOKTYPE SECTION

	public static void booktypeoperations() {

		String choise;
		while (true) {

			if (activemember.getMembertype().equals("admin")) {
				System.out.println("booktype menu");
				System.out.println();
				System.out.println("1.add booktype ");
				System.out.println("2.remove booktype");
				System.out.println("3.update booktype");
				System.out.println("4.list all booktypes ");

				System.out.println("9.return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("1")) {
					addBooktype();
				} else if (choise.equals("2")) {
					removeBooktype();
				} else if (choise.equals("3")) {
					updateBooktype();
				} else if (choise.equals("4")) {
					listAllBooktypes();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}

			} else if (activemember.getMembertype().equals("member")) {
				System.out.println("booktype menu");
				System.out.println();

				System.out.println("4.list all booktypes ");

				System.out.println("9.return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("4")) {
					listAllBooktypes();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}

			}
		}

	}

	public static void addBooktype() {
		BookType booktype = new BookType();
		int booktypeid;
		String booktypename;

		System.out.println("add Booktype");
		System.out.println();
		System.out.print("Booktypeid    :");
		booktypeid = input.nextInt();
		input.nextLine();
		System.out.print("booktype name :");
		booktypename = input.nextLine();

		booktype.setBooktypeid(booktypeid);
		booktype.setBooktypename(booktypename);

		// booktypeidnin onceden kullanilip kullanilmadigi kontrol edelebilir.

		booktypelist.add(booktype);

	}

	public static void removeBooktype() {

		int id;
		String answer;
		System.out.println("remove Booktype");
		System.out.print("enter the the id of the booktype  to be deleted: ");
		id = input.nextInt();
		input.nextLine();
		BookType booktype = null;
		for (BookType b : booktypelist) {
			if (b.getBooktypeid() == id) {
				booktype = b;
				break;
			}

		}

		if (booktype == null) {
			System.out.println("id is not found");
			return;
		}

		System.out.println("booktype name :" + booktype.getBooktypename());
		System.out.println();
		System.out.println("are you sure you want to delete(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {
			booktypelist.remove(booktype);
		}

	}

	public static void updateBooktype() {

		int id;
		String answer;
		System.out.println("update Booktype");
		System.out.print("enter the the id of the booktype  to be uptaded: ");
		id = input.nextInt();
		input.nextLine();
		BookType booktype = null;
		for (BookType b : booktypelist) {
			if (b.getBooktypeid() == id) {
				booktype = b;
				break;
			}

		}

		if (booktype == null) {
			System.out.println("id is not found");
			return;
		}

		System.out.println("booktype name :" + booktype.getBooktypename());
		System.out.println();
		System.out.println("are you sure you want to update(e/h)?");
		answer = input.nextLine();
		String booktypename;
		if (answer.toLowerCase().equals("e")) {
			System.out.print("booktype name: ");
			booktypename = input.nextLine();
			booktype.setBooktypename(booktypename);

		}

	}

	public static void listAllBooktypes() {
		System.out.println("list all Booktypes");

		for (BookType booktype : booktypelist) {
			System.out.println(String.format("%3d", booktype.getBooktypeid()) + " "
					+ String.format("%-20s", booktype.getBooktypename()));

		}
	}

	///////////////////////// author operation section

	public static void authoroperations() {

		String choise;
		while (true) {

			if (activemember.getMembertype().equals("admin")) {
				System.out.println("author menu");
				System.out.println();
				System.out.println("1.add author ");
				System.out.println("2.remove author");
				System.out.println("3.update author");
				System.out.println("4.list all authors ");

				System.out.println("9.return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("1")) {
					addAuthor();
				} else if (choise.equals("2")) {
					removeAuthor();
				} else if (choise.equals("3")) {
					updateAuthor();
				} else if (choise.equals("4")) {
					listAllAuthors();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}

			} else if (activemember.getMembertype().equals("member")) {
				System.out.println("author menu");
				System.out.println();

				System.out.println("4.list all authors ");

				System.out.println("9.return to mainmenu");

				System.out.println();
				System.out.print("your choise: ");
				choise = input.nextLine();

				if (choise.equals("4")) {
					listAllAuthors();
				} else if (choise.equals("9")) {
					// return to main menu
					return;
				} else {
					System.out.println("invalid choise");
				}

			}
		}

	}

	public static void addAuthor() {

		Author author = new Author();
		int authorid;
		String authorname;
		String authorsurname;

		System.out.println("add Author");
		System.out.println();
		System.out.print("Authorid     :");
		authorid = input.nextInt();
		input.nextLine();
		System.out.print("Authorname   :  ");
		authorname = input.nextLine();

		System.out.print("Authorsurname:  ");
		authorsurname = input.nextLine();

		author.setAuthorid(authorid);
		;
		author.setAuthorname(authorname);
		author.setAuthorsurname(authorsurname);

		// authoridnin onceden kullanilip kullanilmadigi kontrol edelebilir.

		authorlist.add(author);

	}

	public static void removeAuthor() {
		int id;
		String answer;
		System.out.println("remove author");
		System.out.print("enter the the id of the author  to be deleted: ");
		id = input.nextInt();
		input.nextLine();
		Author author = null;
		for (Author a : authorlist) {
			if (a.getAuthorid() == id) {
				author = a;
				break;
			}

		}

		if (author == null) {
			System.out.println("id is not found");
			return;
		}

		System.out.println("author name    :" + author.getAuthorname());
		System.out.println("author surname :" + author.getAuthorsurname());
		System.out.println();
		System.out.println("are you sure you want to delete(e/h)?");
		answer = input.nextLine();
		if (answer.toLowerCase().equals("e")) {
			authorlist.remove(author);
		}
	}

	public static void updateAuthor() {
		int id;
		String answer;
		System.out.println("update author");
		System.out.print("enter the the id of the author  to be uptaded: ");
		id = input.nextInt();
		input.nextLine();
		Author author = null;
		for (Author a : authorlist) {
			if (a.getAuthorid() == id) {
				author = a;
				break;
			}

		}

		if (author == null) {
			System.out.println("id is not found");
			return;
		}

		System.out.println("author name    :" + author.getAuthorname());
		System.out.println("author surname :" + author.getAuthorsurname());
		System.out.println();
		System.out.println("are you sure you want to update(e/h)?");
		answer = input.nextLine();
		String authorname;
		String authorsurname;
		if (answer.toLowerCase().equals("e")) {
			System.out.print("author name: ");
			authorname = input.nextLine();
			System.out.print("author surname");
			authorsurname = input.nextLine();

			author.setAuthorname(authorname);
			author.setAuthorsurname(authorsurname);
		}
	}

	public static void listAllAuthors() {
		System.out.println("list all Authors");
		for (Author a : authorlist) {
			System.out.println(
					String.format("%3d %-15s %-15s", a.getAuthorid(), a.getAuthorname(), a.getAuthorsurname()));

		}
	}

	
	public static void bookadvise()
	{
		System.out.println("book advise");
		System.out.println();
		listAllBooktypes();
		int id;
		System.out.print("enter booktype id: ");
		id=input.nextInt();
		input.nextLine();
		ArrayList<Book> books=new ArrayList<>();
		
		for(Book b:booklist) {
			if(b.getBooktypeid()==id) {
				books.add(b);
			}
		}
		Random generator=new Random();
		int index=generator.nextInt(books.size());
		
		Book book=books.get(index);
		System.out.println("the book advised is the following");
		System.out.println();
		System.out.println("book name   : " + book.getBookname());
		System.out.println("author      : " + book.getAuthorid());
		System.out.println("booktype    : " + book.getBookid());
		System.out.println("pressyear   : " + book.getPressyear());
		System.out.println();

		
		
		
	}
}
