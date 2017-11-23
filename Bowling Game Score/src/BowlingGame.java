import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String regex = "^(\\[([0-9],[0-9]|10,0|0,10)\\]){10}((\\[(([0-9],[0-9])|(10,0)|(0,10)|(10,10))\\])|(\\[(([0-9])|(10))\\]))?$";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(game);
		if(!m.find())
		{
			rounds = null;
			return;
		}
		for(int i = 1; i< parts.length; i++)
		{
			String[] numbers = parts[i].split(",");
			if(numbers.length == 1) // extra spare
			{
				rounds.add(new Frame(Integer.parseInt(numbers[0].replace("]", "")),0));
			}
			else
			{
				int a = Integer.parseInt(numbers[0]);
				int b = Integer.parseInt(numbers[1].replace("]", ""));
				if(i == 11)
				{
					if(a > 10 || b > 10)
					{
						rounds = null;
						return;
					}
				}
				else if(a + b > 10)
				{
					rounds = null;
					return;
				}
				rounds.add(new Frame(a,b));
			}
		}
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		if(rounds == null)
		{
			return -1;
		}
		int sum = 0;
		for(int i = 0; i < 10; i++)
		{
			if(rounds.get(i).IsStrike()) // strike
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
			else if(rounds.get(i).IsSpare()) // spare
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
		
		public boolean IsStrike()
		{
			return chanceOne ==10;
		}
		
		public boolean IsSpare()
		{
			if(chanceOne == 10)
				return false;
			
			return chanceOne + ChanceTwo == 10;
		}
	}
}
