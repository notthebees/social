package userinterface;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import app.Message;
import app.SocialApp;
import app.User;

public class CommandProcessor {

	private final SocialApp app;
	private final Scanner scanner;
	private final PrintStream out;

	public CommandProcessor(final SocialApp app, final InputStream in, final PrintStream out) {
		this.app = app;
		this.out = out;
		scanner = new Scanner(in);
	}

	public void getCommand() {
		out.println("Enter command: ");
		final String input = scanner.nextLine();
		final String[] args = input.split(" -> ");
		final User user = new User(args[0]);
		final Message message = new Message(args[1]);
		app.postMessage(user, message);
	}

}
