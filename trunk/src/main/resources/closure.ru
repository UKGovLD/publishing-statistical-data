PREFIX rdf:            <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX qb:             <http://purl.org/linked-data/cube#>

# Insert optional type statements
INSERT {
    ?o rdf:type qb:Observation .
} WHERE {
    [] qb:observation ?o .
};

INSERT {
    ?o rdf:type qb:Observation .
} WHERE {
    ?o qb:dataSet [] .
};

INSERT {
    ?s rdf:type qb:Slice .
} WHERE {
    [] qb:slice ?s.
};

INSERT {
    ?sk rdf:type qb:SliceKey .
} WHERE {
    [] qb:sliceStructure ?sk .
};

INSERT {
    ?cs qb:componentProperty ?p .
    ?p  rdf:type qb:DimensionProperty .
} WHERE {
    ?cs qb:dimension ?p .
};

INSERT {
    ?cs qb:componentProperty ?p .
    ?p  rdf:type qb:MeasureProperty .
} WHERE {
    ?cs qb:measure ?p .
};

INSERT {
    ?cs qb:componentProperty ?p .
    ?p  rdf:type qb:AttributeProperty .
} WHERE {
    ?cs qb:attribute ?p .
}
