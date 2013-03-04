/******************************************************************
 * File:        TestWF.java
 * Created by:  Dave Reynolds
 * Created on:  4 Mar 2013
 *
 * (c) Copyright 2013, Epimorphics Limited
 *
 *****************************************************************/

package com.epimorpics.qbwf;

import org.junit.Test;

import com.hp.hpl.jena.util.FileManager;

import static org.junit.Assert.*;

public class TestWF extends TestBase {

    @Test
    public void testBasic() {
        assertTrue( "One dataset", passes("integrity-1.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check missing dataset", passes("integrity-1.ttl", "abbrv-cube.ttl", "missing-ds.ttl") );
        assertTrue( "Unique dataset", passes("integrity-1.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check multiple dataset", passes("integrity-1.ttl", "abbrv-cube.ttl", "extra-ds.ttl") );

        assertTrue( "One dsd", passes("integrity-2.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check missing dsd", passes("integrity-2.ttl", "abbrv-cube.ttl", "missing-dsd.ttl") );
        assertTrue( "Unique dsd", passes("integrity-2.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check multiple dsd", passes("integrity-2.ttl", "abbrv-cube.ttl", "extra-dsd.ttl") );

        assertTrue ( "measure required (pos)", passes("integrity-3.ttl", "abbrv-cube.ttl") );
        assertFalse( "measure required (neg)", passes("integrity-3.ttl", "missing-measure.ttl") );

        assertTrue ( "range required (pos)", passes("integrity-4.ttl", "abbrv-cube.ttl") );
        assertFalse( "range required (neg)", passes("integrity-4.ttl", "missing-range.ttl") );

        assertTrue ( "codelist for concepts required (pos)", passes("integrity-5.ttl", "abbrv-cube.ttl") );
        assertFalse( "codelist for concepts required (neg)", passes("integrity-5.ttl", "missing-codelist.ttl") );

        assertTrue ( "only attributions optional (pos)", passes("integrity-6.ttl", "abbrv-cube.ttl") );
        assertFalse( "only attributions optional (neg)", passes("integrity-6.ttl", "bad-component-required.ttl") );

        assertTrue ( "slice keys have DSDs (pos)", passes("integrity-7.ttl", "abbrv-cube.ttl") );
        assertFalse( "slice keys have DSDs (neg)", passes("integrity-7.ttl", "bad-slicekey.ttl") );

        assertTrue ( "slice keys use dsd comps (pos)", passes("integrity-8.ttl", "abbrv-cube.ttl") );
        assertFalse( "slice keys use dsd comps (neg)", passes("integrity-8.ttl", "bad-slicekey2.ttl") );

        assertTrue ( "slices have unique keys (pos)", passes("integrity-9.ttl", "abbrv-cube.ttl") );
        assertFalse( "slices have unique keys (neg)", passes("integrity-9.ttl", "missing-slicekey.ttl") );
        assertFalse( "slices have unique keys (neg)", passes("integrity-9.ttl", "duplicate-slicekey.ttl") );

        assertTrue ( "slices declare all key dims (pos)", passes("integrity-10.ttl", "abbrv-cube.ttl") );
        assertFalse( "slices declare all key dims (neg)", passes("integrity-10.ttl", "abbrv-cube.ttl", "missing-slicedim.ttl") );

        assertTrue ( "obs have all dims (pos)", passes("integrity-11.ttl", "abbrv-cube.ttl") );
        assertFalse( "obs have all dims (neg)", passes("integrity-11.ttl", "abbrv-cube.ttl", "missing-obs-dim-value.ttl") );

//        printSelect( FileManager.get().readWholeFileAsUTF8("src/test/resources/test-query.sparql"), "abbrv-cube.ttl");
//        printSelect( FileManager.get().readWholeFileAsUTF8("src/test/resources/test-query.sparql"), "abbrv-cube.ttl", "duplicate-observation.ttl");

        assertTrue ( "obs distinct dims (pos)", passes("integrity-12.ttl", "abbrv-cube.ttl") );
        assertFalse( "obs distinct dims (neg)", passes("integrity-12.ttl", "abbrv-cube.ttl", "duplicate-observation.ttl") );

        assertTrue ( "required attributes (pos)", passes("integrity-13.ttl", "abbrv-cube.ttl") );
        assertFalse( "required attributes (neg)", passes("integrity-13.ttl", "abbrv-cube.ttl", "missing-required-attr.ttl") );

        assertTrue ( "all measures (pos)", passes("integrity-14.ttl", "abbrv-cube.ttl") );
        assertFalse( "all measures (neg)", passes("integrity-14.ttl", "abbrv-cube.ttl", "missing-measure-on-obs.ttl") );
        assertTrue ( "all measures (pos)", passes("integrity-14.ttl", "mt-cube.ttl") );

    }
}
