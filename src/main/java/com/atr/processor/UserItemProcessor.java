package com.atr.processor;

import org.springframework.batch.item.ItemProcessor;

import com.atr.model.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		
		return item;
	}
	
	

}
