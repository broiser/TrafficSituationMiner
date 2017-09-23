package at.jku.csi.rest.model;

import java.io.Serializable;
import java.util.List;

import at.jku.csi.service.model.dto.BaseDTO;

public class PageResult implements Serializable {

	private List<? extends BaseDTO> results;
	private long count;

	public PageResult(List<? extends BaseDTO> results, long count) {
		this.results = results;
		this.count = count;
	}

	public List<? extends BaseDTO> getResults() {
		return results;
	}

	public void setResults(List<? extends BaseDTO> results) {
		this.results = results;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
