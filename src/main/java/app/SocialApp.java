package app;

import java.util.ArrayList;
import java.util.List;

import parser.CommandParser;

public class SocialApp {

	private final List<User> users = new ArrayList<User>();

	public SocialApp(final CommandParser parser) {
		parser.connectTo(this);
	}

	public List<User> users() {
		return users;
	}

}
