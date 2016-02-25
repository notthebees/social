package app;

public class User {

	private final String name;

	public User(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User: " + name;
	}

}
