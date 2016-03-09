package app;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WallItem {

	private final String username;
	private final Message message;

	public WallItem(final String username, final Message message) {
		this.username = username;
		this.message = message;
	}

	@Override
	public String toString() {
		return username + " - " + message;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
