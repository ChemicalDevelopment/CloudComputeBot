/* 
 * Copyright (C) 2016 ChemicalDevelopment
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cloudcomputebot.Twitter;

/**
 * This file is used for connecting to a twitter account and tweeting, retweeting, and mimicing all actions associated with twitter.
 *
 * @author brown
 */
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterLib {

    //The main variable, that we do everything with.
    public static Twitter t;

    /*
    Initializes CloudComputeBot's login 
    (https://twitter.com/CloudComputeBot)
     */
    public static void init() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("yZpAVykzcxwyohWOVBwuFaaaB")
                .setOAuthConsumerSecret("tXZQzYPeh1YmxGUPYotaKFoeLKeI6m8dAPgPh60l4mSltmMpxw")
                .setOAuthAccessToken("4741197613-i0WRUBMg1oGM2JR6GWnAztKs60u8lhMBhlr8TXD")
                .setOAuthAccessTokenSecret("H4EMPCNPV0NkTom3aUZB8J2uOaWnYHC28vxyTZ1bCvpd6");
        TwitterFactory tf = new TwitterFactory(cb.build());
        t = tf.getInstance();
    }

    /*
    Tweets 's', unless is it larger than a tweet
     */
    public static void tweet(String s) throws TwitterException {
        t.updateStatus(s);
    }
    
    /*
    Returns mentions of you
    */
    public static List<Status> getMentions() throws TwitterException {
        return t.getMentionsTimeline();
    }
}
