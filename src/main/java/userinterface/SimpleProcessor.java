package userinterface;

import java.util.Scanner;

import app.Message;
import app.NetworkingApp;
import app.User;

public class SimpleProcessor implements CommandProcessor {

	private final NetworkingApp app;
	private final Scanner scanner;

	public SimpleProcessor(final NetworkingApp app) {
		this.app = app;
		scanner = new Scanner(System.in);
	}

	/* (non-Javadoc)
	 * @see userinterface.CommandProcessor#getCommand()
	 */
	public void getCommand() {
		final String input = scanner.nextLine();
		final String[] args = input.split(" -> ");
		final User user = new User(args[0]);
		if (args.length > 1) {
			final Message message = new Message(args[1]);
			app.postMessage(user, message);
		} else {
			app.readTimeline(user);
		}
	}

}
