package lt.sventes.cart.controller;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CreateCartCommand {
	private String username;

	@NotNull
	@Length(min = 1, max = 30)
	private String description;

	public CreateCartCommand() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
