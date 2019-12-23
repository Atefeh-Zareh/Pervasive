package ir.ac.aut.ceit.pervasive.fantastic;

/**
 * 
 * @author Atefeh Zareh May 5, 2014
 */
public class SensorReport {
	private String type;
	private String name;
	private String values;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public SensorReport(String type, String name, String values) {
		super();
		this.type = type;
		this.name = name;
		this.values = values;
	}

	@Override
	public String toString() {
		return "Type:" + type + ", " + "Name: " + name + ", " + "Values: "
				+ values + "\n";
	}
}
