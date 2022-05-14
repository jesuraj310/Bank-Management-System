#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include<dirent.h>
#include<time.h>

//Global Declaration
int count=0;
char Time[20],Date[20];
int checkUserPassword(char *pass);

//User Details
int userAccountDetail(char user_name[25],char user_password[20],FILE *fptr){
    char acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],file_name[28];
    strcpy(file_name,user_name);
    strcat(file_name,".txt");
    if(fptr=fopen(file_name,"r")){
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        if(!strcmp(user_password,pass)){
            printf("\n\t\tA/C No. UserName \t Password \t Age \t Branch \t Aadhar Num \t Amount \t");
            printf("\n\t\t %s \t %s \t %s \t %s \t %s \t %s \t %s",acc_no,name,pass,age,branch,aadhar,amount);
            if(atoi(amount)<1000){
                printf("\n\t\tYour Account Balance is Lesser Then Minimum Balance(1000)...\n");
            }
            return 1;
        }
        else{
            printf("\n\t\tPassword is wrong\n");
            return 0;
        }
    }
    else{
        printf("\n\t\tUser Name is Wrong\n");
        return 0;
    }
    fclose(fptr);
}

//Edit User Data
void editAge(char Name[25],int Age,FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    sprintf(age,"%d",Age);
    fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
}
void editBranch(char Name[25],char Branch[30],FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,Branch,aadhar,amount);
    fclose(fptr);
}
void editAadhar(char Name[25],long long int Aadhar,FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    sprintf(aadhar,"%lld",Aadhar);
    fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
}
int ForgotPassword(char Name[25],long long int Aadhar_no,FILE *fptr){
    char fname[28],Aadhar[15],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],user_password[30];
    strcpy(fname,Name);
    strcat(fname,".txt");
    if(fptr=fopen(fname,"r")){
        sprintf(Aadhar,"%lld",Aadhar_no);
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        if(strcmp(aadhar,Aadhar)==0){
            printf("\n\t\tEnter the New Password : ");
            scanf("%s",user_password);
            while(checkUserPassword(user_password)){
                printf("\n\t\tEnter the New Password : ");
                scanf("%s",user_password);
            }
            printf("\n\t!!!Password Successfully Changed!!!\n");
        }
        else{
            printf("\n\t!!!Aadhar Number is Wrong!!!");
        }
        fclose(fptr);
        fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,user_password,age,branch,aadhar,amount);
        fclose(fptr);
    }
    else{
        printf("\n\t!!!User Name Was Wrong!!!");
    }
}
int AddAmount(char user_name[25],long int Amount,FILE *fptr){
    char acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],file_name[28],str[101],last[101]="\0";
    strcpy(file_name,user_name);
    strcat(file_name,".txt");
    fptr=fopen(file_name,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        while(fscanf(fptr,"%s",str)!=EOF){
            strcat(last,str);
            strcat(last," ");
        }
    fclose(fptr);
    long int User_amount=atoi(amount)+Amount;
    sprintf(amount,"%ld",User_amount);
    fptr=fopen(file_name,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount,last);
        memset(last,0,101);
    fclose(fptr);
}
//Bank Transaction Message

int appendMessage(char Name[25],char Trans_Name[25],long int Trans_Amount,FILE *fptr){
    char fname[28];
    strcpy(fname,Trans_Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"a");
        fprintf(fptr," %s Send %ld",Name,Trans_Amount);
    fclose(fptr);
}
//Checking Account Details
long int ReturnUserAmount(char Name[25],FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    long int Amount;
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        Amount=atoi(amount);
    fclose(fptr);
    return Amount;
}
int checkAccountisThere(char name[25],FILE *fptr){
    char fname[28];
    strcpy(fname,name);
    strcat(fname,".txt");
    if(fptr=fopen(fname,"r")){
        fclose(fptr);
        return 0;
    }
    else
        return 1;
}
int check_Account_is_There(char Name[25],char user_password[30],FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],last[1001],ch;
    int index=0;
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    if(strcmp(pass,user_password)==0){
        fptr=fopen(fname,"r");
        printf("\n\t\t");
        while(fscanf(fptr,"%s",last)!=EOF){
            if(index>6){
                printf("%s ",last);
                if(index%3==0){
                    printf("\n\t\t");
                }
            }
            index++;
        }
        fclose(fptr);
        fptr=fopen(fname,"w");
            fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        fclose(fptr);
        return 0;
    }
    else{
           printf("\n\t\t!!! Password is Wrong ... Retry !!!");
        return 1;
    }
}
int checkAccountNumberisThere(int account_no,DIR * d,FILE * fptr){
    struct dirent *de;
    d = opendir(".");
    if (d == NULL)
    {
        printf("\n\t\tDirectory Not Create");
        return 1;
    }
    while ((de = readdir(d)) != NULL){
        int l=strlen(de->d_name);
        char acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
        if(l>=8 && strcmp(de->d_name,"Account_No.txt")!=0){
            if(de->d_name[l-4]=='.' && de->d_name[l-3]=='t' && de->d_name[l-2]=='x' && de->d_name[l-1]=='t'){
                fptr=fopen(de->d_name,"r");
                    fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
                    if(atoi(acc_no)==account_no){
                        printf("\n\t\tA/C No. UserName \t Password \t Age \t Branch \t Aadhar Num \t Amount \t");
                        printf("\n\t\t %s \t %s \t %s \t %s \t %s \t %s \t %s",acc_no,name,pass,age,branch,aadhar,amount);
                        return 0;
                    }
                fclose(fptr);
            }
        }
    }
    closedir(d);
    return 1;
}
int check_yes(char *str){
    char str_upper[11];
    for(int i=0;i<strlen(str);i++){
        if((str[i]>='a' && str[i]<='z')||(str[i]>='A' && str[i]<='Z'))
            str_upper[i]=toupper(str[i]);
    }
    return strcmp(str_upper,"YES");
}

