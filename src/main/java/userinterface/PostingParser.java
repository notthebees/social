package userinterface;

import app.Message;
import app.NetworkingApp;

public class PostingParser implements CommandParser {

	private final String separator = " -> ";

	public boolean recognises(final String command) {
		return command.contains(separator);
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(separator);
		final String username = args[0];
		final Message message = new Message(args[1], username);
		app.postMessage(username, message);
	}

}
