/*
 * Copyright (c) 2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.language;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * CC class stole from Dream (this is the only code i've stolen, please have mercy)
 */
@SuppressWarnings({"unused"})
public class CC {
    public static String blue = ChatColor.BLUE.toString(),
            aqua = ChatColor.AQUA.toString(),
            yellow = ChatColor.YELLOW.toString(),
            red = ChatColor.RED.toString(),
            gray = ChatColor.GRAY.toString(),
            gold = ChatColor.GOLD.toString(),
            green = ChatColor.GREEN.toString(),
            white = ChatColor.WHITE.toString(),
            black = ChatColor.BLACK.toString();
    public static String
            darkBlue = ChatColor.DARK_BLUE.toString(),
            darkAqua = ChatColor.DARK_AQUA.toString(),
            darkGray = ChatColor.DARK_GRAY.toString(),
            darkGreen = ChatColor.DARK_GREEN.toString(),
            darkPurple = ChatColor.DARK_PURPLE.toString(),
            darkRed = ChatColor.DARK_RED.toString();
    public static String
            dBlue = darkBlue,
            dAqua = darkAqua,
            dGray = darkGray,
            dGreen = darkGreen,
            dPurple = darkPurple,
            dRed = darkRed;
    public static String lightPurple = ChatColor.LIGHT_PURPLE.toString();
    public static String lPurple = lightPurple;
    public static String
            bold = ChatColor.BOLD.toString(),
            magic = ChatColor.MAGIC.toString(),
            italic = ChatColor.ITALIC.toString(),
            strikeThrough = ChatColor.STRIKETHROUGH.toString(),
            underLine = ChatColor.UNDERLINE.toString(),
            reset = ChatColor.RESET.toString();
    public static String
            b = bold,
            m = magic,
            i = italic,
            s = strikeThrough,
            r = reset;
    public static String bBlue = blue + b, bAqua = aqua + b, bYellow = yellow + b, bRed = red + b, bGray = gray + b;
    public static String bGold = gold + b, bGreen = green + b, bWhite = white + b, bBlack = black + b;
    public static String bdBlue = dBlue + b, bdAqua = dAqua + b, bdGray = dGray + b, bdGreen = dGreen + b, bdPurple = dPurple + b;
    public static String bdRed = dRed + b;
    public static String blPurple = lPurple + b;
    public static String iBlue = blue + i, iAqua = aqua + i, iYellow = yellow + i, iRed = red + i, iGray = gray + i;
    public static String iGold = gold + i, iGreen = green + i, iWhite = white + i, iBlack = black + i;
    public static String idBlue = dBlue + i, idAqua = dAqua + i, idGray = dGray + i, idGreen = dGreen + i, idPurple = dPurple + i;
    public static String idRed = dRed + i;
    public static String ilPurple = lPurple + i;

    public static String
            peace = "☮", flower = "✿", plane = "✈", sixyNine = "♋", death = "☠", yinYan = "☯", heart = "♥",
            peaceHand = "✌", thickCross = "✖", nuke = "☢", biohazard = "☣", medical = "☤", hollowHeart = "♡",
            thinItalicCross = "✗", ItalicCross = "✘", star = "★", hollowStar = "☆", checkerStar = "✯", leftMoon = "☾",
            rightMoon = "☽", sun = "☼", boldSun = "☀ ", snowman = "☃", singleMusicNote = "♪", doubleMusicNote = "♫",
            phone = "✆", mail = "✉", female = "♂", male = "♀", upTrigle = "▲", downTrigle = "▼", leftTrigle = "◀",
            rightTrigle = "▶", thinItalicTick = "✓", italicTick = "✔",
            smiley1 = "☺", smiley2 = "ت", smiley3 = "ツ", smiley4 = "ッ", smiley5 = "シ", smiley6 = "Ü",
            oneInCircle = "➀", twoInCircle = "②", threeInCircle = "➂", fourInCircle = "➃", fiveInCircle = "➄",
            sixInCircle = "➅", sevenInCircle = "➆ ", eightInCircle = "➇", nineInCircle = "➈", zeroInCircle = "⓪",
            arrow = "➨", bArrow = "➜", checkerArrow = "➣", thickArrow = "➤",
            veryLightShadedBlock = "░", lightShadedBlock = "▒", shadedBlock = "▓", fullyShadedBlock = "█",
            pipeUp = "║", pipeAcross = "═", pipeUpMidRightSplit = "╠", pipeUpMidLeftSplit = "╣",
            pipeTopRight = "╔", pipeBottomRight = "╚", pipeTopLeft = "╗", pipeBottomLeft = "╝";

    public static List<String> wrapString(String str, int lineLength) {
        String[] split = WordUtils.wrap(str, lineLength, null, true).split("\\r\\n");
        String[] fixed = new String[split.length];
        fixed[0] = split[0];
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            String line = split[i];
            String previous = split[i - 1];
            int code = previous.lastIndexOf("§");
            if (code != -1) {
                char cCode = previous.charAt((code == previous.length() - 1) ? code : (code + 1));
                if (code == previous.length() - 1) {
                    if (ChatColor.getByChar(line.charAt(0)) != null) {
                        fixed[i - 1] = previous.substring(0, previous.length() - 1);
                        line = "§" + line;
                        split[i] = line;
                    }
                } else if ((line.length() < 2 || ChatColor.getByChar(line.charAt(1)) == null) &&
                        ChatColor.getByChar(cCode) != null) {
                    line = "§" + cCode + line;
                }
            }
            fixed[i] = line;
            split[i] = line;
            lines.add(fixed[i]);
        }
        return lines;
    }
}
