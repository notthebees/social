package userinterface;

import java.util.List;

import app.Message;
import app.NetworkingApp;

public class WallParser implements CommandParser {

	public boolean recognises(final String command) {
		return command.endsWith(" wall");
	}

	public void process(final String command, final NetworkingApp app) {
		final String[] args = command.split(" wall");
		final String username = args[0];
		final List<Message> wall = app.readWall(username);
		print(wall);
	}

	private void print(final List<Message> wall) {
		for (final Message message : wall) {
			System.out.println(message);
		}
	}

}
