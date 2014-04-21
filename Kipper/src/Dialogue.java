
public class Dialogue {

	public String speaker;
	public String text;
	public String actor;
	public Dialogue(String say, String message) {
		speaker=say;
		if (speaker.equals("")){
			text = message;
		}
		else {
			text="\""+message+"\"";
		}
	}
	public Dialogue(String say, String message, String actor) {
		speaker=say;
		this.actor=actor;
		if (speaker.equals("")){
			text = message;
		}
		else {
			text="\""+message+"\"";
		}
	}
}