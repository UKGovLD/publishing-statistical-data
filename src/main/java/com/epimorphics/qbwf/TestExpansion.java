/******************************************************************
 * File:        TestExpansion.java
 * Created by:  Dave Reynolds
 * Created on:  4 Mar 2013
 *
 * (c) Copyright 2013, Epimorphics Limited
 *
 *****************************************************************/

package com.epimorphics.qbwf;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.DatasetFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.util.FileManager;

public class TestExpansion {

    static final String closure = FileManager.get().readWholeFileAsUTF8("closure.ru");
    static final String flatten = FileManager.get().readWholeFileAsUTF8("flatten.ru");

    public static Model expand(Model m) {
        Dataset ds = DatasetFactory.create(m);
        GraphStore graphStore = GraphStoreFactory.create(ds) ;
        UpdateAction.parseExecute(closure, graphStore);
        UpdateAction.parseExecute(flatten, graphStore);
        Model result = ModelFactory.createModelForGraph( graphStore.getDefaultGraph() );
        result.setNsPrefixes(m);
        return result;
    }

    public static void main(String[] args) {
        Model src = FileManager.get().loadModel("src/test/resources/abbrv-cube.ttl");
//        Model src = FileManager.get().loadModel("src/test/resources/mt-cube.ttl");
        expand(src).write(System.out, "Turtle");
    }

}
