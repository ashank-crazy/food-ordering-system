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
	- Daily Sales Report is generated based on the current date, with each order date corresponding to the date the order was placed.
	- Normal customer can become VIP customer by choosing upgradetoVIP option from customer interface.
	- Customers can leave feedback for any food item present in the menu.


Sources :

- Date and Time
https://www.javatpoint.com/java-date

- Custom Comparator in Priority Queue
https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
