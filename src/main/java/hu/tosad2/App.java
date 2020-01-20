package hu.tosad2;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule.Attribute;
import domain.BusinessRule.BusinessRule;
import domain.BusinessRule.BusinessRuleFacade;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> values = new ArrayList<String>();
        values.add("1");
        values.add("6");
        List<Attribute> attributen = new ArrayList<Attribute>();
        Attribute attribute = new Attribute("attribuut1", "entiteit1");
        attributen.add(attribute);
        BusinessRule facade  = new BusinessRuleFacade("1",attributen, values, "ACMP", "<");
        facade.createNewBusinessRule();
    }
}
