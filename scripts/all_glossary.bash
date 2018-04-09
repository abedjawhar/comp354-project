\subsubsection{1. Create User Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{First name, last name, username and password are mandatory}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user cannot sign up without providing a valid first name, last name, a username and a password}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Sign Up'\\ 2. Leave all the input fields blank\\ 3. Click 'Sign up'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Sign up fails, the account is not created\\ An error window displays all the errors\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The username must be unique}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user cannot sign up with an already existing username}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Successfully sign\\ 2. Log out\\ 3. Go to 'Sign up'\\ 4. Fill in all the input fields\\ 5. Set the username field to be the username of the user created in the first step\\ 6. Click 'Sign Up'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Sign up failed, the account is not created\\ An error window notifies that the username already exists\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The password must be valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user cannot sign up with a password not matching the required format, as specified in the business rules}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Sign up'\\ 2. Fill in all the input fields\\ 3. Set the password to an alpha-numeric sequence of length less than 4\\ 4. Set the repeat password field to match the password field\\ 5. Click 'Sign Up'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Sign up failed, the account is not created\\ An error window notifies that the password is not valid\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The user account is successfully created}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user must be able to successfully create an account provided that all input information is valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Sign up'\\ 2. Fill in all the input fields with valid data\\ 3. Click 'Sign Up'\\ 4. Moved to the login page: input the username and the passowrd\\ 5. Click 'Login'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Sign up successful, the account is created\\ The user is logged-in to the newly created account\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{2. Delete User Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Password required}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The program asks for the user's password before to delete the account}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Update User Account'\\ 2. Click 'Delete user'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A an input window appears asking for the user password\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The password must be valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user cannot delete the account if the password is invalid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Update User Account'\\ 2. Click 'Delete user'\\ 3. Enter a wrong password\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The account is not deleted\\ An error window must appear notifying the user that the password was invalid\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The account is successfully deleted}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user account is successfully deleted if the password is correct}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Update User Account'\\ 2. Click 'Delete user'\\ 3. Enter the correct password\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The account is not deleted\\ An error window must appear notifying the user that the password was invalid\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{The user account is successfully created}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user must be able to successfully create an account provided that all input information is valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to 'Sign up'\\ 2. Fill in all the input fields with valid data\\ 3. Click 'Sign Up'\\ 4. Moved to the login page: input the username and the passowrd\\ 5. Click 'Login'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Sign up successful, the account is created\\ The user is logged-in to the newly created account\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{3. Add Bank Account to a User Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Add a valid bank account to a user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{A valid bank account should be added to the user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to the main screen\\ 2. Input an account ID in the 'Enter Account ID' field\\ 3. Click the 'Add' button\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A row should be added in the table of account\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Add a same account to a user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{An account should not be added to the same user twice}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to the main screen\\ 2. Input an account ID in the 'Enter Account ID' field\\ 3. Click the 'Add' button\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Failure, the account is not added\\ An error window displays all the errors\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Add an account used by another user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{An account used by another user should not be added to another user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to the main screen\\ 2. Input an account ID in the 'Enter Account ID' field\\ 3. Click the 'Add' button\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Failure, the account is not added\\ An error window displays all the errors\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{4. Remove Bank Account from a User Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Remove a bank account from a user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{An existing bank account should be removed}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to the main screen\\ 2. Select a line on the accounts table\\ 3. Click the 'Remove Selected' button\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The row should be removed in the table of account\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Remove no bank account from a user}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{If no account is selected, no accounts should be removed}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Go to the main screen\\ 2. Click the 'Remove Selected' button\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Nothing happens because no account was selected\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{5. View Transactions for Specific Bank Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Selection of a bank account is mandatory}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user select an exsiting bank account.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click a bank account from bank account list\\ 2. Click 'View All Transactions'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Transaction list is displayed for selected bank account\\ Empty list is shown if there is no transactions\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{6. View All Transactions from all Bank Accounts}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Display all transactions}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Empty selection in bank account list return all transactions of exsiting bank accounts}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}All transactions in bank account list are shown.\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{7. Update User Account}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{First name is mendatory}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{First name is required in user profile}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'First anme'\\ 2. Input first name\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New first name is saved if it is not empty\\ Error message is shown if the text field is empty.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Last anme is mendatory}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Last name is required in user profile}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Last anme'\\ 2. Input last name\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New last name is saved if it is not empty\\ Error message is shown if the text field is empty.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{First name is valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Validating first name}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'First anme'\\ 2. Input first name\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New first name is saved if it is not empty\\ Error message is shown if the input does not pass the validation.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Last name is valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Validating last name}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Last anme'\\ 2. Input last name\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New last name is saved if it is not empty\\ Error message is shown if the input does not pass the validation.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Password input validation}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Validate password input}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Update Password' or 'Confirm New Password'\\ 2. Input new password\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New password is accepted if the password is valid.\\ Error message is shown if the password is not valid.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Two password input matches}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Input of two passwords should match}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Update Password'\\ 2. Input new password\\ 3. Click the textfield 'Confirm New Password'\\ 4. Input new password the second time\\ 5. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}New password is saved if two input matches\\ Error message is shown if two input does not match.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Email address is valid}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{Validating email address}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Email'\\ 2. Input email address\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A valid email address is saved\\ Error message is shown if the input email address is not valid.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Phone number can be saved}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{A phone number can be saved to profile}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Phone Number'\\ 2. Input a phone number\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The phone number can be saved to user profile\\ Error message is shown if saving failed.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Current address can be saved}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{An address can be saved to profile}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the textfield 'Current address'\\ 2. Input an address\\ 3. Click the button 'Save changes'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The address can be saved to user profile\\ Error message is shown if saving failed.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Delete user account}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{User profile is deleted}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'Delete User'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}User profile is removed from the database\\ Error message is shown if deletion fails\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{8. Sort transactions by any attribute}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Sort by Date}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to see the transactions sorted by date.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. click on the attribute 'Date' one time to sort in ascending order or two times for descending order\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transactions list is sorted in ascending or descending date order\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Sort by Amount}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to see the transactions sorted by amount.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. click on the attribute 'Amount' one time to sort in ascending order or two times for descending order\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transactions list is sorted in ascending or descending amount order\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Sort by Type}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to see the transactions sorted by type.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. click on the attribute 'Type' one time to sort in ascending order or two times for descending order\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transactions list is sorted in types of transactions\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Sort by Category}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to see the transactions sorted by categories.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. click on the attribute 'Category' one time to sort in ascending order or two times for descending order\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transactions list is sorted in categories.\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{9. Categorize transaction}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Categorize from predefined list}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to set the category of the transaction from the predefined categories.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. select the desired transaction to be categorized.\\ 3. press on the category option and choose the appropriate category from the drop down menu.\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transaction's category is set to the one chosen by the user.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Create a new category}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to create a category for the transaction.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. select the desired transaction to be categorized.\\ 3. press on the category option and type in the new category.\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transaction's category is set to the one created by the user.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Category created is too long.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to create a category for the transaction.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. select the desired transaction to be categorized.\\ 3. press on the category option and type in the new category which is longer than allowed.\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The transaction's category is not set because the category entered is too long.\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{10. Filter transactions by date range}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Filter transactions with valid date range}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user wants to see the transactions within a valid date range.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. select a start date which is before at least one transaction.\\ 3. select an end date or keep it blank if the desired date is the current date.\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The displayed transactions are withing the date range selected.\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Select an invalid date range}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The user sets an invalid date range.}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. click on 'View All Transactions' button or double click on a specific bank account from the Account List view \\ 2. select a start date which is after any transaction.\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}The list of transactions is empty.\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{11. Search transaction by existing category}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Filter transactions by category in all transactions view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The All Transactions view should let filter by categories}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Input a category in the 'category' field\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Only the categories starting with what was inputted should be displayed\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Filter transactions by category in detailed account view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The accounts details view should let filter by categories}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Select an account by double clicking on a row\\ 2. Input a category in the 'category' field\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}Only the categories starting with what was inputted should be displayed\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{An empty search should return all transactions}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{When the category field is empty, all transactions should be shown}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Input a category in the 'category' field\\ 3. Clear the category field\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}All transactions from before the filtering should be shown\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{A search with a non-existing category should yield no result}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{If no categories match the category filter, no transactions should be shown}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Input a category that does not exist in the 'category' field\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}No transactions should be shown\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{12. Generate transaction statement by exporting to CSV}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Generate statement from All Transactions view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The All Transactions view should let generate a statement}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Click the button 'Generate Excel'\\ 3. Select the location of the generated file\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A file named 'all-transactions-TIMESTAMP.csv' should be generated in the selected folder\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Generate statement from Account Details view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{The Account Details view should let generate a statement}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Select an account by double clicking on a row\\ 2. Click the button 'Generate Excel'\\ 3. Select the location of the generated file\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A file named 'transactions-TIMESTAMP.csv' should be generated in the selected folder\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Generate empty statement}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{An account with no transactions should still generate a statement}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Click the button 'Generate Excel'\\ 3. Select the location of the generated file\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}A file named 'all-transactions-TIMESTAMP.csv' should be generated in the selected folder and should only have headers\\ \end{tabular}}\\ \hline
\end{longtable}

