package org.clxmm.autocode.learn.demo.prox.staticprox;

public class LandlordImpl implements Landlord {
    @Override
    public boolean rental() {
        System.out.println("李老板的房子出租啦");
        return true;
    }
}
