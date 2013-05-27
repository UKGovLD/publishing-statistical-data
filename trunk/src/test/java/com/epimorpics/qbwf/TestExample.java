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
        "integrity-1.sparql",
        "integrity-2.sparql",
        "integrity-3.sparql",
        "integrity-4.sparql",
        "integrity-5.sparql",
        "integrity-6.sparql",
        "integrity-7.sparql",
        "integrity-8.sparql",
        "integrity-9.sparql",
        "integrity-10.sparql",
        "integrity-11.sparql",
        "integrity-12.sparql",
        "integrity-13.sparql",
        "integrity-14.sparql",
        "integrity-15.sparql",
        "integrity-16.sparql",
        "integrity-17.sparql",
        "integrity-18.sparql",
        "integrity-19a.sparql",
        "integrity-19b.sparql",
        "integrity-20.sparql",
        "integrity-21.sparql"
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
