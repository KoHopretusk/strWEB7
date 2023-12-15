import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String position;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
public class EmployeeServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=employee;integratedSecurity=true;trustServerCertificate=true";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employee")) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("Id"));
                employee.setLastName(rs.getString("Last_Name"));
                employee.setFirstName(rs.getString("First_Name"));
                employee.setMiddleName(rs.getString("Middle_Name"));
                employee.setGender(rs.getString("Gender"));
                employee.setDateOfBirth(rs.getDate("Date_of_Birth"));
                employee.setAddress(rs.getString("Address"));
                employee.setPosition(rs.getString("Position"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("employees.jsp").forward(request, response);
    }
}