/*
 * Copyright (c) 2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.language;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ColourPlaceholders {
    @Getter
    private final HashMap<String, String> placeholders;

    public ColourPlaceholders() {
        placeholders = new HashMap<>();

        // Colours
        placeholders.put("blue", CC.blue);

        placeholders.put("aqua", CC.aqua);

        placeholders.put("yellow", CC.yellow);

        placeholders.put("red", CC.red);

        placeholders.put("gray", CC.gray);
        placeholders.put("grey", CC.gray);

        placeholders.put("gold", CC.gold);

        placeholders.put("green", CC.green);

        placeholders.put("white", CC.white);

        placeholders.put("black", CC.black);


        // Dark colours
        placeholders.put("darkblue", CC.darkBlue);
        placeholders.put("dblue", CC.darkBlue);

        placeholders.put("darkaqua", CC.darkAqua);
        placeholders.put("daqua", CC.darkAqua);

        placeholders.put("darkgray", CC.darkGray);
        placeholders.put("dgray", CC.darkGray);
        placeholders.put("darkgrey", CC.darkGray);
        placeholders.put("dgrey", CC.darkGray);

        placeholders.put("darkgreen", CC.darkGreen);
        placeholders.put("dgreen", CC.darkGreen);

        placeholders.put("darkpurple", CC.darkPurple);
        placeholders.put("dpurple", CC.darkPurple);

        placeholders.put("darkred", CC.darkRed);
        placeholders.put("dred", CC.darkRed);


        // Light colours
        placeholders.put("lightpurple", CC.lightPurple);
        placeholders.put("lpurple", CC.lightPurple);


        // Formatting codes
        placeholders.put("bold", CC.bold);
        placeholders.put("b", CC.bold);

        placeholders.put("magic", CC.magic);
        placeholders.put("m", CC.magic);

        placeholders.put("italic", CC.italic);
        placeholders.put("i", CC.italic);

        placeholders.put("strikethrough", CC.strikeThrough);
        placeholders.put("s", CC.strikeThrough);

        placeholders.put("reset", CC.reset);
        placeholders.put("r", CC.reset);


        // Bold colours
        placeholders.put("boldblue", CC.bBlue);
        placeholders.put("bblue", CC.bBlue);

        placeholders.put("boldaqua", CC.bAqua);
        placeholders.put("baqua", CC.bAqua);

        placeholders.put("boldyellow", CC.bYellow);
        placeholders.put("byellow", CC.bYellow);

        placeholders.put("boldred", CC.bRed);
        placeholders.put("bred", CC.bRed);

        placeholders.put("boldgray", CC.bGray);
        placeholders.put("bgray", CC.bGray);
        placeholders.put("boldgrey", CC.bGray);
        placeholders.put("bgrey", CC.bGray);

        placeholders.put("boldgold", CC.bGold);
        placeholders.put("bgold", CC.bGold);

        placeholders.put("boldgreen", CC.bGreen);
        placeholders.put("bgreen", CC.bGreen);

        placeholders.put("boldwhite", CC.bWhite);
        placeholders.put("bwhite", CC.bWhite);

        placeholders.put("boldblack", CC.bBlack);
        placeholders.put("bblack", CC.bBlack);

        placeholders.put("bolddarkblue", CC.bdBlue);
        placeholders.put("bdarkblue", CC.bdBlue);
        placeholders.put("bdblue", CC.bdBlue);

        placeholders.put("bolddarkaqua", CC.bdAqua);
        placeholders.put("bdarkaqua", CC.bdAqua);
        placeholders.put("bdaqua", CC.bdAqua);

        placeholders.put("bolddarkgray", CC.bdGray);
        placeholders.put("bdarkgray", CC.bdGray);
        placeholders.put("bdgray", CC.bdGray);
        placeholders.put("bolddarkgrey", CC.bdGray);
        placeholders.put("bdarkgrey", CC.bdGray);
        placeholders.put("bdgrey", CC.bdGray);

        placeholders.put("bolddarkgreen", CC.bdGreen);
        placeholders.put("bdarkgreen", CC.bdGreen);
        placeholders.put("bdgreen", CC.bdGreen);

        placeholders.put("bolddarkpurple", CC.bdPurple);
        placeholders.put("bdarkpurple", CC.bdPurple);
        placeholders.put("bdpurple", CC.bdPurple);

        placeholders.put("bolddarkred", CC.bdRed);
        placeholders.put("bdarkred", CC.bdRed);
        placeholders.put("bdred", CC.bdRed);

        placeholders.put("boldlightpurple", CC.blPurple);
        placeholders.put("blightpurple", CC.blPurple);
        placeholders.put("blpurple", CC.blPurple);


        // Italic colours
        placeholders.put("italicblue", CC.iBlue);
        placeholders.put("iblue", CC.iBlue);

        placeholders.put("italicaqua", CC.iAqua);
        placeholders.put("iaqua", CC.iAqua);

        placeholders.put("italicyellow", CC.iYellow);
        placeholders.put("iyellow", CC.iYellow);

        placeholders.put("italicred", CC.iRed);
        placeholders.put("ired", CC.iRed);

        placeholders.put("italicgray", CC.iGray);
        placeholders.put("igray", CC.iGray);
        placeholders.put("italicgrey", CC.iGray);
        placeholders.put("igrey", CC.iGray);

        placeholders.put("italicgold", CC.iGold);
        placeholders.put("igold", CC.iGold);

        placeholders.put("italicgreen", CC.iGreen);
        placeholders.put("igreen", CC.iGreen);

        placeholders.put("italicwhite", CC.iWhite);
        placeholders.put("iwhite", CC.iWhite);

        placeholders.put("italicblack", CC.iBlack);
        placeholders.put("iblack", CC.iBlack);

        placeholders.put("italicdarkblue", CC.idBlue);
        placeholders.put("idarkblue", CC.idBlue);
        placeholders.put("idblue", CC.idBlue);

        placeholders.put("italicdarkaqua", CC.idAqua);
        placeholders.put("idarkaqua", CC.idAqua);
        placeholders.put("idaqua", CC.idAqua);

        placeholders.put("italicdarkgray", CC.idGray);
        placeholders.put("idarkgray", CC.idGray);
        placeholders.put("idgray", CC.idGray);
        placeholders.put("italicdarkgrey", CC.idGray);
        placeholders.put("idarkgrey", CC.idGray);
        placeholders.put("idgrey", CC.idGray);

        placeholders.put("italicdarkgreen", CC.idGreen);
        placeholders.put("idarkgreen", CC.idGreen);
        placeholders.put("idgreen", CC.idGreen);

        placeholders.put("italicdarkpurple", CC.idPurple);
        placeholders.put("idarkpurple", CC.idPurple);
        placeholders.put("idpurple", CC.idPurple);

        placeholders.put("italicdarkred", CC.idRed);
        placeholders.put("idarkred", CC.idRed);
        placeholders.put("idred", CC.idRed);

        placeholders.put("italiclightpurple", CC.ilPurple);
        placeholders.put("ilightpurple", CC.ilPurple);
        placeholders.put("ilpurple", CC.ilPurple);


        // Symbols
        placeholders.put("peace", CC.peace);

        placeholders.put("flower", CC.flower);

        placeholders.put("plane", CC.plane);

        placeholders.put("sixtyNine", CC.sixyNine);
        placeholders.put("69", CC.sixyNine);

        placeholders.put("death", CC.death);
        placeholders.put("skull", CC.death);

        placeholders.put("yinyang", CC.yinYan);
        placeholders.put("yinyan", CC.yinYan);

        placeholders.put("heart", CC.heart);

        placeholders.put("peacehand", CC.peaceHand);

        placeholders.put("thickcross", CC.thickCross);

        placeholders.put("nuke", CC.nuke);
        placeholders.put("nuclear", CC.nuke);

        placeholders.put("biohazard", CC.biohazard);

        placeholders.put("caduceus", CC.medical);
        placeholders.put("medical", CC.medical);

        placeholders.put("hollowheart", CC.hollowHeart);

        placeholders.put("thinitaliccross", CC.thinItalicCross);

        placeholders.put("italiccross", CC.ItalicCross);

        placeholders.put("star", CC.star);

        placeholders.put("hollowstar", CC.hollowStar);

        placeholders.put("checkerstar", CC.checkerStar);

        placeholders.put("leftmoon", CC.leftMoon);

        placeholders.put("rightmoon", CC.rightMoon);

        placeholders.put("sun", CC.sun);

        placeholders.put("boldsun", CC.boldSun);

        placeholders.put("snowman", CC.snowman);

        placeholders.put("musicnote", CC.singleMusicNote);
        placeholders.put("singlemusicnote", CC.singleMusicNote);

        placeholders.put("doublemusicnote", CC.doubleMusicNote);

        placeholders.put("phone", CC.phone);

        placeholders.put("mail", CC.mail);

        placeholders.put("female", CC.female);

        placeholders.put("male", CC.male);

        placeholders.put("uptriangle", CC.upTrigle);
        placeholders.put("utriangle", CC.upTrigle);

        placeholders.put("downtriangle", CC.downTrigle);
        placeholders.put("dtriangle", CC.downTrigle);

        placeholders.put("lefttriangle", CC.leftTrigle);
        placeholders.put("ltriangle", CC.leftTrigle);

        placeholders.put("righttriangle", CC.rightTrigle);
        placeholders.put("rtriangle", CC.rightTrigle);

        placeholders.put("italictick", CC.italicTick);

        placeholders.put("thinitalictick", CC.thinItalicTick);

        placeholders.put("smiley1", CC.smiley1);

        placeholders.put("smiley2", CC.smiley2);

        placeholders.put("smiley3", CC.smiley3);

        placeholders.put("smiley4", CC.smiley4);

        placeholders.put("smiley5", CC.smiley5);

        placeholders.put("smiley6", CC.smiley6);

        placeholders.put("oneincircle", CC.oneInCircle);
        placeholders.put("circle1", CC.oneInCircle);

        placeholders.put("twoincircle", CC.twoInCircle);
        placeholders.put("circle2", CC.twoInCircle);

        placeholders.put("threeincircle", CC.threeInCircle);
        placeholders.put("circle3", CC.threeInCircle);

        placeholders.put("fourincircle", CC.fourInCircle);
        placeholders.put("circle4", CC.fourInCircle);

        placeholders.put("fiveincircle", CC.fiveInCircle);
        placeholders.put("circle5", CC.fiveInCircle);

        placeholders.put("sixincircle", CC.sixInCircle);
        placeholders.put("circle6", CC.sixInCircle);

        placeholders.put("sevenincircle", CC.sevenInCircle);
        placeholders.put("circle7", CC.sevenInCircle);

        placeholders.put("eightincircle", CC.eightInCircle);
        placeholders.put("circle8", CC.eightInCircle);

        placeholders.put("nineincircle", CC.nineInCircle);
        placeholders.put("circle9", CC.nineInCircle);

        placeholders.put("zeroincircle", CC.zeroInCircle);
        placeholders.put("circle0", CC.zeroInCircle);

        placeholders.put("arrow", CC.arrow);

        placeholders.put("boldarrow", CC.bArrow);
        placeholders.put("barrow", CC.bArrow);

        placeholders.put("checkerarrow", CC.checkerArrow);

        placeholders.put("thickarrow", CC.thickArrow);

        placeholders.put("verylightshadedblock", CC.veryLightShadedBlock);
        placeholders.put("vlshadedblock", CC.veryLightShadedBlock);

        placeholders.put("lightshadedblock", CC.lightShadedBlock);
        placeholders.put("lshadedblock", CC.lightShadedBlock);

        placeholders.put("shadedblock", CC.shadedBlock);

        placeholders.put("fullyshadedblock", CC.fullyShadedBlock);
        placeholders.put("fshadedblock", CC.fullyShadedBlock);

        placeholders.put("pipeup", CC.pipeUp);
        placeholders.put("pipevertical", CC.pipeUp);

        placeholders.put("pipeacross", CC.pipeAcross);
        placeholders.put("pipehorizontal", CC.pipeAcross);

        placeholders.put("pipeupmidleftsplit", CC.pipeUpMidLeftSplit);

        placeholders.put("pipeupmidrightsplit", CC.pipeUpMidRightSplit);

        placeholders.put("pipetopright", CC.pipeTopRight);

        placeholders.put("pipebottomright", CC.pipeBottomRight);

        placeholders.put("pipetopleft", CC.pipeTopLeft);

        placeholders.put("pipebottomleft", CC.pipeBottomLeft);

        placeholders.put("doublerightarrow", "»");

        placeholders.put("doubleleftarrow", "«");
    }

    /**
     * Replaces placeholders with colour codes and symbols (i.e [pipeacross] would become ═)
     * @param input Input string
     * @return Input string with placeholders replaced
     */
    public String replaceSymbols(@NotNull String input) {
        String str = input;
        for (String key : placeholders.keySet()) {
            str = str.replace("[" + key + "]", placeholders.get(key));
        }
        return str;
    }
}
