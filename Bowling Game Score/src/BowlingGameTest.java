/** BowlingGameTest 
 *
 * @author CDT414 Student:
 * @version 1.0 
 * @date 2016-11-24
 */
import junit.framework.TestCase;

/** BowlingGame Score calculator test cases 
 *  
 */	 
public class BowlingGameTest extends TestCase {
        
	/** test01 
	 * 	
	 *  If no game is provided, score should be -1 (error)   
	 */	    
	// Not enough frames
	public void test01a() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// To many frames
	public void test01b() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6][2,6][2,6][2,6]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// base case
	public void test02() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(81, bowlingGame.getScore());
    }	
	// strike
	public void test03() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(94, bowlingGame.getScore());
    }	
	// multiple strikes
	public void test04() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(112, bowlingGame.getScore());
    }	
	// spare
	public void test05() {
        BowlingGame bowlingGame = new BowlingGame("[1,9][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(88, bowlingGame.getScore());
    }	
	// multiple spare
	public void test06() {
        BowlingGame bowlingGame = new BowlingGame("[8,2][5,5][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(98, bowlingGame.getScore());
    }	
	// strike followed by spare
	public void test07() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(103, bowlingGame.getScore());
    }	
	// spare in last frame
	public void test08() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,8][7]");
        assertEquals(90, bowlingGame.getScore());
    }	
	// strike in last frame
	public void test09() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][10,0][7,2]");
        assertEquals(92, bowlingGame.getScore());
    }	
	// strike in last bonus frame
	public void test10() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,8][10]");
        assertEquals(93, bowlingGame.getScore());
    }	
	// maximum score
	public void test11() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(300, bowlingGame.getScore());
    }	
	// Negative values
	public void test12() {
        BowlingGame bowlingGame = new BowlingGame("[-10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// to big numbers in frame
	public void test13() {
        BowlingGame bowlingGame = new BowlingGame("[9,9][9,9][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// to big numbers
	public void test14() {
        BowlingGame bowlingGame = new BowlingGame("[90,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// Incorrect value format ( to many parameters )
	public void test15() {
        BowlingGame bowlingGame = new BowlingGame("[8,2,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(-1, bowlingGame.getScore());
    }	
	// Incorrect value format ( to few parameters )
	public void test16() {
        BowlingGame bowlingGame = new BowlingGame("[8][10,0][10][10][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(-1, bowlingGame.getScore());
    }
	// No hits at all
	public void test17() {
        BowlingGame bowlingGame = new BowlingGame("[0,0][0,0][0,0][0,0][0,0][0,0][0,0][0,0][0,0][0,0][0,0]");
        assertEquals(-1, bowlingGame.getScore());
    }
	// Strike with bonus values missing
	public void test18() {
	    BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][10,0]");
	    assertEquals(-1, bowlingGame.getScore());
	}
	// Spare with bonus values missing
	public void test19() {
	    BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][7,3]");
	    assertEquals(-1, bowlingGame.getScore());
	}
	// Two strikes with bonus missing
	public void test20() {
	    BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][10,0][10,0]");
	    assertEquals(-1, bowlingGame.getScore());
	}
	// test if extras are  bigger than 10
	public void test21() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][15,10]");
        assertEquals(-1, bowlingGame.getScore());	
	}

	
	
}