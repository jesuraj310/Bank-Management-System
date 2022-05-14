import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

class Email {
    static final String USERNAME = "jadeja0310@gmail.com";
    static final String PASSWORD = "j.jesu310";

    public void sendMail(String mailFrom, String mailTo, String mailSubject,String mailText) throws Exception {

        Properties config = createConfiguration();

        // Creates a mail session. We need to supply username and
        // password for Gmail authentication.
        Session session = Session.getInstance(config, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        Email.USERNAME,
                        Email.PASSWORD);
            }
        });

        // Creates email message
        Message message = new MimeMessage(session);
        message.setSentDate(new Date());
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(mailTo));
        message.setSubject(mailSubject);
        message.setText(mailText);

        // Send a message
        Transport.send(message);
    }

    public Properties createConfiguration() {
        return new Properties() {
            {
                put("mail.smtp.auth", "true");
                put("mail.smtp.host", "smtp.gmail.com");
                put("mail.smtp.port", "587");
                put("mail.smtp.starttls.enable", "true");
                put("mail.smtp.ssl.protocols", "TLSv1.2");
            }
        };
    }
}

class UserData{
	/*   <------------------------------------------------              OTP  Generator              ------------------------------------------------->   */
	
	public static String generatorOTP(int length) 
	{  
		Random obj = new Random(); 
		char[] otp = new char[length]; 
		for (int i=0; i<length; i++) 
		{ 
			otp[i]= (char)(obj.nextInt(10)+48); 
		} 
		String OTP = new String(otp);
		//System.out.print("Your OTP is : "+OTP);
		return OTP;
	} 
	
	/*  <---------------------------------------            Create Account For New User and Show User Details           ------------------------------->   */
	
