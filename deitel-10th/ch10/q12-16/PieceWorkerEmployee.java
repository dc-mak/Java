// Ex 10.14

public class PieceWorkerEmployee extends Employee {
	private final double wagePerPiece;
	private final int pieces;

	public double getWagePerPiece() { return wagePerPiece; }
	public int getPieces() { return pieces; }

	public PieceWorkerEmployee(String firstName, String lastName,
			String socialSecurityNumber, double wagePerPiece, int pieces) {
		super(firstName, lastName, socialSecurityNumber);

		if (wagePerPiece < 0.0) 
			throw new IllegalArgumentException(
				"Wage per piece must be greater than 0.");

		if (pieces < 0) 
			throw new IllegalArgumentException(
				"Number of pieces sold must be greater or equal than 0.");

		this.wagePerPiece = wagePerPiece;
		this.pieces = pieces;
	}
	
	@Override
	public double earnings() { return wagePerPiece * pieces; }

	@Override
	public String toString() {
		return String.format("%s %s%n%s%.2f%n%s%d",
				"piece worker: ", super.toString(),
				"Wage per piece: ", getWagePerPiece(),
				"Pieces sold: ", getPieces());
	}
}
