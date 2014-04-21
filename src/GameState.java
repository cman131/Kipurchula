import java.util.ArrayList;

public class GameState {
	public String id;
	public String background;
	public ArrayList<Dialogue> message;
	public String SETID;
	public GameState(String id, String background, ArrayList<Dialogue> text){
		this.id=id;
		SETID=id;
		this.background=background;
		this.message=message;
	}
	public String toString() {
		return SETID;
	}
	public void trimID() {
		char[] ch=SETID.toCharArray();
		SETID="";
		for (int i=1; i<ch.length; i++) {
			SETID+=ch[i];
		}
	}
	public void resetID() {
		SETID=id;
	}
}
