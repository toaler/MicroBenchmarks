package org.bpt.microbenchmark.text;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringInterning {
	private static final int MAGNITUDE = 3;
	private static final String[] strings = new String[MAGNITUDE];
    private static Interner<String> INTERNER = Interners.newWeakInterner();
    

	static {
		Random r = new Random();
		
		for (int i = 0 ; i < MAGNITUDE ; i++) {
			String s = new String();
			for (int j = 0 ; j < (10 ^ i) ; j++) {
				s.concat(Character.toString((char) (r.nextInt(26) + 'a')));
			}
			strings[i] = s;
		}
	}
	
	@GenerateMicroBenchmark
	public String arrayLookup() {
		return strings[0];
	}
	
	@GenerateMicroBenchmark
	public String StringCreationSizeOne() {
		return new String(strings[0]);
	}
	
	@GenerateMicroBenchmark
	public String StringInternSizeOne() {
		return strings[0].intern();
	}
	
	@GenerateMicroBenchmark
	public String StringGuavaWeakInternerSizeOne() {
		return INTERNER.intern(strings[0]);
	}
	
	@GenerateMicroBenchmark
	public String StringCreationSizeTen() {
		return new String(strings[1]);
	}
	
	@GenerateMicroBenchmark
	public String StringInternSizeTen() {
		return strings[1].intern();
	}
	
	@GenerateMicroBenchmark
	public String StringGuavaWeakInternerSizeTen() {
		return INTERNER.intern(strings[1]);
	}
	
	@GenerateMicroBenchmark
	public String StringCreationSizeOneHundred() {
		return new String(strings[2]);
	}
	
	@GenerateMicroBenchmark
	public String StringInternSizeOneHundred() {
		return strings[2].intern();
	}
	
	@GenerateMicroBenchmark
	public String StringGuavaWeakInternerSizeOneHundred() {
		return INTERNER.intern(strings[2]);
	}
}