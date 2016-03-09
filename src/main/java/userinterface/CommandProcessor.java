package userinterface;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import app.NetworkingApp;
import app.User;

public class CommandProcessor {

	private final NetworkingApp app;
	private final Scanner scanner;
	private final Set<CommandParser> parsers = new HashSet<CommandParser>();

	public CommandProcessor(final NetworkingApp app) {
		this.app = app;
		scanner = new Scanner(System.in);

		parsers.add(new PostingParser());
		parsers.add(new FollowingParser());
	}

	public void getCommand() {
		final String command = scanner.nextLine();
		boolean commandRecognised = false;
		for (final CommandParser parser : parsers) {
			if (parser.recognises(command)) {
				parser.process(command, app);
				commandRecognised = true;
			}
		}
		if (! commandRecognised) {
			processReadCommand(command);
		}

	}

	private void processReadCommand(final String command) {
		final User user = new User(command);
		app.readTimeline(user);
	}

}
