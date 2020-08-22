package me.djtheredstoner.wideputin.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class WidePutinConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            description = "Turn on or off the entire mod.",
            category = "Wide Putin",
            subcategory = "General"
    )
    public boolean enabled = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Widen",
            description = "Make things wider.",
            category = "Wide Putin",
            subcategory = "General"
    )
    public boolean widen = true;

    @Property(
            type = PropertyType.SLIDER,
            name = "Wideness",
            description = "Adjust how wide you things to be.",
            min = 100,
            max = 300,
            category = "Wide Putin",
            subcategory = "General"
    )
    public int wideness = 300;

    @Property(
            type = PropertyType.SWITCH,
            name = "Putin Skin",
            description = "Change your skin to a putin skin",
            category = "Wide Putin",
            subcategory = "General"
    )
    public boolean skin = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Everyone Putin Skin",
            description = "Change other players skin to a putin skin",
            category = "Wide Putin",
            subcategory = "General"
    )
    public boolean skinOthers = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Music",
            description = "Play the wide putin music.",
            category = "Wide Putin",
            subcategory = "General"
    )
    public boolean music = true;

    public WidePutinConfig() {
        super(new File("./config/wideputin.toml"));
        initialize();
    }
}
