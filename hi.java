import java.util.*;
import java.util.Calendar;

class UserData{
	static User head;
	static User tail;
	class User{
		
		int Account_No;
		String User_name;
		String User_password;
		String Name;
		int Age;
		String Branch_Name;
		String Aadhar;
		long Amount;
		User next;
		public User(int Account_No,String User_name,String User_password,String Name,int Age,String Branch_Name,String Aadhar,long Amount){
			this.Account_No=Account_No;
			this.User_name=User_name;
			this.User_password=User_password;
			this.Name=Name;
			this.Age=Age;
			this.Branch_Name=Branch_Name;
			this.Aadhar=Aadhar;
			this.Amount=Amount;
			this.next=null;
		}
	}
	
	/*  <---------------------------------------                     Create Account For New User and Show User Details                            ------------------------------->   */
	public void CreateAccount(int Account_No,String User_name,String User_password,String Name,int Age,String Branch_Name,String Aadhar,long Amount){
		User newUser = new User(Account_No,User_name,User_password,Name,Age,Branch_Name,Aadhar,Amount);
		
		if(UserData.head==null){
			UserData.head=newUser;
			UserData.tail=newUser;
		}
		else{
			UserData.tail.next = newUser;
			UserData.tail = newUser;
		}
	}
	public void ShowUserDetails(String User_Name){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_Name)){
				System.out.print("\n\t\t"+current.Account_No+" "+current.User_name+" "+current.User_password+" "+current.Name+" "+current.Age+" "+current.Branch_Name+" "+current.Aadhar+" "+current.Amount);
				break;
			}
			current=current.next;
		}
		return;
	}
	/*  <---------------------------------------                    Check User Name , Password , Aadhar_No                                       ------------------------------->   */
	public int checkUserName(String User_Name){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_Name)){
				return 0;
			}
			current=current.next;
		}
		return 1;
	}
	public int checkUserPasswordforLogin(String User_Name,String User_password){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_Name) && current.User_password.equals(User_password)){
				return 1;
			}
			current=current.next;
		}
		return 0;
	}
	public int checkUserAadharNumber(String Aadhar_No){
		User current = head;
		if(head==null){
			System.out.println("Accout is Not There ...");
			return 1;
		}
		while(current!=null){
			if(current.Aadhar.equals(Aadhar_No)){
				return 0;
			}
			current=current.next;
		}
		return 1;
	}
	/*  <---------------------------------------                    Edit User Details                        ------------------------------->   */
	public void ForgotPassword(String User_name,String User_password){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.User_password = User_password;
				return;
			}
			current=current.next;
		}
	}
	public void EditName(String User_name,String Name){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Name = Name;
				return;
			}
			current=current.next;
		}
	}
	public void EditAge(String User_name,int Age){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Age = Age;
				return;
			}
			current=current.next;
		}
	}
	public void EditBranchName(String User_name,String Branch){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Branch_Name = Branch;
				return;
			}
			current=current.next;
		}
	}
	public void EditAadharNumber(String User_name,String Aadhar_No){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Aadhar = Aadhar_No;
				return;
			}
			current=current.next;
		}
	}
	
	/*  <-----------------------------------------                   User Amount Transaction                     ----------------------------------------------------->   */
	public void AddAmount(String User_name,long AddCash){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Amount = current.Amount + AddCash;
				return;
			}
			current=current.next;
		}
	}
	public void WithdrawAmount(String User_name,long WithdrawCash){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				current.Amount = current.Amount - WithdrawCash;
				return;
			}
			current=current.next;
		}
	}
	public long returnAmount(String User_name){
		User current = UserData.head;
		while(current!=null){
			if(current.User_name.equals(User_name)){
				return current.Amount;
			}
			current=current.next;
		}
		return 0;
	}
	public String returnUserName(int Acc_No){
		User current = UserData.head;
		while(current!=null){
			if(current.Account_No == Acc_No){
				return current.User_name;
			}
			current=current.next;
		}
		return null;
	}
	/* <-------------------------------------------------------------            Transaction History            --------------------------------------------------------------    */
	public int checkDate(int Day,int Mon,int Year){
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR) , day=cal.get(Calendar.DATE),mon=cal.get(Calendar.MONTH) + 1;
		System.out.print("\n\t\t\t "+day+" "+mon+" "+year);
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

