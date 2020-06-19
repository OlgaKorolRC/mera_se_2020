package Lecture_6_2;

public enum BycicleModel {

	NO_MODEL("No Model"),
	KAMA("KAMA"),
	SHKOLNIK("SHKOLNIK"),
	SALUT("SALUT"),
	VELA("VELA");
	
	BycicleModel(String code){
		this.code=code;
	}
	
	public static String getCode(int i) {
		switch (i) {
			case 1:
				return KAMA.code;
			case 2:
				return SHKOLNIK.code;
			case 3:
				return SALUT.code;
			case 4: 
				return VELA.code;
			default:
				return NO_MODEL.code;
				
		}
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;
	
}