int checkAgeLimit(int Age){
    if(Age>=18){
        return 1;
    }
    else{
        return 0;
    }
}

int checkAadharNo(long long int Aadhar){
    char aadhar[15];
    sprintf(aadhar,"%lld",Aadhar);
    return strlen(aadhar);
}

int checkUserName(char name[25],FILE *fptr){
    if(strlen(name)>=4 && strlen(name)<=25){
        char fname[28];
        strcpy(fname,name);
        strcat(fname,".txt");
        if((fptr=fopen(fname,"r"))==0){
            fclose(fptr);
            return 0;
        }
        else
            printf("\n\t\tThat User Name Already Exist");
    }
    else{
        if(strlen(name)<4){
            printf("\n\t\tUser Name Size is less Then 4 . Size Range 4-25 Must");
        }
        else{
            printf("\n\t\tUser Name Size is Large Then 25 . Size Range 4-25 Must");
        }
    }
}

int checkRePass(char *pass,char *repass){
    if(!strcmp(pass,repass)){
        printf("\t\t SignUp SuccessFully ");
        printf("\n\t\tEnter User Details \n");
        return 0;
    }
    printf("\n\t\t!!!Retype the Password Correctly!!!");
}

int checkDay(int Day,int Month,int Year){
    time_t t = time(NULL);
    struct tm tm = *localtime(&t);
    if(Year<(tm.tm_year+1900)){
        if(Month<=12){
            if(Day<=31){
                return 0;
            }
        }
    }
    else{
        if(Year==tm.tm_year+1900){
            if(Month==tm.tm_mon+1){
                if(Day==tm.tm_mday){
                    return 0;
                }
                else{
                    if(Day<tm.tm_mday){
                        return 0;
                    }
                    else{
                        printf("\n\t\t\tDon't Enter Future Dates");
                    }
                }
            }
            else{
                if(Month<tm.tm_mon+1){
                    return 0;
                }
                else{
                    printf("\n\t\t\tDon't Enter Future Dates");
                }
            }
        }
        else{
            printf("\n\t\t\tDon't Enter Future Dates");
        }
    }
}

int checkDatesWhoIsFirst(int Day1,int Mon1,int Year1,int Day2,int Mon2,int Year2){
    if(Year1<Year2){
        return 1;
    }
    else{
        if(Year1==Year2){
            if(Mon1<Mon2){
                return 1;
            }
            else{
                if(Mon1==Mon2){
                    if(Day1<Day2){
                        return 1;
                    }
                    else{
                        if(Day1==Day2)
                            return 1;
                        else
                            return 0;
                    }
                }
                else{
                    return 0;
                }
            }
        }
        else{
            return 0;
        }
    }
}

int checkUserPassword(char *pass){
    int lnum=0,unum=0,nnum=0,snum=0;
    for(int i=0;i<strlen(pass);i++){
        if(pass[i]>='a' && pass[i]<='z'){
            lnum++;
        }
        else if(pass[i]>='A' && pass[i]<='Z'){
            unum++;
        }
        else if(pass[i]>='0' && pass[i]<='9'){
            nnum++;
        }
        else if(pass[i]=='@' || pass[i]=='#' || pass[i]=='$' || pass[i]=='*' || pass[i]=='!'){
            snum++;
        }
    }
    if(lnum>0 && unum>0 && nnum>0 && snum>0){
        return 0;
    }
    else{
        if(lnum==0)
            printf("\n\t\tLower letter Miss");
        if(unum==0)
            printf("\n\t\tUpper letter Miss");
        if(nnum==0)
            printf("\n\t\tNumeric value Miss");
        if(snum==0)
            printf("\n\t\tSpecial letter Miss( @, #, $, *, !)");
    }
}

    /* ---------------------------------------------------------------------------------------------------------------------------------------- */
//Main Function

