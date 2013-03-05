/******************************************************************
 * File:        TestBase.java
 * Created by:  Dave Reynolds
 * Created on:  4 Mar 2013
 *
 * (c) Copyright 2013, Epimorphics Limited
 *
 *****************************************************************/

package com.epimorpics.qbwf;

import com.epimorphics.qbwf.TestExpansion;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class TestBase {
   

    public Model loadModel(String...files) {
        Model m = ModelFactory.createDefaultModel();
        for (String file : files) {
            FileManager.get().readModel(m,  "src/test/resources/" + file);
        }
        return m;
    }

    public boolean passes(IntegrityCheck check, String...files) {
        return check.passes( TestExpansion.expand( loadModel(files) ) );
    }

    public boolean passes(String checkFile, String...files) {
        return IntegrityCheck.fromFile(checkFile).passes( TestExpansion.expand( loadModel(files) ) );
    }

    public void printSelect(String query, String ...files) {
        Model m = TestExpansion.expand( loadModel(files) );
        QueryExecution qe = QueryExecutionFactory.create( IntegrityCheck.PREFIXES+ query, m);
        try {
            ResultSet rs = qe.execSelect();
            ResultSetFormatter.out(System.out, rs);
        } finally {
            qe.close();
        }
    }

}
