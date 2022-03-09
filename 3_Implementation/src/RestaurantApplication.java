/*
 * author : Sikanth Bandi
 * email : srikanthreddy2000b@gmail.com
 */

import java.util.Scanner;

class Operations {
	public static void OwnerOperations(RestaurantOwner owner) {
		Scanner sc = new Scanner(System.in);
		boolean ownerLoggedIn = true;
		while (ownerLoggedIn) {
			System.out.println("\n------------------> OPERATIONS <------------------\n");
			System.out.println("Available Options :");
			System.out.println(" 1. Display Menu ");
			System.out.println(" 2. Add Food Item ");
			System.out.println(" 3. Remove Food Item ");
			System.out.println(" 4. Update Food Item ");
			System.out.println(" 5. Exit ");
			// System.out.print("Select what you want : ");
			byte choice = sc.nextByte();
			String foodItem = "";
			float price = 0F;
			switch (choice) {

			case 1:
				owner.displayMenu();
				break;
			case 2:
				System.out.print("Enter number of food items to add in menu : ");
				int noOfItems = sc.nextInt();
				for (int i = 0; i < noOfItems; i++) {
					System.out.print("Enter food item " + (i + 1)+": ");
					foodItem = sc.next();
					System.out.print("Enter its price : ");
					price = sc.nextFloat();
					if (owner.addItem(foodItem, price))
						System.out.println("\n"+foodItem + " added in menu\n");
					else
						System.out.println(foodItem + " already in menu.");
					owner.addItem(foodItem, price);
				}

				break;
			case 3:
				System.out.print("Enter food item to remove :");
				foodItem = sc.next();
				if (owner.deleteItem(foodItem))
					System.out.println(foodItem + " removed from menu.");
				else
					System.out.println(foodItem + " does not exist in menu.");
				break;
			case 4:
				System.out.print("Enter Food item to update its price : ");
				foodItem = sc.next();
				System.out.print("\nEnter its price : ");
				price = sc.nextFloat();
				if (owner.update(foodItem, price))
					System.out.println("Menu updated\n");
				else
					System.out.println("Failed to update menu ");
				break;
			
			case 5:
				return;
			default:
				System.out.println("Exiting from Owner Module.");
				System.out.println("--------------------------------------------------------------------");
				ownerLoggedIn = false;
			}
		}
		sc.close();
	}

	public static void CustomerOpeartions(Customer customer) {
		Scanner sc = new Scanner(System.in);
		boolean customerLoggedIn = true;
		while (customerLoggedIn) {
			System.out.println("-------------------Please select your choice-------------------");
			System.out.println("1. Display Menu ");
			System.out.println("2. Order Food Item ");
			System.out.println("3. Cancel Food Item ");
			System.out.println("4. Update Food Item Quantity ");
			System.out.println("5. Display Order ");
			System.out.println("6. Pay Bill ");
			System.out.println("7. Exit ");
			String foodItem = "";
			int quantity = 0;
			System.out.print("Select what you want : ");
			byte choice = sc.nextByte();

			switch (choice) {

			// case 1 is for displaying the menu items in the restaurant
			case 1:
				System.out.println("---------------MENU---------------");
				customer.displayMenu();
				break;

			// case 2 is to add food items into the menu
			case 2:
				System.out.print("Enter food item to add : ");
				foodItem = sc.next();
				System.out.print("Enter " + foodItem + "\'s quantity : ");
				quantity = sc.nextInt();
				if (customer.orderFood(foodItem, quantity))
					System.out.println(quantity + " " + foodItem + " added in order.");
				else
					System.out.println(foodItem + " does not exist in menu.");
				break;

			// case 3 is to remove food items from the menu
			case 3:
				System.out.print("Enter food item to remove : ");
				foodItem = sc.next();
				if (customer.removeFood(foodItem))
					System.out.println(foodItem + " removed from order");
				else
					System.out.println("Failed to remove " + foodItem);
				break;

			// case 4 is to update the food items price
			case 4:
				System.out.print("Enter food to update quantity : ");
				foodItem = sc.next();
				if (customer.update(foodItem, quantity))
					System.out.println(quantity + " " + foodItem + " added in order.");
				else
					System.out.println("Failed to update quantity for " + foodItem);
				break;

			// case 5 is to see your orders
			case 5:
				System.out.println("Your order is :");
				customer.displayOrder();
				break;

			// case 6 is to display bill for the ordered food items
			case 6:
				System.out.println("Your total amount for following order ");
				System.out.println("-------------------------------------------------------");
				System.out.println("FOOD \t\t QUANTITY");
				System.out.println("-------------------------------------------------------");
				customer.displayOrder();
				System.out.println("Total Amount(including GST) = " + customer.totalBill());
				System.out.println("-------------------------------------------------------\n");
				break;

			// case 7 is to exit from the page
			case 7:
				return;

			default:
				System.out.println("Exiting from Customer Module.");
				System.out.println("-------------------------------------------------------");
				customerLoggedIn = false;
			}
		}
		sc.close();
	}
}

public class RestaurantApplication {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestaurantOwner owner = new RestaurantOwner();
		Customer customer = new Customer();
		Scanner sc = new Scanner(System.in);
		boolean wantToContinue = true;
		while (wantToContinue) {
			System.out.println("\n----------------> WELCOME TO MINUTES-RESTAURANT <----------------\n");
			System.out.println("Login As  Owner or Customer");
			System.out.println("1. Owner\n2. Customer\n3. Exit");
			System.out.print("Select Login Method : ");
			
			byte loginAs = sc.nextByte();

			switch (loginAs) {
				
			case 1:
				System.out.println("----------------------- AUTHENTICATION -----------------------");
				System.out.print("Enter username : ");
				String username = sc.next();
				System.out.print("Enter password : ");
				String password = sc.next();
				if (owner.authorize(username, password)) {
				// if (uname == "srikanth" && password == "srikanth") {
					Operations.OwnerOperations(owner);
				} else {
					System.out.println("Invalid credentials.");
				}
				break;

			case 2:
				Operations.CustomerOpeartions(customer);
				break;

			case 3:
				System.out.println("\n____________________>-- THANK YOU VISIT AGAIN --<____________________");
				wantToContinue = false;
				break;

			default:
				System.out.println("\nInvalid Choice!! " + " Exiting from the page ");
				wantToContinue = false;
			}

		}
		sc.close();
	}
}