int main(){
    FILE * fptr;
    DIR * d;
    int choice,user_choice,Exit=1,Edit=1,user_age,user_account_no,edit_choice,User=1,LogIn=1,history_choice,History=1,History_count=0,History_Day,History_Mon,History_year,History_Day2,History_Mon2,History_year2,Same_Acc_No,Manager,manager_choice,Cashier,Cashier_choice;
    long int user_amount,receiver_amount,WithdrawCash;long long int user_aadhar_no;
    char str[11],file_name[28],user_name[25],new_user_name[25],user_password[20],user_re_pass[20],user_branch[101],ch,trans_name[28],Str1[25],Str2[25],Str3[25],Str4[25],Str5[25],Str6[20],Str7[20],Amount[10],manager_user_name[25],manager_password[30],Cashier_User_Name[25],Cashier_Password[30];
    //If Account Number File Not Create Then It Will Create
    if(fptr=fopen("Account_No.txt","r")==0){
        fptr=fopen("Account_No.txt","w");
        fprintf(fptr,"%s","0");
        fclose(fptr);
    }

    //If History File Not Create Then It will Create
    if(fptr=fopen("History.txt","r")==0){
        fptr=fopen("History.txt","w");
        fclose(fptr);
    }

    //It Will Collect The Account Number From Account_No File
    fptr=fopen("Account_No.txt","r");
        fscanf(fptr,"%s",str);
        count=atoi(str);
    fclose(fptr);

    do{
        printf("\n              Sample Bank Management                 \n");
        printf("\n1.Manager");
        printf("\n2.cashier");
        printf("\n3.User");
        printf("\n4.Exit");
        printf("\nEnter Who are You : ");
        scanf("%d",&choice);
        switch(choice){                   //Which Roles are There Like Manager, Cashier, User .
            case 1:
                Manager=1;
                printf("\n\tEnter the User Name : ");
                scanf("%s",manager_user_name);
                while(strcmp(manager_user_name,"manager")!=0){
                    printf("\n\tManager User Name was Wrong\n");
                    printf("\n\tEnter the Correct User Name : ");
                    scanf("%s",manager_user_name);
                }
                printf("\n\tEnter the Password : ");
                scanf("%s",manager_password);
                while(strcmp(manager_password,"Manager@123")!=0){
                    printf("\n\tManager Password is Wrong\n");
                    printf("\n\tEnter the Correct Password : ");
                    scanf("%s",manager_password);
                }
                struct dirent *de;
                int UserName_list=0;
                do{
                    printf("\n\t        Manager        \n");
                    printf("\n\t1.Can View All Users List ");
                    printf("\n\t2.Specific Account Details");
                    printf("\n\t3.LogOut");
                    printf("\n\tEnter the Choice : ");
                    scanf("%d",&manager_choice);
                    switch(manager_choice){
                        case 1:
                            d = opendir(".");
                            while ((de = readdir(d)) != NULL){
                                int l=strlen(de->d_name),acc_no;
                                char a[10];
                                if(l>=8){
                                    if(de->d_name[l-4]=='.' && de->d_name[l-3]=='t' && de->d_name[l-2]=='x' && de->d_name[l-1]=='t'){
                                        fptr=fopen(de->d_name,"r");
                                            char *token=strtok(de->d_name,".");
                                            if(strcmp(token,"Account_No") && strcmp(token,"History")){
                                                UserName_list++;
                                                if(UserName_list==1)
                                                    printf("\n\t\t\tThe Bank Users Name List");
                                                printf("\n\t\t%s",token);
                                            }
                                        fclose(fptr);
                                    }
                                }
                            }
                            closedir(d);
                            if(UserName_list==0)
                                printf("\n\tNo Users Are There in Bank ");
                            printf("\n");
                            break;
                        case 2:
                            printf("\n\t\tEnter the Account Number : ");
                            scanf("%d",&user_account_no);
                            while(checkAccountNumberisThere(user_account_no,d,fptr)){
                                printf("\n\t\tGiven Account Number is Not There!!!");
                                printf("\n\t\tEnter the Correct Account Number : ");
                                scanf("%d",&user_account_no);
                            }
                            break;
                        case 3:
                            Manager=0;
                            printf("\n\t\tManager LogOut Successfully\n");
                            break;
                        default:
                            printf("\n\t\tInvalid Input ");
                            break;
                    }
                }while(Manager);
                break;
            case 2:
                Cashier=1;
                printf("\n\tEnter the Cashier User Name : ");
                scanf("%s",Cashier_User_Name);
                while(strcmp(Cashier_User_Name,"cashier")!=0){
                    printf("\n\tCashier User Name was Wrong\n");
                    printf("\n\tEnter the Correct Cashier User Name : ");
                    scanf("%s",Cashier_User_Name);
                }
                printf("\n\tEnter the Cashier Password : ");
                scanf("%s",Cashier_Password);
                while(strcmp(Cashier_Password,"Cashier@123")!=0){
                    printf("\n\tCashier Password is Wrong\n");
                    printf("\n\tEnter the Correct Cashier Password : ");
                    scanf("%s",Cashier_Password);
                }
                do{
                    printf("\n\t\t         Cashier         \n");
                    printf("\n\t\t1.All Time Transactions For Total Bank Accounts ");
                    printf("\n\t\t2.LogOut");
                    printf("\n\t\tEnter Your Choice : ");
                    scanf("%d",&Cashier_choice);
                    switch(Cashier_choice){
                        case 1:
                            History_count=0;
                            fptr=fopen("History.txt","r");
                            while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                History_count++;
                                if(History_count==1)
                                    printf("\n\t\t\t\tAll Time Transaction Details ");
                                printf("\n\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                            }
                            if(History_count==0)
                                printf("\n\t\tNo Transaction That Time ");
                            fclose(fptr);
                            printf("\n");
                            break;
                        case 2:
                            Cashier=0;
                            printf("\n\t\tCashier Account LogOut Successfully");
                            break;
                        default:
                            printf("\n\t\tInvalid Input\n");
                    }
                }while(Cashier);
                break;
            case 3:
                User=1;
                do{
                    printf("\n\t        User          \n");
                    printf("\n\t1.Create Account Or SignUp");
                    printf("\n\t2.Login");
                    printf("\n\t3.Forgot Password");
                    printf("\n\t4.Back");
                    printf("\n\tEnter the Choice : ");
                    scanf("%d",&user_choice);
                    switch(user_choice){
                        case 1:
                            printf("\t\t      Create Account       ");
                            printf("\n\t\tEnter the Name( User Name Min 4 Letters & Max 25 Letters ): ");
                            scanf("%s",user_name);
                            while(checkUserName(user_name,fptr)){
                                printf("\n\t\tEnter the Name( User Name Min 4 Letters & Max 25 Letters ): ");
                                scanf("%s",user_name);
                            }
                            printf("\n\t\tEnter the Password (Must 1 Upper ,1 Lower ,1 Number, 1 Special Character):");
                            scanf("%s",user_password);
                            while(checkUserPassword(user_password)){
                                printf("\n\t\tEnter the Password (Must 1 Upper ,1 Lower ,1 Number, 1 Special Character):");
                                scanf("%s",user_password);
                            }
                            printf("\n\t\tRe-Enter the Password : ");
                            scanf("%s",user_re_pass);
                            while(checkRePass(user_password,user_re_pass)){
                                printf("\n\t\tRe-Enter the Password : ");
                                scanf("%s",user_re_pass);
                            }
                            printf("\n\t\tEnter the Age ( 18+ Only ): ");
                            scanf("%d",&user_age);
                            if(checkAgeLimit(user_age)==0){
                                printf("\t\tYour Age Limit is Less Then 18 So you con't Create Account ");
                                break;
                            }
                            printf("\n\t\tEnter the Branch : ");
                            scanf("%s",user_branch);
                            printf("\n\t\tEnter the Aadhar Card Number ( 12 Digit Number ): ");
                            scanf("%lld",&user_aadhar_no);
                            while(checkAadharNo(user_aadhar_no)!=12){
                                printf("\n\t\tYour Aadhar Number is Not Correct !!!");
                                printf("\n\t\tEnter the Aadhar Card Number ( 12 Digit Number ): ");
                                scanf("%lld",&user_aadhar_no);
                            }
                            printf("\n\t\tEnter Your First Deposit Amount ( Minimum Balance is 1000 ): ");
                            scanf("%ld",&user_amount);
                            //File Create For New User Only
                                strcpy(file_name,user_name);
                                strcat(file_name,".txt");
                                fptr=fopen(file_name,"a");
                                    fprintf(fptr,"%d %s %s %d %s %lld %ld",++count,user_name,user_password,user_age,user_branch,user_aadhar_no,user_amount);
                                fclose(fptr);
                                fptr=fopen("History.txt","a");
                                    //
                                    time_t t = time(NULL);
                                    struct tm tm = *localtime(&t);
                                    sprintf(Date,"%d-%02d-%02d",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900);
                                    sprintf(Time,"%02d:%02d:%02d",tm.tm_hour,tm.tm_min,tm.tm_sec);
                                    //
                                    fprintf(fptr,"%s %s %s First Deposit Amount %ld ",user_name,Date,Time,user_amount);
                                fclose(fptr);
                            //
                            printf("\n\t\t\tYour Account Number is : %d (It's Very Important ... Don't Forgot)!!!\n",count);
                            printf("\n\t Your Account Created Successfully.... You Can LogIn Now");
                        break;
                        case 2:
                            printf("\n\t\t     LogIn     \n");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            if(checkAccountisThere(user_name,fptr)!=0){
                                printf("\n\t\t!!! UserName Was Wrong !!!");
                                break;
                            }
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            if(check_Account_is_There(user_name,user_password,fptr)==0){
                                printf("\n\t\t!!! Login SuccessFully Do Your Work < All The Best >.... !!!\n");
                                LogIn=1;
                                do{
                                    printf("\n\t\t\t          User Do Something             \n");
                                    printf("\n\t\t\t1.View Account Details");
                                    printf("\n\t\t\t2.Change Details");
                                    printf("\n\t\t\t3.Add Cash");
                                    printf("\n\t\t\t4.Withdraw Cash");
                                    printf("\n\t\t\t5.Money Transaction");
                                    printf("\n\t\t\t6.Transaction History");
                                    printf("\n\t\t\t7.LogOut");
                                    printf("\n\t\t\tEnter the Choice:");
                                    scanf("%d",&choice);
                                    switch(choice){
                                        case 1:
                                            userAccountDetail(user_name,user_password,fptr);
                                            break;
                                        case 2:
                                            if(userAccountDetail(user_name,user_password,fptr)){
                                                printf("\n\t\tIf You Want Any Edit (yes/no) : ");
                                                scanf("%s",str);
                                                check_yes(str);
                                                if(check_yes(str)==0){
                                                    Edit=1;
                                                    do{
                                                        printf("\n\t\t\t\t     Edit Account Details      \n");
                                                        printf("\n\t\t\t\t1.Edit Name");
                                                        printf("\n\t\t\t\t2.Edit Age");
                                                        printf("\n\t\t\t\t3.Edit Branch");
                                                        printf("\n\t\t\t\t4.Edit Aadhar");
                                                        printf("\n\t\t\t\t5.Finish");
                                                        printf("\n\t\t\t\tEnter Your Choice : ");
                                                        scanf("%d",&edit_choice);
                                                        switch(edit_choice){                       //User Edit Bank Details Like( Name , Age , Branch , ....)
                                                            case 1:
                                                                printf("\n\t\t\tEnter the New Name : ");
                                                                scanf("%s",new_user_name);
                                                                while(checkUserName(new_user_name,fptr)){
                                                                    printf("\n\t\tEnter the Name( User Name Min 4 Letters & Max 25 Letters ): ");
                                                                    scanf("%s",new_user_name);
                                                                }
                                                                printf("\n%s %s",strcat(user_name,".txt"),strcat(new_user_name,".txt"));
                                                                int Rename=rename(strcat(user_name,".txt"),strcat(new_user_name,".txt"));
                                                                printf(" %d",Rename);
                                                                break;
                                                            case 2:
                                                                printf("\n\t\t\t\tEnter the Current Age (18+) : ");
                                                                scanf("%d",&user_age);
                                                                if(checkAgeLimit(user_age)==0){
                                                                    printf("\n\t\t\t!!! Your Age Under 18 So You Don't Create Own Account !!!");
                                                                    break;
                                                                }
                                                                editAge(user_name,user_age,fptr);
                                                                userAccountDetail(user_name,user_password,fptr);
                                                                break;
                                                            case 3:
                                                                printf("\n\t\t\t\tEnter the Current Branch : ");
                                                                scanf("%s",user_branch);
                                                                editBranch(user_name,user_branch,fptr);
                                                                userAccountDetail(user_name,user_password,fptr);
                                                                break;
                                                            case 4:
                                                                printf("\n\t\t\t\tEnter the Correct Aadhar Number ( 12 Digit Numbers ): ");
                                                                scanf("%lld",&user_aadhar_no);
                                                                while(checkAadharNo(user_aadhar_no)!=12){
                                                                    printf("\n\t\tYour Aadhar Number is Not Correct !!!");
                                                                    printf("\n\t\tEnter the Aadhar Card Number ( 12 Digit Numbers ): ");
                                                                    scanf("%lld",&user_aadhar_no);
                                                                }
                                                                editAadhar(user_name,user_aadhar_no,fptr);
                                                                userAccountDetail(user_name,user_password,fptr);
                                                                break;
                                                            case 5:
                                                                Edit=0;
                                                                break;
                                                            default:
                                                                printf("\n\t\t\t!!!Invalid Choice....!!!\n");
                                                                break;
                                                        }
                                                    }while(Edit);
                                                    userAccountDetail(user_name,user_password,fptr);
                                                }
                                            }
                                            break;
                                        case 3:
                                            if(userAccountDetail(user_name,user_password,fptr)){
                                                long int AddCash;
                                                printf("\n\t\t\t\t*(If You Want Exit Enter '0' )*");
                                                printf("\n\t\tEnter the Amount You ADD : ");
                                                scanf("%ld",&AddCash);
                                                //AddCash Not A Negative Amount Only Positive Amount Give
                                                while(0>AddCash){
                                                    printf("\n\t\t!!!Enter Amount Only Positive Plz!!!\n");
                                                    printf("\n\t\t\t\t*(If You Want Exit Enter '0' )*");
                                                    printf("\n\t\tEnter the Amount You ADD : ");
                                                    scanf("%ld",&AddCash);
                                                }
                                                //AddCash Will be Zero Then Exit
                                                if(AddCash==0){
                                                    printf("\n\t\t\tAmount Not Added Exit Successfully\n");
                                                    break;
                                                }
                                                AddAmount(user_name,AddCash,fptr);
                                                fptr=fopen("History.txt","a");
                                                //
                                                    time_t t = time(NULL);
                                                    struct tm tm = *localtime(&t);
                                                    sprintf(Date,"%d-%02d-%02d",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900);
                                                    sprintf(Time,"%02d:%02d:%02d",tm.tm_hour,tm.tm_min,tm.tm_sec);
                                                //
                                                    fprintf(fptr,"%s %s %s Add Cash Amount %ld ",user_name,Date,Time,AddCash);
                                                fclose(fptr);
                                                printf("\n\t\t\t%d-%02d-%02d %02d:%02d:%02d Amount %ld Added Your Account Successfully",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900,tm.tm_hour,tm.tm_min,tm.tm_sec,AddCash);
                                                userAccountDetail(user_name,user_password,fptr);
                                            }
                                            break;
                                        case 4:
                                            if(userAccountDetail(user_name,user_password,fptr)){
                                                printf("\n\t\t\t\t*(If You Want Exit Enter '0' )*");
                                                printf("\n\t\tEnter the Amount You Withdraw : ");
                                                scanf("%ld",&WithdrawCash);
                                                user_amount=ReturnUserAmount(user_name,fptr);
                                                //WithdrawCash Not A Negative Amount Only Positive Amount Give
                                                while(0>WithdrawCash){
                                                    printf("\n\t\t!!!Enter Amount Only Positive Plz!!!\n");
                                                    printf("\n\t\t\t\t*(If You Want Exit Enter '0' )*");
                                                    printf("\n\t\tEnter the Amount You Withdraw : ");
                                                    scanf("%ld",&WithdrawCash);
                                                }
                                                user_amount=ReturnUserAmount(user_name,fptr);
                                                //Withdraw Cash Not Higher Then Your Account Balance
                                                if(user_amount<WithdrawCash){
                                                    printf("\n\t\tWithdraw Amount is Higher Then Your Account Balance\n");
                                                    break;
                                                }

                                                //WithdrawCash Will be Zero Then Exit
                                                if(WithdrawCash==0){
                                                    printf("\n\t\t\tAmount Not Added Exit Successfully\n");
                                                    break;
                                                }
                                                AddAmount(user_name,-WithdrawCash,fptr);
                                                fptr=fopen("History.txt","a");
                                                //
                                                    time_t t = time(NULL);
                                                    struct tm tm = *localtime(&t);
                                                    sprintf(Date,"%d-%02d-%02d",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900);
                                                    sprintf(Time,"%02d:%02d:%02d",tm.tm_hour,tm.tm_min,tm.tm_sec);
                                                //
                                                    fprintf(fptr,"%s %s %s Withdraw Cash Amount %ld ",user_name,Date,Time,WithdrawCash);
                                                fclose(fptr);
                                                printf("\n\t\t\t%d-%02d-%02d %02d:%02d:%02d Amount %ld Withdraw Your Account Successfully",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900,tm.tm_hour,tm.tm_min,tm.tm_sec,WithdrawCash);
                                                userAccountDetail(user_name,user_password,fptr);
                                            }
                                            break;
                                        case 5:
                                            Same_Acc_No=0;
                                            printf("\n\t\tEnter the Account Number : ");
                                            scanf("%d",&user_account_no);
                                            while(checkAccountNumberisThere(user_account_no,d,fptr)){
                                                printf("\n\t\tGiven Account Number is Not There!!!");
                                                printf("\n\t\tEnter the Correct Account Number : ");
                                                scanf("%d",&user_account_no);
                                            }
                                            printf("\n\t\t*( If You Not Send Money then Enter Amount Value '0' )* \n");

                                            struct dirent *de;
                                            d = opendir(".");
                                            while ((de = readdir(d)) != NULL){
                                                int l=strlen(de->d_name),acc_no;
                                                char a[10];
                                                if(l>=8){
                                                    if(de->d_name[l-4]=='.' && de->d_name[l-3]=='t' && de->d_name[l-2]=='x' && de->d_name[l-1]=='t'){
                                                        fptr=fopen(de->d_name,"r");
                                                            fscanf(fptr,"%s %s",a,trans_name);
                                                            if(atoi(a)==user_account_no){
                                                                if(strcmp(trans_name,user_name)==0)
                                                                    Same_Acc_No=1;
                                                                break;
                                                            }
                                                        fclose(fptr);
                                                    }
                                                }
                                            }
                                            closedir(d);
                                            if(Same_Acc_No==1){
                                                printf("\n\t\t !!! Your Account to Your Account Money Transaction Will Not Approve !!!");
                                                break;
                                            }
                                            user_amount=ReturnUserAmount(user_name,fptr);
                                            printf("\n\t\tEnter How Much Amount You Transfer : ");
                                            scanf("%ld",&receiver_amount);
                                            if(receiver_amount==0){
                                                printf("\n\t\tTransaction Canceled \n");
                                                break;
                                            }
                                            while(1){
                                                if((user_amount>=receiver_amount)&&(receiver_amount>0))
                                                    break;
                                                else{
                                                    if(user_amount<receiver_amount){
                                                        printf("\n\t\tThe Amount Will Higher then your Account Balance.... Plz Reduce The Amount");
                                                        printf("\n\t\tEnter How Much Amount You Transfer : ");
                                                        scanf("%ld",&receiver_amount);
                                                    }
                                                    else{
                                                        printf("\n\t\tThe Amount Will Lower then Zero .... Plz Increase The Amount");
                                                        printf("\n\t\tEnter How Much Amount You Transfer : ");
                                                        scanf("%ld",&receiver_amount);
                                                    }
                                                }
                                            }
                                            AddAmount(user_name,-receiver_amount,fptr);        //Sender Side Money Reduce
                                            AddAmount(trans_name,receiver_amount,fptr);        //Receiver Side Money Increase
                                            userAccountDetail(user_name,user_password,fptr);
                                            //Append The Message in History File
                                            fptr=fopen("History.txt","a");
                                                sprintf(Amount,"%ld",receiver_amount);
                                                //Time
                                                time_t t = time(NULL);
                                                struct tm tm = *localtime(&t);
                                                sprintf(Date,"%d-%02d-%02d",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900);
                                                sprintf(Time,"%02d:%02d:%02d",tm.tm_hour,tm.tm_min,tm.tm_sec);
                                                //
                                                fprintf(fptr,"%s %s %s send to %s %s ",user_name,Date,Time,trans_name,Amount);
                                                fprintf(fptr,"%s %s %s receive from %s %s ",trans_name,Date,Time,user_name,Amount);
                                            fclose(fptr);
                                            appendMessage(user_name,trans_name,receiver_amount,fptr);
                                            printf("\n\t\t%d-%02d-%02d %02d:%02d:%02d Money %ld Successfully Transferred To %d !!!",tm.tm_mday,tm.tm_mon+1,tm.tm_year+1900,tm.tm_hour,tm.tm_min,tm.tm_sec,receiver_amount,user_account_no);
                                            break;
                                        case 6:
                                            History=1;
                                            do{
                                                printf("\n\t\t\t\t        Transaction History       ");
                                                printf("\n\t\t\t\t1.Some Date to Some Date Transaction History");
                                                printf("\n\t\t\t\t2.Some Date to Current Date Transaction History");
                                                printf("\n\t\t\t\t3.All Time Transactions");
                                                printf("\n\t\t\t\t4.Any Specific Day Transaction History ");
                                                printf("\n\t\t\t\t5.Exit");
                                                printf("\n\t\t\t\tEnter the Choice : ");
                                                scanf("%d",&history_choice);
                                                switch(history_choice){
                                                    case 1:
                                                        History_count=0;
                                                        //First Date
                                                        printf("\n\t\t\t\tEnter the Date One (DD/MM/YYYY) : ");
                                                        scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        while(checkDay(History_Day,History_Mon,History_year)){
                                                            printf("\n\t\t\tEnter the Valid Date One \n");
                                                            printf("\n\t\t\t\tEnter the Date One (DD/MM/YYYY) : ");
                                                            scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        }
                                                        //Second Date
                                                        printf("\n\t\t\t\tEnter the Date Two (DD/MM/YYYY) : ");
                                                        scanf("%d%c%d%c%d",&History_Day2,&ch,&History_Mon2,&ch,&History_year2);
                                                        while(checkDay(History_Day2,History_Mon2,History_year2)){
                                                            printf("\n\t\t\tEnter the Valid Date Two \n");
                                                            printf("\n\t\t\t\tEnter the Date two (DD/MM/YYYY) : ");
                                                            scanf("%d%c%d%c%d",&History_Day2,&ch,&History_Mon2,&ch,&History_year2);
                                                        }
                                                        //Check Who is First
                                                        if(checkDatesWhoIsFirst(History_Day,History_Mon,History_year,History_Day2,History_Mon2,History_year2)){
                                                            fptr=fopen("History.txt","r");
                                                            while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                                                if(strcmp(Str1,user_name)==0){
                                                                    int day,mon,year;
                                                                    char s_day[3],s_mon[3],s_year[5];
                                                                    s_day[0]=Str2[0];s_day[1]=Str2[1];day=atoi(s_day);
                                                                    s_mon[0]=Str2[3];s_mon[1]=Str2[4];mon=atoi(s_mon);
                                                                    s_year[0]=Str2[6];s_year[1]=Str2[7];s_year[2]=Str2[8];s_year[3]=Str2[9];year=atoi(s_year);
                                                                    if(History_year<year && year<History_year2){
                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                        History_count++;
                                                                    }
                                                                    else{
                                                                        //
                                                                        if(History_year==year && year==History_year2){
                                                                            if(History_Mon<mon && mon<History_Mon2){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else if(History_Mon==mon && mon<History_Mon2){
                                                                                if(History_Day<=day){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                            else if(History_Mon<mon && mon==History_Mon2){
                                                                                if(day<=History_Day2){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                            else if(History_Mon==mon && History_Mon2==mon){
                                                                                if(History_Day<=day && day<=History_Day2){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                        }
                                                                        //
                                                                        else if(History_year==year && year<History_year2){
                                                                            if(History_Mon<mon){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else{
                                                                                if(History_Mon==mon){
                                                                                    if(History_Day<=day){
                                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                        History_count++;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        //
                                                                        else if(History_year<year && year==History_year2){
                                                                            if(mon<History_Mon2){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else{
                                                                                if(mon==History_Mon2){
                                                                                    if(day<=History_Day2){
                                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                        History_count++;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            fptr=fopen("History.txt","r");
                                                            while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                                                if(strcmp(Str1,user_name)==0){
                                                                    int day,mon,year;
                                                                    char s_day[3],s_mon[3],s_year[5];
                                                                    s_day[0]=Str2[0];s_day[1]=Str2[1];day=atoi(s_day);
                                                                    s_mon[0]=Str2[3];s_mon[1]=Str2[4];mon=atoi(s_mon);
                                                                    s_year[0]=Str2[6];s_year[1]=Str2[7];s_year[2]=Str2[8];s_year[3]=Str2[9];year=atoi(s_year);
                                                                    if(History_year2<year && year<History_year){
                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                        History_count++;
                                                                    }
                                                                    else{
                                                                        //
                                                                        if(History_year2==year && year==History_year){
                                                                            if(History_Mon2<mon && mon<History_Mon){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else if(History_Mon2==mon && mon<History_Mon){
                                                                                if(History_Day2<=day){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                            else if(History_Mon2<mon && mon==History_Mon){
                                                                                if(day<=History_Day){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                            else if(History_Mon2==mon && History_Mon==mon){
                                                                                if(History_Day2<=day && day<=History_Day){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                        }
                                                                        //
                                                                        else if(History_year2==year && year<History_year){
                                                                            if(History_Mon2<mon){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else{
                                                                                if(History_Mon2==mon){
                                                                                    if(History_Day2<=day){
                                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                        History_count++;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        //
                                                                        else if(History_year2<year && year==History_year){
                                                                            if(mon<History_Mon){
                                                                                printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                History_count++;
                                                                            }
                                                                            else{
                                                                                if(mon==History_Mon){
                                                                                    if(day<=History_Day){
                                                                                        printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                        History_count++;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if(History_count==0)
                                                            printf("\n\t\t\tNo Transaction That Time ");
                                                        fclose(fptr);
                                                        printf("\n");
                                                        break;
                                                    case 2:
                                                        History_count=0;
                                                        printf("\n\t\t\t\tEnter the Date (DD/MM/YYYY) : ");
                                                        scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        while(checkDay(History_Day,History_Mon,History_year)){
                                                            printf("\n\t\t\tEnter the Valid Date\n");
                                                            printf("\n\t\t\t\tEnter the Date Formate of DD/MM/YYYY : ");
                                                            scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        }
                                                        fptr=fopen("History.txt","r");
                                                        while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                                            if(strcmp(Str1,user_name)==0){
                                                                int day,mon,year;
                                                                char s_day[3],s_mon[3],s_year[5];
                                                                s_day[0]=Str2[0];s_day[1]=Str2[1];day=atoi(s_day);
                                                                s_mon[0]=Str2[3];s_mon[1]=Str2[4];mon=atoi(s_mon);
                                                                s_year[0]=Str2[6];s_year[1]=Str2[7];s_year[2]=Str2[8];s_year[3]=Str2[9];year=atoi(s_year);
                                                                if(History_year<year){
                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                    History_count++;
                                                                }
                                                                else{
                                                                    if(History_year==year){
                                                                        if(History_Mon<mon){
                                                                            printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                            History_count++;
                                                                        }
                                                                        else{
                                                                            if(History_Mon==mon){
                                                                                if(History_Day<=day){
                                                                                    printf("\n\t\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                                    History_count++;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if(History_count==0)
                                                            printf("\n\t\t\tNo Transaction That Time ");
                                                        fclose(fptr);
                                                        printf("\n");
                                                        break;
                                                    case 3:
                                                        History_count=0;
                                                        fptr=fopen("History.txt","r");
                                                        while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                                            if(strcmp(Str1,user_name)==0){
                                                                printf("\n\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                History_count++;
                                                            }
                                                        }
                                                        if(History_count==0)
                                                            printf("\n\t\t\tNo Transaction That Time ");
                                                        fclose(fptr);
                                                        printf("\n");
                                                        break;
                                                    case 4:
                                                        History_count=0;
                                                        printf("\n\t\t\t\tEnter the Date (DD/MM/YYYY) : ");
                                                        scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        while(checkDay(History_Day,History_Mon,History_year)){
                                                            printf("\n\t\t\tEnter the Valid Date\n");
                                                            printf("\n\t\t\t\tEnter the Date Formate of DD/MM/YYYY : ");
                                                            scanf("%d%c%d%c%d",&History_Day,&ch,&History_Mon,&ch,&History_year);
                                                        }
                                                        fptr=fopen("History.txt","r");
                                                        while(fscanf(fptr,"%s%s%s%s%s%s%s",Str1,Str2,Str3,Str4,Str5,Str6,Str7)!=EOF){
                                                            if(strcmp(Str1,user_name)==0){
                                                                int day,mon,year;
                                                                char s_day[3],s_mon[3],s_year[5];
                                                                s_day[0]=Str2[0];s_day[1]=Str2[1];day=atoi(s_day);
                                                                s_mon[0]=Str2[3];s_mon[1]=Str2[4];mon=atoi(s_mon);
                                                                s_year[0]=Str2[6];s_year[1]=Str2[7];s_year[2]=Str2[8];s_year[3]=Str2[9];year=atoi(s_year);
                                                                if(year==History_year && mon==History_Mon && day==History_Day){
                                                                    printf("\n\t\t\t%s %s %s %s %s %s %s",Str1,Str2,Str3,Str4,Str5,Str6,Str7);
                                                                    History_count++;
                                                                }
                                                            }
                                                        }
                                                        if(History_count==0)
                                                            printf("\n\t\t\tNo Transaction That Time ");

                                                        fclose(fptr);
                                                        printf("\n");
                                                        break;
                                                    case 5:
                                                        History=0;
                                                        break;
                                                    default:
                                                        printf("\n\t\t !!! Invalid Input !!!");
                                                }
                                            }while(History);
                                            break;
                                        case 7:
                                            LogIn=0;
                                            printf("\n\t\t!!! LogOut Successfully !!!");
                                            break;
                                        default:
                                            printf("\n\t\tInvalid Choice");
                                            break;
                                    }
                                }while(LogIn);
                            }

                            else{
                                printf("\n\tLogin Failed");
                            }
                        break;
                        case 3:
                            printf("\n\t\tEnter the User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter the Aadhar Card Number : ");
                            scanf("%lld",&user_aadhar_no);
                            ForgotPassword(user_name,user_aadhar_no,fptr);
                            break;
                        case 4:
                            User=0;
                            break;
                        default:
                            printf("\n\tIf You want to LogOut Yes/No : ");
                            scanf("%s",str);
                            check_yes(str);
                            if(check_yes(str)==0){
                                User=0;
                            }
                            else{
                                printf("\n\tEnter the Valid Choice !!!");
                            }
                            break;
                    }
                }while(User);
                break;
            case 4:
                printf("\tThanks For the Visit Sir !!!\n\tHave a Good Day....\n\n");
                Exit=0;
                break;
            default:
                printf("\n\tIf You want to Exit Yes/No : ");
                scanf("%s",str);
                check_yes(str);
                if(check_yes(str)==0){
                    printf("\tThanks For the Visit Sir !!!\n\tHave a Good Day....\n\n");
                    Exit=0;
                }
                else{
                    printf("\n\tEnter the Valid Choice !!!");
                }
        }
    }while(Exit);

    //Write a Count in Account Number File
    fptr=fopen("Account_No.txt","w");
        fprintf(fptr,"%d",count);
    fclose(fptr);

    return 0;
}
