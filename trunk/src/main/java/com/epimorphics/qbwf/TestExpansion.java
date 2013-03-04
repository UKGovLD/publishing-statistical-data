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

    Model m;

    public TestExpansion(String src) {
        m = FileManager.get().loadModel(src);
    }

    public Model expand() {
        Dataset ds = DatasetFactory.create(m);
        GraphStore graphStore = GraphStoreFactory.create(ds) ;
        UpdateAction.readExecute("src/main/resources/closure.ru", graphStore) ;
        UpdateAction.readExecute("src/main/resources/flatten.ru", graphStore) ;
        Model result = ModelFactory.createModelForGraph( graphStore.getDefaultGraph() );
        result.setNsPrefixes(m);
        return result;
    }

    public static void main(String[] args) {
        TestExpansion test = new TestExpansion("src/test/resources/abbrv-cube.ttl");
        test.expand().write(System.out, "Turtle");
    }

}
