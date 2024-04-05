package techarmy;

import java.util.*;

import java.util.ArrayList;

//import java.util.HashMap;

import java.util.List;

import java.util.Map;

class ConferenceRoom {

	private String name;

	private int capacity;

	private int location;

	private boolean isBooked;

	private User bookedBy;

	private String meetingTitle;

	public ConferenceRoom(String name, int capacity, int location) {

		this.name = name;

		this.capacity = capacity;

		this.location = location;

		this.isBooked = false;

	}

	public boolean bookRoom(User user, String meetingTitle) {

		if (!isBooked) {

			isBooked = true;

			bookedBy = user;

			this.meetingTitle = meetingTitle;

			return true;

		} else {

			return false;

		}

	}

	public void cancelBooking() {

		isBooked = false;

		bookedBy = null;

		meetingTitle = null;

	}

	public String getName() {

		return name;

	}

	public int getCapacity() {

		return capacity;

	}

	public int getLocation() {

		return location;

	}

	public boolean isBooked() {

		return isBooked;

	}

	public User getBookedBy() {

		return bookedBy;

	}

	public String getMeetingTitle() {

		return meetingTitle;

	}

}

class User {

	private String name;

	private List<ConferenceRoom> bookedRooms;

	public User(String name) {

		this.name = name;

		this.bookedRooms = new ArrayList<>();

	}

	public String getName() {

		return name;

	}

	public void bookRoom(Admin admin, ConferenceRoom room2, String meetingTitle) {

		// ConferenceRoom room = admin.getConferenceRooms().get(room2);

		if (room2 != null) {

			if (room2.bookRoom(this, meetingTitle)) {

				bookedRooms.add(room2);

				System.out.println(name + " has booked " + room2.getName() + " for " + meetingTitle);

			} else {

				System.out.println("Sorry, " + room2.getName() + " is already booked.");

			}

		} else {

			System.out.println("Room not found.");

		}

	}

	public void cancelBooking(ConferenceRoom room) {

		room.cancelBooking();

		bookedRooms.remove(room);

		System.out.println(name + " has canceled the booking for " + room.getName());

	}

	public List<ConferenceRoom> getBookedRooms() {

		return bookedRooms;

	}

}

class Admin {
	Scanner scanner=new Scanner(System.in);

	public Map<String, ConferenceRoom> conferenceRooms;

	public Admin() {

		this.conferenceRooms = new HashMap<>();

	}

	public void addConferenceRoom(String name, int capacity, int location) {

		ConferenceRoom room = new ConferenceRoom(name, capacity, location);

		conferenceRooms.put(name, room);

		System.out.println("Conference room " + name + " added successfully.");

	}

	public void listConferenceRooms() {

		System.out.println("List of Conference Rooms:");

		for (ConferenceRoom room : conferenceRooms.values()) {

			System.out.println("Name: " + room.getName() +

					", Capacity: " + room.getCapacity() +

					", Location(floor number): " + room.getLocation() +

					", Status: " + (room.isBooked() ? "Busy" : "Free") +

					", Booked by: " + (room.isBooked() ? room.getBookedBy().getName() : "None") +

					", Meeting Title: " + (room.isBooked() ? room.getMeetingTitle() : "None"));

		}

	}

	public void see_availability() {

		for (ConferenceRoom room : conferenceRooms.values()) {

			System.out.print("RoomName: " + room.getName());

			System.out.println(" Status: " + (room.isBooked() ? "Busy" : "Free"));

		}

	}

	public void see_availability_by_roomname() {

		System.out.println("Enter room name:");

	}

	public void get_user_details() {

		int flag = 0;

		for (ConferenceRoom room : conferenceRooms.values()) {

			if (room.isBooked()) {

				System.out.println("Booked by: " + room.getBookedBy().getName());

				flag++;

			}

		}

		if (flag == 0) {

			System.out.println("The status of all conference rooms is Free.");

		}

	}
	
