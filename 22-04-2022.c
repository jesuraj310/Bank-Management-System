#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//Global Declaration
int count=0;

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
            printf("\n\t\tPassword is wrong");
            return 0;
        }
    }
    else{
        printf("\n\t\tUser Name is Wrong");
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
void editAadhar(char Name[25],long int Aadhar,FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    strcpy(fname,Name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    sprintf(aadhar,"%ld",Aadhar);
    fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
}
int ForgotPassword(char Name[25],long int Aadhar_no,FILE *fptr){
    char fname[28],Aadhar[15],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],user_password[30];
    strcpy(fname,Name);
    strcat(fname,".txt");
    if(fptr=fopen(fname,"r")){
        sprintf(Aadhar,"%ld",Aadhar_no);
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
        if(strcmp(aadhar,Aadhar)==0){
            printf("\n\t\tEnter the New Password : ");
            scanf("%s",user_password);
            while(checkUserPassword(user_password)){
                printf("\n\t\tEnter the New Password : ");
                scanf("%s",user_password);
            }
            printf("!!!Password Successfully Changed!!!");
        }
        fclose(fptr);
        fptr=fopen(fname,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,user_password,age,branch,aadhar,amount);
        fclose(fptr);
    }
}
int AddAmount(char user_name[25],long int Amount,FILE *fptr){
    char acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15],file_name[28];
    strcpy(file_name,user_name);
    strcat(file_name,".txt");
    fptr=fopen(file_name,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    long int User_amount=atoi(amount)+Amount;
    sprintf(amount,"%ld",User_amount);
    fptr=fopen(file_name,"w");
        fprintf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
}
//Checking Account Details
int ReturnUserAmount(char Name[25],FILE *fptr){
    char fname[28],acc_no[8],name[25],pass[20],age[10],branch[30],aadhar[15],amount[15];
    strcpy(fname,name);
    strcat(fname,".txt");
    fptr=fopen(fname,"r");
        fscanf(fptr,"%s %s %s %s %s %s %s",acc_no,name,pass,age,branch,aadhar,amount);
    fclose(fptr);
    int Amount=atoi(amount);
    return Amount;
}
int checkAccountisThere(char name[25],FILE *fptr){
    char fname[28];
    strcpy(fname,name);
    strcat(fname,".txt");
    if(fptr=fopen(fname,"r")){
        return 0;
    }
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

int checkAadharNo(long int Aadhar){
    char aadhar[15];
    sprintf(aadhar,"%ld",Aadhar);
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
    printf("\t\t!!!SignUp Failled \n\t\tRetype the Password Correctly!!!");
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
    FILE *fptr;
    int choice,user_choice,Exit=1,user_age,user_account_no,edit_choice,User=1;
    long int user_aadhar_no,user_amount;
    char str[11],file_name[28],user_name[25],user_password[20],user_re_pass[20],user_branch[101],ch;
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
                printf("\nManager");
                break;
            case 2:
                printf("\nCashier ");
                break;
            case 3:
                User=1;
                do{
                    printf("\n\t        User          \n");
                    printf("\n\t1.Create Account");
                    printf("\n\t2.view Account Details");
                    printf("\n\t3.Change Details");
                    printf("\n\t4.Forgot Password");
                    printf("\n\t5.Add Cash");
                    printf("\n\t6.Money Transaction");
                    printf("\n\t7.Finish");
                    printf("\n\tEnter the Work : ");
                    scanf("%d",&user_choice);
                    switch(user_choice){                    //User Enroll And Do Something Like Create , View, Edit .
                                                //Create Account
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
                            printf("\n\t\tEnter the Aadhar Card Number ( 10 Digit Number ): ");
                            scanf("%ld",&user_aadhar_no);
                            while(checkAadharNo(user_aadhar_no)!=10){
                                printf("\n\t\tYour Aadhar Number is Not Correct !!!");
                                printf("\n\t\tEnter the Aadhar Card Number ( 10 Digit Number ): ");
                                scanf("%ld",&user_aadhar_no);
                            }
                            printf("\n\t\tEnter Your First Deposit Amount ( Minimum Balance is 1000 ): ");
                            scanf("%ld",&user_amount);
                            //
                                count++;
                                strcpy(file_name,user_name);
                                strcat(file_name,".txt");
                                fptr=fopen(file_name,"a");
                                    fprintf(fptr,"%d %s %s %d %s %ld %ld",count,user_name,user_password,user_age,user_branch,user_aadhar_no,user_amount);
                                fclose(fptr);
                            //
                            //createAccount(user_name,user_password,user_age,user_branch,user_aadhar_no,user_amount);
                            printf("\n\t\t\tYour Account Number is : %d (It's Very Important ... Don't Forgot)!!!\n",count);
                            break;
                                                //View Account Details
                        case 2:
                            printf("\n\t\t     View Account Details     \n");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            userAccountDetail(user_name,user_password,fptr);
                            break;
                                                //Edit Account Details
                        case 3:
                            printf("\n\t\t     View Account Details     \n");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            if(userAccountDetail(user_name,user_password,fptr)){
                                printf("\n\t\tIf You Want Any Edit (yes/no) : ");
                                scanf("%s",str);
                                check_yes(str);
                                if(check_yes(str)==0){
                                    int Edit=1;
                                    do{
                                        printf("\n\t\t\t     Edit Account Details      \n");
                                        printf("\n\t\t\t1.Edit Name");
                                        printf("\n\t\t\t2.Edit Age");
                                        printf("\n\t\t\t3.Edit Branch");
                                        printf("\n\t\t\t4.Edit Aadhar");
                                        printf("\n\t\t\t5.Finish");
                                        printf("\n\t\t\tEnter Your Choice : ");
                                        scanf("%d",&edit_choice);
                                        switch(edit_choice){                       //User Edit Bank Details Like( Name , Age , Branch , ....)
                                            case 1:
                                                printf("\n\t\t\tEnter the Correct Name : ");
                                                scanf("%s",user_name);
                                                break;
                                            case 2:
                                                printf("\n\t\t\tEnter the Current Age : ");
                                                scanf("%d",&user_age);
                                                editAge(user_name,user_age,fptr);
                                                userAccountDetail(user_name,user_password,fptr);
                                                break;
                                            case 3:
                                                printf("\n\t\t\tEnter the Current Branch : ");
                                                scanf("%s",user_branch);
                                                editBranch(user_name,user_branch,fptr);
                                                userAccountDetail(user_name,user_password,fptr);
                                                break;
                                            case 4:
                                                printf("\n\t\t\tEnter the Correct Aadhar Number : ");
                                                scanf("%ld",&user_aadhar_no);
                                                editAadhar(user_name,user_aadhar_no,fptr);
                                                userAccountDetail(user_name,user_password,fptr);
                                                break;
                                            case 5:
                                                Edit=0;
                                                break;
                                            default:
                                                Edit=0;
                                        }
                                    }while(Edit);
                                    userAccountDetail(user_name,user_password,fptr);
                                }
                            }
                            break;
                        case 4:
                            printf("\n\t\tEnter the User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter the Aadhar Card Number : ");
                            scanf("%ld",&user_aadhar_no);
                            ForgotPassword(user_name,user_aadhar_no,fptr);
                            break;
                        case 5:
                            printf("\n\t\t          Add Cash               ");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            if(userAccountDetail(user_name,user_password,fptr)){
                                long int AddCash;
                                printf("\n\t\tEnter the Amount You ADD : ");
                                scanf("%ld",&AddCash);
                                AddAmount(user_name,AddCash,fptr);
                                userAccountDetail(user_name,user_password,fptr);
                            }
                            break;
                        case 6:
                            printf("\n\t\t             Money Transaction             ");
                            printf("\n\t\tEnter Sender User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Sender Password : ");
                            scanf("%s",user_password);
                            if(userAccountDetail(user_name,user_password,fptr)){
                                char trans_name[28],trans_account_no;
                                printf("\n\t\tEnter Receiver User Name : ");
                                scanf("%s",trans_name);
                                while(checkAccountisThere(trans_name,fptr)){
                                    printf("\n\t\tEnter Correct Receiver User Name ");
                                    printf("\n\t\tEnter The User Name : ");
                                    scanf("%s",trans_name);
                                }
                                printf("\n\t\tEnter the Account NUmber : ");
                                scanf("%d",&user_account_no);
                                while(user_account_no<=count){
                                    printf("\n\t\tEnter the Correct Receiver Account Number!!!\n");
                                    printf("\n\t\tEnter the Account Number : ");
                                    scanf("%d",&user_account_no);
                                }
                                user_amount=ReturnUserAmount(user_name,fptr);
                                long int receiver_amount;
                                printf("\n\t\tEnter How Much Amount You Transfer : ");
                                scanf("%ld",&receiver_amount);
                                while(user_amount>=receiver_amount){
                                    printf("\n\t\tThe Amount Will Higher then your Account Balance.... Plz Reduce The Amount");
                                    printf("\n\t\tEnter How Much Amount You Transfer : ");
                                    scanf("%ld",&receiver_amount);
                                }
                                AddAmount(user_name,-receiver_amount,fptr);
                                AddAmount(trans_name,receiver_amount,fptr);
                                userAccountDetail(user_name,user_password,fptr);
                                printf("\n\t\tMoney Successfully Transferred !!!");
                            }
                            break;
                        case 7:
                            User=0;
                            break;
                        default:
                            User=0;
                            break;
                        }
                    }while(User);
                break;
            case 4:
                printf("\t\tThanks For the Visit Sir !!!\n\t\tHave a Good Day\n\n");
                Exit=0;
                break;
            default:
                printf("\n\tIf You want to Exit Yes/No : ");
                scanf("%s",str);
                check_yes(str);
                if(check_yes(str)==0){
                    printf("\tThanks For the Visit Sir !!!\n\t\tHave a Good Day\n\n");
                    Exit=0;
                }
                else{
                    printf("\n\tEnter the Valid Id !!!");
                }
        }
    }while(Exit);
    return 0;
}
