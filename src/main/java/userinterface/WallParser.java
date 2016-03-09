package userinterface;

import app.NetworkingApp;

public class WallParser implements CommandParser {

	public boolean recognises(final String command) {
		return command.endsWith(" wall");
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(" wall");
		final String username = args[0];
		app.wall(username);
	}

}
