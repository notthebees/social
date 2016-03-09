package userinterface;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import app.Message;
import app.NetworkingApp;

public class TestCommandProcessor {
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock NetworkingApp app;

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
	public void processesWallCommand() {
		final InputStream in = new ByteArrayInputStream("Alice wall".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).readWall("Alice");
		}});

		processor.getCommand();
	}

	@Test
	public void processesFollowCommand() {
		final InputStream in = new ByteArrayInputStream("Alice follows Bob".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).follow("Alice", "Bob");
		}});

		processor.getCommand();
	}

	@Test
	public void processesReadCommandAndPrints() {
		final InputStream in = new ByteArrayInputStream("Alice".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		final List<Message> timeline = new ArrayList<Message>();
		timeline.add(new Message("Hi"));

		context.checking(new Expectations() {{
			oneOf(app).readTimeline("Alice"); will(returnValue(timeline));
		}});

		processor.getCommand();

		assertThat(outContent.toString(), equalTo("Hi\n"));
	}

	@Test
	public void processesPostMessageCommand() {
		final InputStream in = new ByteArrayInputStream("Alice -> Hi Mom!".getBytes());
		System.setIn(in);

		final CommandProcessor processor = new CommandProcessor(app);

		context.checking(new Expectations() {{
			oneOf(app).postMessage("Alice", new Message("Hi Mom!"));
		}});

		processor.getCommand();
	}

}
