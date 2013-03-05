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
    }

    @Test
    public void testDSD() {
        assertTrue( "One dsd", passes("integrity-2.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check missing dsd", passes("integrity-2.ttl", "abbrv-cube.ttl", "missing-dsd.ttl") );
        assertTrue( "Unique dsd", passes("integrity-2.ttl", "abbrv-cube.ttl") );
        assertFalse( "Check multiple dsd", passes("integrity-2.ttl", "abbrv-cube.ttl", "extra-dsd.ttl") );
    }

    @Test
    public void testMeasure() {
        assertTrue ( "measure required (pos)", passes("integrity-3.ttl", "abbrv-cube.ttl") );
        assertFalse( "measure required (neg)", passes("integrity-3.ttl", "missing-measure.ttl") );
    }
    
    @Test
    public void testRange() {
        assertTrue ( "range required (pos)", passes("integrity-4.ttl", "abbrv-cube.ttl") );
        assertFalse( "range required (neg)", passes("integrity-4.ttl", "missing-range.ttl") );
    }
    
    @Test
    public void testCodelistPresent() {
        assertTrue ( "codelist for concepts required (pos)", passes("integrity-5.ttl", "abbrv-cube.ttl") );
        assertFalse( "codelist for concepts required (neg)", passes("integrity-5.ttl", "missing-codelist.ttl") );
    }
    
    @Test
    public void testOptionality() {
        assertTrue ( "only attributes optional (pos)", passes("integrity-6.ttl", "abbrv-cube.ttl") );
        assertFalse( "only attributes optional (neg)", passes("integrity-6.ttl", "bad-component-required.ttl") );
    }
    
    @Test
    public void testSliceKeysHaveDSD() {
    
        assertTrue ( "slice keys have DSDs (pos)", passes("integrity-7.ttl", "abbrv-cube.ttl") );
        assertFalse( "slice keys have DSDs (neg)", passes("integrity-7.ttl", "bad-slicekey.ttl") );
    }
    @Test
    public void testSliceKeysDimsInDSD() {
        assertTrue ( "slice keys use dsd comps (pos)", passes("integrity-8.ttl", "abbrv-cube.ttl") );
        assertFalse( "slice keys use dsd comps (neg)", passes("integrity-8.ttl", "bad-slicekey2.ttl") );
    }
    
    @Test
    public void testSlicesHaveUniqueKey() {
        assertTrue ( "slices have unique keys (pos)", passes("integrity-9.ttl", "abbrv-cube.ttl") );
        assertFalse( "slices have unique keys (neg)", passes("integrity-9.ttl", "missing-slicekey.ttl") );
        assertFalse( "slices have unique keys (neg)", passes("integrity-9.ttl", "duplicate-slicekey.ttl") );
    }

    @Test
    public void testSlicesDeclareAllDimsInKey() {
        assertTrue ( "slices declare all key dims (pos)", passes("integrity-10.ttl", "abbrv-cube.ttl") );
        assertFalse( "slices declare all key dims (neg)", passes("integrity-10.ttl", "abbrv-cube.ttl", "missing-slicedim.ttl") );
    }
    
    @Test
    public void testAllDimsPresent() {
        assertTrue ( "obs have all dims (pos)", passes("integrity-11.ttl", "abbrv-cube.ttl") );
        assertFalse( "obs have all dims (neg)", passes("integrity-11.ttl", "abbrv-cube.ttl", "missing-obs-dim-value.ttl") );
    }
    
    @Test
    public void testNoDuplicateObs() {
        assertTrue ( "obs distinct dims (pos)", passes("integrity-12.ttl", "abbrv-cube.ttl") );
        assertFalse( "obs distinct dims (neg)", passes("integrity-12.ttl", "abbrv-cube.ttl", "duplicate-observation.ttl") );
    }
    
    @Test
    public void testRequredAttributes() {
        assertTrue ( "required attributes (pos)", passes("integrity-13.ttl", "abbrv-cube.ttl") );
        assertFalse( "required attributes (neg)", passes("integrity-13.ttl", "abbrv-cube.ttl", "missing-required-attr.ttl") );
    }
    
    @Test
    public void testAllMeasuresPresent() {
        assertTrue ( "all measures (pos)", passes("integrity-14.ttl", "abbrv-cube.ttl") );
        assertFalse( "all measures (neg)", passes("integrity-14.ttl", "abbrv-cube.ttl", "missing-measure-on-obs.ttl") );
        assertTrue ( "all measures (pos)", passes("integrity-14.ttl", "mt-cube.ttl") );
    }
    
    @Test
    public void testMTCubeMeasureForMeasureType() {
        assertTrue ( "measure for measureType (pos)", passes("integrity-15.ttl", "mt-cube.ttl") );
        assertFalse( "measure for measureType (neg)", passes("integrity-15.ttl", "mt-cube.ttl", "mt-missing-measure.ttl") );
    }
    
    @Test
    public void testMTCubeOneMeasureAtATime() {
        assertTrue ( "single measure for measureType obs (pos)", passes("integrity-16.ttl", "mt-cube.ttl") );
        assertFalse( "single measure for measureType obs (neg)", passes("integrity-16.ttl", "mt-cube.ttl", "mt-extra-measure.ttl") );
    }

    @Test
    public void testMTCubeMeasuresPresent() {
//        printSelect( FileManager.get().readWholeFileAsUTF8("src/test/resources/test-query.sparql"), "mt-cube.ttl");
//        printSelect( FileManager.get().readWholeFileAsUTF8("src/test/resources/test-query.sparql"), "mt-cube-3-measures.ttl");
//        printSelect( FileManager.get().readWholeFileAsUTF8("src/test/resources/test-query.sparql"), "mt-cube-with-missing-points.ttl");

        assertTrue ( "all measures in measureType cube (pos)", passes("integrity-17.ttl", "mt-cube.ttl") );
        assertTrue ( "all measures in measureType cube (pos)", passes("integrity-17.ttl", "mt-cube-3-measures.ttl") );
        assertFalse( "all measures in measureType cube (neg)", passes("integrity-17.ttl", "mt-cube-with-missing-points.ttl") );
    }
    
    @Test
    public void testDatasestLinkup() {
        assertTrue ( "dataset/slice/obs loop (pos)", passes("integrity-18.ttl", "abbrv-cube.ttl") );
        assertFalse( "dataset/slice/obs loop (neg)", passes("integrity-18.ttl", "abbrv-cube.ttl", "observation-bad-dataset.ttl") );
    }
    
    @Test
    public void testCodelistChecking() {
        assertTrue ( "codelist checking (ConceptScheme) (pos)", passes("integrity-19a.ttl", "abbrv-cube.ttl", "codelist-complete.ttl") );
        assertFalse( "codelist checking (ConceptScheme) (neg)", passes("integrity-19a.ttl", "abbrv-cube.ttl", "codelist-partial.ttl") );
    
        assertTrue ( "codelist checking (Collection) (pos)", passes("integrity-19b.ttl", "abbrv-cube.ttl", "collection-complete.ttl") );
        assertFalse( "codelist checking (Collection) (neg)", passes("integrity-19b.ttl", "abbrv-cube.ttl", "collection-partial.ttl") );
    }
    
    @Test
    public void testHierarchyChecking() {
        assertTrue ( "(pos)", passes("integrity-20.ttl", "abbrv-cube.ttl", "hierarchy-good.ttl") );
        assertTrue ( "(pos)", passes("integrity-20.ttl", "abbrv-cube.ttl", "hierarchy-good-multiroot.ttl") );
        
        assertTrue ( "(pos)", passes("integrity-21.ttl", "abbrv-cube.ttl", "hierarchy-good-inverse.ttl") );
        
        assertFalse( "(neg)", passes("integrity-20.ttl", "abbrv-cube.ttl", "hierarchy-bad.ttl") );
        assertFalse( "(neg)", passes("integrity-21.ttl", "abbrv-cube.ttl", "hierarchy-bad-inverse.ttl") );
    }
}
