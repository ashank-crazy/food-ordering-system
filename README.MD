Justifications for using collections :

1) Map 
	- To store the items added to the cart along with their quantities.
	- I used it because it allows efficient retrieval, addition, and removal of items based on the key.

2) List 
	- To maintain a history of all orders placed by the customer.
	- I used it because it maintains the order of insertion, which is important for keeping a chronological history of orders. It also allows easy iteration over the orders.

3) HashMap 
	- To store the total sales for each day.
	- I used it because on an average retrieval, addition and removal of an item is O(1) or constant time operation.

4) PriorityQueue 
	- To store pending order.
	- I used it to ensure VIP orders are processed before normal orders and also to ensure that normal orders are processed in chronological order.

5) ArrayList 
	- I used it because ArrayList allows dynamic resizing and is efficient for cases where elements are primarily accessed by index, which is ideal for managing ordered lists of items.

Assumptions :
1) Daily Sales Report is generated based on the current date, with each order date corresponding to the date the order was placed.
2) Normal customer can become VIP customer by choosing upgradetoVIP option from customer interface.
3) Customers can leave feedback for any food item present in the menu.

Sources :

- Date and Time - https://www.javatpoint.com/java-date

- Custom Comparator in Priority Queue - https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/

- File I/O - https://www.tutorialspoint.com/java/java_files_io.htm , Lecture slides 

- JUnit - https://youtu.be/Zug8zYR0SmA?feature=shared

- Swing :
1) JPanel - https://www.javatpoint.com/java-jpanel
2) JLabel - https://www.javatpoint.com/java-jlabel
3) JTable - https://www.javatpoint.com/java-jtable
4) JScrollPane - https://www.javatpoint.com/java-jscrollpane
5) In general - https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
