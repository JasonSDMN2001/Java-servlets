package previousResources;

public class User {
    protected String username;
    protected String name ;
    protected String surname;
    protected String password;
    protected String address;
    protected long telephone;
    public static int usersCounter = 0;
    //���������� getters - setters ��� ��� ����������




    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public long getTelephone(){
        return telephone;
    }
    public void setTelephone(long telephone){
        this.telephone = telephone;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean Login(){ //�������������� ������� ��� login
        return true;
    }
    public boolean Logout(){ //�������������� ������� ��� logout
        return true;
    }

    public User(String user,String pass){
        username=user;
        password=pass;
        usersCounter++;
    } //constructor ���� ��������� ��� ������� �������������� ��������� ���� 1 �� ��� ���������� ���������� user.
}