package jdbc;

public class Members {
    // variaveis
    private final Long id;
    private final String name;
    private final Long idTeam;

    //Constructor
    public Members(Long id, String name, Long idTeam) {
        this.id = id;
        this.name = name;
        this.idTeam = idTeam;
    }

    @Override
    public String toString() {
        return "Members{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamName='" + idTeam + '\'' +
                '}';
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getIdTeam() {
        return idTeam;
    }
}
