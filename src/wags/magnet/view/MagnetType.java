package wags.magnet.view;

public enum MagnetType {
	LOOP ("loop"),
	CONDITIONAL ("conditional"),
	RETURN ("return"),
	DECLARATION ("declaration"),
	ASSORTED ("assorted"),
	FUNCTION ("function"),
	MAIN ("main"),
	FACT ("fact"),
	RULE ("rule"),
	TERM ("term");
	
	private String key;

	MagnetType(String key){
		this.key = key;
	}
	
	@Override
	public String toString() {
		return key;
	}
}
