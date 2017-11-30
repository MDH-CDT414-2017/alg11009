import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** BowlingGame Score calculator 
 *
 * @author CDT414 alg11009 - Andreas Ljungberg: 
 * @version 1.1 
 * @date 2017-11-23
 * 
 * Version     DateTime        Initials    Description
 * 1.0		    171123 1600     AL          Initial version
 * 1.1          171125 1900     AL          Added few test cases, fixed errors from new test cases
 */
public class BowlingGame  
{

	private List<Frame> _rounds;
	/** BowlingGame Score calculator constructor which require string as input 
	 * @param game Expected format "[n,n][n,n]..[n,n]"
	 * 
	 */	 
	public BowlingGame(String game)
	{	
		if(game == null)
			return;
		_rounds = new ArrayList<Frame>();
		String[] parts = game.split("\\[");
		//String regex = "^(\\[([0-9],[0-9]|10,0|0,10)\\]){10}((\\[(([0-9],[0-9])|(10,[0-9])|([0-9],10)|(10,10))\\])|(\\[(([0-9])|(10))\\]))?$"; // match with this nice pattern
		String regex = "^(\\[([0-9],[0-9]|10,0|0,10)\\]){9}(((\\[10,0\\])\\[(([0-9],[0-9])|(10,[0-9])|([0-9],10)|(10,10))\\])|(\\[[0-9],([0-9]|(10))\\](\\[(([0-9])|(10))\\])?))$";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(game);
		if(!m.find()) // does it match ? 
		{
			_rounds = null;
			return;
		}
		for(int i = 1; i< parts.length; i++) // parse valid text, regex made sure we will be able to parse it.
		{
			String[] numbers = parts[i].split(",");
			if(numbers.length == 1) // extra spare ( only 1 value )
			{
				_rounds.add(new Frame(Integer.parseInt(numbers[0].replace("]", "")),0));
			}
			else
			{
				int a = Integer.parseInt(numbers[0]);
				int b = Integer.parseInt(numbers[1].replace("]", ""));
				if(a + b > 10 && i != 11) // for a regular frame a and b cannot be larger than 10 ( 10 pins only exists)
				{
					_rounds = null;
					return;
				}
				_rounds.add(new Frame(a,b));
			}
		}
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		if(_rounds == null) // no data / incorrect data
		{
			return -1;
		}
		int sum = 0;
		for(int i = 0; i < 10; i++)
		{
			if(_rounds.get(i).IsStrike()) // strike
			{
				if(i == 9) // round 10 (AKA we got bonus throw frame ( where both might be 10, regular condition would jump over this. ) 
				{
					sum += _rounds.get(i+1).chanceOne + _rounds.get(i+1).chanceTwo;
				}
				else if(_rounds.get(i+1).chanceOne == 10) // strike with next being strike, jump to third frame then
				{
					sum += _rounds.get(i + 2).chanceOne;

					sum += _rounds.get(i+1).chanceOne;
				}
				else // base case where strike didn't follow
				{
					sum += _rounds.get(i+1).chanceOne + _rounds.get(i+1).chanceTwo;
				}
				
				sum += 10; // Add for strike, always a 10 :)
			}
			else if(_rounds.get(i).IsSpare()) // spare
			{
				sum += 10;
				if(i < _rounds.size() -1) // make sure next frame exists
				{
					sum += _rounds.get(i+1).chanceOne;
				}
				else // missing bonus round 
				{
					return -1;
				}
			}
			else //total
			{
				if(i == 9 && _rounds.size() == 11)
					return -1;
				sum += _rounds.get(i).chanceOne + _rounds.get(i).chanceTwo;
			}
		}
		return sum;
	}
	
	public class Frame
	{
		public Frame(int one, int two)
		{
			chanceOne = one;
			chanceTwo = two;
		}
		public int chanceOne, chanceTwo;
		
		public boolean IsStrike()
		{
			return chanceOne ==10;
		}
		
		public boolean IsSpare()
		{
			return (chanceOne != 10) && chanceOne + chanceTwo == 10;
		}
	}
}
