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
package cloudcomputebot.Input;

import cloudcomputebot.CloudComputeBot;
import fractalrender.ImageCreator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author director
 */
public class Decipher {

    static String[] validCmds = new String[]{
        "fractal:",
        "fractal",
        "expression: "
    };

    public static void handleInput(Status status, User sender) throws FileNotFoundException, IOException, TwitterException {
        String[] lines = argsNoComments(status.getText());
        if (lines.length > 0) {
            String cmd = lines[0];
            if (cmd.equalsIgnoreCase(validCmds[0]) || cmd.equalsIgnoreCase(validCmds[1])) {
                String[] fractalInputStream = new String[lines.length - 1];
                int c = 0;
                for (int i = 1; i < lines.length; i++) {
                    fractalInputStream[c] = lines[i];
                    c++;                    
                }
                ImageCreator fractalCreator = new ImageCreator();
                fractalCreator.setArgsForTwitter(fractalInputStream);
                BufferedImage fractal = fractalCreator.drawFractal();
                StatusUpdate retweet = new StatusUpdate("@" + sender.getScreenName());
                File img = new File("image.gif");
                ImageIO.write(fractal, "gif", img);
                retweet.setMedia(img);
                CloudComputeBot.t.updateStatus(retweet);
            }
        }
    }

    public static String[] argsNoComments(String input) {
        String[] seperateLines = input.split("[,\\s]+");
        List<String> linesNoComments = new ArrayList<>();
        for (String line : seperateLines) {
            if (!(line.contains("#") || line.contains("//") || line.contains("@"))) { //if there is no comment or hashtag
                linesNoComments.add(line);
            }
        }
        String[] result = new String[linesNoComments.size()];
        int c = 0;
        for (String s : linesNoComments) {
            result[c] = s;
            c++;
        }
        return result;
    }
}
