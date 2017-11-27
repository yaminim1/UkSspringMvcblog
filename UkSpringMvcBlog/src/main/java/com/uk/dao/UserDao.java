package com.uk.dao;

import com.uk.model.Users;

public interface UserDao {

	Users findByName(String username);
}
