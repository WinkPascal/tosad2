package hu.tosad2;


import domain.BusinessRule.BusinessRuleFacade;

public class App{
    public static void main( String[] args ) throws InterruptedException {
        BusinessRuleFacade businessRuleFacade = new BusinessRuleFacade(1);
        System.out.println(businessRuleFacade.createNewBusinessRule());
    }
}