	public void ShowUserDetails(String User_name){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT * FROM bms where user_name = '"+User_name+"' ");
			if(result.next()){
				int Account_No = result.getInt("account_no");
				String uname = result.getString("user_name");
				String password = result.getString("user_pass");
				String email = result.getString("mail");
				String name = result.getString("name_");
				int Age = result.getInt("age");
				String Branch = result.getString("branch_name");
				String Aadhar_No = result.getString("aadhar_num");
				long Amount = result.getLong("amount");
				System.out.print("\n\t\t"+Account_No+"\t"+uname+"\t"+password+"\t"+email+"\t"+name+"\t"+Age+"\t"+Branch+"\t"+Aadhar_No+"\t"+Amount);
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	/*  <---------------------------------------                    Check User Name , Password , Aadhar_No                                       ------------------------------->   */
	
	public int checkUserAadharNumber(String User_name,String Aadhar_No){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,aadhar_num FROM bms where user_name = '"+User_name+"'");
			if(result.next()){
				String aadhar = result.getString("aadhar_num");
				if(aadhar.equals(Aadhar_No)){
					return 1;
				}
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return 0;
	}
	public int checkAccountNumber(int Account_No){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT account_no FROM bms where account_no = "+Account_No+"");
			if(result.next()){
				int Acc_no = result.getInt("account_no");
				if(Acc_no == Account_No){
					return 1;
				}
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return 0;
	}
	/*  <---------------------------------------                    Edit User Details                        ------------------------------->   */
	public void ForgotPassword(String User_name,String User_password){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,name_ FROM bms where user_name = '"+User_name+"'");
			String str = "UPDATE bms set user_pass = '"+User_password+"' WHERE user_name = '"+User_name+"'";
			Connect.st.execute(str);
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public String returnEmail(String User_name){
		try{
			ResultSet result = Connect.st.executeQuery(" SELECT user_name,mail FROM bms WHERE user_name = '"+User_name+"' ");
			if(result.next()){
				String uname = result.getString("user_name");
				String email = result.getString("mail");
				return email;
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return "";
	}
	public void EditName(String User_name,String Name){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,name_ FROM bms where user_name = '"+User_name+"'");
			String str = "UPDATE bms set name_ = '"+Name+"' WHERE user_name = '"+User_name+"'";
			Connect.st.execute(str);
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public void EditAge(String User_name,int Age){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,age FROM bms where user_name = '"+User_name+"'");
			String str = "UPDATE bms set age = '"+Age+"' WHERE user_name = '"+User_name+"'";
			Connect.st.execute(str);
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public void EditBranchName(String User_name,String Branch){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,branch_name FROM bms where user_name = '"+User_name+"'");
			String str = "UPDATE bms set branch_name = '"+Branch+"' WHERE user_name = '"+User_name+"'";
			Connect.st.execute(str);
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public void EditAadharNumber(String User_name,String Aadhar_No){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,aadhar_num FROM bms where user_name = '"+User_name+"'");
			String str = "UPDATE bms set aadhar_num = '"+Aadhar_No+"' WHERE user_name = '"+User_name+"'";
			Connect.st.execute(str);
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	/*  <-----------------------------------------                   User Amount Transaction                     ----------------------------------------------------->   */
	public void AddAmount(String User_name,long AddCash){
		try{
			ResultSet Result = Connect.st.executeQuery("SELECT user_name,amount FROM bms where user_name = '"+User_name+"'");
			if(Result.next()){
				long Amount = Result.getLong("amount");
				String str = "UPDATE bms set amount = '"+(Amount+AddCash)+"' WHERE user_name = '"+User_name+"'";
				Connect.st.execute(str);
			}
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public void WithdrawAmount(String User_name,long WithdrawCash){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,amount FROM bms where user_name = '"+User_name+"'");
			if(result.next()){
				long Amount = result.getLong("amount");
				String str = "UPDATE bms set amount = '"+(Amount-WithdrawCash)+"' WHERE user_name = '"+User_name+"'";
				Connect.st.execute(str);
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	public long returnAmount(String User_name){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,amount FROM bms where user_name = '"+User_name+"'");
			if(result.next()){
				long Amount = result.getLong("amount");
				return Amount;
			}
			//result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return 0;
	}
	public int returnAccountNumberCount(){
		try{
			ResultSet result = Connect.st.executeQuery(" SELECT count(*) FROM bms ");
			if(result.next()){
				int Account_No = result.getInt("count");
				return Account_No;
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return 0;
	}
	
	public String returnUserName(int Account_No){
		try{
			ResultSet result = Connect.st.executeQuery(" SELECT account_no,user_name FROM bms ");
			while(result.next()){
				int acc_no = result.getInt("account_no");
				if(Account_No == acc_no){
					String uname = result.getString("user_name");
					return uname;
				}
			}
			result.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return "";
	}
	/*  <---------------------------------------------------------            Transaction History              --------------------------------------------------->  */
	public int checkDate(int Day,int Mon,int Year){
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR) , day=cal.get(Calendar.DATE),mon=cal.get(Calendar.MONTH) + 1;
		//System.out.print("\n\t\t\t "+day+" "+mon+" "+year);
		if(Day > 31){
			System.out.print("\n\t\t\tDon't Enter Your Own Dream Date !!!\n");
			return 0;
		}
		if(Mon >12){
			System.out.print("\n\t\t\tDon't Enter Your Own Dream Date !!!\n");
			return 0;
		}
		if(Year < year){
			return 1;
		}
		else{
			if(Year == year){
				if(Mon < mon){
					return 1;
				}
				else{
					if(Mon == mon){
						if(Day < day){
							return 1;
						}
						else{
							if(Day == day)
								return 1;
							else{
								System.out.print("\n\t\t\tDon't Enter Future Dates !!!\n");
								return 0;
							}
						}
					}
					else{
						System.out.print("\n\t\t\tDon't Enter Future Dates !!!\n");
						return 0;
					}
				}
			}
			else{
				System.out.print("\n\t\t\tDon't Enter Future Dates !!!\n");
				return 0;
			}
		}
	}
	public int checkTwoDates(int Day1,int Mon1,int Year1,int Day2,int Mon2,int Year2,int Day,int Mon,int Year){
		if(Year1<Year && Year<Year2)
			return 1;
		else{
			if(Year1 == Year && Year == Year2){
				if(Mon1 < Mon && Mon < Mon2)
					return 1;
				else{
					if(Mon1 == Mon && Mon < Mon2){
						if(Day1 <= Day)
							return 1;
					}
					else if(Mon1 < Mon && Mon == Mon2){
						if(Day <= Day2)
							return 1;
					}
					else if(Mon1 == Mon && Mon == Mon2){
						if(Day1 < Day && Day < Day2)
							return 1;
						else{
							if(Day1 == Day && Day==Day2)
								return 1;
							else if(Day1 == Day && Day < Day2)
								return 1;
							else if( Day1 < Day && Day == Day2)
								return 1;
						}
					}
				}
			}
			else{
				if(Year1 == Year && Year < Year2){
					if(Mon1 < Mon)
						return 1;
					else{
						if(Mon1 == Mon){
							if(Day1 <= Day)
								return 1;
						}
					}
				}
				else if(Year1 < Year && Year == Year2){
					if(Mon < Mon2)
						return 1;
					else{
						if(Mon == Mon2){
							if(Day <= Day2)
								return 1;
						}
					}
				}
			}
		}
		return 0;
	}
	public String returnDate(){
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR) , day=cal.get(Calendar.DATE),mon=cal.get(Calendar.MONTH) + 1;
		String Date = Integer.toString(day)+"/"+Integer.toString(mon)+"/"+Integer.toString(year);
		return Date;
	}
	public int returnIntDate(String Date){
		String[] rem_date = Date.split("/");
		int YEAR,MON,DAY,year,mon,day;
		YEAR = Integer.parseInt(rem_date[2]);MON = Integer.parseInt(rem_date[1]);DAY = Integer.parseInt(rem_date[0]);
		Calendar cal = Calendar.getInstance();
		year=cal.get(Calendar.YEAR) ; day=cal.get(Calendar.DATE)-10; mon=cal.get(Calendar.MONTH) + 1;
		//System.out.print("\n\t\t\t"+DAY+" "+MON+" "+YEAR+" "+day+" "+mon+" "+year);
		if(YEAR==year && MON==mon-1 && day == 1){
			return 1;
		}
		else{
			return 0;
		}
	}
	public String returnTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		String[] date_time = formatter.format(date).split(" ");
		//System.out.print(date_time[1]);
		return date_time[1];
	}
}

class signUp{
	/*   <------------------------------------------------------------            Check  SignUp  Data           ------------------------------------------------------------->    */
	public int CheckUserName(String user_name){
		if(user_name.length()>=4 && user_name.length()<=25)
			return 1;
		else{
			if(user_name.length()<4)
				System.out.println("\n\t\tUserName Length Is Less Than 4 ");
			else
				System.out.println("\n\t\tUserName Length Is More Than 25 ");
			return 0;
		}
	}
	public int CheckUserPassword(String user_password){
		int small=0,upper=0,special=0,num=0;
		for(int i=0;i<user_password.length();i++){
			if(user_password.charAt(i)>='a' && user_password.charAt(i)<='z')
				small++;
			else if(user_password.charAt(i)>='A' && user_password.charAt(i)<='Z')
				upper++;
			else if(user_password.charAt(i)>='0' && user_password.charAt(i)<='9')
				num++;
			else if(user_password.charAt(i)=='!' || user_password.charAt(i)=='@' || user_password.charAt(i)=='#' || user_password.charAt(i)=='$' || user_password.charAt(i)=='^' || user_password.charAt(i)=='*' || user_password.charAt(i)=='&')
				special++;
		}
		if(small>0 && upper>0 && special>0 && num>0)
			return 1;
		else{
			if(small==0)
				System.out.print("\n\t\tYour Password Miss Small Character");
			if(upper==0)
				System.out.print("\n\t\tYour Password Miss Upper Character");
			if(special==0)
				System.out.print("\n\t\tYour Password Miss Special Character Like ( ! , @ , # , $ , ^ , & , * ) ");
			if(num==0)
				System.out.print("\n\t\tYour Password Miss Numeric Value ");
			return 0;
		}
	}
}
class Connect{
	static Connection c = null;
	static Statement st = null;
	static void con(){
		try{
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
			st = c.createStatement();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
}
class Close{
	static void cl(){
		try{
			Connect.st.close();
			Connect.c.close();
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
}
class LogIn{
	static String password = "";
	public int Check_UserNameisThere(String User_name){
		try{
			ResultSet result = Connect.st.executeQuery("SELECT user_name,user_pass FROM bms where user_name = '"+User_name+"'");
			while(result.next()){
				String name = result.getString("user_name");
				if(name.equals(User_name)){
					password = result.getString("user_pass");
					return 1;
				}
			}
		}
		catch(Exception e){
			System.err.print(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return 0;
	}
}

class jdbc{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int who=1,User,Account_No;
		Connect conn = new Connect();
		conn.con();
		UserData newAccount = new UserData();
		do{
			System.out.print("\n             Sample Bank Management System          \n");
			System.out.print("\n1.Manager");
			System.out.print("\n2.Cashier");
			System.out.print("\n3.User");
			System.out.print("\n4.Exit");
			System.out.print("\nEnter Your Role : ");
			int choice = sc.nextInt();
			switch(choice){
				case 1: 
					System.out.println("\n\t           Manager            ");
					String From,To,D,T,Str;int rem_mon;long Trans_Amount;
					ResultSet result;
					try{
						result = Connect.st.executeQuery(" SELECT * FROM remainder ");
						while(result.next()){
							From = result.getString("from");
							To = result.getString("to");
							D = result.getString("date");
							T = result.getString("time");
							rem_mon = result.getInt("remaining_mon");
							Trans_Amount = result.getLong("trans_amount");
							if(rem_mon==0){
								Str = "DELETE FROM remainder WHERE remaining_mon = "+0+"";
								Connect.st.execute(Str);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
							}
							if(newAccount.returnIntDate(D)==1){
								//System.out.print("*");
								long Amount_Total = newAccount.returnAmount(To);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
								//System.out.print("-");
								Str = "UPDATE remainder SET remaining_mon = "+(rem_mon-1)+",date = '"+newAccount.returnDate()+"' WHERE time = '"+T+"' ";
								Connect.st.execute(Str);
								//System.out.print("@");
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
								newAccount.AddAmount(To,Trans_Amount);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
								newAccount.WithdrawAmount(From,Trans_Amount);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
								Str = "INSERT INTO history VALUES ('"+From+"','"+To+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Send Amount is','"+Trans_Amount+"')";
								Connect.st.executeUpdate(Str);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
								String str = "INSERT INTO history VALUES ('"+To+"','"+From+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Receiveing Amount is','"+Trans_Amount+"')";
								Connect.st.executeUpdate(str);
								result = Connect.st.executeQuery(" SELECT * FROM remainder ");
							}
						}
						result.close();
					}
					catch(Exception e){
						System.err.print(e.getClass().getName()+": "+e.getMessage());
						System.exit(0);
					}
					
					break;
				case 2:
					System.out.println("\n\t           Cashier            ");
					break;
				case 3:
					User=1;
					do{
						//Variable Declaration
						int Age;
						long Amount;
						String User_name,User_password,RePassword,email,otp,Name,Branch,Aadhar_No;
						//
						System.out.println("\n\t           User               \n");
						System.out.println("\t1.Create New Account Or SignUp");
						System.out.println("\t2.Login");
						System.out.println("\t3.Forgot Password");
						System.out.println("\t4.LogOut");
						System.out.print("\tEnter Your Choice : ");
						int user_choice = sc.nextInt();
						
						signUp check = new signUp();
						LogIn login = new LogIn();
						Email gmail = new Email();
						
						switch(user_choice){
							case 1:
								System.out.println("\n\t\t        Create New Account Or SignUp       \n");
		/*  < -------------------------------------------           User Details           ------------------------------------------------- >   */						
								//Get User Name From User
								System.out.print("\n\t\tIf You Want Exit Than Enter 'exit'\n");
								System.out.print("\n\t\tEnter the User Name ( Length 4 - 25) : ");
								User_name = sc.next();
								while(true){
									if(check.CheckUserName(User_name)==1 && login.Check_UserNameisThere(User_name)==0){
										break;
									}
									else if(User_name.length()>=4 && User_name.length()<=25){
										System.out.print("\n\t\t!!!User Name Already Exist... !!!");
									}
									System.out.print("\n\t\tIf You Want Exit Than Enter 'exit'\n");
									System.out.print("\n\t\tEnter the User Name (Length 4 - 25): ");
									User_name = sc.next();
									
								}
								if(((User_name).toUpperCase()).equals("EXIT")){
									System.out.print("\n\t\t!!!Exit Successfully!!!\n");
									break;
								}
								//Get User Password From User
								System.out.print("\n\t\tEnter the User Password (At least One 1 Lower, 1 Upper , 1 Numeric, 1 Special letter) : ");
								User_password = sc.next();
								while(check.CheckUserPassword(User_password)!=1){
									System.out.print("\n\t\tEnter the User Password (At least 1 Lower, 1 Upper , 1 Numeric, 1 Special letter) : ");
									User_password = sc.next();
								}
								System.out.print("\n\t\tReenter the Password : ");
								RePassword = sc.next();
								while(true){
									if(User_password.equals(RePassword)){
										break;
									}
									System.out.print("\n\t\tReentered Password was Wrong ");
									System.out.print("\n\t\tReenter the Password : ");
									RePassword = sc.next();
								}
		/*  <----------------------------------------------------        OTP Verification       --------------------------------------------------->  */
								int OTP_ = 1,eXit = 1;
								do{
									System.out.print("\n\t\tEnter the Email : ");
									email = sc.next();
									System.out.print("\n\t\t\tThe OTP Will Send to Your Entered Email . Plz Read and Enter Correct OTP for SignUp ....\n");
									System.out.print("\n\t\tEnter the OTP : ");
									
									long start = System.currentTimeMillis();
						//			
									String OTP = newAccount.generatorOTP(6);
									String mailFrom = "hackerboytom@gmail.com";
									String mailTo = email;
									String mailSubject = " OTP Send From BMS Application ";
									String mailText = "Your SignUp OTP is : " + OTP + "\nExpire With In 1 Min (60 Sec) \n\t !!! Alert !!!";
									try{
										gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
									}
									catch (Exception e)   
									{  
										e.printStackTrace();   
									}
						//
									otp = sc.next();
									
									long end = System.currentTimeMillis();
									float sec = (end - start) / 1000F;
									
									while(!otp.equals(OTP)){
										System.out.print("\n\t\t!!! Entered OTP Not Correct !!!\n");
										System.out.print("\n\t\tEnter the OTP : ");
										otp = sc.next();
										end = System.currentTimeMillis();
										sec = (end - start) / 1000F;
										if(sec>60.0)
											break;
									}
									int check_retry = 0;
									//System.out.print(sec);
									if(sec>60.0){
										check_retry = 1;
										System.out.print("\n\t\t\t!!!Sorry Time is OVER!!!");
										System.out.print("\n\t\tIf You Want Retry(y/n) : ");
										char ch = sc.next().charAt(0);
										if(ch == 'n' || ch == 'N'){
											System.out.print("\n\t\t\t!!!Exit Successfully!!!");
											OTP_ = 0;
											eXit = 0;
										}
									}
									if(check_retry == 0){
										OTP_ = 0;
									}
								}while(OTP_ == 1);
								if(eXit == 0){
									break;
								}
	/*  <------------------------------------------------------           END of OTP Verification        ----------------------------------------------------->  */							
								System.out.print("\n\t\t\tSignUp Successfully... \n\t\tEnter User Details ....\n");
								//Get Name From User
								System.out.print("\n\t\tEnter the Full Name : ");
								Name = sc.next();
								//Get Age From User 
								System.out.print("\n\t\tEnter the Age ( 13+ ) : ");
								Age = sc.nextInt();
								if(Age < 13){
									System.out.print("\n\t\tYour Age is Under 13 So, You Don't Create Own Account ");
									break;
								}
								//Get Branch Name From User 
								System.out.print("\n\t\tEnter the Branch Name : ");
								Branch = sc.next();
								//Get Aadhar Number From User 
								System.out.print("\n\t\tEnter the Aadhar Number (12 Digit Number) : ");
								Aadhar_No = sc.next();
								while(Aadhar_No.length()!=12){
									System.out.print("\n\t\tInvalid Aadhar Number ");
									System.out.print("\n\t\tEnter the Aadhar Number (12 Digit Number) : ");
									Aadhar_No = sc.next();
								}
								//Enter Your First Deposite Amount ....
								System.out.print("\n\t\t Enter Your First Deposite Amount : ");
								Amount = sc.nextLong();
								while(true){
									if(Amount>=1000){
										break;
									}
									else if(Amount<0){
										System.out.print("\n\t\tAmount Value is Lower Than Zero. So, plz Increase the Amount...");
									}
									else{
										System.out.print("\n\t\tAmount Value is Lower Than Minimum Balance (1000) . So, plz Increase The Amount...");
									}
									System.out.print("\n\t\t Enter Your First Deposite Amount : ");
									Amount = sc.nextLong();
								}
								
								//
								try{
									String str = "INSERT INTO bms (user_name,user_pass,mail,name_,age,branch_name,aadhar_num,amount) VALUES ('"+User_name+"','"+User_password+"','"+email+"','"+Name+"',"+Age+",'"+Branch+"','"+Aadhar_No+"',"+Amount+")";
									Connect.st.executeUpdate(str);
								}
								catch(Exception e){
									System.err.print(e.getClass().getName()+": "+e.getMessage());
									System.exit(0);
								}
								
								try{
									String str = "INSERT INTO history VALUES ('"+User_name+"','"+User_name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Your First Deposite Amount is ','"+Amount+"')";
									Connect.st.executeUpdate(str);
								}
								catch(Exception e){
									System.err.print(e.getClass().getName()+": "+e.getMessage());
									System.exit(0);
								}
								newAccount.ShowUserDetails(User_name);
								System.out.print("\n\t\t\tYour Account Created Successfully ...\n");
								break;
								
							case 2:
								System.out.println("\n\t\t       LogIn Process      \n");
								System.out.print("\n\t\tEnter the User Name : ");
								User_name = sc.next();
								if(login.Check_UserNameisThere(User_name)==0){
									System.out.print("\n\t\t!!!Wrong UserName !!!\n");
									break;
								}
								
								System.out.print("\n\t\tEnter the User Password : ");
								User_password = sc.next();
								while(true){
									if(login.password.equals(User_password)){
										break;
									}
									System.out.print("\n\t\t!!!UserPassword was Wrong !!!");
									System.out.print("\n\t\tEnter the User Password : ");
									User_password = sc.next();
								}
								
								System.out.print("\n\t\tUser LogIn Successfully ...\n");
								int LogIn = 1,LogIn_Choice;
								do{
									System.out.print("\n\t\t\t          LogIn             \n");
									System.out.print("\n\t\t\t1.Show User Details ");
									System.out.print("\n\t\t\t2.Change User Details");
									System.out.print("\n\t\t\t3.Deposite Amount");
									System.out.print("\n\t\t\t4.Withdraw Amount");
									System.out.print("\n\t\t\t5.Money Transaction");
									System.out.print("\n\t\t\t6.Transaction History");
									System.out.print("\n\t\t\t7.Set Reminder For Transaction     ");
									System.out.print("\n\t\t\t8.LogOut");
									System.out.print("\n\t\t\tEnter Your Choice : ");
									LogIn_Choice = sc.nextInt();
									
									switch(LogIn_Choice){
										case 1:
											newAccount.ShowUserDetails(User_name);
											break;
										case 2:
											int Edit=1,Edit_choice;
											newAccount.ShowUserDetails(User_name);
											do{
												System.out.print("\n\t\t\t\t             Edit User Details           ");
												System.out.print("\n\t\t\t\t1.Edit Name");
												System.out.print("\n\t\t\t\t2.Edit Age");
												System.out.print("\n\t\t\t\t3.Edit Branch");
												System.out.print("\n\t\t\t\t4.Edit Aadhar");
												System.out.print("\n\t\t\t\t5.Exit");
												System.out.print("\n\t\t\t\tEnter the Choice : ");
												Edit_choice = sc.nextInt();
												switch(Edit_choice){
													case 1:
														System.out.print("\n\t\t\t\tEnter Your Correct/current Name : ");
														Name = sc.next();
														newAccount.EditName(User_name,Name);
														newAccount.ShowUserDetails(User_name);
														break;
													case 2:
														System.out.print("\n\t\t\t\tEnter Correct/Current Age (13+): ");
														Age = sc .nextInt();
														if(Age<13){
															break;
														}
														newAccount.EditAge(User_name,Age);
														newAccount.ShowUserDetails(User_name);
														break;
													case 3:
														System.out.print("\n\t\t\t\tEnter the New Branch : ");
														Branch = sc.next();
														newAccount.EditBranchName(User_name,Branch);
														newAccount.ShowUserDetails(User_name);
														break;
													case 4:
														System.out.print("\n\t\t\t\tEnter the Correct/current Aadhar Number : ");
														Aadhar_No = sc.next();
														while(Aadhar_No.length()!=12){
															System.out.print("\n\t\t\t\tYour Aadhar Number is Not 12 Digits ");
															System.out.print("\n\t\t\t\tEnter the Correct/current Aadhar Number : ");
															Aadhar_No = sc.next();
														}
														newAccount.EditAadharNumber(User_name,Aadhar_No);
														newAccount.ShowUserDetails(User_name);
														break;
													case 5:
														Edit=0;
														break;
													default:
														System.out.print("\n\t\t\t\t");
														break;
												}
											}while(Edit==1);
											break;
										case 3:
											System.out.print("\n\t\t\tIf You Want Canceled the Deposite Then Enter '0' ");
											System.out.print("\n\t\t\tEnter How Much You Deposite : ");
											long AddCash = sc.nextLong();
											while(AddCash<0){
												System.out.print("\n\t\t\tYour Deposite Amount is Lower Than Zero ...");
												System.out.print("\n\t\t\tIf You Want Canceled the Deposite Then Enter '0' ");
												System.out.print("\n\t\t\tEnter How Much You Deposite : ");
												AddCash = sc.nextInt();
											}
											if(AddCash==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Deposite ");
												break;
											}
											newAccount.AddAmount(User_name,AddCash);
											try{
												String str = "INSERT INTO history VALUES ('"+User_name+"','"+User_name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Your Deposited Amount is ','"+AddCash+"')";
												Connect.st.executeUpdate(str);
											}
											catch(Exception e){
												System.err.print(e.getClass().getName()+": "+e.getMessage());
												System.exit(0);
											}  
											System.out.print("\n\t\t\t!!!Deposite Amount Successfully Added Your Account Balance !!!\n");
											newAccount.ShowUserDetails(User_name);
											break;
										case 4:
											System.out.print("\n\t\t\tIf You Want Canceled the Withdraw Then Enter '0' ");
											System.out.print("\n\t\t\tEnter How Much You Withdraw : ");
											long WithdrawCash = sc.nextLong();
											Amount = newAccount.returnAmount(User_name);
											while(true){
												if(WithdrawCash>=0 && WithdrawCash<=Amount){
													break;
												}
												else if(WithdrawCash<0){
													System.out.print("\n\t\t\tYour Withdraw Amount is Lower Than Zero ...");
												}
												else{
													System.out.print("\n\t\t\tYour Withdraw Amount is Higher Then Your Account Balance ...");
												}
												System.out.print("\n\t\t\tIf You Want Canceled the Withdraw Then Enter '0' ");
												System.out.print("\n\t\t\tEnter How Much You Withdraw : ");
												WithdrawCash = sc.nextInt();
											}
											if(WithdrawCash==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Withdraw ");
												break;
											}
											newAccount.WithdrawAmount(User_name,WithdrawCash);
											if((Amount-WithdrawCash)<1000){
												email = newAccount.returnEmail(User_name);
												String sub = "Account Balance is Low";
												String body = "Your Account Balance ("+(Amount-WithdrawCash)+")is Lower Than Minimum Balance (1000) \n\t\t!!!Alart!!!";
												try{
													gmail.sendMail(User_name,email,sub,body);
												}
												catch(Exception e){
													e.printStackTrace();
												}
											}
											try{
												String str = "INSERT INTO history VALUES ('"+User_name+"','"+User_name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Your Withdrawal Amount is ','"+WithdrawCash+"')";
												Connect.st.executeUpdate(str);
											}
											catch(Exception e){
												System.err.print(e.getClass().getName()+": "+e.getMessage());
												System.exit(0);
											}    
											System.out.print("\n\t\t\t!!!Withdrawal Successfully !!!\n");
											newAccount.ShowUserDetails(User_name);
											break;
										case 5:
											System.out.print("\n\t\t\t            Money Transaction       \n");
											System.out.print("\n\t\t\tIf You Want Canceled the Transaction Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter the Account Number : ");
											int Acc_no = sc.nextInt();
											Account_No = newAccount.returnAccountNumberCount();
											while(true){
												if(newAccount.checkAccountNumber(Acc_no)==1)
													break;
												else if(Acc_no==0){
													break;
												}
												else{
													System.out.print("\n\t\t\tEntered Account Number is Not There");
												}
												System.out.print("\n\t\t\tIf You Want Canceled The Transaction Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter the Account Number : ");
												Acc_no = sc.nextInt();
											}
											if(Acc_no==0)
												break;
											String Reciver_User_Name = newAccount.returnUserName(Acc_no);
											if(Reciver_User_Name.equals(User_name)){
												System.out.print("\n\t\t\t\t!!! Don't Approve to Money Transaction Your Account to Your Account !!!");
												break;
											}
											Reciver_User_Name = newAccount.returnUserName(Acc_no);
											newAccount.ShowUserDetails(Reciver_User_Name);
											System.out.print("\n\t\t\tIf You Want Canceled The Transaction Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter Your Transaction Amount : ");
											long Transaction_Amount = sc.nextLong();
											Amount = newAccount.returnAmount(User_name);
											while(true){
												if(Transaction_Amount>=0 && Transaction_Amount<=Amount){
													break;
												}
												else if(Transaction_Amount<0){
													System.out.print("\n\t\t\tYour Transaction Amount is Lower Than Zero ...");
												}
												else{
													System.out.print("\n\t\t\tYour Transaction Amount is Higher Then Your Account Balance ...");
												}
												System.out.print("\n\t\t\tIf You Want Canceled the Transaction Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter How Much You Transaction Amount : ");
												Transaction_Amount = sc.nextInt();
											}
											if(Transaction_Amount==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Transaction \n");
												break;
											}
											if((Amount-Transaction_Amount)<1000){
												email = newAccount.returnEmail(User_name);
												String sub = "Account Balance is Low";
												String body = "Your Account Balance ("+(Amount-Transaction_Amount)+")is Lower Than Minimum Balance (1000) \n\t\t!!!Alart!!!";
												try{
													gmail.sendMail(User_name,email,sub,body);
												}
												catch(Exception e){
													e.printStackTrace();
												}
											}
											
											try{
												String str = "INSERT INTO history VALUES ('"+User_name+"','"+Reciver_User_Name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Send Amount is','"+Transaction_Amount+"')";
												Connect.st.executeUpdate(str);
											}
											catch(Exception e){
												System.err.print(e.getClass().getName()+": "+e.getMessage());
												System.exit(0);
											}
											try{
												String str = "INSERT INTO history VALUES ('"+Reciver_User_Name+"','"+User_name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"','Receiveing Amount is','"+Transaction_Amount+"')";
												Connect.st.executeUpdate(str);
											}
											catch(Exception e){
												System.err.print(e.getClass().getName()+": "+e.getMessage());
												System.exit(0);
											}
											newAccount.AddAmount(Reciver_User_Name,Transaction_Amount);
											newAccount.WithdrawAmount(User_name,Transaction_Amount);
											
											System.out.print("\n\t\t\t!!!Transaction Amount Send Successfully !!!\n");
											newAccount.ShowUserDetails(User_name);
											break;
										case 6:
											int History=1,History_choice,History_Count=0;
											newAccount.returnTime();
											do{
												System.out.print("\n\t\t\t          Transaction History       ");
												System.out.print("\n\t\t\t1.Some Day To Another Day");
												System.out.print("\n\t\t\t2.All Date");
												System.out.print("\n\t\t\t3.Any Specific Day ");
												System.out.print("\n\t\t\t4.Today Transaction History ");
												System.out.print("\n\t\t\t5.Exit");
												System.out.print("\n\t\t\tEnter Your Choice : ");
												History_choice = sc.nextInt();
												switch(History_choice){
													case 1: 
														System.out.print("\n\t\t\t1.Some Day To Another Day");
														System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
														String date1 = sc.next();
														String[] Date1 = date1.split("/");
														int Day1 =  Integer.parseInt(Date1[0]);
														int Mon1 =  Integer.parseInt(Date1[1]);
														int Year1 =  Integer.parseInt(Date1[2]);
														while(newAccount.checkDate(Day1,Mon1,Year1)==0){
															System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
															date1 = sc.next();
															Date1 = date1.split("/");
															Day1 =  Integer.parseInt(Date1[0]);
															Mon1 =  Integer.parseInt(Date1[1]);
															Year1 =  Integer.parseInt(Date1[2]);
														}
														System.out.print("\n\t\t\tEnter the Date Two (DD/MM/YYYY): ");
														String date2 = sc.next();
														String[] Date2 = date2.split("/");
														int Day2 =  Integer.parseInt(Date2[0]);
														int Mon2 =  Integer.parseInt(Date2[1]);
														int Year2 =  Integer.parseInt(Date2[2]);
														while(newAccount.checkDate(Day2,Mon2,Year2)==0){
															System.out.print("\n\t\t\tEnter the Date Two (DD/MM/YYYY): ");
															date2 = sc.next();
															Date2 = date2.split("/");
															Day2 =  Integer.parseInt(Date2[0]);
															Mon2 =  Integer.parseInt(Date2[1]);
															Year2 =  Integer.parseInt(Date2[2]);
														}
														History_Count=0;
														try{
															result = Connect.st.executeQuery("SELECT * FROM history");
															while(result.next()){
																String name = result.getString("from");
																if(name.equals(User_name)){
																	String Date_[] = (result.getString("date")).split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(newAccount.checkTwoDates(Day1,Mon1,Year1,Day2,Mon2,Year2,Day,Mon,Year)==1){
																		String to_ = result.getString("to");
																		String date_ = result.getString("date");
																		String time_ = result.getString("time");
																		String message_ = result.getString("message");
																		long Am = result.getLong("amount");
																		System.out.print("\n\t\t"+name+" "+to_+" "+date_+" "+time_+" "+message_+" "+Am);
																		History_Count++;
																	}
																}
															}
															result.close();
														}
														catch(Exception e){
															System.err.print(e.getClass().getName()+": "+e.getMessage());
															System.exit(0);
														}
														
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History That Time\n");
														
														break;
													case 2:
														History_Count=0;
														System.out.print("\n\t\t\t\tAll Date");
														try{
															result = Connect.st.executeQuery("SELECT * FROM history");
															while(result.next()){
																String name = result.getString("from");
																if(name.equals(User_name)){
																	String to_ = result.getString("to");
																	String date_ = result.getString("date");
																	String time_ = result.getString("time");
																	String message_ = result.getString("message");
																	long Am = result.getLong("amount");
																	System.out.print("\n\t\t"+name+" "+to_+" "+date_+" "+time_+" "+message_+" "+Am);
																	History_Count++;
																}
															}
															result.close();
														}
														catch(Exception e){
															System.err.print(e.getClass().getName()+": "+e.getMessage());
															System.exit(0);
														}
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History \n");
														break;
													case 3:
														System.out.print("\n\t\t\t\tAny Specific Day ");
														System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
														date1 = sc.next();
													    Date1 = date1.split("/");
														Day1 =  Integer.parseInt(Date1[0]);
														Mon1 =  Integer.parseInt(Date1[1]);
														Year1 =  Integer.parseInt(Date1[2]);
														while(newAccount.checkDate(Day1,Mon1,Year1)==0){
															System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
															date1 = sc.next();
															Date1 = date1.split("/");
															Day1 =  Integer.parseInt(Date1[0]);
															Mon1 =  Integer.parseInt(Date1[1]);
															Year1 =  Integer.parseInt(Date1[2]);
														}
														History_Count=0;
														try{
															result = Connect.st.executeQuery("SELECT * FROM history");
															while(result.next()){
																String name = result.getString("from");
																if(name.equals(User_name)){
																	String Date_[] = (result.getString("date")).split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(Year1 == Year && Mon1 == Mon && Day1 == Day){
																		String to_ = result.getString("to");
																		String date_ = result.getString("date");
																		String time_ = result.getString("time");
																		String message_ = result.getString("message");
																		long Am = result.getLong("amount");
																		System.out.print("\n\t\t"+name+" "+to_+" "+date_+" "+time_+" "+message_+" "+Am);
																		History_Count++;
																	}
																}
															}
															result.close();
														}
														catch(Exception e){
															System.err.print(e.getClass().getName()+": "+e.getMessage());
															System.exit(0);
														}
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History \n");
														
														break;
													case 4:
														System.out.print("\n\t\t\tToday Transaction History ");
														date1 = newAccount.returnDate();
														Date1 = date1.split("/");
														Day1 =  Integer.parseInt(Date1[0]);
														Mon1 =  Integer.parseInt(Date1[1]);
														Year1 =  Integer.parseInt(Date1[2]);
														
														History_Count=0;
														try{
															result = Connect.st.executeQuery("SELECT * FROM history");
															while(result.next()){
																String name = result.getString("from");
																if(name.equals(User_name)){
																	String Date_[] = (result.getString("date")).split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(Year1 == Year && Mon1 == Mon && Day1 == Day){
																		String to_ = result.getString("to");
																		String date_ = result.getString("date");
																		String time_ = result.getString("time");
																		String message_ = result.getString("message");
																		long Am = result.getLong("amount");
																		System.out.print("\n\t\t"+name+" "+to_+" "+date_+" "+time_+" "+message_+" "+Am);
																		History_Count++;
																	}
																}
															}
															result.close();
														}
														catch(Exception e){
															System.err.print(e.getClass().getName()+": "+e.getMessage());
															System.exit(0);
														}
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History \n");
														
														break;
													case 5:
														History = 0;
														break;
													default:
														System.out.print("\n\t\t\t!!!Invalid Input!!!\n");
														break;
												}
											}while(History==1);
											
											break;
										case 7:
											System.out.print("\n\t\t\t      Set Reminder For Transaction     ");
											System.out.print("\n\t\t\tIf You Want Canceled the Transaction Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter the Account Number : ");
											Acc_no = sc.nextInt();
											Account_No = newAccount.returnAccountNumberCount();
											while(true){
												if(newAccount.checkAccountNumber( Acc_no ) == 1)
													break;
												else if(Acc_no == 0){
													break;
												}
												else{
													System.out.print("\n\t\t\tEntered Account Number is Not There");
												}
												System.out.print("\n\t\t\tIf You Want Canceled The Transaction Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter the Account Number : ");
												Acc_no = sc.nextInt();
											}
											if(Acc_no==0)
												break;
											Reciver_User_Name = newAccount.returnUserName(Acc_no);
											if(Reciver_User_Name.equals(User_name)){
												System.out.print("\n\t\t\t\t!!! Don't Approve to Money Transaction Your Account to Your Account !!!");
												break;
											}
											Reciver_User_Name = newAccount.returnUserName(Acc_no);
											newAccount.ShowUserDetails(Reciver_User_Name);
											
											System.out.print("\n\t\t\tIf You Want Canceled The Transaction Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter Your Transaction Amount : ");
											Transaction_Amount = sc.nextLong();
											Amount = newAccount.returnAmount(User_name);
											while(true){
												if(Transaction_Amount>=0 && Transaction_Amount<=Amount){
													break;
												}
												else if(Transaction_Amount<0){
													System.out.print("\n\t\t\tYour Transaction Amount is Lower Than Zero ...");
												}
												else{
													System.out.print("\n\t\t\tYour Transaction Amount is Higher Then Your Account Balance ...");
												}
												System.out.print("\n\t\t\tIf You Want Canceled the Transaction Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter How Much You Transaction Amount : ");
												Transaction_Amount = sc.nextInt();
											}
											if(Transaction_Amount==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Transaction \n");
												break;
											}
											
											System.out.print("\n\t\t\tHow Many Month Extend the Transaction : ");
											int trans_mon = sc.nextInt();
											while(trans_mon<0){
												System.out.print("\n\t\t\t!!! Transaction Month is Lower Than Zero !!!");
												System.out.print("\n\t\t\tHow Many Month Extend the Transaction : ");
												trans_mon = sc.nextInt();
											}
											
											try{
												String str = "INSERT INTO remainder VALUES ('"+User_name+"','"+Reciver_User_Name+"','"+newAccount.returnDate()+"','"+newAccount.returnTime()+"',"+trans_mon+","+Transaction_Amount+")";
												Connect.st.executeUpdate(str);
											}
											catch(Exception e){
												System.err.print(e.getClass().getName()+": "+e.getMessage());
												System.exit(0);
											}
											
											
											break;
										case 8:
											LogIn=0;
											break;
										default:
											System.out.print("\n\t\t\t!!!Invalid Choice!!!\n");
									}
								}while(LogIn==1);
								
								break;
							
							case 3:
								System.out.println("\n\t\t       Forgot Password       \n");
								//Get User Name From User 
								System.out.print("\n\t\tEnter the User Name : ");
								User_name = sc.next();
								
								//LogIn login = new LogIn();
								if(login.Check_UserNameisThere(User_name)==0){
									System.out.print("\n\t\t!!!Wrong UserName !!!\n");
									break;
								}
								//Get Aadhar Number From User
								System.out.print("\n\t\tEnter the User Aadhar_No : ");
								Aadhar_No = sc.next();
								if(newAccount.checkUserAadharNumber(User_name,Aadhar_No)==0){
									System.out.print("\n\t\tWrong User Aadhar Number");
									break;
								}
								
	/*  <----------------------------------------------------        OTP Verification       --------------------------------------------------->  */
								OTP_ = 1;eXit = 1;
								do{
									email = newAccount.returnEmail(User_name);
									System.out.print("\n\t\t\tThe OTP Will Send to Your Entered Email . Plz Read and Enter Correct OTP for SignUp ....\n");
									System.out.print("\n\t\tEnter the OTP : ");
									
									long start = System.currentTimeMillis();
						//			
									String OTP = newAccount.generatorOTP(6);
									String mailFrom = "hackerboytom@gmail.com";
									String mailTo = email;
									String mailSubject = " OTP Send From BMS Application ";
									String mailText = "Your Forgot Password OTP is : " + OTP + "\nExpire With In 1 Min (60 Sec) \n\t !!! Alert !!!";
									try{
										gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
									}
									catch (Exception e)   
									{  
										e.printStackTrace();   
									}
									//long start = System.currentTimeMillis();
									//System.out.print(start);
						//
									otp = sc.next();
									
									long end = System.currentTimeMillis();
									float sec = (end - start) / 1000F;
									
									while(!otp.equals(OTP)){
										System.out.print("\n\t\t!!! Entered OTP Not Correct !!!\n");
										System.out.print("\n\t\tEnter the OTP : ");
										otp = sc.next();
										end = System.currentTimeMillis();
										sec = (end - start) / 1000F;
										if(sec>60.0)
											break;
									}
									int check_retry = 0;
									//System.out.print(sec);
									if(sec>60.0){
										check_retry = 1;
										System.out.print("\n\t\t\t!!!Sorry Time is OVER!!!");
										System.out.print("\n\t\tIf You Want Retry(y/n) : ");
										char ch = sc.next().charAt(0);
										if(ch == 'n' || ch == 'N'){
											System.out.print("\n\t\t\t!!!Exit Successfully!!!");
											OTP_ = 0;
											eXit = 0;
										}
									}
									if(check_retry == 0){
										OTP_ = 0;
									}
								}while(OTP_ == 1);
								if(eXit == 0){
									break;
								}
	/*  <------------------------------------------------------           END of OTP Verification        ----------------------------------------------------->  */	
								
								//Get New Password From User
								System.out.print("\t\tEnter the New Password : ");
								User_password = sc.next();
								while(check.CheckUserPassword(User_password)!=1){
									System.out.print("\n\t\tEnter the New Password (At least 1 Lower, 1 Upper , 1 Numeric, 1 Special letter) : ");
									User_password = sc.next();
								}
								newAccount.ForgotPassword(User_name,User_password);
								newAccount.ShowUserDetails(User_name);
								
								System.out.println("\n\t\t\tPassword Changed Successfully.....");
								break;
								
							case 4:
								System.out.println("\n\t\tLogOut Successfully\n");
								User=0;
								break;
								
							default:
								System.out.println("\n\t\t!!!Invalid Input!!!\n");
								break;
						}
					}while(User==1);
					break;
				case 4:
					System.out.println("\n\t           Life is Full Of Problems And Pains               ");
					who=0;
					break;
				default:
					System.out.println("\n\t!!! Invalid Input !!!\n");
					break;
			}
		
		}while(who==1);
	}
}