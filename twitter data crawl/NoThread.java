package project1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;

public class NoThread {
	public static Long  tweetid=Long.MAX_VALUE;
	public final static String regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";
	public void twit(int current) throws TwitterException, IOException {
		
		String environment = String.join(" OR ","hurricane","air quality","environment","river","floods","carbon","CO2","oxygen","droughts","ecosystem","ecocide","dust storms","beach","tree","clean","smog","weather","pollution","global warming","climate");	
		String crime = String.join(" OR ","crime","robbery","police","corrupt","jail","case","gun","weapon","arrest","murder","terrorist","terrorism","alleg","kill","warrant","trafficking","kidnap","accuse","drugs","illegal","rape","atrocaty");
		String politics = String.join(" OR ","politics","BJP","government","election","war","assembly","congress","independence","hilary","modi","donald","trump","obama","council","senate","president","brexit","power");
		String socialunrest = String.join(" OR ","social unrest","strikes","protests","trouble","blast","rally","bomb","assault","riots","disorder","chaos","turmoil","uproar","quarrel","disturbance","earthquake","volcano");
		String infra = String.join(" OR ","infra","roads","power","water","sanitation","towers","frameworks","resource","smartcity","health","transportation","services","bridge","ground work","building","dams","root");
		//	String city = String.join(" OR ","nyc","delhi","bangkok","paris","mexico city");
		//String lan = String.join(" OR ","English","Hindi","Thai","French","Spanish");
			
		String[] all = new String[] {infra,environment,crime,politics,socialunrest};
		String[] topic=new String[] {"infra","environment","crime","politics","social unrest"};
		String lang[]=new String[]{"en","hi","fr","th","es"};
		String day[]=new String[]{"16","15","14","13","18","17","19","11","12","20"};
		Double latitude[]=new Double[]{40.60817,28.7041,13.7563,48.85993,19.4326};
		Double longitude[]=new Double[]{-73.98627,77.1025,100.5018,2.34146,-99.1332};
		//int randomnumber=(int)(Math.random()*5);
		String city[]=new String[]{"nyc","delhi","bangkok","paris","mexico city"};
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		String consumer_key= "S3r7xQHQFL9KSJ19YkBv6nplx";
		String consumer_secret="kRyXOT29lCiF0Ana5t9R88z4qdHlDYelzwnd5s5w6Jf46p9Fl0";
		String access_token="1038150894649585664-1r76iswLPYPnMGW9eRKdc2bHTDXNmh";
		String access_secret="3nJzH24RngyMFZXPVkgFBeJ6Ff7OMWNU2xB7HiuD1foEU";
		cb.setDebugEnabled(true);
		cb.setJSONStoreEnabled(true);
		cb.setOAuthConsumerKey(consumer_key);
		cb.setOAuthConsumerSecret(consumer_secret);
		cb.setOAuthAccessToken(access_token);
		cb.setOAuthAccessTokenSecret(access_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		//int current=(int)(Math.random()*5);
	//	Query query = new Query(all[current]+" -RT");
	//	Query query = new Query("environment OR social unrest OR hurricane OR river OR terror OR blast OR tree OR strike OR turmoil OR bomb OR protest OR flood -RT");		
		Query query = new Query("environment OR bio OR swach OR global OR oxygen OR air OR ocean OR sea OR co2 OR carbon OR tree OR bio OR swach OR global OR oxygen -RT");
		//System.out.println("\nArray topic"+all[current]);
		//Query query=new Query("पॉलिटिक्स");
		int rand=(int)(Math.random()*5);
		query.setLang(lang[rand]);	
		//query.setLang("th");
		query.setSince("2018-09-17");
		query.setUntil("2018-09-21");
		//query.setMaxId(tweetid);
		query.setCount(100);//100
		
		//query.setGeoCode(new GeoLocation(latitude[randomnumber], longitude[randomnumber]), 800, Query.MILES);
		QueryResult result = twitter.search(query);
		//topic[current]
		FileWriter file = new FileWriter("C:\\Users\\sai\\Desktop\\JSONFILES\\today.json",true);
		//FileOutputStream file = new FileOutputStream("C:\\Users\\sai\\Desktop\\JSONFILES\\"+topic[current]+".json",true);
		//FileOutputStream file = new FileOutputStream("C:\\Users\\sai\\Desktop\\JSONFILES\\"+all[current]+".json",true);
		//OutputStreamWriter out=new OutputStreamWriter(file,"UTF-8");
		//BufferedWriter buff=new BufferedWriter(out);
		for (Status status : result.getTweets())
		{		
			//if(status.getId()<tweetid)
			//{
				//tweetid=status.getId();
				//System.out.println("Latest Tweetid"+tweetid);
				//}
			
			twitter4j.JSONObject jsonResult = new twitter4j.JSONObject();
		     
			jsonResult.put("topic","environment");
			//city
			int randomnumber=(int)(Math.random()*5);
			jsonResult.put("city"," ");	
			jsonResult.put("id", status.getId());
			jsonResult.put("tweet_loc"," ");
			jsonResult.put("tweet_text",status.getText());
			jsonResult.put("tweet_lang",lang[rand]);
			//tweet_xx
			jsonResult.put("text",status.getText());
			String hashtag=" ";
			for(HashtagEntity he:status.getHashtagEntities())
			{
			hashtag=hashtag+he.getText()+" ";	
			}
			jsonResult.put("hashtags",hashtag);
			String usermentionentities=" ";
			for(UserMentionEntity usm:status.getUserMentionEntities())
			{
			usermentionentities=usermentionentities+usm.getText()+" ";
			}
			jsonResult.put("mentions",usermentionentities);
			String url=" ";
			for(URLEntity u:status.getURLEntities())
			{
				url=url+u.getText()+" ";
			}
			jsonResult.put("tweet_urls",url);
			//emoticons
			String emoji=" ";
			Matcher matchEmo = Pattern.compile(regex).matcher(status.getText());
			while (matchEmo.find()) {
			    emoji+=matchEmo.group()+" ";
			}
			jsonResult.put("tweet_emoticons",emoji);
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			String date=sdf.format(status.getCreatedAt());
		    jsonResult.put("tweet_date", date);
		    //location
			
		 // OutputStreamWriter out=new OutputStreamWriter(file,"UTF-8");
		//	BufferedWriter buff=new BufferedWriter(out);
		//	buff.append(jsonResult+"\n\n");
			file.append(jsonResult+"\n\n");
					}
		System.out.println("\nResult count"+result.getCount());
		file.close();
		
	}
	public static void main(String[] args) {
		
		NoThread obj=new NoThread();
		int count=1;
		int arrayval=0;
		System.out.println("Tweetid"+tweetid+"\n");
		try
		{	while(true)
			{
			if(count==181) //181
				{
				count=1;
				System.out.println("Latest Tweetid"+tweetid);
				Thread.sleep(960000);
				}
			if(arrayval>4)//topics count 4
			{ 
				arrayval=0;
			}
			obj.twit(arrayval);
			//System.out.println("\narray topic"+arrayval);
			arrayval++;
			System.out.println("\nTwitter Hits count"+count);
			count++;
			
			}
		}
		catch(Exception e)
		{	
			try
			{
System.out.println("err");
				Thread.sleep(960000);
				NoThread.main(null);
			}
			catch(InterruptedException el)
			{
				el.printStackTrace();
			}
			
			
			e.printStackTrace();
			
		}
	}
}
