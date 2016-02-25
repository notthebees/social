package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.Test;

public class TestSocialApp {

	@Test
	public void newUserCreatedWhenFirstMessagePosted() {
		final SocialApp app = new SocialApp();

		final User alice = new User("Alice");
		app.postMessage(alice, new Message("anything"));

		assertThat(app.users(), contains(alice));
	}

}
