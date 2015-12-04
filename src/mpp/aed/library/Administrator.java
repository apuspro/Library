package mpp.aed.library;

public class Administrator extends User {

    /**
     *
     */
    private static final long serialVersionUID = 4572691115977442776L;

    public Administrator(String username, String password) {
        super(username, password);
    }
    
    @Override
	public String toString(){
		return String.format("|%15s|","Administrator")+String.format("%15s|",this.getUsername())+String.format("%15s|\n",this.getPassword());
	}
}
