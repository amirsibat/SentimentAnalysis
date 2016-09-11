import os
import sys
import pymongo
import argparse
import ConfigParser
import sys
import cgi
from bson import BSON
from bson import json_util
from json.encoder import JSONEncoder
from bson import json_util, ObjectId
import json
import bson
print "Content-type: application/json"




            
def gettweets():
    connection = pymongo.Connection('mongodb://localhost:27017')
    db = connection['tweetdb']
    #query based on the objectid
    results = db.tweets.find()
    
    print bson.json_util.dumps(results)
    
    
    
    
if __name__ == '__main__':
  gettweets()
  
  