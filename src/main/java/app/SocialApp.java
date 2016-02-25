package app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocialApp implements NetworkingApp {

	private final Set<User> users = new HashSet<User>();

	public void postMessage(final User user, final Message message) {
		users.add(user);
	}

	public Set<User> users() {
		return users;
	}

	public List<Message> readTimeline(final User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