\subsubsection{13. Send statement by email}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Send statement by email from All Transactions view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{A statement should be sent by email from the All Transactions view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'View All Transactions'\\ 2. Click the button 'Email CSV'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}An email containing the transactions in your inbox\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Send statement by email from Account Details view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{A statement should be sent by email from the Account Details view}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Select an account by double clicking on a row\\ 2. Click the button 'Email CSV'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}An email containing the transactions in your inbox\\ \end{tabular}}\\ \hline
\end{longtable}
\begin{longtable}{|m{4cm}|l|l|}
\hline
\cellcolor[HTML]{C0C0C0}\textbf{Test Case} & \multicolumn{2}{p{13cm}|}{Send statement by email when no email is configured}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Description} & \multicolumn{2}{p{13cm}|}{A statement can't be sent when no email is configured}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Input/Steps} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}1. Click the button 'Update User Account'\\ 2. Remove the email\\ 3. Click the button 'Save Changes'\\ 4. Click the button 'View All Transactions'\\ 5. Click the button 'Email CSV'\\ \end{tabular}}\\ \hline
\cellcolor[HTML]{C0C0C0}\textbf{Output/Results} & \multicolumn{2}{p{13cm}|}{\begin{tabular}[c]{@{}l@{}}An error window notifies the user that his email is not configured\\ \end{tabular}}\\ \hline
\end{longtable}

