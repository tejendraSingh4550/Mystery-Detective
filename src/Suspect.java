
public class Suspect {

    private String name;
    private String statement;
    private boolean guilty;

    public Suspect(String name, String statement, boolean guilty) {
        this.name = name;
        this.statement = statement;
        this.guilty = guilty;
    }

    public String getName() {
        return name;
    }

    public String getStatement() {
        return statement;
    }

    public boolean isGuilty() {
        return guilty;
    }
}
