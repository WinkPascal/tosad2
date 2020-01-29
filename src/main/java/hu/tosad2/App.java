package hu.tosad2;


import domain.BusinessRule.BusinessRuleFacade;
import domain.BusinessRule.BusinessRuleFacadeInterface;
import domain.BusinessRule.Server;

public class App{
    public static void main( String[] args ) throws InterruptedException {
//        BusinessRuleFacadeInterface businessRuleFacade = new BusinessRuleFacade(7);
//        businessRuleFacade.createNewBusinessRule();
//        businessRuleFacade.setBusinessRule();
        new Server().startServer();
    }
}
