package apache.commons.digester3.example.rulesbinder.module;

import org.apache.commons.digester3.binder.RulesBinder;
import org.apache.commons.digester3.binder.RulesModule;

import apache.commons.digester3.example.rulesbinder.pojo.Address;
import apache.commons.digester3.example.rulesbinder.pojo.Employee;

public class EmployeeModule2
    implements RulesModule
{

	public void configure( RulesBinder rulesBinder )
    {
        rulesBinder.forPattern( "employee" ).createObject().ofType( Employee.class );
        rulesBinder.forPattern( "employee/firstName" ).setBeanProperty();
        rulesBinder.forPattern( "employee/lastName" ).setBeanProperty();

        rulesBinder.forPattern( "employee/address" )
            .createObject().ofType( Address.class )
            .then()
            .setNext( "addAddress" );
        rulesBinder.forPattern( "employee/address/type" ).setBeanProperty();
		rulesBinder.forPattern("employee/address/city" ).setBeanProperty();
        rulesBinder.forPattern( "employee/address/state" ).setBeanProperty();
    }

}
