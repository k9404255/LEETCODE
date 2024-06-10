package leetCode.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class M0341_FlattenNestedListIterator {
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a
		// nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a
		// single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a nested
		// list
		// Return empty list if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	// solution 1
	public class NestedIterator1 implements Iterator<Integer> {
		int idx = 0;
		int size;
		List<NestedInteger> nestedList;
		NestedIterator1 i;

		public NestedIterator1(List<NestedInteger> nestedList) {
			this.nestedList = nestedList.stream().filter(row -> {
				if (row.isInteger()) {
					return true;
				} else {
					return row.isInteger() || new NestedIterator1(row.getList()).hasNext();
				}
			}).collect(Collectors.toList());
			this.size = this.nestedList.size();
		}

		@Override
		public Integer next() {
			NestedInteger ni = nestedList.get(idx);
			Integer result = 0;
			if (ni.isInteger()) {
				idx++;
				result = ni.getInteger();
			} else if (i == null || i.hasNext()) {
				if (i == null) {
					i = new NestedIterator1(ni.getList());
				}
				result = i.next();
			}

			if (i != null && !i.hasNext()) {
				i = null;
				idx++;
			}

			return result;
		}

		@Override
		public boolean hasNext() {
			if (idx < size) {
				return true;
			}

			return false;
		}
	}

	// solution 2
	public class NestedIterator2 implements Iterator<Integer> {
		private List<Integer> flattened;
		private int index;

		public NestedIterator2(List<NestedInteger> nestedList) {
			flattened = new ArrayList<>();
			index = 0;
			flattened = flatten(nestedList);
		}

		private List<Integer> flatten(List<NestedInteger> nested) {
			List<Integer> result = new ArrayList<>();

			for (NestedInteger ni : nested) {
				if (ni.isInteger()) {
					result.add(ni.getInteger());
				} else {
					result.addAll(flatten(ni.getList()));
				}
			}

			return result;
		}

		public Integer next() {
			return flattened.get(index++);
		}

		public boolean hasNext() {
			return index < flattened.size();
		}
	}

	public static void main(String[] args) {

	}
}
