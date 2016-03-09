package userinterface;

import app.NetworkingApp;
import app.User;

public class FollowingParser implements CommandParser {

	private final String separator = " follows ";

	public boolean recognises(final String command) {
		return command.contains(separator);
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(separator);
		final User subscriber = new User(args[0]);
		final User publisher = new User(args[1]);
		app.follow(subscriber, publisher);
	}

}
