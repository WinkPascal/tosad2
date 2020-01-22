package hu.tosad2;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule.Attribute;
import domain.BusinessRule.BusinessRule;
import domain.BusinessRule.BusinessRuleFacadeInterface;
import domain.BusinessRule.BusinessRuleFacade;

public class App
{



    public static void main( String[] args ) throws InterruptedException {
        List<String> values = new ArrayList<String>();
        values.add("1");
        values.add("6");
        List<Attribute> attributen = new ArrayList<Attribute>();
        Attribute attribute = new Attribute("attribuut1", "entiteit1");
        attributen.add(attribute);

      //  BusinessRuleFacadeInterface idk = new BusinessRuleFacade(1);
       // idk.createNewBusinessRule();
       BusinessRule facade  = new BusinessRule("1",attributen, values, "TCMP", "<");
      System.out.println(facade.generate());

    //    new Server().startServer();
    }
}
