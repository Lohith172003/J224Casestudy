package com.hexaware.controller;

import java.util.Scanner;
import com.hexaware.dao.AdminDao;

public class AdminController implements AdminControllerInterface {

	AdminDao ad = new AdminDao();
	static Scanner sc = new Scanner(System.in);

	@Override
	public void updateAdmin(int aid) {

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

		ad.updateAdmin(aid, newName, newEmail, newPassword);

	}
	
	

}
