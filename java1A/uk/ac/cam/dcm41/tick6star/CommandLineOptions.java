package uk.ac.cam.dcm41.tick6star;

public class CommandLineOptions {

	public static String WORLD_TYPE_LONG = "--long";
	public static String WORLD_TYPE_AGING = "--aging";
	public static String WORLD_TYPE_ARRAY = "--array";
	private String worldType = null;
	private Integer index = null;
	private String source = null;

	public CommandLineOptions(String[] args) throws CommandLineException {
		//args array indices
		int typeArg = -1;
		int sourceArg = -1;	
		int patArg = -1;

		if (args.length == 0) {
			throw new CommandLineException("Error: no arguments found");
		} else if (args.length == 1) {
			sourceArg = 0;
		} else if (args.length == 2) {
			sourceArg = 0;
			patArg = 1;
		} else { //args.lenghth >= 3
			typeArg = 0;
			sourceArg = 1;
			patArg = 2;
		}
		
		if (patArg != -1) {
			try {
				index = Integer.parseInt(args[patArg]);
			} catch (NumberFormatException nfe) {
				if (args.length == 2) { //If second argument is not a number,
					patArg = -1;		//assume first argument is a worldType
					typeArg = 0;		//and second is source.
					sourceArg = 1;
				} else { //args.length >= 3
					throw new CommandLineException("Error: third argument must be a valid number.");
				}
			}
		}

		source = args[sourceArg];
		if (typeArg != -1) 
			worldType = args[typeArg];
		else 
			worldType = WORLD_TYPE_ARRAY;
	}

	public String getWorldType() {return worldType;}
	public Integer getIndex() {return index;}
	public String getSource() {return source;}
}
