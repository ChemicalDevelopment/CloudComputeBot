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
package cloudcomputebot;

import cloudcomputebot.Twitter.TwitterLib;
import twitter4j.FilterQuery;
import twitter4j.Twitter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 *
 * @author director
 */
public class CloudComputeBot {
    
    public static Twitter t;

    public static void main(String[] args) {
        TwitterLib.init();
        t = TwitterLib.t;
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(t.getConfiguration());
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.follow(new long[]{4741197613L});
        twitterStream.addListener(new MentionListener());
        twitterStream.filter(filterQuery);
    }

}
