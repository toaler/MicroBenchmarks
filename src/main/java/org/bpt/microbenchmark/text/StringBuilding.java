package org.bpt.microbenchmark.text;

import java.util.Random;
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
public class StringBuilding {
	private static final int MAX_STRING_SIZE_POWER = 9;
	private static final String[] s = new String[MAX_STRING_SIZE_POWER + 1];
	
	static {
		Random r = new Random();
		
		for (int i = 0 ; i <= MAX_STRING_SIZE_POWER ; i++) {
			s[i] = new String();
			for (int j = 0 ; j < Math.pow(2, i) ; j++) {
				s[i] = s[i].concat(Character.toString((char) (r.nextInt(26) + 'a')));
			}
		}
	}
	
	private String concat(final String s, final int iterations) {
		String buf = null;
		for (int i = 0 ; i < iterations ; i++) {
			buf = buf.concat(s);
		}
		return buf;
	}
	
	private String twoConcat(final String s) {
		return concat(s, 2);
	}
	
	private String threeConcat(final String s) {
		return concat(s, 3);
	}
	
	private String fourConcat(final String s) {
		return concat(s, 4);
	}
	
	private String stringBuilderDefault(final String s, final int iterations) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < iterations ; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
	private String twoStringBuilderDefault(final String s) {
		return stringBuilderDefault(s, 2);
	}
	
	private String threeStringBuilderDefault(final String s) {
		return stringBuilderDefault(s, 3);
	}
	
	private String fourStringBuilderDefault(final String s) {
		return stringBuilderDefault(s, 4);
	}
	
