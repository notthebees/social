package userinterface;

import app.NetworkingApp;

public class FollowingParser implements CommandParser {

	private final String separator = " follows ";

	public boolean recognises(final String command) {
		return command.contains(separator);
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(separator);
		final String subscriberName = args[0];
		final String publisherName = args[1];
		app.follow(subscriberName, publisherName);
	}

}
