package org.bpt.microbenchmark.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

import com.google.common.collect.ImmutableSortedMap;

public class MapSearch {

	private static final int SEARCH_SIZE = 1 * 1000 * 100;
	private static final int STRING_SIZE = 20;
	
	private static final String[] STRINGS = new String[SEARCH_SIZE];
	private static final Map<String, Boolean> STRING_HASH_MAP = new HashMap<String, Boolean>(SEARCH_SIZE);
	private static final Map<String, Boolean> STRING_ISM;
	static {
		Random r = new Random();
		
		for (int i = 0 ; i < SEARCH_SIZE ; i++) {
			STRINGS[i] = new String();
			for (int j = 0 ; j < STRING_SIZE ; j++) {
				STRINGS[i] = STRINGS[i].concat(Character.toString((char) (r.nextInt(26) + 'a')));
			}
			STRING_HASH_MAP.put(STRINGS[i], true);
		}
		
		STRING_ISM = ImmutableSortedMap.copyOf(STRING_HASH_MAP);
	}
	
	private static long id = 0;

	@GenerateMicroBenchmark
	public String getOverhead() {
		return STRINGS[(int)(id++ % SEARCH_SIZE)];
	}
	
	@GenerateMicroBenchmark
	public Boolean getJavaUtilHashMap() {
		return STRING_HASH_MAP.get(STRINGS[(int)(id++ % SEARCH_SIZE)]);
	}
	
	@GenerateMicroBenchmark
	public Boolean getGuavaImmutableSortedMap() {
		return STRING_ISM.get(STRINGS[(int)(id++ % SEARCH_SIZE)]);
	}
}
