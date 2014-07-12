package com.rhl.vo;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Users")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "User")
	private Collection<UserVO> users;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param users
	 */
	public Users(Collection<UserVO> users) {
		super();
		this.users = users;
	}

	/**
	 * @return the users
	 */
	public Collection<UserVO> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Collection<UserVO> users) {
		this.users = users;
	} 
}
