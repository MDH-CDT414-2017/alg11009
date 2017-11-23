import java.util.ArrayList;
import java.util.List;

/** BowlingGame Score calculator 
 *
 * @author CDT414 alg11009 - Andreas Ljungberg: 
 * @version 1.0 
 * @date 2017-11-23
 * 
 * Version	Initials	Time	Description
 * 
 */
public class BowlingGame {


	private List<Frame> rounds;
	/** BowlingGame Score calculator constructor which require string as input 
	 * @param game Expected format "[n,n][n,n]..[n,n]"
	 * 
	 */	 
	public BowlingGame(String game)
	{	
		rounds = new ArrayList<Frame>();
		String[] parts = game.split("\\[");
		
		for(int i = 1; i< parts.length; i++)
		{
			String[] numbers = parts[i].split(",");
			rounds.add(new Frame(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1].replace("]", ""))));
		}
		
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		int sum = 0;
		for(int i = 0; i < rounds.size(); i++)
		{
			sum += rounds.get(i).chanceOne + rounds.get(i).ChanceTwo;
		}
		return sum;
	}
	
	public class Frame
	{
		public Frame(int one, int two)
		{
			chanceOne = one;
			ChanceTwo = two;
		}
		public int chanceOne, ChanceTwo;
	}
}
