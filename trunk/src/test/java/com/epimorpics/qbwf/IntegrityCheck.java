/******************************************************************
 * File:        IntegrityCheck.java
 * Created by:  Dave Reynolds
 * Created on:  4 Mar 2013
 *
 * (c) Copyright 2013, Epimorphics Limited
 *
 *****************************************************************/

package com.epimorpics.qbwf;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class IntegrityCheck {
    String query;
    boolean successOnTrue = false;

    public static final String PREFIXES =
            "PREFIX rdf:            <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs:           <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX skos:           <http://www.w3.org/2004/02/skos/core#>\n" +
            "PREFIX qb:             <http://purl.org/linked-data/cube#>\n" +
            "PREFIX xsd:            <http://www.w3.org/2001/XMLSchema#>\n";

    public static IntegrityCheck fromFile(String qfile) {
        return new IntegrityCheck( PREFIXES + FileManager.get().readWholeFileAsUTF8( qfile), false);
    }

    public static IntegrityCheck fromFile(String qfile, boolean successOnTrue) {
        return new IntegrityCheck( PREFIXES + FileManager.get().readWholeFileAsUTF8( qfile), successOnTrue);
    }

    public IntegrityCheck(String query, boolean successOnTrue) {
        this.query = query;
        this.successOnTrue = successOnTrue;
    }

    public boolean passes(Model m) {
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        try {
            boolean result = qexec.execAsk();
            return successOnTrue ? result : !result;
        } finally {
            qexec.close();
        }
    }
}
