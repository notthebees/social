package userinterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import app.Message;
import app.NetworkingApp;
import app.User;

public class TestCommandProcessor {
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock NetworkingApp app;

	private final User alice = new User("Alice");

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
	public void processesFollowCommand() {
		final InputStream in = new ByteArrayInputStream("Alice follows Bob".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).follow(alice, new User("Bob"));;
		}});

		processor.getCommand();
	}

	@Test
	public void processesReadCommand() {
		final InputStream in = new ByteArrayInputStream("Alice".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).readTimeline(alice);;
		}});

		processor.getCommand();
	}

	@Test
	public void processesPostMessageCommand() {
		final InputStream in = new ByteArrayInputStream("Alice -> Hi Mom!".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).postMessage(alice, new Message("Hi Mom!"));
		}});

		processor.getCommand();
	}

}
