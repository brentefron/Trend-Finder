import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class imageGet {

	public String[] getImageLinks(String Make, String Model) {
		String searchText = Make + " " + Model;
		searchText = searchText.replaceAll(" ", "+");
		String[] links = new String[1];
		String key="AIzaSyDUmEpoEHSYB-pBKrM_slE2KmTyX_LJGwk";
		String cx = "004334298273927197431:bennqbfbvec";
		URL url;
		try {
			url = new URL(
					"https://www.googleapis.com/customsearch/v1?key="+key+ "&cx="+ cx +"&q="+ searchText + "&searchType=image" + "&alt=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();    
			conn.setRequestMethod("GET");     
			conn.setRequestProperty("Accept", "application/json");     
			BufferedReader br = 
					new BufferedReader(new InputStreamReader( (conn.getInputStream())));      
			String output;     
			System.out.println("Output from Server .... \n");     
			while ((output = br.readLine()) != null) 
			{
				for (int i = 0; i < 1; i++) {
					if(output.contains("\"link\": \"")) {                             
						links[i]=output.substring(output.indexOf("\"link\": \"")+
								("\"link\": \"").length(), output.indexOf("\","));             
						System.out.println(links[i]);       //Will print the google search links         
					}          
				}
			}     
			conn.disconnect();                               

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}
}