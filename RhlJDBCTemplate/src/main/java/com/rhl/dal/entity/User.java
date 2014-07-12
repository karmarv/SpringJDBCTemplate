/**
 * 
 */
package com.rhl.dal.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Rahul Vishwakarma
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String organization;
	private String phone;
	private int enabled;
	private Timestamp createdTime;
	
	public User() {
		super();
	}

	
	/**
	 * @param userId
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param organization
	 * @param phone
	 * @param enabled
	 * @param createdTime
	 */
	public User(int userId, String username, String firstName, String lastName,
			String email, String password, String organization, String phone,
			int enabled, Timestamp createdTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.organization = organization;
		this.phone = phone;
		this.enabled = enabled;
		this.createdTime = createdTime;
	}


	public User(String username, String firstName, String lastName,
			String email, String password, String organization, String phone,
			int enabled) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.organization = organization;
		this.phone = phone;
		this.enabled = enabled;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}


	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	/**
	 * @return the createdTime
	 */
	public Timestamp getCreatedTime() {
		return createdTime;
	}


	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password
				+ ", organization=" + organization + ", phone=" + phone
				+ ", enabled=" + enabled + ", createdTime=" + createdTime + "]";
	}


}
