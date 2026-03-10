package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.rosemods.windswept.core.registry.WindsweptSounds.*;

public class WindsweptSoundProvider extends SoundDefinitionsProvider {
    public WindsweptSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Windswept.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.add(MUSIC_DISC_RAIN.get(), definition().with(sound(Windswept.location("records/rain")).stream()));
        this.add(MUSIC_DISC_SNOW.get(), definition().with(sound(Windswept.location("records/snow")).stream()));
        this.add(MUSIC_DISC_BUMBLEBEE.get(), definition().with(sound(Windswept.location("records/bumblebee")).stream()));
        this.add(PINECONE_NOTE.get(), definition().with(sound(Windswept.location("pinecone_note"))));

        this.add(CHILLED_DEATH.get(), definition().with(
                sound(Windswept.location("entity/chilled/death/death1")),
                sound(Windswept.location("entity/chilled/death/death2")),
                sound(Windswept.location("entity/chilled/death/death3")),
                sound(Windswept.location("entity/chilled/death/death4"))
        ).subtitle("subtitles.entity.chilled.death"));
    }
}