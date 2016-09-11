package Default;

import java.util.List;
import java.util.Properties;



import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/*
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
*/


public class CoreNLP {
	

	private StanfordCoreNLP m_pipeline = null;
	
	public CoreNLP(){
		Properties props = new Properties();
		props.setProperty("annotators", /*" pos, lemma, ner,dcoref,*/"tokenize, ssplit, parse,  sentiment");
		m_pipeline = new StanfordCoreNLP(props);
	}
	
	public String ProccessData(String data ){
		
		
		Annotation annotation = m_pipeline.process(data);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		StringBuilder sb = new StringBuilder(10000);
		for (CoreMap sentence : sentences) {
			
			String sentiment = sentence.get(SentimentCoreAnnotations.ClassName.class);

			sb.append(sentiment);
			//jsonObj.put("sentiment", sentiment);
			//output = jsonObj.toString();
			//System.out.println(test);
			//System.out.println(sentiment + "\t" + sentence);
            //Tree sentiTree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
			//PrintTree(sentiTree);
			//System.out.println(sentiTree);
	    }
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	/*
	public static void init() {
        pipeline = new StanfordCoreNLP("MyPropFile.properties");
    }
	
	public static int findSentiment(String tweet) {

		int mainSentiment = 0;
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(tweet);
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence
                        .get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }

            }
        }
        return mainSentiment;
    }
	*/
	

}
