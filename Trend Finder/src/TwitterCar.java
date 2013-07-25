import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import twitter4j.*;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterCar {

	private static final String ACCESS_TOKEN = "1560772970-Pf4kcXT71kWxA6dNxUz2QhglB7TXFz8RFNZAFdE";

	private static final String ACCESS_TOKEN_SECRET = "dIwtf8OsEeZUskeVlI1LElARqf4llrtVA2As4sC0gg";

	private static final String CONSUMER_KEY = "3HDbxSe6fK9sFgdPo2ZB1Q";

	private static final String CONSUMER_SECRET = "muVfcXPlkEgBWc9ZHi85V5z9ZwGyW0AnsMyPuxf7CaU";	

	public static void main(String[] args) throws TwitterException {

		System.out.println(twitter("Acura ", "RLX"));
	}

	public static ArrayList<String[]> cars() {

		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost:1234/cars";

		//  Database credentials
		String USER = "root";
		String PASS = "wps14042";

		Set<String> data = new HashSet<String>();
		ArrayList<String[]> data2 = new ArrayList<String[]>();

		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);



			stmt = conn.createStatement();
			String sql = ("SELECT Classification FROM cars");
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String send = rs.getString(1);
				for (int i = 0; i < 24; i++) {
					data.add(send.split("\\| 2")[0].toString());
				}
			}

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

		for (String dat : data) {
			data2.add(dat.replace(" ", "").split("\\|"));
		}

		return data2;

	}
	public static List<Status> tweetslist(String inputMake, String inputModel) throws TwitterException {

		ConfigurationBuilder builder = new ConfigurationBuilder();

		builder.setOAuthAccessToken(ACCESS_TOKEN);

		builder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

		builder.setOAuthConsumerKey(CONSUMER_KEY);

		builder.setOAuthConsumerSecret(CONSUMER_SECRET);

		OAuthAuthorization auth = new OAuthAuthorization(builder.build());

		Twitter twitter = new TwitterFactory().getInstance(auth);



		Query query = new Query(inputMake + " " + inputModel);
		QueryResult result;
		result = twitter.search(query.lang("en").count(100).resultType(Query.RECENT));
		List<Status> tweets = result.getTweets();
		return tweets;
	}

	public static LinkedHashSet<String> twitter(String inputMake, String inputModel) throws TwitterException {


		LinkedHashSet<String> hashtag = new LinkedHashSet<String>();



		List<Status> tweets = tweetslist(inputMake, inputModel);

		if (tweets.toString().contains("#"))
		{ 
			for (Status tweet : tweets) {
				if (tweet.getText().contains("#"))
				{
					if ((regexChecker("#[A-Za-z0-9]{1,}", tweet.getText())) != null)
					{
						hashtag.add(regexChecker("#[A-Za-z0-9]{1,}", tweet.getText()));
					}
					else
						System.out.print("err");
				} 
				else
				{
					System.out.print("");
				}
			}
		}
		else
		{
			hashtag.add("Sorry...no results :(");
		}

		return hashtag;

	}

	public static ArrayList<String> users(String inputMake, String inputModel) throws TwitterException {


		ArrayList<String> hashtag = new ArrayList<String>();
		List<Status> tweets = tweetslist(inputMake, inputModel);

		for (int i = 0; i < 3; i++) {

			hashtag.add(tweets.get(i).getUser().getScreenName());
		}
		return hashtag;
	}
	public static ArrayList<String> tweets(String inputMake, String inputModel) throws TwitterException {


		ArrayList<String> hashtag = new ArrayList<String>();
		List<Status> tweets = tweetslist(inputMake, inputModel);

		for (int i = 0; i < 3; i++) {
			hashtag.add(tweets.get(i).getText());
		}
		return hashtag;
	}

	public static ArrayList<String> links(String inputMake, String inputModel) throws TwitterException {


		ArrayList<String> hashtag = new ArrayList<String>();
		List<Status> tweets = tweetslist(inputMake, inputModel);

		for (int i = 0; i < 3; i++) {
			Status t = tweets.get(i);

			StringBuffer address = new StringBuffer();
			address.append("http://twitter.com/");
			address.append(t.getUser().getScreenName());
			address.append("/statuses/");
			address.append(t.getId());

			String theAddressYouWant = address.toString();
			hashtag.add(theAddressYouWant);

		}
		return hashtag;
	}



	public static String regexChecker(String theRegex, String str2Check) {
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		String idc = null;
		while(regexMatcher.find()) {
			if(regexMatcher.group().length() != 0) {
				idc = (regexMatcher.group().trim());
			} 
			else
				break;
		}
		return idc;

	}
}