	private String stringBuilderSized(final String s, final int size, final int iterations) {
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0 ; i < iterations ; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	private String twoStringBuilderSized(final String s, final int size) {
		return stringBuilderSized(s, size, 2);
	}
	
	private String threeStringBuilderSized(final String s, final int size) {
		return stringBuilderSized(s, size, 3);
	}
	
	private String fourStringBuilderSized(final String s, final int size) {
		return stringBuilderSized(s, size, 4);
	}
	
	private String plus(final String s, final int iterations) {
		String buf = s;
		for (int i = 0 ; i < (iterations - 1) ; i++) {
			buf = buf + s;
		}
		return buf;
	}
	
	private String twoPlus(final String s) {
		return plus(s, 2);
	}
	
	private String threePlus(final String s) {
		return plus(s, 3);
	}
	
	private String fourPlus(final String s) {
		return plus(s, 4);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat001Char() {
		return twoConcat(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat002Char() {
		return twoConcat(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat004Char() {
		return twoConcat(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat008Char() {
		return twoConcat(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat016Char() {
		return twoConcat(s[4]);
	}

	@GenerateMicroBenchmark
	public String twoConcat032Char() {
		return twoConcat(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat064Char() {
		return twoConcat(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat128Char() {
		return twoConcat(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat256Char() {
		return twoConcat(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String twoConcat512Char() {
		return twoConcat(s[9]);
	}
	
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault001Char() {
		return twoStringBuilderDefault(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault002Char() {
		return twoStringBuilderDefault(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault004Char() {
		return twoStringBuilderDefault(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault008Char() {
		return twoStringBuilderDefault(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault016Char() {
		return twoStringBuilderDefault(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault032Char() {
		return twoStringBuilderDefault(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault064Char() {
		return twoStringBuilderDefault(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault128Char() {
		return twoStringBuilderDefault(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault256Char() {
		return twoStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderDefault512Char() {
		return twoStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized001Char() {
		return twoStringBuilderSized(s[0], 2);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized002Char() {
		return twoStringBuilderSized(s[1], 4);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized004Char() {
		return twoStringBuilderSized(s[2], 8);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized008Char() {
		return twoStringBuilderSized(s[3], 16);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized016Char() {
		return twoStringBuilderSized(s[4], 32);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized032Char() {
		return twoStringBuilderSized(s[5], 64);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized064Char() {
		return twoStringBuilderSized(s[6], 128);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized128Char() {
		return twoStringBuilderSized(s[7], 256);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized256Char() {
		return twoStringBuilderSized(s[8], 512);
	}
	
	@GenerateMicroBenchmark
	public String twoStringBuilderSized512Char() {
		return twoStringBuilderSized(s[9], 1024);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus001Char() {
		return twoPlus(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus002Char() {
		return twoPlus(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus004Char() {
		return twoPlus(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus008Char() {
		return twoPlus(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus016Char() {
		return twoPlus(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus032Char() {
		return twoPlus(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus064Char() {
		return twoPlus(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus128Char() {
		return twoPlus(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus256Char() {
		return twoPlus(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String twoPlus512Char() {
		return twoPlus(s[9]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat001Char() {
		return threeConcat(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat002Char() {
		return threeConcat(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat004Char() {
		return threeConcat(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat008Char() {
		return threeConcat(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat016Char() {
		return threeConcat(s[4]);
	}

	@GenerateMicroBenchmark
	public String threeConcat032Char() {
		return threeConcat(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat064Char() {
		return threeConcat(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat128Char() {
		return threeConcat(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat256Char() {
		return threeConcat(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String threeConcat512Char() {
		return threeConcat(s[9]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault001Char() {
		return threeStringBuilderDefault(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault002Char() {
		return threeStringBuilderDefault(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault004Char() {
		return threeStringBuilderDefault(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault008Char() {
		return threeStringBuilderDefault(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault016Char() {
		return threeStringBuilderDefault(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault032Char() {
		return threeStringBuilderDefault(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault064Char() {
		return threeStringBuilderDefault(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault128Char() {
		return threeStringBuilderDefault(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault256Char() {
		return threeStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderDefault512Char() {
		return threeStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized001Char() {
		return threeStringBuilderSized(s[0], 3);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized002Char() {
		return threeStringBuilderSized(s[1], 6);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized004Char() {
		return threeStringBuilderSized(s[2], 12);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized008Char() {
		return threeStringBuilderSized(s[3], 24);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized016Char() {
		return threeStringBuilderSized(s[4], 48);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized032Char() {
		return threeStringBuilderSized(s[5], 96);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized064Char() {
		return threeStringBuilderSized(s[6], 192);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized128Char() {
		return threeStringBuilderSized(s[7], 384);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized256Char() {
		return threeStringBuilderSized(s[8], 768);
	}
	
	@GenerateMicroBenchmark
	public String threeStringBuilderSized512Char() {
		return threeStringBuilderSized(s[9], 1536);
	}
	
	@GenerateMicroBenchmark
	public String threePlus001Char() {
		return threePlus(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus002Char() {
		return threePlus(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus004Char() {
		return threePlus(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus008Char() {
		return threePlus(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus016Char() {
		return threePlus(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus032Char() {
		return threePlus(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus064Char() {
		return threePlus(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus128Char() {
		return threePlus(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus256Char() {
		return threePlus(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String threePlus512Char() {
		return threePlus(s[9]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat001Char() {
		return fourConcat(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat002Char() {
		return fourConcat(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat004Char() {
		return fourConcat(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat008Char() {
		return fourConcat(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat016Char() {
		return fourConcat(s[4]);
	}

	@GenerateMicroBenchmark
	public String fourConcat032Char() {
		return fourConcat(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat064Char() {
		return fourConcat(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat128Char() {
		return fourConcat(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat256Char() {
		return fourConcat(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String fourConcat512Char() {
		return fourConcat(s[9]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault001Char() {
		return fourStringBuilderDefault(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault002Char() {
		return fourStringBuilderDefault(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault004Char() {
		return fourStringBuilderDefault(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault008Char() {
		return fourStringBuilderDefault(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault016Char() {
		return fourStringBuilderDefault(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault032Char() {
		return fourStringBuilderDefault(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault064Char() {
		return fourStringBuilderDefault(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault128Char() {
		return fourStringBuilderDefault(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault256Char() {
		return fourStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderDefault512Char() {
		return fourStringBuilderDefault(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized001Char() {
		return fourStringBuilderSized(s[0], 3);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized002Char() {
		return fourStringBuilderSized(s[1], 6);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized004Char() {
		return fourStringBuilderSized(s[2], 12);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized008Char() {
		return fourStringBuilderSized(s[3], 24);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized016Char() {
		return fourStringBuilderSized(s[4], 48);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized032Char() {
		return fourStringBuilderSized(s[5], 96);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized064Char() {
		return fourStringBuilderSized(s[6], 192);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized128Char() {
		return fourStringBuilderSized(s[7], 384);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized256Char() {
		return fourStringBuilderSized(s[8], 768);
	}
	
	@GenerateMicroBenchmark
	public String fourStringBuilderSized512Char() {
		return fourStringBuilderSized(s[9], 1536);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus001Char() {
		return fourPlus(s[0]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus002Char() {
		return fourPlus(s[1]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus004Char() {
		return fourPlus(s[2]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus008Char() {
		return fourPlus(s[3]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus016Char() {
		return fourPlus(s[4]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus032Char() {
		return fourPlus(s[5]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus064Char() {
		return fourPlus(s[6]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus128Char() {
		return fourPlus(s[7]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus256Char() {
		return fourPlus(s[8]);
	}
	
	@GenerateMicroBenchmark
	public String fourPlus512Char() {
		return fourPlus(s[9]);
	}
}