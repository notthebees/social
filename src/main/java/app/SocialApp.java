package app;

import java.util.ArrayList;
import java.util.List;

public class SocialApp {

	private final List<User> users = new ArrayList<User>();

	public void postMessage(final User user, final Message message) {
		users.add(user);
	}

	public List<User> users() {
		return users;
	}

}
