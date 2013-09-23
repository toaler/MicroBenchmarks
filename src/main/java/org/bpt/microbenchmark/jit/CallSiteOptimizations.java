package org.bpt.microbenchmark.jit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class CallSiteOptimizations {
	private static final int SIZE = 100;
	private static final int CALL_SITE_DEGREE = 3;
	
	private static final List<List<IOperation>> iOperationSets;
	private static final List<List<BaseOperation>> vOperationSets;
	
	private static List<IOperation> iMonomorphic, iBimorphic, iMegamorphic;
	private static List<BaseOperation> vMonomorphic, vBimorphic, vMegamorphic;
	
	private long id = 0;
	
	static {
		iOperationSets = new ArrayList<List<IOperation>>();
		vOperationSets = new ArrayList<List<BaseOperation>>();
		
		for (int i = 0 ; i < CALL_SITE_DEGREE ; i++) {
			// Allocate new set
			IOperation[] iOperation = new IOperation[SIZE];
			BaseOperation[] vOperation = new BaseOperation[SIZE];
			
			for (int j = 0 ; j < SIZE ; j++) {
				int type = (j % (i + 1)) + 1;
				iOperation[j] = CallSiteOptimizations.newIOperation(type);
				vOperation[j] = CallSiteOptimizations.newOperation(type);
			}
			
			iOperationSets.add(i, Arrays.asList(iOperation));
			vOperationSets.add(i, Arrays.asList(vOperation));
			
			Collections.shuffle(iOperationSets.get(i));
			Collections.shuffle(vOperationSets.get(i));
		}

		iMonomorphic = iOperationSets.get(0);
		iBimorphic = iOperationSets.get(1);
		iMegamorphic = iOperationSets.get(2);
		
		vMonomorphic = vOperationSets.get(0);
		vBimorphic = vOperationSets.get(1);
		vMegamorphic = vOperationSets.get(2);
	}
	
	private interface IOperation {
		public long op();
	}
	
	private static class IOperationOne implements IOperation {
		public long op() {
			return 1;
		}
	}
	
	private static class IOperationTwo implements IOperation {
		public long op() {
			return 1;
		}
	}
	
	private static class IOperationThree implements IOperation {
		public long op() {
			return 1;
		}
	}
		
	private static IOperation newIOperation(final int op) {
		switch (op) {
		case 1:
			return new IOperationOne();
		case 2:
			return new IOperationTwo();
		case 3:
			return new IOperationThree();
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private static abstract class BaseOperation {
		public abstract long op();
	}
	
	private static class OperationOne extends BaseOperation {
		@Override
		public long op() {
			return 1;
		}
	}
	
	private static class OperationTwo extends BaseOperation {
		@Override
		public long op() {
			return 1;
		}
	}

	private static class OperationThree extends BaseOperation {
		@Override
		public long op() {
			return 1;
		}
	}
	
	private static BaseOperation newOperation(final int op) {
		switch (op) {
		case 1:
			return new OperationOne();
		case 2:
			return new OperationTwo();
		case 3:
			return new OperationThree();
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method to measure call site overhead.
	 */
	@GenerateMicroBenchmark
	public long InterfaceAOverhead() {
		iMonomorphic.get((int) (id++ % SIZE));
		return id;
	}
	
	/**
	 * This the case where Class Hierarchy Analysis (CHA) passes and the call to
	 * op() is made static via inlining.
	 */
	@GenerateMicroBenchmark
	public long InterfaceBMonomorphicCallSite() {
		return iMonomorphic.get((int) (id++ % SIZE)).op();
	}
	
	/**
	 * CHA fails and ad a 1 entry cache is created. The key of the cache is
	 * 'this' and the value is the method to execute. HotSpot JIT handles
	 * bimorphic inlining, and performs the 'this' comparison on a second hot
	 * class if the first failed.
	 */
	@GenerateMicroBenchmark
	public long InterfaceCBimorphicCallSite() {
		return iBimorphic.get((int) (id++ % SIZE)).op();
	}
	
	/**
	 * In the megamorphic call site example, the inline cache will not be used
	 * and a interface call will pursue.
	 */
	@GenerateMicroBenchmark
	public long InterfaceDMegamorphicCallSite() {
		return iMegamorphic.get((int) (id++ % SIZE)).op();
	}

	/**
	 * Method to measure call site overhead.
	 */
	@GenerateMicroBenchmark
	public long VirtualAOverhead() {
		vMonomorphic.get((int) (id++ % SIZE));
		return id;
	}
	
	/**
	 * This the case where Class Hierarchy Analysis (CHA) passes and the call to
	 * op() is made static via inlining.
	 */
	@GenerateMicroBenchmark
	public long VirtualBMonomorphicCallSite() {
		return vMonomorphic.get((int) (id++ % SIZE)).op();
	}
	
	/**
	 * CHA fails and ad a 1 entry cache is created. The key of the cache is
	 * 'this' and the value is the method to execute. HotSpot JIT handles
	 * bimorphic inlining, and performs the 'this' comparison on a second hot
	 * class if the first failed.
	 */
	@GenerateMicroBenchmark
	public long VirtualCBimorphicCallSite() {
		return vBimorphic.get((int) (id++ % SIZE)).op();
	}
	
	/**
	 * In the megamorphic call site example, the inline cache will not be used
	 * and a interface call will pursue.
	 */
	@GenerateMicroBenchmark
	public long VirtualDMegamorphicCallSite() {
		return vMegamorphic.get((int) (id++ % SIZE)).op();
	}
}