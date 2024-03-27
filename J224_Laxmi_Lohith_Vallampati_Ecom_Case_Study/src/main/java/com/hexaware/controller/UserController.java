package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.entity.Customer;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.dao.CustomerDao;

public class UserController implements UserControllerInterface {

	CustomerDao ud = new CustomerDao();
	static Scanner sc = new Scanner(System.in);

	@Override
	public boolean addCustomer() {

		Customer c = new Customer();

		System.out.print("Enter your name : ");
		c.setName(sc.next());

		System.out.print("Enter your email : ");
		c.setEmail(sc.next());

		System.out.print("Enter your Password : ");
		c.setPassword(sc.next());

		return ud.createUser(c);
	}

	@Override
	public boolean deleteCustomer() {

		System.out.print("enter the customer id : ");
		int cid = sc.nextInt();
		try {
			return ud.deleteUser(cid);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public void updateCustomer(int uid) {
		String newName = null;
		String newEmail = null;
		String newPassword = null;

		System.out.print("Do you want to update the name? (y/n): ");
		char choice = sc.next().charAt(0);
		if (Character.toLowerCase(choice) == 'y') {
			System.out.print("Enter the new name: ");
			newName = sc.next();
		}

		System.out.print("Do you want to update the email? (y/n): ");
		choice = sc.next().charAt(0);
		if (Character.toLowerCase(choice) == 'y') {
			System.out.print("Enter the new email: ");
			newEmail = sc.next();
		}

		System.out.print("Do you want to update the password? (y/n): ");
		choice = sc.next().charAt(0);
		if (Character.toLowerCase(choice) == 'y') {
			System.out.print("Enter the new password: ");
			newPassword = sc.next();
		}

		ud.updateUser(uid, newName, newEmail, newPassword);

	}

}
