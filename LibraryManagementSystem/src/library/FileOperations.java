package library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperations {

	public static ArrayList<BookType> readBooktypes() {
		
		ArrayList<BookType> booktypelist = new ArrayList<>();
		
		
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("booktypes.txt"));
			String line;
			String [] columns;
			BookType b;
			while(true) {
				
				line=reader.readLine();
				
				if(line!=null) {
					columns=line.split(";");
					
					b=new BookType();
					
					b.setBooktypeid( Integer.valueOf( columns[0].trim()));
					b.setBooktypename(columns[1].trim());
					
					booktypelist.add(b);
				}
				else {
					break;
				}

			}
			
			reader.close();
		}
		catch(IOException exp){
			System.out.println("booktype.txt couldnt opened");
		}
		
		
		return booktypelist;
	}
	
	public static void writeBooktypes(ArrayList<BookType> booktypes) {
		
		PrintWriter writer;
	
	   try {
		   writer=new PrintWriter("booktypes.txt");
			
			for (BookType b : booktypes) {
				writer.println(String.format("%3d ; %-20s",b.getBooktypeid(),b.getBooktypename() ) );
			}
			writer.close();
			
	   }
		catch(FileNotFoundException exp) {
			System.out.println("booktypes.txt file not found");
		}
		

	}
	
	public static ArrayList<Author> readAuthors(){
		ArrayList<Author> authorlist=new ArrayList<>();
		
		
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("authors.txt"));
			String line;
			String [] columns;
			Author a;
			while(true) {
				
				line=reader.readLine();
				
				if(line!=null) {
					columns=line.split(";");
					
					a=new Author();
					
					a.setAuthorid( Integer.valueOf( columns[0].trim()));
					a.setAuthorname(columns[1].trim());
					a.setAuthorsurname(columns[2].trim());
					
					authorlist.add(a);
				}
				else {
					break;
				}

			}
			
			reader.close();
		}
		catch(IOException exp){
			System.out.println("Authors.txt couldnt opened");
		}
		
		return authorlist;
	}
	
	public static void writeAuthors(ArrayList<Author> authorlist){
		
		
		PrintWriter writer;
		
		   try {
			   writer=new PrintWriter("authors.txt");
				
				for (Author a : authorlist) {
					writer.println(String.format("%3d ; %-20s ; %-20s",a.getAuthorid(),a.getAuthorname(),a.getAuthorsurname() ) );
				}
				writer.close();
				
		   }
			catch(FileNotFoundException exp) {
				System.out.println("authors.txt file not found");
			}
			
	}
	
	public static ArrayList<Member> readMembers(){
		ArrayList<Member> memberlist= new ArrayList<>();
		
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("members.txt"));
			String line;
			String [] columns;
			Member m;

			while(true) {
				
				line=reader.readLine();
				
				if(line!=null) {
					columns=line.split(";");
					
					m=new Member();
					
					m.setMemberid( Integer.valueOf( columns[0].trim()));
					m.setUsername(columns[1].trim());
					m.setMembername(columns[2].trim());
					m.setMembersurname(columns[3].trim());
					m.setPassword(columns[4].trim());
					m.setAddress(columns[5].trim());
					m.setPhone(columns[6].trim());
					m.setMembertype(columns[7].trim());
					
					memberlist.add(m);
				}
				else {
					break;
				}

			}
			
			reader.close();
		}
		catch(IOException exp){
			System.out.println("Members.txt couldnt opened");
		}
		
		
		
		return memberlist;
	}
	public static void writeMembers(ArrayList<Member> memberlist) {
		
		
		PrintWriter writer;

		   try {
			   writer=new PrintWriter("members.txt");
				
				for (Member m : memberlist) {
					writer.println(String.format("%3d ; %-15s ; %-15s ; %-15s ; %-15s ; %-30s ; %-15s ; %-10s",
							m.getMemberid(),m.getUsername(),m.getMembername(),m.getMembersurname(),
							m.getPassword(),m.getAddress(),m.getPhone(),m.getMembertype()));
				}
				writer.close();
				
		   }
			catch(FileNotFoundException exp) {
				System.out.println("Members.txt file not found");
			}
			
	}
	
	public static ArrayList<Book> readBooks(){
		ArrayList<Book> booklist=new ArrayList<>();

		
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("books.txt"));
			String line;
			String [] columns;
			Book b;

			while(true) {
				
				line=reader.readLine();
				
				if(line!=null) {
					columns=line.split(";");
					
					b=new Book();
					
					b.setBookid( Integer.valueOf( columns[0].trim()));
					b.setBookname(columns[1].trim());
					b.setAuthorid(Integer.valueOf( columns[2].trim()));
					b.setBooktypeid(Integer.valueOf( columns[3].trim()));
					b.setPressyear(Integer.valueOf( columns[4].trim()));
				
					
					booklist.add(b);
				}
				else {
					break;
				}

			}
			
			reader.close();
		}
		catch(IOException exp){
			System.out.println("books.txt couldnt opened");
		}
		
		return booklist;
	}
	
	public static void writeBooks(ArrayList<Book> booklist) {
		
		PrintWriter writer;

		   try {
			   writer=new PrintWriter("books.txt");
				
				for (Book b : booklist) {
					writer.println(String.format("%3d ; %-15s ; %3d ; %3d ; %4d",
							b.getBookid(),b.getBookname(),b.getAuthorid(),b.getBooktypeid(),
							b.getPressyear()));
				}
				writer.close();
				
		   }
			catch(FileNotFoundException exp) {
				System.out.println("books.txt file not found");
			}
		
	}
	
	public static ArrayList<Reservation> readreservations(){
		ArrayList<Reservation> reservationlist=new ArrayList<>();
//		private int reservationid;
//		private Date reservationdate;
//		private int bookid;
//		private int memberid;
//		private Date deliverydate;
//		private Date deliverydateactual;
//		private int rate;
		
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("reservations.txt"));
			String line;
			String [] columns;
			Reservation r;

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String strdeliverydate;
			String strdeliverydateactual;
			
			while(true) {
				
				line=reader.readLine();
				
				if(line!=null) {
					columns=line.split(";");
					
					r=new Reservation();
					try {
						
					
					r.setReservationid( Integer.valueOf( columns[0].trim()));
			
					r.setBookid(Integer.valueOf( columns[2].trim()));
					r.setMemberid(Integer.valueOf( columns[3].trim()));
					
					r.setRate(Integer.valueOf( columns[6].trim()));
					
					
					r.setReservationdate(df.parse(columns[1].trim()));
					
					strdeliverydate=columns[4].trim();
					strdeliverydateactual=columns[5].trim();
					
					r.setDeliverydate(strdeliverydate.equals("-")? null : df.parse(strdeliverydate));
					r.setDeliverydateactual(strdeliverydateactual.equals("-")? null : df.parse(strdeliverydateactual));
					}
					catch (ParseException e) {
						System.out.println("invalid date");
					}
					
					reservationlist.add(r);
					
					
				}
				else {
					break;
				}

			}
			
			reader.close();
		}
		catch(IOException exp){
			System.out.println("reservations.txt couldnt opened");
		}
		
		return reservationlist;
		
		
	}
	public static void writeReservations(ArrayList<Reservation>reservationlist) {
		
		PrintWriter writer;

		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String strreservationdate;
		String strdeliverydate;
		String strdeliverydateactual;
		
		
		   try {
			   writer=new PrintWriter("reservations.txt");
				
				for (Reservation r : reservationlist) {
					strreservationdate=df.format(r.getReservationdate());
					strdeliverydate=r.getDeliverydate()!=null? df.format(r.getDeliverydate()) :"-";
					strdeliverydateactual=r.getDeliverydateactual()!=null ? df.format(r.getDeliverydateactual()) :"-";
							
					
					writer.println(String.format("%3d ; %-10s ; %3d ; %3d ; %-10s ; %-10s ; %3d",
							r.getReservationid(),strreservationdate,r.getBookid(),r.getMemberid(),
							strdeliverydate,strdeliverydateactual,r.getRate()));
				}
				writer.close();
				
		   }
			catch(FileNotFoundException exp) {
				System.out.println("reservations.txt file not found");
			}
		
	}
	
	
}
