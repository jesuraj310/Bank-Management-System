import java.util.*;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.*;

class Email {
    static final String USERNAME = "hackerboytom@gmail.com";
    static final String PASSWORD = "Hacking123";

    public void sendMail(String mailFrom, String mailTo, String mailSubject,
            String mailText) throws Exception {

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
	
	public void ShowUserDetails(String User_Name){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			System.out.print("\n\t");
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				System.out.print(data+"\t");
				if(i==8){
					if(Long.parseLong(data)<1000)
						System.out.print("\n\t\t\t!!!Your Account Balance is Lower Than Minimum Balance ( 1000 ) !!!\n");
				}
				
			}
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	/*  <---------------------------------------                    Check User Name , Password , Aadhar_No                                       ------------------------------->   */
	
	public int checkUserPasswordforLogin(String User_Name,String User_password){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",password;
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<3;i++){
				password=myReader.nextLine();
				if(password.equals(User_password)){
					return 1;
				}
			}
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return 0;
	}
	public int checkUserAadharNumber(String User_Name,String Aadhar_No){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",Aadhar;
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<8;i++){
				Aadhar = myReader.nextLine();
				if(Aadhar.equals(Aadhar_No))
					return 0;
			}
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return 1;
	}
	/*  <---------------------------------------                    Edit User Details                        ------------------------------->   */
	public void ForgotPassword(String User_Name,String User_password){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==2)
					data = User_password;
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public String returnEmail(String User_Name){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==3){
					return data;
				}
			}
			myReader.close();
		}
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return "";
	}
	public void EditName(String User_Name,String Name){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==4){
					data = Name;
				}
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public void EditAge(String User_Name,int Age){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==5)
					data = Integer.toString(Age);
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public void EditBranchName(String User_Name,String Branch){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==6)
					data = Branch;
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public void EditAadharNumber(String User_Name,String Aadhar_No){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==7)
					data = Aadhar_No;
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	/*  <-----------------------------------------                   User Amount Transaction                     ----------------------------------------------------->   */
	public void AddAmount(String User_Name,long AddCash){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==8){
					data = Long.toString(AddCash + Long.parseLong(data));
				}
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public void WithdrawAmount(String User_Name,long WithdrawCash){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		ArrayList<String> Data = new ArrayList<String>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==8){
					data = Long.toString(Long.parseLong(data) - WithdrawCash);
				}
				Data.add(data);
				
			}
				FileWriter fw = new FileWriter(filepath);
				BufferedWriter Writer = new BufferedWriter(fw);
					Writer.write("");
				Writer.close();
			
				FileWriter FW = new FileWriter(filepath,true);
				BufferedWriter Writer1 = new BufferedWriter(FW);
					for(int i=0;i<Data.size();i++){
						Writer1.write(Data.get(i)+"\n");
					}
				Writer1.close();
			
			myReader.close();
		} 
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	public long returnAmount(String User_Name){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_Name + ".txt",data;
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			for(int i=0;i<9;i++){
				data = myReader.nextLine();
				if(i==8){
					return Long.parseLong(data);
				}
			}
			myReader.close();
		}
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return 0;
	}
	
	public String returnUserName(int Account_No){
		String filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\";
		try{
			File dir = new File(filepath);
			String[] AllFileNames = dir.list();
			for(int i=0;i<AllFileNames.length;i++){
				if(8<=AllFileNames[i].length()){
					String fname = AllFileNames[i];
					String[] Fname = fname.split(".txt");
					if(fname.charAt(fname.length()-4)=='.' && fname.charAt(fname.length()-3)=='t' && fname.charAt(fname.length()-2)=='x' && fname.charAt(fname.length()-1)=='t' && !fname.equals("Account_No.txt") && !fname.equals("History.txt")){
						File myObj = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\"+fname);
						Scanner myReader = new Scanner(myObj);
						String data = myReader.nextLine();
						if(Account_No == Integer.parseInt(data)){
							//System.out.print(Fname[0]);
							return Fname[0];
						}
						myReader.close();
					}
				}
			}
		}
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
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

class BMS{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int who,User,Account_No=0;
		who=1;
		
		try{
			File fAN = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\Account_No.txt");
			if(!fAN.exists()){
				fAN.createNewFile();
				BufferedWriter WriterAN = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\Account_No.txt"));
					WriterAN.write("0");
				WriterAN.close();
			}
			File myObj = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\Account_No.txt");
			Scanner myReader = new Scanner(myObj);
			String AN = myReader.nextLine();
			Account_No = Integer.parseInt(AN);
			myReader.close();
		}
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		
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
						
						UserData newAccount = new UserData();
						signUp check = new signUp();
						
						String filepath;
						switch(user_choice){
							case 1:
								System.out.println("\n\t\t        Create New Account Or SignUp       \n");
		/*  < -------------------------------------------           User Details           ------------------------------------------------- >   */						
								//Get User Name From User
								System.out.print("\n\t\tIf You Want Exit Than Enter 'exit'\n");
								System.out.print("\n\t\tEnter the User Name ( Length 4 - 25) : ");
								User_name = sc.next();
								while(true){
									filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_name + ".txt";
									File file = new File(filepath);
									if(check.CheckUserName(User_name)==1 && !file.exists()){
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
										Email gmail = new Email();
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
								System.out.print("\n\t\t Enter Your First Deposite Amount : ");
								Amount = sc.nextLong();
								while(Amount<0){
									System.out.print("\n\t\tAmount Value is Lower Than Zero. So, plz Reenter...");
									System.out.print("\n\t\t Enter Your First Deposite Amount : ");
									Amount = sc.nextLong();
								}
								//Create One Object for Create and Store date in linked list
								//UserData newAccount = new UserData();
								
								//
								Account_No++;
								filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_name + ".txt";
								try{
									File file = new File(filepath);
									file.createNewFile();
									BufferedWriter Writer = new BufferedWriter(new FileWriter(filepath,true));
										Writer.write(Integer.toString(Account_No)+"\n"+User_name+"\n"+User_password+"\n"+email+"\n"+Name+"\n"+Integer.toString(Age)+"\n"+Branch+"\n"+Aadhar_No+"\n"+Long.toString(Amount));
									Writer.close();
								}
								catch (IOException e)   
								{  
									e.printStackTrace();   
								}         
								//
								try{
									File file = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt");
									file.createNewFile();
									BufferedWriter Writer = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt",true));
										Writer.write(User_name +" "+ newAccount.returnDate() +" First Deposite Amount "+Long.toString(Amount)+"\n");
									Writer.close();
								}
								catch (Exception e)   
								{  
									e.printStackTrace();   
								}   
								newAccount.ShowUserDetails(User_name);
								System.out.print("\n\t\t\tYour Account Created Successfully ...\n");
								break;
								
							case 2:
								System.out.println("\n\t\t       LogIn Process      \n");
								System.out.print("\n\t\tEnter the User Name : ");
								User_name = sc.next();
								filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_name + ".txt";
								File f = new File(filepath);
								if(!f.exists()){
									System.out.print("\n\t\t!!!Wrong UserName !!!");
									break;
								}
								System.out.print("\n\t\tEnter the User Password : ");
								User_password = sc.next();
								while(newAccount.checkUserPasswordforLogin(User_name,User_password)==0){
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
									System.out.print("\n\t\t\t7.LogOut");
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
												File file = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt");
												file.createNewFile();
												BufferedWriter Writer = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt",true));
													Writer.write(User_name +" "+ newAccount.returnDate() +" Deposite Amount is "+Long.toString(AddCash)+"\n");
												Writer.close();
											}
											catch (Exception e)   
											{  
												e.printStackTrace();   
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
											
											try{
												File file = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt");
												file.createNewFile();
												BufferedWriter Writer = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt",true));
													Writer.write(User_name +" "+ newAccount.returnDate() +" Withdraw Amount is "+Long.toString(WithdrawCash)+"\n");
												Writer.close();
											}
											catch (Exception e)   
											{  
												e.printStackTrace();   
											}  
											System.out.print("\n\t\t\t!!!Withdrawal Successfully !!!\n");
											newAccount.ShowUserDetails(User_name);
											break;
										case 5:
											System.out.print("\n\t\t\t            Money Transaction       \n");
											System.out.print("\n\t\t\tIf You Want Canceled the Transaction Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter the Account Number : ");
											int Acc_no = sc.nextInt();
											while(true){
												if(Acc_no<0)
													System.out.print("\n\t\t\tEntered Account Number is not in Negative !!!");
												else if(Acc_no>Account_No)
													System.out.print("\n\t\t\tEntered Account Number is Not There");
												else{
													break;
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
											
											try{
												File file = new File("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt");
												file.createNewFile();
												BufferedWriter Writer = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt",true));
													Writer.write(User_name +" "+ newAccount.returnDate() +" Send to "+ Reciver_User_Name +" "+Long.toString(Transaction_Amount)+"\n");
													Writer.write(Reciver_User_Name +" "+ newAccount.returnDate() +" Receive From "+ User_name +" "+Long.toString(Transaction_Amount)+"\n");
												Writer.close();
											}
											catch (Exception e)   
											{  
												e.printStackTrace();   
											}
											newAccount.AddAmount(Reciver_User_Name,Transaction_Amount);
											newAccount.WithdrawAmount(User_name,Transaction_Amount);
											
											System.out.print("\n\t\t\t!!!Transaction Amount Send Successfully !!!\n");
											newAccount.ShowUserDetails(User_name);
											break;
										case 6:
											int History=1,History_choice,History_Count=0;
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
														
														try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt"))) {
															for(String line; (line = br.readLine()) != null; ) {
																String[] AllData = line.split(" ");
																String Hname = AllData[0];
																if(Hname.equals(User_name)){
																	String Date_[] = AllData[1].split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(newAccount.checkTwoDates(Day1,Mon1,Year1,Day2,Mon2,Year2,Day,Mon,Year)==1){
																		System.out.print("\n\t\t\t\t"+line);
																		History_Count++;
																	}
																}
															}
														}
														catch (Exception e)   
														{  
															e.printStackTrace();   
														}
														
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History That Time\n");
														
														break;
													case 2:
														History_Count=0;
														System.out.print("\n\t\t\t\tAll Date");
														try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt"))) {
															for(String line; (line = br.readLine()) != null; ) {
																String[] AllData = line.split(" ");
																String Hname = AllData[0];
																if(Hname.equals(User_name)){
																	System.out.print("\n\t\t\t\t"+line);
																	History_Count++;
																}
															}
														}
														catch (Exception e)   
														{  
															e.printStackTrace();   
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
														try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt"))) {
															for(String line; (line = br.readLine()) != null; ) {
																String[] AllData = line.split(" ");
																String Hname = AllData[0];
																if(Hname.equals(User_name)){
																	String Date_[] = AllData[1].split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(Year1 == Year && Mon1 == Mon && Day1 == Day){
																		System.out.print("\n\t\t\t\t"+line);
																		History_Count++;
																	}
																}
															}
														}
														catch (Exception e)   
														{  
															e.printStackTrace();   
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
														while(newAccount.checkDate(Day1,Mon1,Year1)==0){
															System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
															date1 = sc.next();
															Date1 = date1.split("/");
															Day1 =  Integer.parseInt(Date1[0]);
															Mon1 =  Integer.parseInt(Date1[1]);
															Year1 =  Integer.parseInt(Date1[2]);
														}
														History_Count=0;
														try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\History.txt"))) {
															for(String line; (line = br.readLine()) != null; ) {
																String[] AllData = line.split(" ");
																String Hname = AllData[0];
																if(Hname.equals(User_name)){
																	String Date_[] = AllData[1].split("/");
																	int Day = Integer.parseInt(Date_[0]);
																	int Mon = Integer.parseInt(Date_[1]);
																	int Year = Integer.parseInt(Date_[2]);
																	if(Year1 == Year && Mon1 == Mon && Day1 == Day){
																		System.out.print("\n\t\t\t\t"+line);
																		History_Count++;
																	}
																}
															}
														}
														catch (Exception e)   
														{  
															e.printStackTrace();   
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
								filepath = "C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\" + User_name + ".txt";
								File fFP = new File(filepath);
								if(!fFP.exists()){
									System.out.print("\n\t\t!!!Wrong UserName !!!");
									break;
								}
								//Get Aadhar Number From User
								System.out.print("\n\t\tEnter the User Aadhar_No : ");
								Aadhar_No = sc.next();
								if(newAccount.checkUserAadharNumber(User_name,Aadhar_No)!=0){
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
										Email gmail = new Email();
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
		try{
			BufferedWriter WriterAN = new BufferedWriter(new FileWriter("C:\\Users\\jesur\\OneDrive\\Desktop\\Bank_Mangaement_System\\java\\Account_No.txt"));
				WriterAN.write(Integer.toString(Account_No));
			WriterAN.close();
			
		}
		catch (Exception e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		
	}
}