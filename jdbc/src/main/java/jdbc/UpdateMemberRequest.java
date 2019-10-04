package jdbc;

public class UpdateMemberRequest {
    private String name;
    private Long idTeam;

    public UpdateMemberRequest(final String name, final Long idTeam) {
        this.name = name;
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public Long getIdTeam() {
        return idTeam;
    }
}
