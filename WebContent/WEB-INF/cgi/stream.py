#!/usr/bin/env python
print "Content-type: html/text"
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy.parsers import *
from tweepy import Stream, API
import json
from pymongo import MongoClient
import argparse
import ConfigParser
import cgi
from bson import BSON
from bson import json_util
from json.encoder import JSONEncoder
from bson import json_util, ObjectId
import json
import bson
import sys
#print "Content-type: text/html\n\n";

# Authentication details. To  obtain these visit dev.twitter.com
consumer_key = 'Nt8m0JNpwT2jbLrjOX6QZ9hgB'
consumer_secret = 'HiTC8UsCSI3dl2SLgPqaTVjFs1MAPg4hcfTX91dWVYdr9TlvQ4'
access_token = '554916835-DHwDxo721CzwhzW7Yoe1VV8eimVSCLML6HZNhEvn'
access_token_secret = 'sbr9r4UBMjrTT9ZFwGl2NUi3S1eOp47EHxRtyLN9sobtH'
language = 'en'
keywords = sys.argv[1]
#keywords = 'baby'
print keywords



client = MongoClient('localhost', 27017)
db = client.tweetdb
posts = db.tweets
posts.drop();

class TweetStreamListener(StreamListener):
    """ A listener that handles the  received tweets
        using on_status method"""
    
    
    
                
                
    def save(self, status):
        count = posts.count()
        user_json = status.user.__getstate__()
        user_json['created_at'] = str(status.user.created_at)
        user_json['type'] = 'user'

        place_json = None
        if status.place != None:
            place_json = status.place.__getstate__()
            if status.place.bounding_box != None:
                place_json['bounding_box'] = \
                    status.place.bounding_box.__getstate__()

        doc = {
            'type': 'Tweet',
            'contributors': status.contributors,
            'coordinates': status.coordinates,
            'created_at': str(status.created_at),
            'entities': status.entities,
            'favorite_count': status.favorite_count,
            'favorited': status.favorited,
            'geo': status.geo,
            'id': status.id,
            
            'id_str': status.id_str,
            'in_reply_to_screen_name': status.in_reply_to_screen_name,
            'in_reply_to_status_id': status.in_reply_to_status_id,
            'in_reply_to_status_id_str': status.in_reply_to_status_id_str,
            'in_reply_to_user_id': status.in_reply_to_user_id,
            'in_reply_to_user_id_str': status.in_reply_to_user_id_str,
            'lang': status.lang,
            'place': place_json,
            'retweet_count': status.retweet_count,
            'retweeted': status.retweeted,
            'source': status.source,
            'source_url': status.source_url,
            'text': status.text,
            'truncated': status.truncated,
            'user': user_json['name'],
            'location' : user_json['location'],
            

            }
                    #print doc
                    #print status.user.__getstate__()

        if count > 10: 
                #print "exiting now..!!"
                sys.exit()
                sys.stdout.flush()
        posts.insert(doc)
       
        #print doc['text'].encode('ascii', errors='ignore').strip()
        
                    
        
        
    
    
    def on_status(self, status):
        """this method will handle and parse recieved tweets"""
        
        found = posts.find_one({'id': status.id})

        if hasattr(status, 'lang') and status.lang == language \
            and found == None:
            self.save(status)
        return True

        
def track(words):
    '''Get tweets that include the keywords specified by the user: 
    <word1> <word2>..'''
    listener = TweetStreamListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)

    stream = Stream(auth, listener)

    stream.filter(track=[keywords])
    

      

if __name__ == '__main__':
    track(keywords)
    sys.exit(0)   #stop python script 