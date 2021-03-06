##Assignment: Biblioteca Release 1

#Business Case

The Bangalore Public Library has approached us to design and implement a Library Management System for them. Based on their requirements, we have decided to develop a new system named Biblioteca. Since the library has a large list of requirements, we will be making multiple releases of Biblioteca, with each release incrementally adding more functionality. 

In order to easily add more functionality in the future as well as maintain a high level of quality, Biblioteca will be built using a test driven approach.

The requirements for the first release of Biblioteca are given below.

##User Stories

+ Welcome Message  - As a customer, I would like to see a welcome message when I start the application, so that I feel welcome and know that Biblioteca is available.

+ List Books - As a customer, after the welcome message appears I would like to see a list of all library books, so that I can browse for books I might want to check-out. 

+ Assume that there is a pre-existing list of books. You don't need to support adding or removing books from the library.

+ Book Details - As a customer, I'd like the list of all books to include each books Author and Year Published, so that I can be confident that I have found the book I am looking for. The book listing should have columns for this information.

+ Main Menu  - As a customer, instead of automatically seeing the book list, I would like to see a list of options and be able to choose one. For now, the only option should be 'List Books'. All future options should be added to this mainMenu also.

+ Invalid Menu Option - As a customer, I would like to be notified when I choose an invalid option with the message “Select a valid option!”, so that I can know when I need to re-enter my choice.

+ Quit - As a customer, I would like to continue choosing options until I choose to 'Quit'.

+ Checkout Book - As a librarian, I would like customers to be able to check-out a book. Checked out books should not appear in the list of all library books.

+ Successful Checkout - As a customer, I would like to know that a book has been checked out by seeing the message “Thank you! Enjoy the book”.

+ Unsuccessful Checkout - As a customer, I would like to be notified if the book I tried to check-out is not available by seeing the message, “That book is not available.”, so that I know that I need to select a different book or fix my spelling error.

+ Return Book - As a librarian, I would like customers to be able to return a book, so that other customers can check that book out. Returned books should appear in the list of library books.

+ Successful Return - As a customer, I would like to be notified if the book I am returning belongs to this library by seeing the message, “Thank you for returning the book.”, so that I know I returned the book to the right library.

+ Unsuccessful Return - As a customer, I would like to be notified if the book I am returning has not been added to this library by seeing the message, “That is not a valid book to return.”, so that I can return it to the correct library or fix my spelling error.

##Additional User Stories for Biblioteca two.

+ List Movies - As a customer, I would like to see a list of available movies, so that I can browse for a movie that I might check-out. Movies have a name, year, director and movie rating (from 1-10 or unrated).

+ Check-out Movie - As a customer, I would like to check out a movie from the library, so I can enjoy it at home.

+ User Accounts - Login - As a librarian, I want to know who has checked out a book, so that I can hold them accountable for returning it. Users must now login using their library number (which is in the format xxx-xxxx) and a password in order to check-out and return books. User credentials are predefined, so registering new users is not part of this story.

+ User Accounts - User information - As a customer, I want to be able to see my user information (name, email address and phone number), so that I know that the library can contact me. This option should only be available when the customer is logged in and should only display that customer’s information.

##Technical Requirements

+ All the code must be developed using TDD.
+ Biblioteca needs to be a console application. Use your own imagination for any UI elements
+ We do not need a database for this release.

#Running Instructions

+ Run the “main” method from BibliotecaApp
+ Program will print welcome message
+ Program will print a list of mainMenu options
+ Enter text of option you would like to use, note this currently does take notice of white spaces. You can enter more than one option and it will run in priority order. e.g. “checkout booklist” will print the booklist to the screen and then ask what book you want to checkout whereas if you type “checkout return” or “return checkout” it will always ask what book you want to checkout before it asks what book you wish to return. 
+ If all books have been checked out the booklist will be blank.
+ enter “Book List” or “book list” to see the list of available books
+ to check out a book enter text containing the key word “Checkout”, you will be asked for the title of the book you wish to check out.
+ to return a book enter the keyword “Return”, you will be asked for the title of the book you wish to return
+ enter “Quit” at any time to quit.
+ If an invalid selection is made the program will ask you to enter a valid selection. 

###Improvements

+ Extract some classes from the larger classes, structure/design to be decided.
+ adjust text in mainMenu display options and book title to make it more intuitive
+ Read file for list of books and mainMenu items rather than adding from an array.
+ Add a message to be displayed when all books have been checked out of the library.