	public void available() {

		System.out.println("Enter the capacity of the room");

		int c=scanner.nextInt();



		int flag = 0;



		for (ConferenceRoom room1 : conferenceRooms.values()) {



			if (!room1.isBooked() && room1.getCapacity()>=c) {

				System.out.println("RoomName: " + room1.getName());



				//System.out.println("Booked by: " + room1.getBookedBy().getName());



				flag++;



			}



		}



		if (flag == 0) {



			System.out.println("The rooms are not available according to the capacity enter by you");



		}

	}



	public Map<String, ConferenceRoom> getConferenceRooms() {

		return conferenceRooms;

	}

}

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Admin admin = new Admin();

		User user = null;

		admin.addConferenceRoom("2-2", 5, 2);

		admin.addConferenceRoom("2-1", 15, 2);

		admin.addConferenceRoom("10-3", 9, 10);

//		System.out.println("Are you an admin or a user? (admin/user)");
//
//		String userType = scanner.nextLine();
		int ua=0;
		do {
			System.out.println("1.admin\n2.user");
			 ua=scanner.nextInt();
			 switch(ua) {
			 case 1:
			 {
				 boolean exit = false;

					while (!exit) {

						System.out.println("Select an option:");

						System.out.println("1. Add conference room");

						System.out.println("2. List conference rooms");

						System.out.println("3. See the avaibility status");

						System.out.println("4. See which team/User has booked the room");

						System.out.println("5. Exit");

						int choice = scanner.nextInt();

						scanner.nextLine(); // Consume newline character

						switch (choice) {

						case 1:

							System.out.println("Enter floor number:");

							int floorNumber = scanner.nextInt();

							scanner.nextLine(); // Consume newline character

							System.out.println("Enter room number:");

							int roomNumber = scanner.nextInt();

							scanner.nextLine(); // Consume newline character

							String roomName = floorNumber + "-" + roomNumber;

							if (admin.getConferenceRooms().containsKey(roomName)) {

								System.out.println("Invalid room name. Room already exists. Please enter another room name.");

							} else {

								System.out.println("Enter room capacity:");

								int capacity = scanner.nextInt();

								scanner.nextLine(); // Consume newline character

								System.out.println("Enter room location:");

								String location = scanner.nextLine();

								admin.addConferenceRoom(roomName, capacity, floorNumber);

							}

							break;

						case 2:

							admin.listConferenceRooms();

							break;

						case 3:

							admin.see_availability();

							break;

						case 4:

							admin.get_user_details();

							break;

						case 5:

							exit = true;

							break;

						default:

							System.out.println("Invalid choice. Please enter a valid option.");

						}

					}
				 
			 
			 break;
			 }
			 case 2:
				 
				 boolean exit = false;

					while (!exit) {

						System.out.println("Select an option:");

						System.out.println("1. Book a room");

						System.out.println("2. Cancel booking");

						System.out.println("3. See the number of available rooms");

						System.out.println("4. Exit");

						int choice = scanner.nextInt();

						scanner.nextLine(); // Consume newline character

						switch (choice) {

						case 1:

							if (user == null) {

								System.out.println("Enter your name:");

								String userName = scanner.nextLine();

								user = new User(userName);

							}

							System.out.println("Enter room name to book:");

							String roomToBook = scanner.nextLine();

							ConferenceRoom room = admin.getConferenceRooms().get(roomToBook);

							if (room != null) {

								System.out.println("Enter meeting title:");

								String meetingTitle = scanner.nextLine();

								user.bookRoom(admin, room, meetingTitle);

							} else {

								System.out.println("Room not found.");

							}

							break;

						case 2:

							if (user != null && !user.getBookedRooms().isEmpty()) {

								ConferenceRoom bookedRoom = user.getBookedRooms().get(0);

								user.cancelBooking(bookedRoom);

							} else {

								System.out.println("You have no bookings to cancel.");

							}

							break;

						case 3:

							admin.available();
								break;
						case 4:

							exit = true;

							break;

						default:

							System.out.println("Invalid choice. Please enter a valid option.");

						}

					}
					break;
					default:
						System.out.println("invalid option");
			 }
				 
		}while(ua<3);

		

		scanner.close();

	}

}