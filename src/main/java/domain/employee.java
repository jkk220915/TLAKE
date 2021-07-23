package domain;

import java.util.Objects;

public class employee {

    private String name;
    private String EmployeeId;
    private String password;
    private String gender;
    private int age;
    private boolean isAdmin;
    private String status;

    public employee() {
    }

    public employee(String name, String employeeId, String password, String gender, int age, boolean isAdmin, String status) {
        this.name = name;
        EmployeeId = employeeId;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        employee employee = (employee) o;
        return age == employee.age &&
                isAdmin == employee.isAdmin &&
                Objects.equals(name, employee.name) &&
                Objects.equals(EmployeeId, employee.EmployeeId) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(status, employee.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, EmployeeId, password, gender, age, isAdmin, status);
    }

    @Override
    public String toString() {
        return "employee{" +
                "name='" + name + '\'' +
                ", EmployeeId='" + EmployeeId + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", status='" + status + '\'' +
                '}';
    }
}
