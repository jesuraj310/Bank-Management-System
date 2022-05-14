#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//Singly Linked List Node Creation Process
struct Node{
    int account_no;
    char name[25];
    char password[20];
    int age;
    char branch[101];
    long int aadhar_no;
    long int amount;
    struct Node *next;
};

    /* ---------------------------------------------------------------------------------------------------------------------------------------- */
//Global Variables Declaration
struct Node *head;
struct Node *tail;
int count=0;

    /* ---------------------------------------------------------------------------------------------------------------------------------------- */

//Bank Account Creation
void createAccount(char name[25],char password[20],int age,char branch[101],long int aadhar_no,long int amount){
    struct Node *p = malloc(sizeof(struct Node));
    p->account_no=++count;
    strcpy(p->name,name);
    strcpy(p->password,password);
    p->age=age;
    strcpy(p->branch,branch);
    p->aadhar_no=aadhar_no;
    p->amount=amount;
    p->next=NULL;
    if(head==NULL){
        head=p;
        tail=head;
    }
    else{
        tail->next=p;
        tail=p;
    }
}
  /* ----------------------------------------------------------------------------------------------------------------------------------------*/

//Edit Bank Details
void userEditAadhar(int Account_no,long int Aadhar_no){
    struct Node *q=head;
    while(q!=NULL){
        if(q->account_no==Account_no){
            q->aadhar_no=Aadhar_no;
            break;
        }
        q=q->next;
    }
}
void userEditBranch(int Account_no,char *Branch){
    struct Node *q=head;
    while(q!=NULL){
        if(q->account_no==Account_no){
            strcpy(q->branch,Branch);
            break;
        }
        q=q->next;
    }
}
void userEditAge(int Account_no,int Age){
    struct Node *q=head;
    while(q!=NULL){
        if(q->account_no==Account_no){
            q->age=Age;
            break;
        }
        q=q->next;
    }
}
void userEditName(int Account_no,char *Name){
    struct Node *q=head;
    while(q!=NULL){
        if(q->account_no==Account_no){
            strcpy(q->name,Name);
            break;
        }
        q=q->next;
    }
}
    /*----------------------------------------------------------------------------------------------------------------------------------------*/

//Display User Account Details
int userAccountDetail(char name[25],char password[20]){
    struct Node *q=head;
    while(q!=NULL){
        if(!strcmp(q->password,password)){
            printf("\n\t\tA/C No \t User Name \t Password \t Age \t User Branch \t Aadhar No \t Amount");
            printf("\n\t\t%d \t %s \t %s \t\t %d \t %s \t %ld \t %ld\n",q->account_no,q->name,q->password,q->age,q->branch,q->aadhar_no,q->amount);
            if(q->amount<1000){
                printf("\n\t\tYour Account Balance is Lesser Then Minimum Balance ( 1000 )...\n");
            }
            return q->account_no;
        }
        q=q->next;
    }
    printf("\n\t\tUser Password is Wrong!!!");
    return 0;
}
int userAddCash(int Account_no,long int Amount){
    struct Node *q=head;
    while(q!=NULL){
        if(q->account_no == Account_no){
            q->amount+=Amount;
            return;
        }
        q=q->next;
    }
}
    /* ---------------------------------------------------------------------------------------------------------------------------------------- */

//Check Account is There Or Not...If User Forgot, Then Give Aadhar Number it will Check Aadhar Number match any Account Number
int checkAccount(long int Aadhar_no){
    struct Node *q=head;
    while(q!=NULL){
        if(q->aadhar_no == Aadhar_no){
            return 1;
        }
        q=q->next;
    }
    return 0;
}
int checkForgotPass(long int Aadhar_no){
    struct Node *q=head;
    while(q!=NULL){
        if(q->aadhar_no== Aadhar_no){
            return 1;
        }
        q=q->next;
    }
}
int NewPassword(long int Aadhar_no,char password[20]){
    struct Node *q=head;
    while(q!=NULL){
        if(q->aadhar_no==Aadhar_no){
            strcpy(q->password,password);
            printf("\n\t\tNew Password Updated Successfully");
            return;
        }
    }
}

    /* ---------------------------------------------------------------------------------------------------------------------------------------- */

