package in.binarycodes.lib.data;

import java.io.Serializable;

public class Pair<T1, T2> implements Serializable {
	private static final long serialVersionUID = 132812365079481533L;

	private T1 first;
	private T2 last;

	private Pair() {
	}

	private Pair(T1 first, T2 last) {
		this.first = first;
		this.last = last;
	}

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getLast() {
		return last;
	}

	public void setLast(T2 last) {
		this.last = last;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		boolean check = false;
		if (obj instanceof Pair) {
			Pair pair = (Pair) obj;
			check = this.getFirst().equals(pair.getFirst())
					&& this.getLast().equals(pair.getLast());
		}
		return check;
	}
}
