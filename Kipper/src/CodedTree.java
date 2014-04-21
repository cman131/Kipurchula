public class CodedTree {
	public CodedTree nextA = null;
	public CodedTree nextB = null;
	public CodedTree nextC = null;
	public CodedTree nextD = null;
	public GameState data;
	public CodedTree(GameState data) {
		this.data=data;
	}
	public CodedTree findPlace(String code) {
		if (code=="") {
			return this;
		}
		else {
			if (code.toCharArray()[0]=='A') {
				return nextA.findPlace(trimcode(code));
			}
			else if (code.toCharArray()[0]=='B') {
				return nextB.findPlace(trimcode(code));
			}
			else if (code.toCharArray()[0]=='C') {
				return nextC.findPlace(trimcode(code));
			}
			else {
				return nextD.findPlace(trimcode(code));
			}
		}
	}
	public String trimcode(String code) {
		char[] ch = code.toCharArray();
		String changer = "";
		for (int i=1; i<ch.length; i++) {
			changer+=ch[i];
		}
		return changer;
	}
	public void add(GameState adder) {
		if (data == null) {
			data=adder;
		}
		else {
			if (adder.toString().toCharArray()[0]=='A') {
				if (nextA != null) {
					adder.trimID();
					nextA.add(adder);
				}
				else {
					adder.resetID();
					nextA=new CodedTree(adder);
				}
			}
			else if (adder.toString().toCharArray()[0]=='B') {
				if (nextB != null) {
					adder.trimID();
					nextB.add(adder);
				}
				else {
					adder.resetID();
					nextB=new CodedTree(adder);
				}
			}
			else if (adder.toString().toCharArray()[0]=='C') {
				if (nextC != null) {
					adder.trimID();
					nextC.add(adder);
				}
				else {
					adder.resetID();
					nextC=new CodedTree(adder);
				}
			}
			else if (adder.toString().toCharArray()[0]=='D') {
				if (nextD != null) {
					adder.trimID();
					nextD.add(adder);
				}
				else {
					adder.resetID();
					nextD=new CodedTree(adder);
				}
			}
		}
	}
}
