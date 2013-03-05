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
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.resultset.ResultSetRewindable;
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
        ResultSetFormatter.out(System.out, runQuery(query, files));
    }

    public ResultSetRewindable runQuery(String query, String...files) {
        Model m = TestExpansion.expand( loadModel(files) );
        QueryExecution qe = QueryExecutionFactory.create( IntegrityCheck.PREFIXES+ query, m);
        try {
            ResultSet rs = qe.execSelect();
            return ResultSetFactory.makeRewindable(rs);
        } finally {
            qe.close();
        }
    }

    public boolean queryReturnsEmpty(String query, String...files) {
        ResultSetRewindable rs = runQuery(query, files);
        return rs.size() == 0;
    }

    public Resource queryResult(String query, String var, String...files) {
        ResultSetRewindable rs = runQuery(query, files);
        if (rs.size() == 1) {
            return rs.next().getResource(var);
        }
        return null;
    }

    public boolean queryFromFileReturnsEmpty(String queryf, String...files) {
        String query = FileManager.get().readWholeFileAsUTF8(queryf);
        return queryReturnsEmpty(query, files);
    }

    public Resource queryFromFileResult(String queryf, String var, String...files) {
        String query = FileManager.get().readWholeFileAsUTF8(queryf);
        return queryResult(query, var, files);
    }

}
