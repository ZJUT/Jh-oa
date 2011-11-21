package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Ke;

public class FreeKeTogether {
	private long keId;
	private int total;
	private Ke ke;
	private UserTogether usertogether;

	public long getKeId() {
		return keId;
	}

	public void setKeId(long keId) {
		this.keId = keId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Ke getKe() {
		return ke;
	}

	public void setKe(Ke ke) {
		this.ke = ke;
	}

	public UserTogether getUsertogether() {
		return usertogether;
	}

	public void setUsertogether(UserTogether usertogether) {
		this.usertogether = usertogether;
	}

	@Override
	public String toString() {
		return "FreeKeTogether [keId=" + keId + ", total=" + total + ", ke="
				+ ke + ", usertogether=" + usertogether + "]";
	}

}
