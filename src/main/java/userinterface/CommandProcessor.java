package userinterface;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import app.NetworkingApp;
import app.Timeline;

public class CommandProcessor {

	private final NetworkingApp app;
	private final Scanner scanner;
	private final Set<CommandParser> parsers = new HashSet<CommandParser>();
	private boolean terminated = false;

	public CommandProcessor(final NetworkingApp app) {
		this.app = app;
		scanner = new Scanner(System.in);

		parsers.add(new PostingParser());
		parsers.add(new FollowingParser());
		parsers.add(new WallParser());
	}

	public void getCommand() {
		final String command = scanner.nextLine();
		if (command.equals("quit")) {
			terminated = true;
			return;
		}
		for (final CommandParser parser : parsers) {
			if (parser.recognises(command)) {
				parser.process(command, app);
				return;
			}
		}
		processReadCommand(command);

	}

	private void processReadCommand(final String command) {
		final String username = command;
		final Timeline timeline = app.readTimeline(username);
		System.out.println(timeline);
	}

	public boolean terminated() {
		return terminated;
	}

}
