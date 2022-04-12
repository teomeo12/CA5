# OOP CA4 Time Complexity of the different opeations

Different operations of the program are executed by switch case options
 from the main menu inside App.java. The options are as follows:

* Option 1 - Display all Singers:
Add Singer objects to the arrayList - O(N)
Display the arrayList of singers(or get a record from the arrayList) - O(N)

* Option 2 - Hash Map Retrieve:
The user is promoted to enter the genre of the singer. The genre is a key to retrieve
the associated Singer (value) in the hashMap.
The time complexity for this operation is:  O(1)


* Option 3 - Tree Map Retrieve:
In Tree Map the time complexity for adding or retrieving an object is: O(log(n))


* Option 4 - Priority Queue Sequence Simulation:
In this operation the singer objects are added or removed with time complexity of:  O(log N)

* Option 5 - Priority Queue Two-Field Comparison Demo:
Priority queue with 2 fields being name and age of the singer -  O(log N)
