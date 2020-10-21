package org.semanticweb.owl.simpleowlapi;

import java.io.FileNotFoundException;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
simpleOWLAPI is a light-weight wrapper for the OWLAPI enabling more concise OWL ontology development.

Copyright (C) <2020> Kody Moodley

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

public class TestCase1 {
	public static void main(String [] args) throws OWLOntologyCreationException {
		SimpleOWLAPIFactory s = SimpleOWLAPIFactory.getInstance(SelectedReasoner.PELLET);
		s.createOntology("http://com.kodymoodley/ontologies/2020/testontology#");
		
		s.createClasses("Penguin Peacock Bird Robin FlyingOrganism Fish Wing Gender Male Female Plant TailFeather Toe");
		s.createOProperties("hasPart isPartOf hasGender knows eats hunts");
		s.createIndividuals("tweety woody nemo sharky sheila feather1");
		s.createDProperties("hasWeight name bornOn");

		s.makeSymmetric("knows");
		s.makeIRReflexive("hasGender");
		s.makeAntiSymmetric("hasGender");
		
		s.setFullIRIRendering(true);
		
		s.createAxiom("tweety Type: hasWeight value \"300.56\"^^xsd:double");
		s.createAxiom("tweety Type: hasGender exactly 1 Gender");
		s.createAxiom("tweety Type: name value \"Tweety\"^^xsd:string");
		
		s.setFullIRIRendering(false);
		
		s.createAxiom("isPartOf inverseOf: hasPart");
		s.createAxiom("eats subPropertyOf: hunts");
		
		s.createAxiom("Gender equivalentTo: Male or Female");
		s.createAxiom("Male disjointWith: Female");
		
		s.createAxiom("Bird subClassOf FlyingOrganism and (hasPart max 5 Toe)");
		s.createAxiom("Bird subClassOf hasPart exactly 2 Wing");
		s.createAxiom("Penguin subClassOf Bird");
		s.createAxiom("Penguin subClassOf eats some Fish");
		s.createAxiom("Robin subClassOf Bird and (eats only Plant)");
		
		s.createAxiom("Peacock subClassOf Bird and (hasPart min 50 TailFeather)");
		s.createAxiom("Penguin subClassOf not FlyingOrganism");
		
		s.createAxiom("nemo Type: Fish");
		s.createAxiom("tweety Type: Penguin");
		s.createAxiom("woody Type: Robin");
		s.createAxiom("sheila Type: Peacock");
		s.createAxiom("sharky Type: Fish");
		s.createAxiom("feather1 Type: TailFeather");
		
		s.createAxiom("{tweety,woody} subClassOf Bird");
		
		s.createAxiom("tweety Type: eats value nemo");
		s.createAxiom("sheila Type: hasPart value feather1");
		s.createAxiom("woody Type: knows value nemo");
		System.out.println(s.owlReasoner.ontology.getOntologyID().getDefaultDocumentIRI().get().toString());
		
		s.setFullIRIRendering(false);
		
		s.createOntology("http://com.kodymoodley/ontologies/2020/testontology2#");
		
		s.setOntology("http://com.kodymoodley/ontologies/2020/testontology2#");
		System.out.println(s.owlReasoner.ontology.getOntologyID().getDefaultDocumentIRI().get().toString());
		s.createClasses("Man Woman Person Lecturer Student Course");
		s.createOProperties("teaches takes marriedTo");
		s.createIndividuals("john mary nicole kody semweb");
		s.createAxiom("john Type: Man");
		s.createAxiom("Man or Woman subClassOf Person");
		s.createAxiom("Student equivalentTo: Person and takes some Course");
		s.createAxiom("Man disjointWith: Woman");
		s.createAxiom("mary Type: Woman");
		s.createAxiom("john Type: marriedTo value mary");
		s.createAxiom("semweb Type: Course");
		s.createAxiom("john Type: takes value semweb");
		s.makeSymmetric("marriedTo");
		s.getOntology();
		s.owlReasoner.getAllTypes();
		s.owlReasoner.getAllOPropertyAssertions();
		s.getOntology();
		s.setOntology("http://com.kodymoodley/ontologies/2020/testontology#");
		
		s.owlReasoner.explainInconsistency();
		s.removeAxiom("tweety Type: Penguin");
		s.owlReasoner.isSatisfiable("Penguin");
		s.owlReasoner.explainUnsatisfiability("Penguin");
		s.owlReasoner.explainEntailment("Robin subClassOf FlyingOrganism");
		s.owlReasoner.getSuperClasses("Robin");
		s.owlReasoner.getSubClasses("FlyingOrganism");
		s.owlReasoner.getName();
		
		try {
			s.saveOntology("testontology4.owl");
		} catch (OWLOntologyStorageException oe) {
			oe.printStackTrace();
		}
		catch (FileNotFoundException fe) {
			fe.printStackTrace();
		}
		
		s.getOntologies();
		s.getOntology();
		s.getOntologies();
		s.printOntology();
		s.printOntologyStats();
		s.setOntology("http://com.kodymoodley/ontologies/2020/testontology2#");
		s.printOntology();
		s.printOntologyStats();
	}
}
