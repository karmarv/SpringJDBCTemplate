/**
 * 
 */
package com.rhl.dal.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rahul Vishwakarma
 *
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private String userPass;
	private String clientIp;
	private String sessionId;
	private List<String> authorities;
	
	public UserInfo(int id, String userName, String userPass) {
		super();
		this.userId = id;
		this.userName = userName;
		this.userPass = userPass;
	}
	public UserInfo() {
		super();
	}
	
	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}
	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName
				+ ", userPass=[PROTECTED]" + ", clientIp=" + clientIp
				+ ", sessionId=" + sessionId + "]";
	}
	/**
	 * @return the authorities
	 */
	public List<String> getAuthorities() {
		return authorities;
	}
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
}
