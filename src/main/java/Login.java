public class Login {
    String userName;
    String passWord;


    public Login(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return userName + " " + passWord;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Login){
            Login that = (Login)obj;
            if(this.userName == that.userName && this.passWord == that.passWord){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
