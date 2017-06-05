
package apache.commons.digester3.example.rulesbinder;

import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.xml.sax.SAXException;

import apache.commons.digester3.example.rulesbinder.module.EmployeeModule;
import apache.commons.digester3.example.rulesbinder.pojo.Address;
import apache.commons.digester3.example.rulesbinder.pojo.Employee;
import apache.commons.digester3.example.simpletest.ExampleMain;

public class DigesterLoaderMain {

	private static DigesterLoader dl = DigesterLoader.newLoader(new EmployeeModule())
			.setNamespaceAware(false);
	public static void main(String[] args) {

		try {


			Digester digester = dl.newDigester();
			Employee employee = digester.parse(ExampleMain.class.getClassLoader().getResourceAsStream("employee.xml"));

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
