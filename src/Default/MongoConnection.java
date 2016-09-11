package Default;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import Default.CoreNLP;



import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.JsonArray;

import org.json.simple.JSONArray;

import static java.util.concurrent.TimeUnit.SECONDS;


public class MongoConnection {
	
	
	public static JSONArray GetTweetsFromDb(){
	// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
	// if it's a member of a replica set:

	// or
	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

	DB db = mongoClient.getDB( "tweetdb" );
	DBCollection table = db.getCollection("tweets");
	
	
	
	//BasicDBObject searchQuery = new BasicDBObject();
	DBCursor cursor = table.find();
	//StringBuilder sb = new StringBuilder();
	JSONArray list = new JSONArray();
	
	CoreNLP m_CoreNLP = new CoreNLP();
	
	while (cursor.hasNext()) {
		
		 DBObject resultElement = cursor.next();
		  Map resultElementMap = resultElement.toMap();
		 // System.out.println(resultElementMap);
		  resultElementMap.remove("_id");
		  
		  String text = cursor.curr().get("text").toString();
		  String feeling = m_CoreNLP.ProccessData(text);
		  resultElementMap.put("sentiment", feeling);
		  
		  
		 list.add(resultElementMap);
		//System.out.println(cursor.next());
		
	
		
	
	}
	mongoClient.close();
	return list;
	}
}
