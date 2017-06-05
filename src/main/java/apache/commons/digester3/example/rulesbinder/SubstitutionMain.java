/*
 * $Id$
 * Copyright (c)  by iCafeMavin Information Technology Inc. All right reserved.
 */
package apache.commons.digester3.example.rulesbinder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.Substitutor;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.substitution.MultiVariableExpander;
import org.apache.commons.digester3.substitution.VariableSubstitutor;
import org.xml.sax.SAXException;

import apache.commons.digester3.example.rulesbinder.module.EmployeeModule;
import apache.commons.digester3.example.rulesbinder.pojo.Address;
import apache.commons.digester3.example.rulesbinder.pojo.Employee;
import apache.commons.digester3.example.simpletest.ExampleMain;

public class SubstitutionMain {
	private static DigesterLoader dl = DigesterLoader.newLoader(new EmployeeModule())
			.setNamespaceAware(false);
	public static void main(String[] args) {

		try {

			 // set up the variables the input xml can reference
			  Map<String, Object> vars = new HashMap<String, Object>();
			  vars.put( "user.name", "me" );
			  vars.put( "city", "WenZhou" );

			  // map ${varname} to the entries in the var map
			  MultiVariableExpander expander = new MultiVariableExpander();
			  expander.addSource( "$", vars );

			  // allow expansion in both xml attributes and element text
			  Substitutor substitutor = new VariableSubstitutor( expander );

			

			Digester digester = dl.newDigester();
			digester.setSubstitutor(substitutor);
			
			Employee employee = digester.parse(ExampleMain.class.getClassLoader().getResourceAsStream("employee$.xml"));

			System.out.print(employee.getFirstName() + " ");
			System.out.print(employee.getLastName() + ", ");
			for (Address a : employee.getAddressList()) {
				System.out.print(a.getType() + ", ");
				System.out.print(a.getCity() + ", ");
				System.out.println(a.getState());
			}

		} catch (IOException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		}
	}
}
