package usc.teamfive.helpinghand.datastructures;

//an object representing a login to the database
//the goal is to simplify storing db login info throughout the project
public class DatabaseLogin {

 // private variables
 private String url;
 private String name;
 private String user;
 private String pass;

 // constructor
 public DatabaseLogin(String url, String name, String user, String pass) {
     this.url = url;
     this.name = name;
     this.user = user;
     this.pass = pass;
 }

 public DatabaseLogin() {
     this.url = "";
     this.name = "";
     this.user = "";
     this.pass = "";
 }

 // getters
 public String getUrl() {
     return this.url;
 }

 public String getName() {
     return this.name;
 }

 public String getUser() {
     return this.user;
 }

 public String getPass() {
     return this.pass;
 }

 // setters
 public void setUrl(String url) {
     this.url = url;
 }

 public void setName(String name) {
     this.name = name;
 }

 public void setUser(String user) {
     this.user = user;
 }

 public void setPass(String pass) {
     this.pass = pass;
 }

 // functions
 public String getLogin() {
     return url + name + "?user=" + user + "&password=" + pass;
 }

}
