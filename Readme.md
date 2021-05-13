# Room occupation service

# The challenge
Build a room occupancy optimization tool for one of our hotel clients! Our customer
has a certain number of free rooms each night, as well as potential guests that would
like to book a room for that night.
Our hotel clients have two different categories of rooms: Premium and Economy. Our
hotels want their customers to be satisfied: they will not book a customer willing to
pay EUR 100 or more for the night into an Economy room. But they will book lower
paying customers into Premium rooms if these rooms are empty and all Economy
rooms are occupied with low paying customers. The highest paying customers below
EUR 100 will get preference for the “upgrade”. Customers always only have one
specific price they are willing to pay for the night.
Please build a small API that provides an interface for hotels to enter the numbers of
Premium and Economy rooms that are available for the night and then tells them
immediately how many rooms of each category will be occupied and how much
money they will make in total. Potential guests are represented by an array of
numbers that is their willingness to pay for the night.
Use the following raw JSON file/structure as mock data for potential guests in your
tests: [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
Requirements for a valid solution
● A working algorithm implemented in at least Java 11.
● Tracking progress through Git commits.
● Minimal readme explaining how to run the project and tests.
● Tests/TDD (at least use the ones specified above).
● Clean code structure and formatting.
● Thoughtful naming of variables and functions.
● Go for high code quality rather than completeness, if you feel time pressure.
Nice to haves
● A restful JSON API implemented with Spring Boot or a comparable
framework.
● Anything else you would like to add.

Tests
Test 1
● (input) Free Premium rooms: 3
● (input) Free Economy rooms: 3
● (output) Usage Premium: 3 (EUR 738)
● (output) Usage Economy: 3 (EUR 167.99)
Test 2
● (input) Free Premium rooms: 7
● (input) Free Economy rooms: 5
● (output) Usage Premium: 6 (EUR 1054)
● (output) Usage Economy: 4 (EUR 189.99)
Test 3
● (input) Free Premium rooms: 2
● (input) Free Economy rooms: 7
● (output) Usage Premium: 2 (EUR 583)
● (output) Usage Economy: 4 (EUR 189.99)
Test 4
● (input) Free Premium rooms: 7
● (input) Free Economy rooms: 1
● (output) Usage Premium: 7 (EUR 1153)
● (output) Usage Economy: 1 (EUR 45.99)

# The tech stack
* Java 11
* Spring boot
* Rest api + JSON
  
# Notes
* Ive added field to specify booking date, so user from hotel can precise during issuing request query, however its missing timezone
* I assumed that all bookings are in EUR and theres 1 to 1 relationship between guest and room
* Some tests are missing: data access layer units, controller/api layer units + contract, service layer.
* Data access layer is stubbed in-memory for simplicity
* Todo: mapping errors to http responses
* Other todos mark in the code by comments //TODO

TODO:
* impl
* logging
* error handling
* test
* api doc
* document