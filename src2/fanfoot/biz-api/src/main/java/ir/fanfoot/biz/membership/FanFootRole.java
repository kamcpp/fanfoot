package ir.fanfoot.biz.membership;

public class FanFootRole implements Role {

    private String name;

    public FanFootRole(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
