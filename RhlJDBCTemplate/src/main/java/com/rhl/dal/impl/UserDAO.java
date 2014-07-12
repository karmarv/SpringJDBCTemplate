/**
 * 
 */
package com.rhl.dal.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rhl.dal.entity.User;

/**
 * @author Rahul Vishwakarma
 *
 */
@Repository
public class UserDAO {

	static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	private JdbcTemplate rhlServiceJdbcTemplate;

	/*
	 * Create a user table in the database upon initialization
	 */
	@PostConstruct
	public void createUserDataTable(){
		try {
			log.info("Creating database table");
			String sql = "CREATE TABLE IF NOT EXISTS `user` ("
					+ " `user_id` INT NOT NULL AUTO_INCREMENT,"
					+ " `user_name` VARCHAR(45) NULL,"
					+ " `first_name` VARCHAR(45) NULL,"
					+ " `last_name` VARCHAR(45) NULL,"
					+ " `email` VARCHAR(45) NULL,"
					+ " `password` VARCHAR(45) NULL,"
					+ " `organization` VARCHAR(45) NULL, "
					+ " `enabled` INT NULL DEFAULT 1 ,"
					+ " `phone` VARCHAR(45) NULL,"
					+ " `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "  PRIMARY KEY (`user_id`))"; 
			String insert = "INSERT INTO `user` (`user_id`, "
					+ "`user_name`, `first_name`, "
					+ "`last_name`, `email`, `password`, "
					+ "`organization`, `enabled`, `phone`) "
					+ " VALUES ('1', 'Vishwakarma', 'Rahul', 'Vishwakarma', "
					+ "'rvk@hemail.com', 'wefbvseuf', 'RVK', '1', '9862541689')";
			log.debug(sql);
			rhlServiceJdbcTemplate.execute(sql);
			rhlServiceJdbcTemplate.execute(insert);
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		
	}
	
