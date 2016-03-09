package userinterface;

import app.NetworkingApp;
import app.Wall;

public class WallParser implements CommandParser {

	public boolean recognises(final String command) {
		return command.endsWith(" wall");
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(" wall");
		final String username = args[0];
		final Wall wall = app.readWall(username);
		System.out.println(wall);
	}

}
