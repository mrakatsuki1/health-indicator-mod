package com.healthindicator.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Unique
    private Entity healthIndicator$currentEntity = null;

    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"))
    private void healthIndicator$captureEntity(T entity,
                                                Text text,
                                                MatrixStack matrices,
                                                VertexConsumerProvider vertexConsumers,
                                                int light,
                                                float tickDelta,
                                                CallbackInfo ci) {
        this.healthIndicator$currentEntity = entity;
    }

    @Redirect(
        method = "renderLabelIfPresent",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/font/TextRenderer;draw(" +
                     "Lnet/minecraft/text/Text;" +
                     "FF" +
                     "IZ" +
                     "Lorg/joml/Matrix4f;" +
                     "Lnet/minecraft/client/render/VertexConsumerProvider;" +
                     "Lnet/minecraft/client/font/TextRenderer$TextLayerType;" +
                     "II)I"
        )
    )
    private int healthIndicator$colorizeNametag(TextRenderer textRenderer,
                                                 Text text,
                                                 float x,
                                                 float y,
                                                 int color,
                                                 boolean shadow,
                                                 Matrix4f matrix,
                                                 VertexConsumerProvider vertexConsumers,
                                                 TextRenderer.TextLayerType layerType,
                                                 int backgroundColor,
                                                 int light) {

        Entity entity = this.healthIndicator$currentEntity;

        if (entity instanceof LivingEntity living) {
            float maxHealth = living.getMaxHealth();
            float currentHealth = living.getHealth();
            float percent = (maxHealth > 0f) ? (currentHealth / maxHealth) * 100f : 100f;

            int healthColor;
            if (percent > 75f) {
                healthColor = 0x55FF55; // Green
            } else if (percent > 50f) {
                healthColor = 0xFFFF55; // Yellow
            } else if (percent > 25f) {
                healthColor = 0xFFAA00; // Orange
            } else {
                healthColor = 0xFF5555; // Red
            }

            MutableText coloredText = Text.literal(text.getString())
                    .setStyle(Style.EMPTY.withColor(healthColor));

            return textRenderer.draw(coloredText, x, y, healthColor, shadow,
                    matrix, vertexConsumers, layerType, backgroundColor, light);
        }

        return textRenderer.draw(text, x, y, color, shadow, matrix,
                vertexConsumers, layerType, backgroundColor, light);
    }

    @Inject(method = "renderLabelIfPresent", at = @At("RETURN"))
    private void healthIndicator$clearEntity(T entity,
                                              Text text,
                                              MatrixStack matrices,
                                              VertexConsumerProvider vertexConsumers,
                                              int light,
                                              float tickDelta,
                                              CallbackInfo ci) {
        this.healthIndicator$currentEntity = null;
    }
}
