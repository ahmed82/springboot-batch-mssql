package com.atr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atr.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getString("ASSOCIATE_ID"));
		user.setFirstName(rs.getString("ASSOCIATE_FIRST_NAME"));
		user.setLastName(rs.getString("ASSOCIATE_LAST_NAME"));
		user.setName(rs.getString("ASSOCIATE_NAME"));
		user.setPhone(rs.getString("ASSOCIATE_PHONE"));
		user.setExt(rs.getString("ASSOCIATE_EXT"));
		user.setReportingAllowed(rs.getBoolean("ASSOCIATE_REPORTING_ALLOWED"));
		user.setSecurityLevel(rs.getInt("ASSOCIATE_SECURITY_LEVEL"));
		user.setActive(rs.getBoolean("ACTIVE"));
		return user;
	}
	
	

}
