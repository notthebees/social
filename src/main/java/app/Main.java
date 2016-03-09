package app;

import userinterface.CommandProcessor;

public class Main {

	public static void main(final String[] args) {
		final NetworkingApp app = new SocialApp();
		final CommandProcessor processor = new CommandProcessor(app);
		while (! processor.terminated()) {
			processor.getCommand();
		}
	}

}
