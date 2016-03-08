package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import userinterface.CommandProcessor;
import userinterface.SimpleProcessor;

public class TestApp {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

	@Test
	public void testPostingAndReading() {
		final String userName = "Alice";
		final String message = "Hi Mom!";
		final String postCommand = userName + " -> " + message;
		final InputStream in = new ByteArrayInputStream((postCommand+"\n"+userName).getBytes());
		System.setIn(in);

		final SocialApp app = new SocialApp();
		final CommandProcessor processor = new SimpleProcessor(app);
		processor.getCommand();
		processor.getCommand();
		assertThat(outContent.toString(), equalTo(message));
	}

	@Test
	public void testMessagePost() {
		final InputStream in = new ByteArrayInputStream("Alice -> Hi Mom!".getBytes());
		System.setIn(in);
		final SocialApp app = new SocialApp();
		final CommandProcessor processor = new SimpleProcessor(app);
		processor.getCommand();
		assertThat(app.users(), contains(new User("Alice")));
	}
}
