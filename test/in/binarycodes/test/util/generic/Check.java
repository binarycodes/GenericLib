package in.binarycodes.test.util.generic;

public class Check {
	private Integer id;
	private String description;

	public Check() {
		super();
	}

	public Check(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return description.concat(" (").concat(id.toString()).concat(")");
	}

}
