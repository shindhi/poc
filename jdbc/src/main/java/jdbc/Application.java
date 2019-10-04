package jdbc;

public class Application {
    public static void main(String[] args) {
        final MemberDAO memberDAO = new MemberDAO();

        System.out.println(memberDAO.findAll());

        memberDAO.findById(4L).ifPresent(System.out::println);

        memberDAO.delete(4L);

        memberDAO.findById(4L).ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
    }
}