class hi{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int who,User,Account_No=0,index=0;
		ArrayList<String> history = new ArrayList<String>();
		who=1;
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
						String User_name,User_password,RePassword,Name,Branch,Aadhar_No;
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
						Calendar cal = Calendar.getInstance();
						switch(user_choice){
							case 1:
								System.out.println("\n\t\t        Create New Account Or SignUp       \n");
		/*  < -------------------------------------------           User Details           ------------------------------------------------- >   */						
								//Get User Name From User
								System.out.print("\n\t\tEnter the User Name ( Length 4 - 25) : ");
								User_name = sc.next();
								while(true){
									if(check.CheckUserName(User_name)==1 && newAccount.checkUserName(User_name)==1){
										break;
									}
									else if(User_name.length()>=4 && User_name.length()<=25){
										System.out.print("\n\t\t!!!User Name Already Exist... !!!");
									}
									System.out.print("\n\t\tEnter the User Name (Length 4 - 25): ");
									User_name = sc.next();
									
								}
								//Get User Password From User
								System.out.print("\t\tEnter the User Password (At least One 1 Lower, 1 Upper , 1 Numeric, 1 Special letter) : ");
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
								System.out.print("\n\t\tSignUp Successfully \n\t\t\tEnter User Details ....");
								sc.nextLine();
								//Get Name From User
								System.out.print("\n\t\tEnter the Full Name : ");
								Name = sc.nextLine();
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
								Account_No++;
								newAccount.CreateAccount(Account_No,User_name,User_password,Name,Age,Branch,Aadhar_No,Amount);
								
								//int year=cal.get(Calendar.YEAR) , day=cal.get(Calendar.DATE)+1,mon=cal.get(Calendar.MONTH) + 1;
								history.add(User_name+" "+Integer.toString(cal.get(Calendar.DATE))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.YEAR))+" First Deposite Amount "+Long.toString(Amount));
								//System.out.print(history);
								newAccount.ShowUserDetails(User_name);
								break;
								
							case 2:
								System.out.println("\n\t\t       LogIn Process      \n");
								System.out.print("\n\t\tEnter the User Name : ");
								User_name = sc.next();
								if(newAccount.checkUserName(User_name)!=0){
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
								System.out.print("\n\t\tUser LogIn Successfully ...");
								int LogIn = 1,LogIn_Choice;
								do{
									System.out.print("\n\t\t\t          LogIn             \n");
									System.out.print("\n\t\t\t1.Show User Details ");
									System.out.print("\n\t\t\t2.Change User Details");
									System.out.print("\n\t\t\t3.Deposite Amount");
									System.out.print("\n\t\t\t4.Withdraw Amount");
									System.out.print("\n\t\t\t5.Amount Transaction");
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
														sc.nextLine();
														System.out.print("\n\t\t\t\tEnter Your Correct/current Name : ");
														Name = sc.nextLine();
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
											System.out.print("\n\t\t\t            Deposite Amount       \n");
											System.out.print("\n\t\t\tIf You Want Canceled the Deposite Then Enter '0' \n");
											System.out.print("\n\t\t\tEnter How Much You Deposite : ");
											long AddCash = sc.nextLong();
											while(AddCash<0){
												System.out.print("\n\t\t\tYour Deposite Amount is Lower Than Zero ...");
												System.out.print("\n\t\t\tIf You Want Canceled the Deposite Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter How Much You Deposite : ");
												AddCash = sc.nextInt();
											}
											if(AddCash==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Deposite ");
												break;
											}
											newAccount.AddAmount(User_name,AddCash);
											
											history.add(User_name+" "+Integer.toString(cal.get(Calendar.DATE))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.YEAR))+" Deposite Amount is "+Long.toString(AddCash));
											newAccount.ShowUserDetails(User_name);
											break;
										case 4:
											System.out.print("\n\t\t\t            Withdraw Amount       \n");
											System.out.print("\n\t\t\tIf You Want Canceled the Withdraw Then Enter '0' \n");
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
												System.out.print("\n\t\t\tIf You Want Canceled the Withdraw Then Enter '0' \n");
												System.out.print("\n\t\t\tEnter How Much You Withdraw : ");
												WithdrawCash = sc.nextInt();
											}
											if(WithdrawCash==0){
												System.out.print("\n\t\t\tSuccessfully Canceled Withdraw ");
												break;
											}
											newAccount.WithdrawAmount(User_name,WithdrawCash);
											
											history.add(User_name+" "+Integer.toString(cal.get(Calendar.DATE))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.YEAR))+" Withdraw Amount is "+Long.toString(WithdrawCash));

											newAccount.ShowUserDetails(User_name);
											break;
										case 5:
											System.out.print("\n\t\t\t            Amount Transaction       \n");
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
											String CheckName = newAccount.returnUserName(Acc_no);
											if(CheckName.equals(User_name)){
												System.out.print("\n\t\t\t\t!!! Don't Approve to Money Transaction Your Account to Your Account !!!");
												break;
											}
											String Reciver_User_Name = newAccount.returnUserName(Acc_no);
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
											newAccount.AddAmount(Reciver_User_Name,Transaction_Amount);
											newAccount.WithdrawAmount(User_name,Transaction_Amount);
											
											history.add(Reciver_User_Name+" "+Integer.toString(cal.get(Calendar.DATE))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.YEAR))+" Receive From "+User_name+" "+Long.toString(Transaction_Amount));
											history.add(User_name+" "+Integer.toString(cal.get(Calendar.DATE))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.YEAR))+" Send to "+Reciver_User_Name+" "+Long.toString(Transaction_Amount));
											
											newAccount.ShowUserDetails(User_name);
											break;
										case 6:
											int History_choice,History_Count,Transaction_History;
											do{
												Transaction_History=1;
												System.out.print("\n\t\t\t          Transaction History       ");
												System.out.print("\n\t\t\t1.Some Day To Another Day");
												System.out.print("\n\t\t\t2.All Date");
												System.out.print("\n\t\t\t3.Exit");
												System.out.print("Enter Your Choice : ");
												History_choice = sc.nextInt();
												switch(History_choice){
													case 1: 
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
														System.out.print("Enter the Date Two (DD/MM/YYYY): ");
														String date2 = sc.next();
														String[] Date2 = date2.split("/");
														int Day2 =  Integer.parseInt(Date2[0]);
														int Mon2 =  Integer.parseInt(Date2[1]);
														int Year2 =  Integer.parseInt(Date2[2]);
														while(newAccount.checkDate(Day2,Mon2,Year2)==0){
															System.out.print("\n\t\t\tEnter the Date One (DD/MM/YYYY): ");
															date2 = sc.next();
															Date2 = date2.split("/");
															Day2 =  Integer.parseInt(Date2[0]);
															Mon2 =  Integer.parseInt(Date2[1]);
															Year2 =  Integer.parseInt(Date2[2]);
														}
														History_Count=0;
														for(int i=0;i<(history.size());i++){
															String History[] = history.get(i).split(" ");
															if(History[0].equals(User_name)){
																String Date_[] = History[1].split("/");
																int Day = Integer.parseInt(Date_[0]);
																int Mon = Integer.parseInt(Date_[1]);
																int Year = Integer.parseInt(Date_[2]);
																if(newAccount.checkTwoDates(Day1,Mon1,Year1,Day2,Mon2,Year2,Day,Mon,Year)==1){
																	System.out.print("\n\t\t\t\t"+history.get(i));
																	History_Count++;
																}
															}
															//System.out.print("\n\t\t\t\t"+Day+" "+Mon+" "+Year);
														}
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History ");
														break;
													case 2:
														History_Count=0;
														for(int i=0;i<(history.size());i++){
															String History[] = history.get(i).split(" ");
															if(History[0].equals(User_name)){
																System.out.print("\n\t\t\t\t"+history.get(i));
																History_Count++;
															}
														}
														if(History_Count==0)
															System.out.print("\n\t\t\t\tNo Transactions of Your History ");
														break;
													case 3:
														Transaction_History=0;
														break;
												}
											}while(Transaction_History==1);
											
											break;
										case 7:
											LogIn=0;
											break;
									}
								}while(LogIn==1);
								break;
							
							case 3:
								System.out.println("\n\t\t       Forgot Password       \n");
								//Get User Name From User 
								System.out.print("\n\t\tEnter the User Name : ");
								User_name = sc.next();
								if(newAccount.checkUserName(User_name)!=0){
									System.out.print("\n\t\tWrong UserName");
									break;
								}
								//Get Aadhar Number From User
								System.out.print("\n\t\tEnter the User Aadhar_No : ");
								Aadhar_No = sc.next();
								if(newAccount.checkUserAadharNumber(Aadhar_No)!=0){
									System.out.print("\n\t\tWrong User Aadhar Number");
									break;
								}
								//Get New Password From User
								System.out.print("\t\tEnter the New Password : ");
								User_password = sc.next();
								while(check.CheckUserPassword(User_password)!=1){
									System.out.print("\n\t\tEnter the New Password (At least 1 Lower, 1 Upper , 1 Numeric, 1 Special letter) : ");
									User_password = sc.next();
								}
								newAccount.ForgotPassword(User_name,User_password);
								newAccount.ShowUserDetails(User_name);
								System.out.println("\n\t\tPassword Changed Successfully.....");
								
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
					System.out.println("\n\t!!! Invalid Input !!!");
					break;
			}
		
		}while(who==1);
		
	}
}