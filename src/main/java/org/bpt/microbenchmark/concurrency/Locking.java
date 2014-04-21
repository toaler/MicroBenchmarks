package org.bpt.microbenchmark.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Locking {

	@State(Scope.Benchmark)
	public static class Resource {
		private long resource = 0;

		public void write(long resource) {
			this.resource += resource;
		}

		public long read() {
			return resource;
		}
	}
	
	@State(Scope.Benchmark)
	public static class ResourceSynchronized {
		private long resource = 0;

		public synchronized void write(long resource) {
			this.resource += resource;
		}

		public synchronized long read() {
			return resource;
		}
	}

	@State(Scope.Benchmark)
	public static class ResourceVolatile {
		private volatile long resource = 0;

		public synchronized void write(long resource) {
			this.resource += resource;
		}

		public long read() {
			return resource;
		}
	}

	@State(Scope.Benchmark)
	public static class ResourceReentrantLock {
		private long resource = 0;
		private static ReentrantLock lock = new ReentrantLock();

		public void write(long resource) {
			lock.lock();
			try {
				this.resource += resource;
			} finally {
				lock.unlock();
			}
		}

		public long read() {
			lock.lock();
			try {
				return resource;
			} finally {
				lock.unlock();
			}
		}
	}

	@State(Scope.Benchmark)
	public static class ResourceReentrantReadWriteLock {
		private long resource = 0;
		private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		public void write(long resource) {
			lock.writeLock().lock();
			try {
				this.resource += resource;
			} finally {
				lock.writeLock().unlock();
			}
		}

		public long read() {
			lock.readLock().lock();
			try {
				return resource;
			} finally {
				lock.readLock().unlock();
			}
		}
	}
	
	@State(Scope.Benchmark)
	public static class ResourceAtomic {
		private AtomicLong resource = new AtomicLong();

		public void write(long resource) {
			this.resource.addAndGet(resource);
		}

		public long read() {
			return resource.get();
		}
	}
	
	@State(Scope.Benchmark)
	public static class ResourceImmutable {
		private final long resource;
		
		public ResourceImmutable() {
			resource = 0L;
		}
		
		private ResourceImmutable(long resource) {
			this.resource = resource;
		}

		public ResourceImmutable write(long resource) {
			return new ResourceImmutable(resource + this.resource);
		}

		public long read() {
			return resource;
		}
	}

	@GenerateMicroBenchmark
	public void testWriteUnsync(Resource resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadUnsync(Resource resource) {
		resource.read();
	}

	@GenerateMicroBenchmark
	public void testWriteSync(ResourceSynchronized resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadSync(ResourceSynchronized resource) {
		resource.read();
	}

	@GenerateMicroBenchmark
	public void testWriteSyncVolatile(ResourceVolatile resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadSyncVolatile(ResourceVolatile resource) {
		resource.read();
	}

	@GenerateMicroBenchmark
	public void testWriteRL(ResourceReentrantLock resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadRL(ResourceReentrantLock resource) {
		resource.read();
	}

	@GenerateMicroBenchmark
	public void testWriteRRWL(ResourceReentrantReadWriteLock resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadRRWL(ResourceReentrantReadWriteLock resource) {
		resource.read();
	}
	
	@GenerateMicroBenchmark
	public void testWriteAtomic(ResourceAtomic resource) {
		resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadAtomic(ResourceAtomic resource) {
		resource.read();
	}
	
	
	@GenerateMicroBenchmark
	public void testWriteImmutable(ResourceImmutable resource) {
		resource = resource.write(1L);
	}

	@GenerateMicroBenchmark
	public void testReadImmutable(ResourceImmutable resource) {
		resource.read();
	}
}