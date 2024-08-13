package com.holidayBookingSystem.resource.PersistanceLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.holidayBookingSystem.resource.ApiLayer.BookingAPI;
import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.CreditCardPayment;
import com.holidayBookingSystem.resource.DAO.Customer;
import com.holidayBookingSystem.resource.DAO.Hotel;
import com.holidayBookingSystem.resource.DAO.PaymentMethod;
import com.holidayBookingSystem.resource.DAO.Room;

public class DAO {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class.getName());
	
	private static int HotelID = 301;
	private static List<Hotel> hotels = new ArrayList<>();
	private static int RoomID = 201;
	private static List<Room> rooms = new ArrayList<>();
	private static int CustomerID = 1;
	private static List<Customer> customers = new ArrayList<>();
	private static int BookingID = 101;
	private static List<Booking> bookings = new ArrayList<>();
	private static int PaymentID = 401;
	private static List<PaymentMethod> payments = new ArrayList<>();
	
	public DAO() {
		LOGGER.info("Populating Dummy Data in db");
		// Populating Dummy Data in DB
		createHotelnRooms();
		createCustomernBookings();
		LOGGER.info("Dummy data populated");
	}
	
	private static void createHotelnRooms() {
		LOGGER.info("Creating Hotels and Rooms data");
		Hotel hotel = new Hotel();
		Room room1 = new Room();
		Room room2 = new Room();
		
		hotel.setId(HotelID++);
		hotel.setName("Grand Hotel");
		hotel.setAddress("123 Main St, Anytown");
		
		room1.setId(RoomID++);
		room1.setHotel(hotel.getId());
		room1.setRoomNumber("101");
		room1.setType("Deluxe");
		
		room2.setId(RoomID++);
		room2.setHotel(hotel.getId());
		room2.setRoomNumber("102");
		room2.setType("Standard");
		
		
		hotel.addRoom(room1);
		hotel.addRoom(room2);
		
		
		hotels.add(hotel);
		rooms.add(room1);
		rooms.add(room2);
	}
	
	private static void createCustomernBookings() {
		LOGGER.info("Creating customers , bookings, and payments data");
		Customer customer = new Customer();
		customer.setId(CustomerID++);
		customer.setName("John Doe");
		customer.setEmail("john.doe@example.com");
		
		Booking booking = new Booking();
		booking.setId(BookingID++);
		booking.setStartDate(new Date(System.currentTimeMillis()));
		booking.setEndDate(new Date(System.currentTimeMillis()));
		booking.setRoom(rooms.get(0));
		booking.setCustomerID(customer.getId());
		
		rooms.get(0).addBookings(booking.getId());
		
		PaymentMethod payment = new CreditCardPayment();
		payment.setId(PaymentID++);
		payment.setPaymentDate(new Date(System.currentTimeMillis()));
		payment.setAmount(1500);
		payment.setPaymentMethod();
		payment.setBookingID(booking.getId());
		payment.setCustomerID(customer.getId());
		
		
		customer.addPayment(payment.getId());
		
		booking.setPayment(payment);
		
		customer.addBooking(booking);
		
		
		customers.add(customer);
		bookings.add(booking);
		payments.add(payment);
		
		
	}
	
    public List<Hotel> getAllHotels() {
        return hotels;
    }
    
    public List<Customer> getAllCustomers(){
    	return customers;
    }
    
    public List<PaymentMethod> getAllPayments(){
    	return payments;
    }
    
    public List<Booking> getAllBookings() {
    	return bookings;
    }
    
    public List<Room> getAllRooms(){
    	return rooms;
    }
    
    public Hotel getHotelByID(int id) {
    	
//    	return hotels.stream()
//                .filter(hotel -> hotel.getId() == id)
//                .findFirst()
//                .orElse(null); 
    	
    	for (Hotel hotel : hotels) {
    		if (hotel.getId() == id) {
    			return hotel;
    		}
    	}
    	return null;
    }
    
    public Customer getCustomerByID(int id) {
    	for (Customer customer : customers) {
    		if (customer.getId() == id)
    			return customer;
    	}
    	return null;
    }
    public Booking getBookinByID(int id) {
    	for (Booking booking : bookings) {
    		if (booking.getId() == id)
    			return booking;
    	}
    	return null;
    }
    
    public Room getRoomByID(int id) {
    	for (Room room : rooms) {
    		if(room.getId() == id)
    			return room;
    	}
    	return null;
    }
    
	public PaymentMethod getPaymentByID (int id) {
		for (PaymentMethod payment : payments) {
			if (payment.getId() == id)
				return payment;
		}
		return null;
	}
    
    public boolean deletePaymentByID(int id) {
    	for (PaymentMethod payment : payments) {
    		if (payment.getId() == id) {
    			boolean result = deletePaymentFromBookingsByID(payment.getBookingID())
                        && deletePaymentFromCustomerByID(payment.getCustomerID(), payment.getId())
                        && payments.remove(payment);
        return result;
    		}
    	}
    	return false;
    }
    private boolean deletePaymentFromBookingsByID(int id) {
    	Booking booking = getBookinByID(id);
        if (booking != null) {
            booking.setPayment(null);
            return true;
        }
        return false;
    }
    
    private boolean deletePaymentFromCustomerByID(int id, Integer paymentID) {
    	for (Customer customer : customers) {
    		if (customer.getId() == id) {
    			boolean result = customer.removePayment(paymentID);
    			return result;
    		}
    	}
    	return false;
    }
    
    
    public boolean deleteBookingByID(int id) {
    	for (Booking booking : bookings) {
    		if (booking.getId() == id ) {
    			return deleteBookingFromCustomerByID(booking.getCustomerID(), booking)
    					&& deleteBookingFromRoomByID(booking.getRoom().getId(), booking.getId())
    					&& bookings.remove(booking);
    		}
    	}
    	return false;
    }
    
    private boolean deleteBookingFromCustomerByID(int id, Booking booking) {
    	for (Customer customer : customers) {
    		if (customer.getId() == id) {
    			boolean result = customer.removeBooking(booking);
    			return result;
    		}
    	}
    	return false;
    }
    private boolean deleteBookingFromRoomByID(int id, Integer bookingID) {
    	for (Room room : rooms) {
    		if (room.getId() == id) {
    			return room.removeBooking(bookingID);
    		}
    	}
    	return false;
    }
    
    
    public boolean deleteRoomByID(int id) {
    	for (Room room : rooms) {
    		if (room.getId() == id) {
    			for (Integer booking : room.getBookings()) {
    				deleteRoomFromBookingByID(booking);
    			}
    			return deleteRoomFromHotelByID(room , room.getHotel())
    					&& rooms.remove(room);
    		}
    	}
    	return false;
    }
    
    private boolean deleteRoomFromHotelByID(Room room, int HotelID) {
    	Hotel hotel = getHotelByID(HotelID);
    	if (hotel != null)
    		return hotel.getRooms().remove(room);
    	
    	return false;
    }
    
    private boolean deleteRoomFromBookingByID(int bookingID) {
    	Booking booking = getBookinByID(bookingID);
    	if (booking != null)
    		booking.setRoom(null);
    	return true;
    }
    
    public boolean deleteHotelByID(int id) {
    	Hotel hotel = getHotelByID(id);
    	if (hotel != null) {
    		List<Room> rooms = new ArrayList<>();
    		for (Room room : hotel.getRooms()) {
    			rooms.add(room);
    		}
    		
    		for (Room room : rooms) {
    			deleteRoomByID(room.getId());
    		}
    		rooms = null;
    		return hotels.remove(hotel);
    	}
    	return false;
    }
    
    public boolean deleteCustomerByID(int id) {
    	Customer customer = getCustomerByID(id);
    	if (customer != null) {
    		return customers.remove(customer);
    	}
    	return false;
    }
    
    
    public boolean createHotel(Hotel hotel) {
    	hotel.setId(HotelID++);
    	for (Room r : hotel.getRooms()) {
    		r.setId(RoomID++);
    		r.setHotel(hotel.getId());
    		rooms.add(r); // adding room in rooms db
    	}
    	//adding hotel in db
    	hotels.add(hotel);
    	return true;
    }
    
    public boolean updateHotel(Hotel hotel, int id) {
    	System.out.println(hotel);
    	Hotel oldHotel = getHotelByID(id);
    	if (oldHotel != null) {
    		if (hotel.getName() != null)
    			oldHotel.setName(hotel.getName());
    		if (hotel.getAddress() != null)
    			oldHotel.setAddress(hotel.getAddress());
    		
    		return true;
    	}
    	return false;
    }
    
    public boolean createRoom(Room room) {
    	Hotel hotel = getHotelByID(room.getHotel());
    	if (hotel != null) {
    		room.setId(RoomID++);
    		hotel.addRoom(room);
    		rooms.add(room);
    		return true;
    	}
    	return false;
    }
    
    public boolean updateRoom(Room room, int id) {
    	Room oldRoom = getRoomByID(id);
    	if (room != null) {
    		if(room.getRoomNumber() != null)
    			oldRoom.setRoomNumber(room.getRoomNumber());
    		if (room.getType() != null)
    			oldRoom.setType(room.getType());
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean createCustomer(Customer customer) {
    	customer.setId(CustomerID++);
    	customers.add(customer);
    	return true;
    }
    public boolean updateCustomer(Customer customer, int id) {
    	Customer oldcustomer = getCustomerByID(id);
    	if (customer.getName() != null)
    		oldcustomer.setName(customer.getName());
    	if(customer.getEmail() != null)
    		oldcustomer.setEmail(customer.getEmail());
    	return true;
    }
    
    public boolean createPayment(PaymentMethod payment) {
    	Booking booking = getBookinByID(payment.getBookingID());
    	if (booking.getPayment() != null)
    		return false;
    	
    	payment.setId(PaymentID++);
    	Customer customer = getCustomerByID(booking.getCustomerID());
    	
    	booking.setPayment(payment);
    	payment.setCustomerID(customer.getId());
    	customer.addPayment(payment.getId());
    	
    	payments.add(payment);
   	
    	return true;
    	
    }
    
    public boolean updatePayment(PaymentMethod payment, int paymentID) {
    	PaymentMethod oldpayment = getPaymentByID(paymentID);
    	// for the paymenst, we can only update the Date of transaction
    	if (payment.getPaymentDate() != null)
    		oldpayment.setPaymentDate(new Date(System.currentTimeMillis()));
    	
    	return true;
    }
    
    public boolean createBooking(Booking booking) {
    	booking.setId(BookingID++);
    	
    	Customer customer = getCustomerByID(booking.getCustomerID());    	
    	customer.addBooking(booking);
    	
    	Room room = getRoomByID(booking.getRoom().getId());
    	room.addBookings(booking.getId());

    	booking.setRoom(room);

    	bookings.add(booking);
    	return true;
    }
    
    public boolean updateBooking(Booking booking, int id) {
    	Booking oldbooking = getBookinByID(id);
    	
    	if (booking.getStartDate() != null)
    		oldbooking.setStartDate(new Date(System.currentTimeMillis()));
    	
    	if (booking.getEndDate() != null)
    		oldbooking.setEndDate(new Date(System.currentTimeMillis()));
    	
    	return true;
    	
    }


}