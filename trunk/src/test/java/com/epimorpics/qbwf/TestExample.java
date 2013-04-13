/******************************************************************
 * File:        TestExample.java
 * Created by:  Dave Reynolds
 * Created on:  13 Apr 2013
 * 
 * (c) Copyright 2013, Epimorphics Limited
 *
 *****************************************************************/

package com.epimorpics.qbwf;

import com.epimorphics.qbwf.TestExpansion;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class TestExample {

    public static final String[] TESTS = {
        "integrity-1.ttl",
        "integrity-2.ttl",
        "integrity-3.ttl",
        "integrity-4.ttl",
        "integrity-5.ttl",
        "integrity-6.ttl",
        "integrity-7.ttl",
        "integrity-8.ttl",
        "integrity-9.ttl",
        "integrity-10.ttl",
        "integrity-11.ttl",
        "integrity-12.ttl",
        "integrity-13.ttl",
        "integrity-14.ttl",
        "integrity-15.ttl",
        "integrity-16.ttl",
        "integrity-17.ttl",
        "integrity-18.ttl",
        "integrity-19a.ttl",
        "integrity-19b.ttl",
        "integrity-20.ttl",
        "integrity-21.ttl"
    };
    
    public static boolean test(String file) {
        boolean ok = true;
        Model abbrevmodel = FileManager.get().loadModel(file);
        Model model = TestExpansion.expand(abbrevmodel);
        model.add( FileManager.get().loadModel("specs/src/main/vocab/sdmx-dimension.ttl"));
//        model.write(System.out, "Turtle");
        for (String test : TESTS) {
            boolean passes = IntegrityCheck.fromFile(test).passes(model);
            if (!passes) {
                System.err.println("FAILED " + test);
                ok = false;
            }
            
        }
        return ok;
    }
    
    public static void main(String[] args) {
        if ( test("specs/src/main/example/example.ttl") ) {
            System.out.println("Passed");
        }
    }
}