	/**
	 * Insert a user data and 
	 * return its primary key
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public int addUserInfo(final User user)
			throws Exception {
		int userId=0;
		final String sql = "INSERT INTO USER(USER_NAME,FIRST_NAME,LAST_NAME,EMAIL,"
				+ "PASSWORD,ORGANIZATION,ENABLED,PHONE) "
				+ " VALUES (?,?,?,?,?,?,?,?)";
		try {
			log.debug(sql);
			log.debug(user+"");
			KeyHolder keyHolder = new GeneratedKeyHolder();
			rhlServiceJdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql,
							new String[] { "USER_ID" });
					ps.setString(1, user.getUsername());
					ps.setString(2, user.getFirstName());
					ps.setString(3, user.getLastName());
					ps.setString(4, user.getEmail());
					ps.setString(5, user.getPassword());
					ps.setString(6, user.getOrganization());
					ps.setInt(7, user.getEnabled());
					ps.setString(8, user.getPhone());
					return ps;
				}
			}, keyHolder);
			userId = (int) keyHolder.getKey().intValue();	
			log.info("Successfully inserted userId : "+userId);
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return userId;
	}
	
	/**
	 * Batch insert users
	 * @param users
	 * @return
	 */
	@Transactional(readOnly = false)
	public int addUserInfoBatch(final List<User> users){
		int[] updatesCount = null;
		try{
			final String sql = "INSERT INTO USER(USER_NAME,FIRST_NAME,LAST_NAME,EMAIL,"
					+ "PASSWORD,ORGANIZATION,ENABLED,PHONE) "
					+ " VALUES (?,?,?,?,?,?,?,?)";
		    log.debug(sql);
		    updatesCount = rhlServiceJdbcTemplate.batchUpdate(sql,  
			    new BatchPreparedStatementSetter() {
            		@Override
            		public void setValues(PreparedStatement ps, int i) throws SQLException {
            			User user = users.get(i);
            			ps.setString(1, user.getUsername());
    					ps.setString(2, user.getFirstName());
    					ps.setString(3, user.getLastName());
    					ps.setString(4, user.getEmail());
    					ps.setString(5, user.getPassword());
    					ps.setString(6, user.getOrganization());
    					ps.setInt(7, user.getEnabled());
    					ps.setString(8, user.getPhone());
            		}
            		@Override
            		public int getBatchSize() {
            		    return users.size();
            		}
			    });
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return updatesCount.length;
	}
	
	
	/**
	 * Get all user
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<User> getUserInfo() throws Exception {
		List<User> rows = null;
		try {
			String sql = "SELECT * FROM data.user";
			RowMapper<User> rowMapper = new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException {
					User u = new User();
					u.setUserId(rs.getInt("USER_ID"));
					u.setUsername(rs.getString("USER_NAME"));
					u.setFirstName(rs.getString("FIRST_NAME"));
					u.setLastName(rs.getString("LAST_NAME"));
					u.setEmail(rs.getString("EMAIL"));
					u.setOrganization(rs.getString("ORGANIZATION"));
					u.setEnabled(rs.getInt("ENABLED"));
					u.setPhone(rs.getString("PHONE"));
					u.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
					return u;
				}
			};
			log.debug(sql);
			rows = rhlServiceJdbcTemplate.query(sql,rowMapper);
			log.info("Queried users "+ rows.size());
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return rows;
	}
	
	/**
	 * get a single user
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public User getUserInfo(int userId) throws Exception {
		User u = null;
		try {
			String sql = "SELECT * FROM data.user WHERE user_id="+userId;
			RowMapper<User> rowMapper = new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException {
					User u = new User();
					u.setUserId(rs.getInt("USER_ID"));
					u.setUsername(rs.getString("USER_NAME"));
					u.setFirstName(rs.getString("FIRST_NAME"));
					u.setLastName(rs.getString("LAST_NAME"));
					u.setEmail(rs.getString("EMAIL"));
					u.setOrganization(rs.getString("ORGANIZATION"));
					u.setEnabled(rs.getInt("ENABLED"));
					u.setPhone(rs.getString("PHONE"));
					u.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
					return u;
				}
			};
			log.debug(sql);
			u = rhlServiceJdbcTemplate.queryForObject(sql,rowMapper);
			log.info("Queried user  "+ u);
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return u;
	}
	
	/**
	 * Get the user data using bean property row mapper
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public User getUserInfoBeanProperty(int userId) throws Exception {
		User u = null;
		try {
			String sql = "SELECT * FROM data.user WHERE user_id="+userId;
			log.debug(sql);
			u = rhlServiceJdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
			log.info("Queried user  "+ u);
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return u;
	}

	
	/**
	 * Update the user data
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateUserInfo(final User user) {
		String resultString = "";
		String sql = "UPDATE USER SET "
							 + " USER_NAME=?,"
							 + " FIRST_NAME=?,"
							 + " LAST_NAME=?,"
							 + " EMAIL=?,"
							 + " PASSWORD=?,"							 
							 + " ORGANIZATION=?,"
							 + " ENABLED=?,"
							 + " PHONE=?,"
							 + " CREATED_TIME=? "
							 + " WHERE USER_ID=?";
		try {
			log.debug(sql);
			log.debug(user+"");
			int key = rhlServiceJdbcTemplate.update(sql,  
				    new Object[] { user.getUsername(),
									user.getFirstName(),
									user.getLastName(),
									user.getEmail(),
									user.getPassword(),
									user.getOrganization(),
									user.getEnabled(),
									user.getPhone(),
									new Timestamp(System.currentTimeMillis()),
									user.getUserId()}); 
			log.debug("Successfully updated");
			resultString = key+"";
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return resultString;
	}

	
	/**
	 * Delete user data
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean deleteUserInfo(int userId){
		try {
			String sql = "DELETE FROM USER WHERE USER_ID="+userId; 
			log.debug(sql);
			int rows = rhlServiceJdbcTemplate.update(sql);
			return (rows>0)?true:false;
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return false;
	}
	
	/**
	 * Count users in the table user
	 * @return
	 */
	@Transactional(value = "rhlServiceTransactionManager")
	public int countUserInfo(){
		try {
			String sql = "SELECT count(1) FROM USER"; 
			log.debug(sql);
			int count = rhlServiceJdbcTemplate.queryForInt(sql);
			return count;
		} catch (DataAccessException e) {
			log.error("Error "+ e.getMessage());
		} catch (Exception e) {
			log.error("Error "+ e.getMessage());
		}
		return 0;
	}
	
}
