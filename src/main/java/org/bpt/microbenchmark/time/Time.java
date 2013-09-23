package org.bpt.microbenchmark.time;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Time {

	@GenerateMicroBenchmark
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	@GenerateMicroBenchmark
	public long nanoTime() {
		return System.nanoTime();
	}
}
