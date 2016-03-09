package app;

import java.util.HashSet;
import java.util.Set;

public class SocialApp implements NetworkingApp {

	private final Set<User> users = new HashSet<User>();

	public void postMessage(final String username, final Message message) {
		final User user;
		if (exists(username)) {
			user = findUser(username);
		}
		else {
			user = new User(username);
			users.add(user);
		}
		user.addMessage(message);
	}

	private boolean exists(final String username) {
		for (final User user : users) {
			if (user.nameIs(username)) {
				return true;
			}
		}
		return false;
	}

	public Timeline readTimeline(final String username) {
		final User user = findUser(username);
		return user.timeline();
	}

	private User findUser(final String username) {
		for (final User user : users) {
			if (user.nameIs(username)) {
				return user;
			}
		}
		return null;
	}

	public void follow(final String subscriberName, final String publisherName) {
		final User subscriber = findUser(subscriberName);
		final User publisher = findUser(publisherName);
		subscriber.addSubscription(publisher);
	}

	public Wall readWall(final String username) {
		final User user = findUser(username);
		return user.wall();
	}

}
