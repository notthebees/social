package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSocialApp {

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
	public void readsUsersTimeline() {
		final SocialApp app = new SocialApp();

		app.postMessage(alice, new Message("Hi"));
		app.postMessage(alice, new Message("Bye"));

		app.readTimeline(alice);

		assertThat(outContent.toString(), equalTo("Hi\nBye\n"));
	}

	@Test
	public void noDuplicateUsernames() {
		final SocialApp app = new SocialApp();

		final User alice2 = new User("Alice");
		app.postMessage(alice, new Message("something"));
		app.postMessage(alice2, new Message("something"));

		assertThat(app.users(), contains(alice));
	}

	@Test
	public void newUsersCreatedWhenFirstMessagePosted() {
		final SocialApp app = new SocialApp();

		final User bob = new User("Bob");
		app.postMessage(alice, new Message("something"));
		app.postMessage(bob, new Message("something"));

		assertThat(app.users(), contains(alice, bob));
	}

}