//Checking Account Details
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

int checkUserName(char *name){
    if(strlen(name)>=4 && strlen(name)<=25)
        return 0;
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
    printf("\t\t SignUp Failled");
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
    int choice,user_choice,Exit=1,user_age,user_account_no,edit_choice,User=1;
    long int user_aadhar_no,user_amount;
    char str[11],user_name[25],user_password[20],user_re_pass[20],user_branch[101],ch;
    do{
        printf("\n              Sample Bank Management                 \n");
        printf("\n1.Manager");
        printf("\n2.cashier");
        printf("\n3.User");
        printf("\n4.Exit");
        printf("\nEnter Who you are : ");
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
                    printf("\n\t6.Finish");
                    printf("\n\tEnter the Work : ");
                    scanf("%d",&user_choice);
                    switch(user_choice){                    //User Enroll And Do Something Like Create , View, Edit .
                                                //Create Account
                        case 1:
                            printf("\t\t      Create Account       ");
                            printf("\n\t\tEnter the Name( User Name Min 4 Letters & Max 25 Letters ): ");
                            scanf("%s",user_name);
                            while(checkUserName(user_name)){
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
                            createAccount(user_name,user_password,user_age,user_branch,user_aadhar_no,user_amount);
                            printf("\n\t\t\tYour Account Number is : %d (It's Very Important ... Don't Forgot)!!!\n",count);
                            break;
                                                //View Account Details
                        case 2:
                            printf("\n\t\t     View Account Details     \n");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            userAccountDetail(user_name,user_password);
                            break;
                                                //Edit Account Details
                        case 3:
                            printf("\n\t\t     View Account Details     \n");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            user_account_no=userAccountDetail(user_name,user_password);
                            if(user_account_no==0)
                                break;
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
                                            userEditName(user_account_no,user_name);
                                            break;
                                        case 2:
                                            printf("\n\t\t\tEnter the Current Age : ");
                                            scanf("%d",&user_age);
                                            userEditAge(user_account_no,user_age);
                                            break;
                                        case 3:
                                            printf("\n\t\t\tEnter the Current Branch : ");
                                            scanf("%s",user_branch);
                                            userEditBranch(user_account_no,user_branch);
                                            break;
                                        case 4:
                                            printf("\n\t\t\tEnter the Correct Aadhar Number : ");
                                            scanf("%ld",&user_aadhar_no);
                                            userEditAadhar(user_account_no,user_aadhar_no);
                                            break;
                                        case 5:
                                            Edit=0;
                                            break;
                                        default:
                                            Edit=0;
                                    }
                                }while(Edit);
                                userAccountDetail(user_name,user_password);
                            }
                            break;
                        case 4:
                            printf("\n\t\tEnter the User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter the Aadhar Number : ");
                            scanf("%ld",&user_aadhar_no);
                            if(checkForgotPass(user_aadhar_no)){
                                printf("\n\t\tEnter the New Password (Must 1 Upper ,1 Lower ,1 Special Character):");
                                scanf("%s",user_password);
                                while(checkUserPassword(user_password)){
                                    printf("\n\t\tEnter the Password (Must 1 Upper ,1 Lower ,1 Special Character):");
                                    scanf("%s",user_password);
                                }
                                NewPassword(user_aadhar_no,user_password);
                            }
                            else{
                                printf("\n\t\tYour is Aathar Number is Not There !!! Plz Create Account \n");
                            }
                            break;
                        case 5:
                            printf("\n\t\t          Add Cash               ");
                            printf("\n\t\tEnter User Name : ");
                            scanf("%s",user_name);
                            printf("\n\t\tEnter Password  : ");
                            scanf("%s",user_password);
                            user_account_no=userAccountDetail(user_name,user_password);
                            if(user_account_no>0){
                                printf("\n\t\tEnter How Amount You Add in Bank : ");
                                scanf("%ld",&user_amount);
                                userAddCash(user_account_no,user_amount);
                                userAccountDetail(user_name,user_password);
                            }
                            break;
                        case 6:
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
