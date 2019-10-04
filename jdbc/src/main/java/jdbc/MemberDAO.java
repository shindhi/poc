package jdbc;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MemberDAO {

    private final DataSource dataSource;

    public MemberDAO() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("diogo");
        dataSource.setPassword("123123");
        dataSource.setDatabaseName("diogo");
        this.dataSource = dataSource;
    }

    public void insert(final Members member) {

        try (
                final Connection connection = dataSource.getConnection(); // Iniciando conexacao
                final PreparedStatement stmp = connection.prepareStatement( // Preparando o comando
                "INSET INTO member (name, id_team) values (?, ?)" //SQl
                );
        ) {
            stmp.setString(1, member.getName());
            stmp.setLong(2, member.getIdTeam());
            stmp.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error insert member");
            throw new RuntimeException(e);
        }

    }

    public List<Members> findAll() {
        try (
                final Connection connection = dataSource.getConnection();
                final Statement stmt = connection.createStatement();
                final ResultSet rs = stmt.executeQuery("SELECT id, name, id_team FROM member");
        ) {
            final List<Members> members = new ArrayList<>();

            while(rs.next()) {
                final Members member = new Members(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("id_team")
                );
                members.add(member);
            }

            return members;
        } catch (SQLException e) {
            System.err.println("Error select members");
            throw new RuntimeException(e);
        }
    }

    public Optional<Members> findById(final Long id) {
        try (
                Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "SELECT id, name, id_team FROM member WHERE id = ?"
                );
        ) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Members(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("id_team")
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.err.println("Error find by id");
            throw new RuntimeException(e);
        }
    }

    public void update(final Long id, final UpdateMemberRequest updateMemberRequest) {
        try (
                final Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE member set name = ?, id_team = ? WHERE id = ?"
                )
        ) {
            stmt.setString(1, updateMemberRequest.getName());
            stmt.setLong(2, updateMemberRequest.getIdTeam());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in update");
            throw new RuntimeException(e);
        }
    }

    public void delete(final Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "DELETE FROM member WHERE id = ?"
                )
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in delete");
            throw new RuntimeException(e);
        }

    }
}
