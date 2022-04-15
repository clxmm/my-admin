package org.clxmm.autocode.learn.demo.prox.staticprox;

public class LandlordProxy  implements Landlord{


    private final Landlord landlord;

    public LandlordProxy(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public boolean rental() {
        beforeRental();
        if (landlord.rental()) {
            afterRental();
            return true;
        }
        return false;
    }

    private void afterRental() {
        System.out.println("收取佣金");
    }

    private void beforeRental() {
        System.out.println("带人看房子");
    }



}
