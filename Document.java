import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The Document class provides methods to calculate the flesch readablity score of text from user input
 * @author anush
 *
 */
public class Document
{
	/**
	 * Stores text that the user inputs
	 */
	private String text;
	
	/**
	 * Initializes Document class and stores input into text 
	 * @param t Stored in text
	 */
	public Document(String t)
	{
		text = t;
	}

	/**
	 * 
	 * @return text
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Sets text to t
	 * @param t
	 */
	public void setText(String t)
	{
		text = t;
	}
	
	/**
	 * 
	 * @param pattern The string
	 * @return List of words
	 */
	public List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}
	
	/**
	 * Uses regex to split text into an array of words and returns length of array
	 * @return the number of words in text
	 */
	public double getNumWords()
	{
	    if(this.getText().isEmpty())
	    	return 0;
		String [] words = this.getText().split("[^A-Za-z]+");
		return (double)words.length;
	}
	
	/**
	 * Uses regex to split text into an array of sentences and returns length of array
	 * @return The number of sentences in text
	 */
	public double getNumSentences()
	{
		if(this.getText().isEmpty())
	    	return 0;
        String[] sentences = this.getText().split("[.!?]+");
		return (double)sentences.length;
	}
	
	/**
	 * Uses regex to calculate the number of syllables in a given word
	 * @param word 	The word that syllables need to be counted in
	 * @return 		The number of syllables in given word
	 */
	public double countSyllables(String word)
	{
		 if(word.isEmpty())
		    	return 0;
		 String [] syll = word.split("[aeiouy]+");
		 char [] chars = word.toCharArray();
		 double num = (double)syll.length-1;
		 if(chars[chars.length-1] == 'a' || chars[chars.length-1] == 'i' || chars[chars.length-1] == 'o' || chars[chars.length-1] == 'u' || chars[chars.length-1] == 'y')
	     	num++;
		 if(chars[chars.length-1] == 'e' && (chars[chars.length-2] == 'a' || chars[chars.length-2] == 'i' || chars[chars.length-2] == 'o' || chars[chars.length-2] == 'y'))
			 num++;
		 try {
		 if(word.substring(word.length()-3).equals("ble"))
			 num++;
		 }
		 catch(StringIndexOutOfBoundsException e) {};
		 if(num == 0)
			 num++;
		 return num;
	}
	
	/**
	 * Uses regex to split text into an array for word and calls the countSyllables method on each word in array to derive
	 * total number of syllables in text
	 * @return The number of syllables in text
	 */
	public double getNumSyllables()
	{
        String [] words = this.getText().split("[^A-Za-z]+");
		double s = 0;
        for(int i = 0; i < words.length; i++)
		{
			s = s + this.countSyllables(words[i]);
		}
		return s;
	}
	/**
	 * Calls getNumWords, getNumSetences, and getNumSyllables and implements formula to derive flesch readability score of
	 * text
	 * @return the flesch readability score of text
	 */
	public double getFleschScore()
	{
		double f = 206.835 - 1.015*(this.getNumWords()/this.getNumSentences()) - 84.6*(this.getNumSyllables()/this.getNumWords());
	    return f;
	}
	
	/**
	 * 
	 * @param score Flesch readability score used to determine target audience of text
	 * @return A String with information on the target audience of readers that text is most appropriate for based on a
	 * given flesch score
	 */
	public String targetAudience(double score)
	{
		String difficulty = "";
		String schoolLevel = "";
		if(score <= 10.0)
		{
			difficulty = "extremely difficult to read.";
			schoolLevel = "professionals";
		}
		else if(score <= 30.0)
		{
			difficulty = "very difficult to read.";
			schoolLevel = "college graduates";
		}
		else if(score <= 50.0)
		{
			difficulty = "difficult to read.";
			schoolLevel = "college students";
		}
		else if(score <= 60.0)
		{
			difficulty = "fairly difficult to read.";
			schoolLevel = "10th to 12th graders";
		}
		else if(score <= 70.0)
		{
			difficulty = "plain English and easily understood by 13- to 15-year-old students.";
			schoolLevel = "8th to 9th graders";
		}
		else if(score <= 80.0)
		{
			difficulty = "fairly easy to read.";
			schoolLevel = "7th graders";
		}
		else if(score <= 90.0)
		{
			difficulty = "easy to read. It can be conversational English for consumers.";
			schoolLevel = "6th graders";
		}
		else if(score <= 100.0)
		{
			difficulty = "very easy to read. It's easily understood by an average 11-year-old student.";
			schoolLevel = "5th graders";
		}
		else
		{
			return "Your text is very easy to read.";
		}
		String t = "Your text is " + difficulty + " It's most appropriate for " + schoolLevel + ".";
		return t;
	}
	
}
