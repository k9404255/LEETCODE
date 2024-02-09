package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0049_GroupAnagrams {
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (int i = 0; i < strs.length; i++) {
			char charArray[] = strs[i].toCharArray();
			Arrays.sort(charArray);
			String sortedStr = new String(charArray);

			if (map.containsKey(sortedStr)) {
				map.get(sortedStr).add(strs[i]);
			} else {
				List<String> list = new ArrayList<>();
				list.add(strs[i]);
				map.put(sortedStr, list);
			}
		}

		return new ArrayList<List<String>>(map.values());
	}

	public static void main(String[] args) {
		System.out.println(groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
		System.out.println(groupAnagrams(new String[] { "" }));
		System.out.println(groupAnagrams(new String[] { "a" }));
	}
}
