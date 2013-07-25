import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;



import twitter4j.TwitterException;



public class Htmlpagecreator {

	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("done");

	}

	public static PrintWriter htmlPage(Set<String> modelTrends, String makeInput, String modelInput) throws FileNotFoundException, TwitterException {



		PrintWriter pw = new PrintWriter(new FileOutputStream("trends.html"));



		imageGet img = new imageGet();

		ArrayList<String> users= TwitterCar.users(makeInput, modelInput);
		ArrayList<String> tweets= TwitterCar.tweets(makeInput, modelInput);
		ArrayList<String> links= TwitterCar.links(makeInput, modelInput);
		String Make = makeInput;
		String Doctype = "<!DOCTYPE html>";
		String HTMLcode = "<html>\n<body>\n";		
		String Style = "<style>body{background-color:LightBlue;}h1{color:DarkBlue;text-align:center;}h2{font-family:'Cambria';font-size:20px;}h3{color:DarkGreen;font-family:'Cambria';font-size:25px;}h4{color:Red;font-family:'Cambria';font-size:20px;}p{font-family:'Cambria';font-size:18px;}img.normal{max-height:600px;min-height:350px}</style>";
		String Header = "<h1>What is trending on Twitter?</h2>\n<hr>";
		String MakeHeaderBody = "<h2>Make: " + Make + "</h2>";
		String Image = "<img class='normal' src=" + "\"" + img.getImageLinks(makeInput, modelInput)[0] + "\"" + ">";
		String Close = "</body>\n</html>";



		pw.println(Doctype);
		pw.println(HTMLcode);
		pw.println(Style);
		pw.println(Header);
		pw.println(MakeHeaderBody);
		String ModelsBody = "<h3>Model: " + modelInput + "</h2>";
		pw.println(ModelsBody);

		String[] trends = modelTrends.toArray(new String[modelTrends.size()]);

		String ModelsTrendsBody1 = "<p>\n <h4>Trends</h4> <br>";
		pw.println(ModelsTrendsBody1);
		for(String trend : trends)
		{
			String ModelsTrendsBody2 = "<a href=\"http://www.twitter.com/search?q=%23" + trend.substring(1) + "&src=typd\">" + trend + "</a>";
			pw.println(ModelsTrendsBody2);
		}
		String ModelsTrendsBody3 = "</p>";
		pw.println(ModelsTrendsBody3);
		String ModelsTweetsBody1 = "<p>\n <h4>Tweets</h4> <br>";
		pw.println(ModelsTweetsBody1);
		for(int i=0; i<3; i++)
		{
			String ModelsTweetsBody2 = "&bull;" + "<a href=\"" + "http://www.twitter.com/" + users.get(i) + "\">" + "@" + users.get(i) + "</a>" + ": " + tweets.get(i) + "     " + "<a href=\"" + links.get(i) + "\">Link</a><br><br>";

			pw.println(ModelsTweetsBody2);
		}
		String ModelsTweetsBody3 = "</p>";
		pw.println(ModelsTweetsBody3);
		pw.println(Image);
		pw.println(Close);
		pw.close();







		System.out.println("Done!");
		return pw;

	}


}
