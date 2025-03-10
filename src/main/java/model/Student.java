package model;


public class Student {

    private int s_id;
    private String email;
    private String password;
    private String name;
    private int c_id;
    private int gender;

    public Student() {
    }

    public Student(int s_id, String email, String password, String name, int c_id, int gender) {
        this.s_id = s_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.c_id = c_id;
        this.gender = gender;
    }
    public Student(String name, String email, String password, int gender, int c_id) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.c_id = c_id;
        this.gender = gender;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }
}
