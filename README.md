[README.txt](https://github.com/user-attachments/files/35068972/README.txt)
Pet Shop System - continuation of Assignment 1

Requirements: JDK 11 or newer.

Compile:
    mkdir out
    javac -d out src/*.java

Run:
    java -cp out PetShopMain

The program runs a fixed demonstration first. At the end it asks
"Open the shop menu for Aiman? (y/n)". Type n to finish, or y for the
interactive menu from Assignment 1.

Files (in src/):
    Careable.java    common interface (polymorphism)
    Pet.java         base class (from Assignment 1, extended)
    Dog.java         derived class 1
    Cat.java         derived class 2
    Fish.java        derived class 3
    Customer.java    additional class 1 (from Assignment 1)
    Sale.java        additional class 2
    PetShop.java     additional class 3 (manages pets, customers and sales)
    PetShopMain.java main class
