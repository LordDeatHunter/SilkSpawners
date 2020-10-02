package wraith.silkspawners.mixin;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wraith.silkspawners.SpawnerRename;

import java.util.List;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Shadow
    @Final
    private List<ScreenHandlerListener> listeners;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(CallbackInfo ci) {
        listeners.add(SpawnerRename.LISTENER);
    }

}