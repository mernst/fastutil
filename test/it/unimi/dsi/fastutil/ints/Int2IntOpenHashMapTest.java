package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Hash;

import java.io.IOException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class Int2IntOpenHashMapTest {

	private static java.util.Random r = new java.util.Random( 0 );

	private static int genKey() {
		return r.nextInt();
	}

	private static int genValue() {
		return r.nextInt();
	}

	private static boolean valEquals( Object o1, Object o2 ) {
		return o1 == null ? o2 == null : o1.equals( o2 );
	}

	@SuppressWarnings({ "unchecked", "boxing" })
	protected static void test( int n, float f ) throws IOException, ClassNotFoundException {
		Int2IntOpenHashMap m = new Int2IntOpenHashMap( Hash.DEFAULT_INITIAL_SIZE, f );
		Map t = new java.util.HashMap();
		/* First of all, we fill t with random data. */
		for ( int i = 0; i < n; i++ )
			t.put( ( Integer.valueOf( genKey() ) ), ( Integer.valueOf( genValue() ) ) );
		/* Now we add to m the same data */
		m.putAll( t );
		assertTrue( "Error: !m.equals(t) after insertion", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after insertion", t.equals( m ) );
		/*
		 * Now we check that m actually holds that data.
		 */
		for ( java.util.Iterator i = t.entrySet().iterator(); i.hasNext(); ) {
			java.util.Map.Entry e = (java.util.Map.Entry)i.next();
			assertTrue( "Error: m and t differ on an entry (" + e + ") after insertion (iterating on t)", valEquals( e.getValue(), m.get( e.getKey() ) ) );
		}
		/* Now we check that m actually holds that data, but iterating on m. */
		for ( java.util.Iterator i = m.entrySet().iterator(); i.hasNext(); ) {
			java.util.Map.Entry e = (java.util.Map.Entry)i.next();
			assertTrue( "Error: m and t differ on an entry (" + e + ") after insertion (iterating on m)", valEquals( e.getValue(), t.get( e.getKey() ) ) );
		}
		/* Now we check that m actually holds the same keys. */
		for ( java.util.Iterator i = t.keySet().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a key (" + o + ") after insertion (iterating on t)", m.containsKey( o ) );
			assertTrue( "Error: m and t differ on a key (" + o + ", in keySet()) after insertion (iterating on t)", m.keySet().contains( o ) );
		}
		/* Now we check that m actually holds the same keys, but iterating on m. */
		for ( java.util.Iterator i = m.keySet().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a key after insertion (iterating on m)", t.containsKey( o ) );
			assertTrue( "Error: m and t differ on a key (in keySet()) after insertion (iterating on m)", t.keySet().contains( o ) );
		}
		/* Now we check that m actually hold the same values. */
		for ( java.util.Iterator i = t.values().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a value after insertion (iterating on t)", m.containsValue( o ) );
			assertTrue( "Error: m and t differ on a value (in values()) after insertion (iterating on t)", m.values().contains( o ) );
		}
		/* Now we check that m actually hold the same values, but iterating on m. */
		for ( java.util.Iterator i = m.values().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a value after insertion (iterating on m)", t.containsValue( o ) );
			assertTrue( "Error: m and t differ on a value (in values()) after insertion (iterating on m)", t.values().contains( o ) );
		}
		/*
		 * Now we check that inquiries about random data give the same answer in m and t. For m we
		 * use the polymorphic method.
		 */
		for ( int i = 0; i < n; i++ ) {
			int T = genKey();
			assertTrue( "Error: divergence in keys between t and m (polymorphic method)", m.containsKey( ( Integer.valueOf( T ) ) ) == t.containsKey( ( Integer.valueOf( T ) ) ) );
			assertTrue( "Error: divergence between t and m (polymorphic method)",
					!( m.get( T ) != ( 0 ) ) != ( ( t.get( ( Integer.valueOf( T ) ) ) == null ? ( 0 ) : ( ( ( (Integer)( t.get( ( Integer.valueOf( T ) ) ) ) ).intValue() ) ) ) != ( 0 ) ) ||
							t.get( ( Integer.valueOf( T ) ) ) != null &&
							!m.get( ( Integer.valueOf( T ) ) ).equals( t.get( ( Integer.valueOf( T ) ) ) ) );
		}
		/*
		 * Again, we check that inquiries about random data give the same answer in m and t, but for
		 * m we use the standard method.
		 */
		for ( int i = 0; i < n; i++ ) {
			int T = genKey();
			assertTrue( "Error: divergence between t and m (standard method)", valEquals( m.get( ( Integer.valueOf( T ) ) ), t.get( ( Integer.valueOf( T ) ) ) ) );
		}
		/* Now we put and remove random data in m and t, checking that the result is the same. */
		for ( int i = 0; i < 20 * n; i++ ) {
			int T = genKey();
			int U = genValue();
			assertTrue( "Error: divergence in put() between t and m",
					valEquals( m.put( ( Integer.valueOf( T ) ), ( Integer.valueOf( U ) ) ), t.put( ( Integer.valueOf( T ) ), ( Integer.valueOf( U ) ) ) ) );
			T = genKey();
			assertTrue( "Error: divergence in remove() between t and m", valEquals( m.remove( ( Integer.valueOf( T ) ) ), t.remove( ( Integer.valueOf( T ) ) ) ) );
		}
		assertTrue( "Error: !m.equals(t) after removal", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after removal", t.equals( m ) );
		/*
		 * Now we check that m actually holds the same data.
		 */
		for ( java.util.Iterator i = t.entrySet().iterator(); i.hasNext(); ) {
			java.util.Map.Entry e = (java.util.Map.Entry)i.next();
			assertTrue( "Error: m and t differ on an entry (" + e + ") after removal (iterating on t)", valEquals( e.getValue(), m.get( e.getKey() ) ) );
		}
		/* Now we check that m actually holds that data, but iterating on m. */
		for ( java.util.Iterator i = m.entrySet().iterator(); i.hasNext(); ) {
			java.util.Map.Entry e = (java.util.Map.Entry)i.next();
			assertTrue( "Error: m and t differ on an entry (" + e + ") after removal (iterating on m)", valEquals( e.getValue(), t.get( e.getKey() ) ) );
		}
		/* Now we check that m actually holds the same keys. */
		for ( java.util.Iterator i = t.keySet().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a key (" + o + ") after removal (iterating on t)", m.containsKey( o ) );
			assertTrue( "Error: m and t differ on a key (" + o + ", in keySet()) after removal (iterating on t)", m.keySet().contains( o ) );
		}
		/* Now we check that m actually holds the same keys, but iterating on m. */
		for ( java.util.Iterator i = m.keySet().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a key after removal (iterating on m)", t.containsKey( o ) );
			assertTrue( "Error: m and t differ on a key (in keySet()) after removal (iterating on m)", t.keySet().contains( o ) );
		}
		/* Now we check that m actually hold the same values. */
		for ( java.util.Iterator i = t.values().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a value after removal (iterating on t)", m.containsValue( o ) );
			assertTrue( "Error: m and t differ on a value (in values()) after removal (iterating on t)", m.values().contains( o ) );
		}
		/* Now we check that m actually hold the same values, but iterating on m. */
		for ( java.util.Iterator i = m.values().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on a value after removal (iterating on m)", t.containsValue( o ) );
			assertTrue( "Error: m and t differ on a value (in values()) after removal (iterating on m)", t.values().contains( o ) );
		}
		int h = m.hashCode();
		/* Now we save and read m. */
		java.io.File ff = new java.io.File( "it.unimi.dsi.fastutil.test" );
		java.io.OutputStream os = new java.io.FileOutputStream( ff );
		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream( os );
		oos.writeObject( m );
		oos.close();
		java.io.InputStream is = new java.io.FileInputStream( ff );
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream( is );
		m = (Int2IntOpenHashMap)ois.readObject();
		ois.close();
		ff.delete();
		assertEquals( "Error: hashCode() changed after save/read", m.hashCode(), h );
		/* Now we check that m actually holds that data. */
		for ( java.util.Iterator i = t.keySet().iterator(); i.hasNext(); ) {
			Object o = i.next();
			assertTrue( "Error: m and t differ on an entry after save/read", valEquals( m.get( o ), t.get( o ) ) );
		}
		/* Now we put and remove random data in m and t, checking that the result is the same. */
		for ( int i = 0; i < 20 * n; i++ ) {
			int T = genKey();
			int U = genValue();
			assertTrue( "Error: divergence in put() between t and m after save/read",
					valEquals( m.put( ( Integer.valueOf( T ) ), ( Integer.valueOf( U ) ) ), t.put( ( Integer.valueOf( T ) ), ( Integer.valueOf( U ) ) ) ) );
			T = genKey();
			assertTrue( "Error: divergence in remove() between t and m after save/read", valEquals( m.remove( ( Integer.valueOf( T ) ) ), t.remove( ( Integer.valueOf( T ) ) ) ) );
		}
		assertTrue( "Error: !m.equals(t) after post-save/read removal", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after post-save/read removal", t.equals( m ) );
		/*
		 * Now we take out of m everything , and check that it is empty.
		 */
		for ( java.util.Iterator i = t.keySet().iterator(); i.hasNext(); )
			m.remove( i.next() );
		assertTrue( "Error: m is not empty (as it should be)", m.isEmpty() );
		m = new Int2IntOpenHashMap( n, f );
		t.clear();
		int x;
		/* Now we torture-test the hash table. This part is implemented only for integers and longs. */
		int p = m.used.length;
		for ( int i = 0; i < p; i++ ) {
			for ( int j = 0; j < 20; j++ ) {
				m.put( i + ( r.nextInt() % 10 ) * p, 1 );
				m.remove( i + ( r.nextInt() % 10 ) * p );
			}
			for ( int j = -10; j < 10; j++ )
				m.remove( i + j * p );
		}
		t.putAll( m );
		/* Now all table entries are REMOVED. */
		for ( int i = 0; i < ( p * f ) / 10; i++ ) {
			for ( int j = 0; j < 10; j++ ) {
				assertTrue( "Error: m and t differ on an entry during torture-test insertion.",
						valEquals( m.put( ( Integer.valueOf( x = i + ( r.nextInt() % 10 ) * p ) ), ( Integer.valueOf( 1 ) ) ), t.put( ( Integer.valueOf( x ) ), ( Integer.valueOf( 1 ) ) ) ) );
			}
		}
		assertTrue( "Error: !m.equals(t) after torture-test insertion", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after torture-test insertion", t.equals( m ) );
		for ( int i = 0; i < p / 10; i++ ) {
			for ( int j = 0; j < 10; j++ ) {
				assertTrue( "Error: m and t differ on an entry during torture-test removal.",
						valEquals( m.remove( ( Integer.valueOf( x = i + ( r.nextInt() % 10 ) * p ) ) ), t.remove( ( Integer.valueOf( x ) ) ) ) );
			}
		}
		assertTrue( "Error: !m.equals(t) after torture-test removal", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after torture-test removal", t.equals( m ) );
		assertTrue( "Error: !m.equals(m.clone()) after torture-test removal", m.equals( m.clone() ) );
		assertTrue( "Error: !m.clone().equals(m) after torture-test removal", m.clone().equals( m ) );
		m.trim();
		assertTrue( "Error: !m.equals(t) after trim()", m.equals( t ) );
		assertTrue( "Error: !t.equals(m) after trim()", t.equals( m ) );
		return;
	}

	@Test
	public void test1() throws IOException, ClassNotFoundException {
		test( 1, Hash.DEFAULT_LOAD_FACTOR );
		test( 1, Hash.FAST_LOAD_FACTOR );
		test( 1, Hash.VERY_FAST_LOAD_FACTOR );
	}

	@Test
	public void test10() throws IOException, ClassNotFoundException {
		test( 10, Hash.DEFAULT_LOAD_FACTOR );
		test( 10, Hash.FAST_LOAD_FACTOR );
		test( 10, Hash.VERY_FAST_LOAD_FACTOR );
	}

	@Test
	public void test100() throws IOException, ClassNotFoundException {
		test( 100, Hash.DEFAULT_LOAD_FACTOR );
		test( 100, Hash.FAST_LOAD_FACTOR );
		test( 100, Hash.VERY_FAST_LOAD_FACTOR );
	}

	@Ignore("Too long")
	@Test
	public void test1000() throws IOException, ClassNotFoundException {
		test( 1000, Hash.DEFAULT_LOAD_FACTOR );
		test( 1000, Hash.FAST_LOAD_FACTOR );
		test( 1000, Hash.VERY_FAST_LOAD_FACTOR );
	}
}
