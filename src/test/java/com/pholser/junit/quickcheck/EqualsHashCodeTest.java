package com.pholser.junit.quickcheck;

import org.junit.Ignore;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@Ignore("turn on when testing lots of permutations")
@RunWith(Theories.class)
public class EqualsHashCodeTest {
    @Theory
    public void equalsIsReflexive(@ForAll Object o) {
        assumeThat(o, is(not(equalTo(null))));

        assertTrue(o.equals(o));
    }

    @Theory
    public void equalsIsSymmetric(@ForAll Object x, @ForAll Object y) {
        assumeThat(x, is(not(equalTo(null))));
        assumeThat(y, is(not(equalTo(null))));
        assumeTrue(y.equals(x));

        assertTrue(x.equals(y));
    }

    @Theory
    public void equalsIsTransitive(@ForAll(sampleSize = 20) Object x,
        @ForAll(sampleSize = 20) Object y,
        @ForAll(sampleSize = 20) Object z) {

        assumeThat(x, is(not(equalTo(null))));
        assumeThat(y, is(not(equalTo(null))));
        assumeThat(z, is(not(equalTo(null))));
        assumeTrue(x.equals(y) && y.equals(z));

        assertTrue(z.equals(x));
    }

    @Theory
    public void equalsIsConsistent(@ForAll Object x, @ForAll Object y) {
        assumeThat(x, is(not(equalTo(null))));
        boolean alwaysTheSame = x.equals(y);

        for (int i = 0; i < 30; i++)
            assertThat(x.equals(y), is(alwaysTheSame));
    }

    @Theory
    public void equalsReturnFalseOnNull(@ForAll Object x) {
        assumeThat(x, is(not(equalTo(null))));

        assertFalse(x.equals(null));
    }

    @Theory
    public void hashCodeIsSelfConsistent(@ForAll Object x) {
        assumeThat(x, is(not(equalTo(null))));
        int alwaysTheSame = x.hashCode();

        for (int i = 0; i < 30; i++)
            assertThat(x.hashCode(), is(alwaysTheSame));
    }

    @Theory
    public void hashCodeIsConsistentWithEquals(@ForAll Object x, @ForAll Object y) {
        assumeThat(x, is(not(equalTo(null))));
        assumeTrue(x.equals(y));

        assertEquals(x.hashCode(), y.hashCode());
    }

    @Theory
    public void equalsWorks(@ForAll Object x, @ForAll Object y) {
        assumeThat(x, is(not(equalTo(null))));
        assumeTrue(x == y);

        assertTrue(x.equals(y));
    }

    @Theory
    public void notEqualsWorks(@ForAll Object x, @ForAll Object y) {
        assumeThat(x, is(not(equalTo(null))));
        assumeTrue(x != y);

        assertFalse(x.equals(y));
    }
}
