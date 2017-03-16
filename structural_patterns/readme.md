## Module 3. Architecture: Structural patterns

**Task 1 (Adapter)**

A software developer, Max, has worked on an e-commerce website.
The website allows users to shop and pay online. The site is integrated with a 3rd party payment gateway, through which users can pay their bills using their credit card.
Everything was going well, until his manager called him for a change in the project. The manager told him that they are planning to change the payment gateway vendor, and he has to implement that in the code.
The problem that arises here is that the site is attached to the XPay payment gateway which takes an XPay type of object. The new vendor, YPay, only allows the YPay type of objects to allow the process.
Max doesn’t want to change the whole set of 100 of classes which have reference to an object of type XPay. This also raises the risk on the project, which is already running on the production. Neither he can change the 3rd party tool of the payment gateway. The problem has occurred due to the incompatible interfaces between the two different parts of the code.
In order to get the process work, Max needs to find a way to make the code compatible with the vendor’s provided API.

**Task 2 (Decorator)**

Starbuzz cafe have problems with software. Their young developer creates so mush classes for each type of coffee. 

For example:
- EspressoMocha
- EspressoSoy
- EspressoSoySoyWhip
- DecafMochaMochaSoyWhip ,etc

 Last week young developer bought a book about structural patterns and found, that "Decorator" could help with redundancy of the system.
