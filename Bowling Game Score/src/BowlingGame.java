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
			if(numbers.length == 1) // extra spare
			{
				rounds.add(new Frame(Integer.parseInt(numbers[0].replace("]", "")),0));
			}
			else
			{
				rounds.add(new Frame(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1].replace("]", ""))));
			}
		}
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		if(!(rounds.size() == 10 || rounds.size() == 11))
		{
			return -1;
		}
		int sum = 0;
		for(int i = 0; i < 10; i++)
		{
			if(rounds.get(i).chanceOne == 10) // strike
			{
				if(i < rounds.size() -1) // make sure next round exists 
				{
					if(i == 9) // round 10
					{
						if(rounds.size() == 11)
						{
							sum += rounds.get(i+1).chanceOne + rounds.get(i+1).ChanceTwo;
						}
					}
					else if(rounds.get(i+1).chanceOne == 10) // strike with next being strike
					{
						if(i < rounds.size() -2)
						{
							sum += rounds.get(i + 2).chanceOne;
						}
						sum += rounds.get(i+1).chanceOne;
					}
					else
					{
						sum += rounds.get(i+1).chanceOne + rounds.get(i+1).ChanceTwo;
					}
				}
				sum += 10;
			}
			else if(rounds.get(i).chanceOne + rounds.get(i).ChanceTwo == 10) // spare
			{
				sum += 10;
				if(i < rounds.size() -1)
				{
					sum += rounds.get(i+1).chanceOne;
				}
			}
			else //total
			{
				sum += rounds.get(i).chanceOne + rounds.get(i).ChanceTwo;
			}
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
