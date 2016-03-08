package app;

import java.util.HashSet;
import java.util.Set;

public class SocialApp implements NetworkingApp {

	private final Set<User> users = new HashSet<User>();

	public void postMessage(final User user, final Message message) {
		users.add(user);
		user.addMessage(message);
	}

	public void readTimeline(final User user) {
		user.printTimeline();
	}

	public Set<User> users() {
		return users;
	}

}
