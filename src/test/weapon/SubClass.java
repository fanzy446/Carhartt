package test.weapon;

public class SubClass {
	private String broadClassName;
	private String subClassName;

	public SubClass(String broadClassName, String subClassName) {
		this.broadClassName = broadClassName;
		this.subClassName = subClassName;
	}

	public String getBroadClassName() {
		return broadClassName;
	}

	public void setBroadClassName(String broadClassName) {
		this.broadClassName = broadClassName;
	}

	public String getSubClassName() {
		return subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}

}
