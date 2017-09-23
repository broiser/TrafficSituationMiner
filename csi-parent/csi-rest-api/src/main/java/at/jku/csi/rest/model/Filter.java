package at.jku.csi.rest.model;

public class Filter {

	public enum FilterType {
		EQUAL,LIKE, LESS_THAN, GREATER_THAN
	}

	private String field;
	private Object value;
	private FilterType type;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public FilterType getType() {
		return type;
	}

	public void setType(FilterType type) {
		this.type = type;
	}
}
