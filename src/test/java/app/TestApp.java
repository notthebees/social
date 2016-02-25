package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import userinterface.CommandProcessor;

public class TestApp {

	@Test
	public void testMessagePost() {
		final InputStream in = new ByteArrayInputStream("Alice -> hi Mom!".getBytes());
		final PrintStream out = new PrintStream(new ByteArrayOutputStream());
		final SocialApp app = new SocialApp();
		final CommandProcessor processor = new CommandProcessor(app, in, out);
		processor.getCommand();
		assertThat(app.users(), contains(new User("Alice")));
	}
}
