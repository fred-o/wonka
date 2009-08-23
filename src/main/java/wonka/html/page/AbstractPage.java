package wonka.html.page;

public abstract class AbstractPage implements Page {
	private Preamble preamble;

	protected AbstractPage(Preamble preamble) {
		this.preamble = preamble;
	}

	public Preamble getPreamble() {
		return this.preamble;
	}
}
