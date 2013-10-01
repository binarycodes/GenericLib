package in.binarycodes.test.util.generic;

public class Employee {
	private Integer employeeId;
	private String employeeName;
	private Check check;

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		check = new Check(this.employeeId + 100,
				this.employeeName.toUpperCase());

	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

}
