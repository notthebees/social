package app;

import java.util.HashSet;
import java.util.Set;

public class SocialApp {

	private final Set<User> users = new HashSet<User>();

	public void postMessage(final User user, final Message message) {
		users.add(user);
	}

	public Set<User> users() {
		return users;
	}

}
