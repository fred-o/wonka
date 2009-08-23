package wonka.html.tag;

import junit.framework.*;

public class TableBuilderTest extends TestCase {
    
	public void testBuildEmptyTable() throws Exception {
	    TableBuilder b = new TableBuilder();
	}

	public void testBuildTableWithHeader() throws Exception {
	    TableBuilder b = new TableBuilder().header(new String[] { "I", "CAN", "HAS", "CHEEZBURGER", "?" });
	}

	public void testBuildTableWithOneRow() throws Exception {
	    TableBuilder b = new TableBuilder().row(new String[] { "I", "CAN", "HAS", "CHEEZBURGER", "?" });
	}

	public void testBuildTableWithSeveralRows() throws Exception {
	    TableBuilder b = new TableBuilder().rows(
			new String[][] {
				new String[] { "I", "CAN" },
				new String[] { "HAS", "CHEEZBURGER" }
			});
	}
	
}