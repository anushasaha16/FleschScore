import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document
{
	private String text;
	
	public Document(String t)
	{
		text = t;
	}


	public String getText()
	{
		return text;
	}
	
	public void setText(String t)
	{
		text = t;
	}
	
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
	
	public int getNumWords()
	{
	    if(this.getText().isEmpty())
	    	return 0;
		String [] words = this.getText().split("[^A-Za-z]+");
		return words.length;
	}

	public int getNumSentences()
	{
		if(this.getText().isEmpty())
	    	return 0;
        String[] sentences = this.getText().split("[.!?]+");
		return sentences.length;
	}

	public int countSyllables(String word)
	{
		 if(word.isEmpty())
		    	return 0;
		 String [] syll = word.split("[aeiouy]+");
		 char [] chars = word.toCharArray();
		 if(chars[chars.length-1] == 'a' || chars[chars.length-1] == 'e' || chars[chars.length-1] == 'i' || chars[chars.length-1] == 'o' || chars[chars.length-1] == 'u')
	     	return syll.length;

		 return syll.length-1;
	}
	
	public int getNumSyllables()
	{
        String [] words = this.getText().split("[^A-Za-z]+");
		int s = 0;
        for(int i = 0; i < words.length; i++)
		{
			s = s + this.countSyllables(words[i]);
		}
		return s;
	}
	
	public double getFleschScore()
	{
		double f = 206.835 - 1.015*(this.getNumWords()/this.getNumSentences()) - 84.6*(this.getNumSyllables()/this.getNumWords());
	    return f;
	}
	
}
