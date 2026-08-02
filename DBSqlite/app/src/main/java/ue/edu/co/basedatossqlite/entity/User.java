package ue.edu.co.basedatossqlite.entity;

public class User {

    private int document;
    private String names;
    private String lastNames;
    private String user;
    private String password;
    private byte status;

    public User() {
    }
    public User(String password, int document, String names, String lastNames, String user) {
        this.password = password;
        this.document = document;
        this.names = names;
        this.lastNames = lastNames;
        this.user = user;
    }

    public User(byte status, String password, String user, String lastNames, String names, int document) {
        this.status = status;
        this.password = password;
        this.user = user;
        this.lastNames = lastNames;
        this.names = names;
        this.document = document;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("document=").append(document);
        sb.append(", names='").append(names).append('\'');
        sb.append(", lastNames='").append(lastNames).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
