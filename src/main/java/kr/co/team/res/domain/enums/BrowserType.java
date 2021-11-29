package kr.co.team.res.domain.enums;

public enum BrowserType {
    WHALE("WHALE"),
    IE("IE"),
    EDGE("Edge"),
    OPERA("Opera"),
    FIREFOX("Firefox"),
    SAFARI("Safari"),
    CHROME("chrome");

    final private String name;

    public String getName() {
        return name;
    }

    private BrowserType(String name){
        this.name = name;
    }
}
